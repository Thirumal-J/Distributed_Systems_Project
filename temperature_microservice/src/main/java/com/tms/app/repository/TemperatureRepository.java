package com.tms.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tms.app.models.TemperatureModel;

@Repository
public interface TemperatureRepository extends CrudRepository<TemperatureModel, Long> {
	
	@Query(value = "SELECT * FROM temperaturetable t where t.created_time > ?1 AND p.created_time <=?2",nativeQuery = true)
	List<TemperatureModel> findGraphData(Date fromdate, Date todate);
	
	@Query(value = "SELECT * FROM temperaturetable t where t.created_time > current_timestamp - interval '7 day' order by created_time ASC",nativeQuery = true)
	List<TemperatureModel> findLastWeekData();
	
	@Query(value = "SELECT * FROM temperaturetable t where t.created_time > current_timestamp - interval '1 day' order by created_time ASC",nativeQuery = true)
	List<TemperatureModel> findLast24HoursData();
}