select ca,cb from tab1 union all select ca,cb from tab2
select ca,cb,count(1) from tab where id in ('102','103') and dt='$dt' group by ca,cb
select name from (select name from zpc a full outer join def b on a.id = b.id) x
select aa,bb from tab1 t1 inner join tab2 t2 
select real_abid,parameters from dwd_bobo.dwd_bobo_fast lateral view EXPLODE(array_abid) abid_array as real_abid where dt='$dt' 
select split(',',str) as arr from tmp.test
select * from zpc1
Select name,ip from zpc2 bieming where age > 10 and area in (select area from city)
Select d.name,d.ip from (select * from zpc3 where age > 10 and area in (select area from city)) d
SELECT id, value FROM (SELECT id, value FROM p1 UNION ALL  SELECT 4 AS id, 5 AS value FROM p1 limit 1) u
select dd from(select id+1 dd from zpc) d
select dd+1 from(select id+1 dd from zpc) d
select * from tablename where unix_timestamp(cz_time) > unix_timestamp('2050-12-31 15:32:28')
select statis_date,time_interval,gds_cd,gds_nm,sale_cnt,discount_amt,discount_rate,price,etl_time,pay_amt from o2ostore.tdm_gds_monitor_rt where time_interval = from_unixtime(unix_timestamp(concat(regexp_replace(from_unixtime(unix_timestamp('201506181700', 'yyyyMMddHHmm')+ 84600 ,  'yyyy-MM-dd HH:mm'),'-| |:',''),'00'),'yyyyMMddHHmmss'),'yyyy-MM-dd HH:mm:ss')
SELECT a.* FROM a JOIN b ON (a.id = b.id AND a.department = b.department)
select day_login.uid from login day_login left outer join (select uid from regusers where dt='20130101') day_regusers on day_login.uid=day_regusers.uid where day_login.dt='20130101' and day_regusers.uid is null
select bb from (select zpc.aa, def.bb from dbx.zpc left outer join dby.def) d limit 3
SELECT `etl_tx_dt` FROM `sda`.`sda10_app_log` WHERE (`etl_tx_dt`=20180614) OR (`etl_tx_dt`=20180613) OR (`etl_tx_dt`=20180612) OR (`etl_tx_dt`=20180611) OR (`etl_tx_dt`=20180610) OR (`etl_tx_dt`=20180609) OR (`etl_tx_dt`=20180608) OR (`etl_tx_dt`=20180607) OR (`etl_tx_dt`=20180606) OR (`etl_tx_dt`=20180605) LIMIT 102