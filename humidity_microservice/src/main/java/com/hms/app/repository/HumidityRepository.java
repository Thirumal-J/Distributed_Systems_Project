package com.hms.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hms.app.models.HumidityModel;

@Repository
public interface HumidityRepository extends CrudRepository<HumidityModel, Long> {
	
	@Query(value = "SELECT * FROM humiditytable h where h.created_time > ?1 AND p.created_time <=?2",nativeQuery = true)
	List<HumidityModel> findGraphData(Date fromdate, Date todate);
	
	@Query(value = "SELECT * FROM humiditytable h where h.created_time > current_timestamp - interval '7 day' order by created_time ASC",nativeQuery = true)
	List<HumidityModel> findLastWeekData();
	
	@Query(value = "SELECT * FROM humiditytable h where h.created_time > current_timestamp - interval '1 day' order by created_time ASC",nativeQuery = true)
	List<HumidityModel> findLast24HoursData();
}