package com.sid.miniurl.web.controller;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.sid.miniurl.db.entity.URLDetails;
import com.sid.miniurl.service.url.URLShortServiceProvider;
import com.sid.miniurl.web.chart.ChartProvider;

@RestController
@EnableWebMvc
@RequestMapping("/")
public class URLController {
	/** The logger for this class. */
	private static final Logger LOG = LogManager.getLogger(URLController.class);

	@RequestMapping(method = RequestMethod.POST)
	public RedirectView createShortURL(@RequestParam("url-text") String longURL, RedirectAttributes attributes) {
		String outcome = "preview";
		try {
			String shortURL = URLShortServiceProvider.getURLShortService().makeShort(new URL(longURL));
			LOG.debug("The short url is " + shortURL);
			attributes.addAttribute("shortURL", shortURL);
			RedirectView redirectView = new RedirectView(shortURL + "/" + outcome);
			redirectView.setExposeModelAttributes(false);
			return redirectView;
		} catch (MalformedURLException e) {
			attributes.addFlashAttribute("error", e.getMessage());
			LOG.error(e);
			return new RedirectView("/", true);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("index");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shortURL}/preview")
	public ModelAndView details(@PathVariable("shortURL") String shortURLParam, ModelMap model) {
		LOG.debug("in details page " + shortURLParam);
		model.addAttribute("shortURL", shortURLParam);
		return new ModelAndView("details");
	}

	protected BufferedImage draw(final JFreeChart chart, final int width, final int height) {
		BufferedImage img = null;
		if (chart != null) {
			img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			final Graphics2D g2 = img.createGraphics();
			chart.draw(g2, new Rectangle2D.Double(0, 0, width, height));
			g2.dispose();
		} else {
			img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		}
		return img;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/chart")
	public void getChartJSP(@RequestParam("shortURL") String shortURLParam, HttpServletResponse response) {
		LOG.debug("creating image");
		ChartProvider chartProvider = new ChartProvider();
		addData(chartProvider, shortURLParam);
		final BufferedImage img = draw(chartProvider.getChart(shortURLParam), 500, 500);
		try {
			ChartUtilities.writeBufferedImageAsJPEG(response.getOutputStream(), img);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addData(ChartProvider chartProvider, String shortURLParam) {
		List<URLDetails> details = URLShortServiceProvider.getURLShortService().getDetails(shortURLParam);
		for (URLDetails detail : details) {
			chartProvider.addValue(detail.getCountryCode(), detail.getClickCount());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shortURL}")
	public RedirectView navigate(@PathVariable("shortURL") String urlCode, HttpServletResponse response,
			HttpServletRequest request) {
		LOG.debug("in navigate page " + urlCode);
		try {
			String fullURL = URLShortServiceProvider.getURLShortService().getFullURL(urlCode).toString();
			return new RedirectView(fullURL);
		} catch (MalformedURLException e) {
			LOG.error(e);
		}
		response.setStatus(403);
		return new RedirectView("/", true);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{shortURL}/preview")
	public RedirectView incrementClickCount(@PathVariable("shortURL") String urlCode,
			@RequestParam("countryCode") String countryCode, HttpServletResponse response, HttpServletRequest request) {
		LOG.debug("in add click " + urlCode + " ," + countryCode);
		if (countryCode != null) {
			URLShortServiceProvider.getURLShortService().addDetails(urlCode, countryCode);
		}
		return new RedirectView("/" + urlCode + "/preview", true);
	}
}
