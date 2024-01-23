package miguel.work.ForeignExchangeRatesService.entity;

import java.time.LocalDate;

public class NtdRateOfDay extends DateField {

	private Double twd;

	public NtdRateOfDay() {
		super();
	}

	public NtdRateOfDay(LocalDate date, Double twd) {
		super(date);
		this.twd = twd;
	}

	public Double getTwd() {
		return twd;
	}

	public void setTwd(Double twd) {
		this.twd = twd;
	}

}
