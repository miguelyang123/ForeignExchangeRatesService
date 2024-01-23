package miguel.work.ForeignExchangeRatesService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import miguel.work.ForeignExchangeRatesService.constants.RtnCode;
import miguel.work.ForeignExchangeRatesService.entity.DateField;
import miguel.work.ForeignExchangeRatesService.service.ifs.ExchangeRateService;
import miguel.work.ForeignExchangeRatesService.vo.CodeMsgRes;
import miguel.work.ForeignExchangeRatesService.vo.DateRangeAndCurrencyReq;
import miguel.work.ForeignExchangeRatesService.vo.GetForexDataRes;
import miguel.work.ForeignExchangeRatesService.vo.ResponseHandler;

@RestController
public class ExchangeRatesController {

	@Autowired
	private ExchangeRateService rateService;

	@PostMapping(value = "forex_api/find_by_date_range")
	public ResponseEntity<Object> FindForexDataByDateRange(@RequestBody DateRangeAndCurrencyReq req) {

		try {

			GetForexDataRes<? extends DateField> res = rateService.getForexDataByDateRange(req);
			return ResponseHandler.generateResponse(new CodeMsgRes(res.getCode(), res.getMessage()), res.getDataList(),
					HttpStatus.OK);

		} catch (Exception e) {

			return ResponseHandler.generateResponse(new CodeMsgRes(RtnCode.ERROR.getCode(), e.getMessage()), null,
					HttpStatus.MULTI_STATUS);

		}
	}

}
