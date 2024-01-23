package miguel.work.ForeignExchangeRatesService.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import miguel.work.ForeignExchangeRatesService.constants.RtnCode;
import miguel.work.ForeignExchangeRatesService.entity.NtdRateOfDay;
import miguel.work.ForeignExchangeRatesService.entity.RateOfDay;
import miguel.work.ForeignExchangeRatesService.entity.UsdRateOfDay;
import miguel.work.ForeignExchangeRatesService.repository.DailyExchangeRateDao;
import miguel.work.ForeignExchangeRatesService.service.ifs.ExchangeRateService;
import miguel.work.ForeignExchangeRatesService.vo.CodeMsgRes;
import miguel.work.ForeignExchangeRatesService.vo.DateRangeAndCurrencyReq;
import miguel.work.ForeignExchangeRatesService.vo.ExchangeRateRes;
import miguel.work.ForeignExchangeRatesService.vo.GetForexDataRes;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

	private final RestTemplate restTemplate = new RestTemplate();
	private ObjectMapper mapper = new ObjectMapper();
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.BASIC_ISO_DATE;

	@Autowired
	private DailyExchangeRateDao dailyExchangeRateDao;

	@Override
	public List<ExchangeRateRes> GetApiData() throws JsonMappingException, JsonProcessingException {
		// Set header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		// Get API data
		ResponseEntity<String> res = restTemplate.exchange("https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates",
				HttpMethod.GET, entity, String.class);
		String resJson = res.getBody();

		System.out.println(res.getHeaders().toString());

		// JSON to list
		List<ExchangeRateRes> exchangeRateDailyList = mapper.readValue(resJson,
				new TypeReference<List<ExchangeRateRes>>() {
				});

		return exchangeRateDailyList;
	}

	@Override
	public CodeMsgRes setDataToBd() {

		try {
			List<ExchangeRateRes> listRes = GetApiData();
			List<RateOfDay> rateOfDayList = new ArrayList<>();

			listRes.forEach(d -> {
				RateOfDay newRateOfDay = new RateOfDay();

				// string to LocalDate
				LocalDate date = LocalDate.parse(d.getDate(), dateTimeFormatter);
				// set Data
				newRateOfDay.setDate(date);
				newRateOfDay.setTwdToUsdRate(d.getUsdNtd());

				rateOfDayList.add(newRateOfDay);
			});

			// use JPA JPA to save all list to Database
			dailyExchangeRateDao.saveAll(rateOfDayList);

			return new CodeMsgRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());

		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return new CodeMsgRes(RtnCode.ERROR.getCode(), e.getMessage());
		}

	}

	@Override
	public GetForexDataRes<?> getForexDataByDateRange(DateRangeAndCurrencyReq req) {
		String currency = req.getCurrency();
		String startDate = req.getStartDate();
		String endDate = req.getEndDate();

		// handle input null error
		if (!StringUtils.hasText(startDate) || !StringUtils.hasText(endDate)) {
			return new GetForexDataRes<>(RtnCode.INPUT_DATE_NULL.getCode(), RtnCode.INPUT_DATE_NULL.getMessage());
		}
		if (!StringUtils.hasText(currency)) {
			return new GetForexDataRes<>(RtnCode.INPUT_CURRENCY_NULL.getCode(), RtnCode.INPUT_CURRENCY_NULL.getMessage());
		}
		
		// date change to LocalDate and check format
		LocalDate startLocalDate = checkAndToLocalDate(startDate);
		LocalDate endLocalDate = checkAndToLocalDate(endDate);
		if(startLocalDate == null || endLocalDate == null) {
			return new GetForexDataRes<>(RtnCode.INPUT_DATE_ERROR.getCode(), RtnCode.INPUT_DATE_ERROR.getMessage());
		}
		
		// handle date range error
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		LocalDate oneYearAgo = today.minusYears(1);
		if(startLocalDate.isAfter(endLocalDate) || startLocalDate.isBefore(oneYearAgo) || endLocalDate.isAfter(yesterday)){
			return new GetForexDataRes<>(RtnCode.DATE_RANGE_MISMATCH.getCode(), RtnCode.DATE_RANGE_MISMATCH.getMessage());
		}
		
		//get Rate data from DB
		List<RateOfDay> res = dailyExchangeRateDao.findDataByDateRange(startLocalDate, endLocalDate);

		
		// handle currency
		if (currency.equalsIgnoreCase("usd")) {
			List<UsdRateOfDay> usdRateList = new ArrayList<>();
			res.forEach( u -> {
				usdRateList.add(new UsdRateOfDay(u.getDate(), u.getTwdToUsdRate()));
			});
			
			return new GetForexDataRes<UsdRateOfDay>(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), usdRateList);
		}
		else if (currency.equalsIgnoreCase("ntd")) {
			List<NtdRateOfDay> ntdRateList = new ArrayList<>();
			res.forEach( u -> {
				// rounding to two decimal places
				double ntdRate =  Math.round(1 / u.getTwdToUsdRate() * 1000.0) / 1000.0;
				ntdRateList.add(new NtdRateOfDay(u.getDate(), ntdRate));
			});
			
			return new GetForexDataRes<NtdRateOfDay>(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage(), ntdRateList);
		}
		// currency input error
		else {
			return new GetForexDataRes<>(RtnCode.INPUT_CURRENCY_ERROR.getCode(), RtnCode.INPUT_CURRENCY_ERROR.getMessage());
		}

	}
	
	private LocalDate checkAndToLocalDate(String inputDate) {
	    try {
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    	return LocalDate.parse(inputDate, formatter);

	    } catch (DateTimeParseException dtpe) {
	        return null;
	    }
	}

}
