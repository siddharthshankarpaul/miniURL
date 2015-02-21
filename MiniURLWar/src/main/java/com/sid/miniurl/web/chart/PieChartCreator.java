package com.sid.miniurl.web.chart;

import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

class PieChartCreator {
	private DefaultPieDataset dataSet = new DefaultPieDataset();
	private JFreeChart chart;

	void addValueToDataSet(String key, int value) {
		dataSet.setValue(key, value);
	}

	public void createChart(String title) {
		chart = ChartFactory.createPieChart3D(title, dataSet, true, true, false);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setNoDataMessage("No data found");
		PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0}: {1} ({2})", new DecimalFormat("0"),
				new DecimalFormat("0%"));
		plot.setLabelGenerator(gen);
	}

	public JFreeChart getChart() {
		if (chart == null) {
			createChart("");
		}
		return chart;
	}
}
