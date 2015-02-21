package com.sid.miniurl.web.chart;

import org.jfree.chart.JFreeChart;

public class ChartProvider {
	private PieChartCreator pieChart = new PieChartCreator();

	public JFreeChart getChart(String shortURL) {
		pieChart.createChart("Details of " + shortURL);
		return pieChart.getChart();
	}

	public void addValue(String key, int value) {
		pieChart.addValueToDataSet(key, value);
	}
}
