package miguel.work.ForeignExchangeRatesService.constants;

public enum RtnCode {

	SUCCESSFUL("0000", "成功"),
	DATE_RANGE_MISMATCH("E001", "日期區間不符"),
	INPUT_DATE_NULL("E001","輸入日期為空值"),
	INPUT_DATE_ERROR("E001","輸入日期格式錯誤"),
	INPUT_CURRENCY_NULL("E001","輸入幣別為空值"),
	INPUT_CURRENCY_ERROR("E001","輸入幣別為錯誤，支援USD/NTD"),
	ERROR("Z001","錯誤");
	
	private String code;

	private String message;

	private RtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
