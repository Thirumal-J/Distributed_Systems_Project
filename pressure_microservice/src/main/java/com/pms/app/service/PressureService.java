package com.pms.app.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pms.app.PressureMicroserviceApplication;
import com.pms.app.models.PressureModel;

@Service
public class PressureService {
	private static final Logger logger = LoggerFactory.getLogger(PressureMicroserviceApplication.class);

	public HashMap<Integer, PressureModel> findGraphData(List<PressureModel> pressureModelList) {
		logger.info("Entering Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
		HashMap<Integer, PressureModel> pressureData = new HashMap<Integer, PressureModel>();
		int i =0;
		for(PressureModel obj:pressureModelList) {
			pressureData.put(i,obj);
			i++;
		}
		logger.info("Exiting Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
		return pressureData;
	}
}
