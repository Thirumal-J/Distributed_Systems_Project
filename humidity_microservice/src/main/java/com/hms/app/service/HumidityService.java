package com.hms.app.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hms.app.HumidityMicroserviceApplication;
import com.hms.app.models.HumidityModel;

@Service
public class HumidityService {

	private static final Logger logger = LoggerFactory.getLogger(HumidityMicroserviceApplication.class);

	public HashMap<Integer, HumidityModel> findGraphData(List<HumidityModel> humidityModelList) {
		logger.info("Entering Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
		HashMap<Integer, HumidityModel> humidityData = new HashMap<Integer, HumidityModel>();
		int i =0;
		for(HumidityModel obj:humidityModelList) {
			humidityData.put(i,obj);
			i++;
		}
		logger.info("Exiting Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
		return humidityData;
	}
}

	
