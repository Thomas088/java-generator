SET sql_mode=(SELECT CONCAT(@@sql_mode,',PIPES_AS_CONCAT'));

DELIMITER //
CREATE OR REPLACE PROCEDURE search_datas(current_column VARCHAR(255), limit_result INT)
 BEGIN
  EXECUTE IMMEDIATE ' SELECT ' || current_column || '
      FROM (
          SELECT FLOOR(mm.min_id + (mm.max_id - mm.min_id + 1) * RAND()) AS id
              FROM (
                  SELECT MIN(id_datas) AS min_id,
                         MAX(id_datas) AS max_id
                      FROM fake_datas 
                   ) AS mm
              JOIN (SELECT id_datas FROM FAKE_DATAS LIMIT '|| (limit_result + 1) ||' ) z
           ) AS init
      JOIN  fake_datas AS r ON r.id_datas  = init.id LIMIT '|| limit_result ||';';  
 END;
//
DELIMITER ;

DELIMITER //
CREATE OR REPLACE PROCEDURE generate_current_date()
 BEGIN
  EXECUTE IMMEDIATE 'SELECT CURRENT_DATE;';
 END;
//

DELIMITER //
CREATE OR REPLACE PROCEDURE generate_current_timestamp()
 BEGIN
  EXECUTE IMMEDIATE 'SELECT CURRENT_TIMESTAMP AS Now;';
 END;
//
DELIMITER ;

DELIMITER //
CREATE OR REPLACE PROCEDURE generate_random_number(min_num INT, max_num INT)
 BEGIN
  EXECUTE IMMEDIATE 'SELECT FLOOR(RAND() * (' || max_num || ' - ' || min_num || ' + 1)) + ' || min_num || ' ;';
 END;
//
DELIMITER ;

DELIMITER //
CREATE OR REPLACE PROCEDURE calculate_diff_number_of_days(date_start VARCHAR(255), date_end VARCHAR(255))
 BEGIN 
	 
	 DECLARE quote_escape VARCHAR(10);
	 DECLARE space_char VARCHAR(10);
	 DECLARE end_day_at VARCHAR(255);
	
	 SET quote_escape = '\'';
	 SET end_day_at = '23:59:59';
	 SET space_char = ' ';
	
	    IF date_start < date_end THEN 
      	EXECUTE IMMEDIATE 'SELECT DATEDIFF(' || quote_escape || date_end || space_char || end_day_at || space_char || quote_escape || ',' || quote_escape || date_start || quote_escape || ') AS NUMBER_OF_DAYS;';
   		ELSE
      	EXECUTE IMMEDIATE 'SELECT DATEDIFF(' || quote_escape || date_start || space_char || end_day_at || space_char || quote_escape || ',' || quote_escape || date_end || quote_escape || ') AS NUMBER_OF_DAYS;';
      END IF;
 END;
//
DELIMITER ;

DELIMITER //
CREATE OR REPLACE PROCEDURE generate_random_date(date_start VARCHAR(255), date_end VARCHAR(255)) -- EN COURS (DEBUG)
 BEGIN 
	
	 DECLARE date_format_template VARCHAR(255);
	 DECLARE start_day_at VARCHAR(255);
	 DECLARE end_day_at VARCHAR(255);
	 DECLARE timestamp_start VARCHAR(255);
	 DECLARE timestamp_end VARCHAR(255);
	 DECLARE quote_escape VARCHAR(10);
	 DECLARE space_char VARCHAR(10);
	
	 SET date_format_template = '%Y-%m-%d';
	 SET start_day_at = '16:00:00';
	 SET end_day_at = '23:00:00';
	 SET timestamp_start = date_start || ' ' || start_day_at; 
	 SET timestamp_end = date_end || ' ' || end_day_at;
	 SET quote_escape = '\''; -- Ajouter et echaper les guillemets (1999-01-01 => '1999-01-01' dans la query).
	 SET space_char = ' ';
	 
	 -- OK BUT DATE RANGE NOT HANDLED
	EXECUTE IMMEDIATE 'SELECT date_format(from_unixtime(rand() * ((unix_timestamp('|| quote_escape || timestamp_start || quote_escape ||') - unix_timestamp('|| quote_escape || timestamp_end || quote_escape ||')) 
														+ unix_timestamp('|| quote_escape ||timestamp_end|| quote_escape ||'))), '|| space_char || quote_escape || date_format_template || ' ' || quote_escape || space_char ||');';	 
 END;
//
DELIMITER ;