package miguel.work.ForeignExchangeRatesService.vo;

public class DateRangeAndCurrencyReq {

	private String startDate;

	private String endDate;

	private String currency;

	public DateRangeAndCurrencyReq() {
		super();
	}

	public DateRangeAndCurrencyReq(String startDate, String endDate, String currency) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.currency = currency;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
