package miguel.work.ForeignExchangeRatesService.vo;

import java.util.List;

import miguel.work.ForeignExchangeRatesService.entity.DateField;

public class GetForexDataRes<T extends DateField> extends CodeMsgRes {

	private List<T> dataList;

	public GetForexDataRes() {
		super();
	}

	public GetForexDataRes(String code, String message) {
		super(code, message);
	}

	public GetForexDataRes(String code, String message, List<T> dataList) {
		super(code, message);
		this.dataList = dataList;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

}
