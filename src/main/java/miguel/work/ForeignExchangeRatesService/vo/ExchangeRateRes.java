package miguel.work.ForeignExchangeRatesService.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExchangeRateRes {

	@JsonProperty("Date")
	private String date;

	// USD/NTD
	@JsonProperty("USD/NTD")
	private Double usdNtd;

	// RMB/NTD
	@JsonProperty("RMB/NTD")
	private Double rmbNtd;

	// EUR/USD
	@JsonProperty("EUR/USD")
	private Double eurUsd;

	// USD/JPY
	@JsonProperty("USD/JPY")
	private Double usdJpy;

	// GBP/USD
	@JsonProperty("GBP/USD")
	private Double gbpUsd;

	// AUD/USD
	@JsonProperty("AUD/USD")
	private Double audUsd;

	// USD/HKD
	@JsonProperty("USD/HKD")
	private Double usdHkd;

	// USD/RMB
	@JsonProperty("USD/RMB")
	private Double usdRmb;

	// USD/ZAR
	@JsonProperty("USD/ZAR")
	private Double usdZar;

	// NZD/USD
	@JsonProperty("NZD/USD")
	private Double nzdUsd;

	public ExchangeRateRes() {
		super();
	}

	public ExchangeRateRes(String date, Double usdNtd, Double rmbNtd, Double eurUsd, Double usdJpy, Double gbpUsd,
			Double audUsd, Double usdHkd, Double usdRmb, Double usdZar, Double nzdUsd) {
		super();
		this.date = date;
		this.usdNtd = usdNtd;
		this.rmbNtd = rmbNtd;
		this.eurUsd = eurUsd;
		this.usdJpy = usdJpy;
		this.gbpUsd = gbpUsd;
		this.audUsd = audUsd;
		this.usdHkd = usdHkd;
		this.usdRmb = usdRmb;
		this.usdZar = usdZar;
		this.nzdUsd = nzdUsd;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getUsdNtd() {
		return usdNtd;
	}

	public void setUsdNtd(Double usdNtd) {
		this.usdNtd = usdNtd;
	}

	public Double getRmbNtd() {
		return rmbNtd;
	}

	public void setRmbNtd(Double rmbNtd) {
		this.rmbNtd = rmbNtd;
	}

	public Double getEurUsd() {
		return eurUsd;
	}

	public void setEurUsd(Double eurUsd) {
		this.eurUsd = eurUsd;
	}

	public Double getUsdJpy() {
		return usdJpy;
	}

	public void setUsdJpy(Double usdJpy) {
		this.usdJpy = usdJpy;
	}

	public Double getGbpUsd() {
		return gbpUsd;
	}

	public void setGbpUsd(Double gbpUsd) {
		this.gbpUsd = gbpUsd;
	}

	public Double getAudUsd() {
		return audUsd;
	}

	public void setAudUsd(Double audUsd) {
		this.audUsd = audUsd;
	}

	public Double getUsdHkd() {
		return usdHkd;
	}

	public void setUsdHkd(Double usdHkd) {
		this.usdHkd = usdHkd;
	}

	public Double getUsdRmb() {
		return usdRmb;
	}

	public void setUsdRmb(Double usdRmb) {
		this.usdRmb = usdRmb;
	}

	public Double getUsdZar() {
		return usdZar;
	}

	public void setUsdZar(Double usdZar) {
		this.usdZar = usdZar;
	}

	public Double getNzdUsd() {
		return nzdUsd;
	}

	public void setNzdUsd(Double nzdUsd) {
		this.nzdUsd = nzdUsd;
	}

}
