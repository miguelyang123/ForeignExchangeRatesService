package miguel.work.ForeignExchangeRatesService.service.ifs;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import miguel.work.ForeignExchangeRatesService.vo.CodeMsgRes;
import miguel.work.ForeignExchangeRatesService.vo.DateRangeAndCurrencyReq;
import miguel.work.ForeignExchangeRatesService.vo.ExchangeRateRes;
import miguel.work.ForeignExchangeRatesService.vo.GetForexDataRes;

public interface ExchangeRateService {

	// get exchange rate Data form API
	public List<ExchangeRateRes> GetApiData() throws JsonMappingException, JsonProcessingException;
	
	// Set Data to DB Service
	public CodeMsgRes setDataToBd();
	
	public GetForexDataRes<?> getForexDataByDateRange(DateRangeAndCurrencyReq req);
	
}
