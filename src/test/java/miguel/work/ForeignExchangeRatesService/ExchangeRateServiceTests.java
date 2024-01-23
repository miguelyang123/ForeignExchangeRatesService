package miguel.work.ForeignExchangeRatesService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import miguel.work.ForeignExchangeRatesService.constants.RtnCode;
import miguel.work.ForeignExchangeRatesService.entity.DateField;
import miguel.work.ForeignExchangeRatesService.service.ifs.ExchangeRateService;
import miguel.work.ForeignExchangeRatesService.vo.CodeMsgRes;
import miguel.work.ForeignExchangeRatesService.vo.DateRangeAndCurrencyReq;
import miguel.work.ForeignExchangeRatesService.vo.ExchangeRateRes;
import miguel.work.ForeignExchangeRatesService.vo.GetForexDataRes;

@SpringBootTest(classes = ForeignExchangeRatesServiceApplication.class)
public class ExchangeRateServiceTests {

	@Autowired
	private ExchangeRateService rateService;

	// 取得成交資料並匯入DB 測試
	@Test
	public void batchToSetDailyRatesDataTest() throws JsonMappingException, JsonProcessingException {

		// GetApiData test
		List<ExchangeRateRes> listRes = rateService.GetApiData();
		Assert.isTrue(listRes.size() != 0, "Fail! api data list is 0 Error!");

		// Set Data to DB Service test
		CodeMsgRes res = rateService.setDataToBd();
		Assert.isTrue(res.getMessage().equals(RtnCode.SUCCESSFUL.getMessage()), "Fail! SetDataToBd Error!");

	}

	// forex API 測試
	@Test
	public void forexAPITest() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate today = LocalDate.now();
		String yesterday = today.minusDays(1).format(formatter);
		String tomorrow = today.plusDays(1).format(formatter);
		String oneWeekAgo = today.minusWeeks(1).format(formatter);
		String twoYearAgo = today.minusYears(2).format(formatter);
		String errStartDate = oneWeekAgo.replace("/", "");
		String errEndDate = yesterday.replace("/", "");

		// null input test
		DateRangeAndCurrencyReq req = new DateRangeAndCurrencyReq(null, null, "usd");
		GetForexDataRes<? extends DateField> res = rateService.getForexDataByDateRange(req);
		Assert.isTrue(res.getMessage().equals(RtnCode.INPUT_DATE_NULL.getMessage()), "Fail! Null date input test Error!");
		req = new DateRangeAndCurrencyReq(oneWeekAgo, yesterday, null);
		res = rateService.getForexDataByDateRange(req);
		Assert.isTrue(res.getMessage().equals(RtnCode.INPUT_CURRENCY_NULL.getMessage()), "Fail! Null currency input test Error!");
		
		// input format test
		req = new DateRangeAndCurrencyReq(errStartDate, yesterday, "usd");
		res = rateService.getForexDataByDateRange(req);
		Assert.isTrue(res.getMessage().equals(RtnCode.INPUT_DATE_ERROR.getMessage()), "Fail! Start date format test Error!");
		req = new DateRangeAndCurrencyReq(oneWeekAgo, errEndDate, "usd");
		res = rateService.getForexDataByDateRange(req);
		Assert.isTrue(res.getMessage().equals(RtnCode.INPUT_DATE_ERROR.getMessage()), "Fail! End date format test Error!");
		
		// date range error test
		req = new DateRangeAndCurrencyReq(yesterday, oneWeekAgo, "usd");
		res = rateService.getForexDataByDateRange(req);
		Assert.isTrue(res.getMessage().equals(RtnCode.DATE_RANGE_MISMATCH.getMessage()), "Fail! start date is After end date test Error!");
		req = new DateRangeAndCurrencyReq(twoYearAgo, oneWeekAgo, "usd");
		res = rateService.getForexDataByDateRange(req);
		Assert.isTrue(res.getMessage().equals(RtnCode.DATE_RANGE_MISMATCH.getMessage()), "Fail! start date is over than one year test Error!");
		req = new DateRangeAndCurrencyReq(oneWeekAgo, tomorrow, "usd");
		res = rateService.getForexDataByDateRange(req);
		Assert.isTrue(res.getMessage().equals(RtnCode.DATE_RANGE_MISMATCH.getMessage()), "Fail! end date is over than yesterday test Error!");
		
		// input currency not correct test
		req = new DateRangeAndCurrencyReq(oneWeekAgo, yesterday, "jpy");
		res = rateService.getForexDataByDateRange(req);
		Assert.isTrue(res.getMessage().equals(RtnCode.INPUT_CURRENCY_ERROR.getMessage()), "Fail! input currency not correct test Error!");
		
		// successful test
		req = new DateRangeAndCurrencyReq(oneWeekAgo, yesterday, "usd");
		res = rateService.getForexDataByDateRange(req);
		Assert.isTrue(res.getMessage().equals(RtnCode.SUCCESSFUL.getMessage()), "Fail! successful usd test Error!");
		req = new DateRangeAndCurrencyReq(oneWeekAgo, yesterday, "ntd");
		res = rateService.getForexDataByDateRange(req);
		Assert.isTrue(res.getMessage().equals(RtnCode.SUCCESSFUL.getMessage()), "Fail! successful ntd test Error!");
		req = new DateRangeAndCurrencyReq(oneWeekAgo, yesterday, "USD");
		res = rateService.getForexDataByDateRange(req);
		Assert.isTrue(res.getMessage().equals(RtnCode.SUCCESSFUL.getMessage()), "Fail! successful upper case usd test Error!");
		req = new DateRangeAndCurrencyReq(oneWeekAgo, yesterday, "NTD");
		res = rateService.getForexDataByDateRange(req);
		Assert.isTrue(res.getMessage().equals(RtnCode.SUCCESSFUL.getMessage()), "Fail! successful upper case ntd test Error!");
		

		
	}

}
