package miguel.work.ForeignExchangeRatesService.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import miguel.work.ForeignExchangeRatesService.service.ifs.ExchangeRateService;
import miguel.work.ForeignExchangeRatesService.vo.CodeMsgRes;

@Component
public class DailyJob {

	@Autowired
	private ExchangeRateService exchangeRateService;

	private String nowDateStr = new SimpleDateFormat().format(new Date());

	// run at 18:00
	@Scheduled(cron = "0 0 18 * * ?")
	public void runDaily() {
		System.out.println("Current time:" + nowDateStr + ", Message: Call insert data to Db Api");
		CodeMsgRes res = exchangeRateService.setDataToBd();
		System.out.println("Code: " + res.getCode() + ", Message: " + res.getMessage());
	}

}
