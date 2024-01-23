package miguel.work.ForeignExchangeRatesService.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "daily_exchange_rate_table")
public class RateOfDay {

	@Id
	@Column(name = "exchange_date")
	private LocalDate date;

	@Column(name = "twd_to_usd_rate")
	private Double twdToUsdRate;

	public RateOfDay() {
		super();
	}

	public RateOfDay(LocalDate date, Double twdToUsdRate) {
		super();
		this.date = date;
		this.twdToUsdRate = twdToUsdRate;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getTwdToUsdRate() {
		return twdToUsdRate;
	}

	public void setTwdToUsdRate(Double twdToUsdRate) {
		this.twdToUsdRate = twdToUsdRate;
	}

}
