package miguel.work.ForeignExchangeRatesService.vo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	public static ResponseEntity<Object> generateResponse(CodeMsgRes codeMsgRes, Object rateOfDayList,
			HttpStatus status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", codeMsgRes);
		// if have data
		if (rateOfDayList != null) {
			map.put("currency", rateOfDayList);
		}

		return new ResponseEntity<Object>(map, status);
	}
}
