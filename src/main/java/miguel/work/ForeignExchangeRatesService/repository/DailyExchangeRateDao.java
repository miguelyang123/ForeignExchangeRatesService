package miguel.work.ForeignExchangeRatesService.repository;



import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import miguel.work.ForeignExchangeRatesService.entity.RateOfDay;

@Repository
public interface DailyExchangeRateDao extends JpaRepository<RateOfDay, LocalDate> {
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO daily_exchange_rate_table (exchange_date, twd_to_usd_rate)" 
			+ " VALUES (?1, ?2)", nativeQuery = true)
	public int insertDailyExchangeRate(String date, Double twdToUsdRate);
	
	@Query(value = "select exchange_date, twd_to_usd_rate from daily_exchange_rate_table "
			+ " where exchange_date >= :startDate and exchange_date <= :endDate ", nativeQuery = true)
	public List<RateOfDay> findDataByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
