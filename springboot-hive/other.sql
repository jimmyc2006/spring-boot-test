insert overwrite table tmp.xxx partition(dt='20180101') select cola,colb from tmp.yyy where cola=10 or cola=20
use gdm;set hive.execution.engine=tez;insert into table dst select a as cc from tab1 union all select b as cc from tab2
insert into table dst select orz from (select a.cola,b.colb from tb1 a inner join tb2 b on a.id = b.id) x
insert into table fin_tab select a.id,b.name,c.addr,d.pwd,f.tel from tb1 a join tb2 b join tb2 c join tb4 d join tmp.tb5 f
create table test as select cola from yyy
create table if not exists tmp.test like tmp.yyy
insert into table tmp.test select dt,real_abid,udid,eventid,parameters from dwd_bobo.dwd_bobo_fast lateral view explode(split(abid,',')) abid_array as real_abid where dt='$dt' 
insert into table tmp.test select real_abid from dwd_bobo.dwd_bobo_fast lateral view explode(split(abid,',')) abid_array as real_abid where dt='$dt' 
create table zpc(id string, name string)
insert overwrite table tmp1 PARTITION (partitionkey='2008-08-15') select * from tmp
FROM (  SELECT p.datekey datekey, p.userid userid, c.clienttype  FROM detail.usersequence_client c JOIN fact.orderpayment p ON p.orderid = c.orderid  JOIN default.user du ON du.userid = p.userid WHERE p.datekey = 20131118 ) base  INSERT OVERWRITE TABLE `test`.`customer_kpi` SELECT base.datekey,  base.clienttype, count(distinct base.userid) buyer_count GROUP BY base.datekey, base.clienttype
truncate table zpc
drop table zpc
alter table old_table_name RENAME TO new_table_name
INSERT OVERWRITE TABLE u_data_new SELECT TRANSFORM (userid, movieid, rating, unixtime) USING 'python weekday_mapper.py' AS (userid, movieid, rating, weekday) FROM u_data
LOAD DATA LOCAL INPATH \"/opt/data/1.txt\" OVERWRITE INTO TABLE table1
CREATE TABLE  table1     (    column1 STRING COMMENT 'comment1',    column2 INT COMMENT 'comment2'        )
ALTER TABLE events RENAME TO 3koobecaf
ALTER TABLE invites ADD COLUMNS (new_col2 INT COMMENT 'a comment')
alter table mp add partition (b='1', c='1')
