CREATE TABLE IF NOT EXISTS `daily_exchange_rate_table` (
  `exchange_date` datetime NOT NULL,
  `twd_to_usd_rate` double NOT NULL,
  PRIMARY KEY (`exchange_date`)
);
