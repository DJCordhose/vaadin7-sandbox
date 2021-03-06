package eu.zeigermann.vaadin.demo.js;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vaadin.annotations.*;
import com.vaadin.ui.AbstractJavaScriptComponent;

@JavaScript({
		"https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js",
		"jquery.flot.js", "flot_connector.js" })
public class Flot extends AbstractJavaScriptComponent {
	public void addSeries(double... points) {
		List<List<Double>> pointList = new ArrayList<List<Double>>();
		for (int i = 0; i < points.length; i++) {
			pointList.add(Arrays.asList(Double.valueOf(i),
					Double.valueOf(points[i])));
		}

		getState().series.add(pointList);
	}

	@Override
	public FlotState getState() {
		return (FlotState) super.getState();
	}
}