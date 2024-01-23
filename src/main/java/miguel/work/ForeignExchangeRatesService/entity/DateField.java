package miguel.work.ForeignExchangeRatesService.entity;

import java.time.LocalDate;

public class DateField {

	private LocalDate date;

	public DateField() {
		super();
	}

	public DateField(LocalDate date) {
		super();
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
