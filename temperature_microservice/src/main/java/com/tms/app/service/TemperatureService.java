package com.tms.app.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tms.app.TemperatureMicroserviceApplication;
import com.tms.app.models.TemperatureModel;

@Service
public class TemperatureService {

	private static final Logger logger = LoggerFactory.getLogger(TemperatureMicroserviceApplication.class);

	public HashMap<Integer, TemperatureModel> findGraphData(List<TemperatureModel> temperatureModelList) {
		logger.info("Entering Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
		HashMap<Integer, TemperatureModel> temperatureData = new HashMap<Integer, TemperatureModel>();
		int i =0;
		for(TemperatureModel obj:temperatureModelList) {
			temperatureData.put(i,obj);
			i++;
		}
		logger.info("Exiting Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
		return temperatureData;
	}
}

	
