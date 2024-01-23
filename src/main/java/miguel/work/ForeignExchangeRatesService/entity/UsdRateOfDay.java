package miguel.work.ForeignExchangeRatesService.entity;

import java.time.LocalDate;

public class UsdRateOfDay extends DateField {

	private Double usd;

	public UsdRateOfDay() {
		super();
	}

	public UsdRateOfDay(LocalDate date, Double usd) {
		super(date);
		this.usd = usd;
	}

	public Double getUsd() {
		return usd;
	}

	public void setUsd(Double usd) {
		this.usd = usd;
	}

}
