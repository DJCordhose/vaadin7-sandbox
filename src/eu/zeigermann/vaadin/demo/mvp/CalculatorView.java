package eu.zeigermann.vaadin.demo.mvp;

interface CalculatorView {
	public void setDisplay(double value);

	interface CalculatorViewListener {
		void buttonClick(char operation);
	}

	public void addListener(CalculatorViewListener listener);
}