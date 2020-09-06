package com.pms.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pms.app.models.PressureModel;

@Repository
public interface PressureRepository extends CrudRepository<PressureModel, Long>  {
	
	@Query(value = "SELECT * FROM pressuretable p where p.created_time > ?1 AND p.created_time <=?2",nativeQuery = true)
	List<PressureModel> findGraphData(Date fromdate, Date todate);
	
	@Query(value = "SELECT * FROM pressuretable p where p.created_time > current_timestamp - interval '7 day' order by created_time ASC",nativeQuery = true)
	List<PressureModel> findLastWeekData();
	
	@Query(value = "SELECT * FROM pressuretable p where p.created_time > current_timestamp - interval '1 day' order by created_time ASC",nativeQuery = true)
	List<PressureModel> findLast24HoursData();
}
