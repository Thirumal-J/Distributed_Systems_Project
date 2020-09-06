package com.hms.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hms.app.HumidityMicroserviceApplication;
import com.hms.app.models.HumidityModel;
import com.hms.app.repository.HumidityRepository;
import com.hms.app.service.HumidityService;


@RestController
@RequestMapping(path = "/humidity_api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HumidityController {

	private static final Logger logger = LoggerFactory.getLogger(HumidityMicroserviceApplication.class);

	@Autowired
	HumidityRepository humidityRepository;

	@Autowired
	HumidityService humidityService;

	@Autowired
	private Environment env;

	@PostMapping(path = "/insertData")
	public HumidityModel insertHumidityData(@Valid @RequestBody JSONObject jsonobject) {
		logger.info("Entering Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
		HumidityModel humidityModel = new HumidityModel();
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setTimeZone(TimeZone.getTimeZone(env.getProperty("timezone")));
			humidityModel = mapper.readValue(jsonobject.toString(), HumidityModel.class);
			humidityModel = humidityRepository.save(humidityModel);
			logger.info("Output Data-->" + humidityModel.toString());
		} catch (Exception ex) {
			logger.info("Exception occurred-->" + ex);
		}
		logger.info("Exiting Method-->" + Thread.currentThread().getStackTrace()[1].getMethodName());
		return humidityModel;

	}

	@GetMapping(path = "/humidityData/last24hours")
	public HashMap<Integer, HumidityModel> getHumidityDataForLast24Hours() {
		logger.info("Entering Method-->" + Thread.currentThread().getStackTrace()[1].getMethodName());
		List<HumidityModel> humidityModelList = new ArrayList<HumidityModel>();
		HashMap<Integer, HumidityModel> humidityData = new HashMap<Integer, HumidityModel>();
		try {
			humidityModelList = (List<HumidityModel>) humidityRepository.findLast24HoursData();
			humidityData = humidityService.findGraphData(humidityModelList);
			logger.info("Output Data-->" + humidityData.toString());

		} catch (Exception ex) {
			logger.info("Exception occurred-->" + ex);
		}
		logger.info("Exiting Method-->" + Thread.currentThread().getStackTrace()[1].getMethodName());
		return humidityData;
	}

	@GetMapping(path = "/humidityData/lastweek")
	public HashMap<Integer, HumidityModel> getHumidityDataForLastWeek() {
		logger.info("Entering Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
		List<HumidityModel> humidityModelList = new ArrayList<HumidityModel>();
		HashMap<Integer, HumidityModel> humidityData = new HashMap<Integer, HumidityModel>();
		try {

			humidityModelList = (List<HumidityModel>) humidityRepository.findLastWeekData();
			humidityData = humidityService.findGraphData(humidityModelList);
			logger.info("Output Data-->" + humidityData.toString());

		} catch (Exception ex) {
			logger.info("Exception occurred-->" + ex);
		}
		logger.info("Exiting Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
		return humidityData;
	}
}