package com.tms.app.controllers;

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
import com.tms.app.TemperatureMicroserviceApplication;
import com.tms.app.models.TemperatureModel;
import com.tms.app.repository.TemperatureRepository;
import com.tms.app.service.TemperatureService;


@RestController
@RequestMapping(path = "/temperature_api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemperatureController {

	private static final Logger logger = LoggerFactory.getLogger(TemperatureMicroserviceApplication.class);

	@Autowired
	TemperatureRepository temperatureRepository;

	@Autowired
	TemperatureService temperatureService;

	@Autowired
	private Environment env;

	@PostMapping(path = "/insertData")
	public TemperatureModel insertTemperatureData(@Valid @RequestBody JSONObject jsonobject) {
		logger.info("Entering Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
		TemperatureModel temperatureModel = new TemperatureModel();
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setTimeZone(TimeZone.getTimeZone(env.getProperty("timezone")));
			temperatureModel = mapper.readValue(jsonobject.toString(), TemperatureModel.class);
			temperatureModel = temperatureRepository.save(temperatureModel);
			logger.info("Output Data-->" + temperatureModel.toString());
		} catch (Exception ex) {
			logger.info("Exception occurred-->" + ex);
		}
		logger.info("Exiting Method-->" + Thread.currentThread().getStackTrace()[1].getMethodName());
		return temperatureModel;

	}

	@GetMapping(path = "/temperatureData/last24hours")
	public HashMap<Integer, TemperatureModel> getTemperatureDataForLast24Hours() {
		logger.info("Entering Method-->" + Thread.currentThread().getStackTrace()[1].getMethodName());
		List<TemperatureModel> temperatureModelList = new ArrayList<TemperatureModel>();
		HashMap<Integer, TemperatureModel> temperatureData = new HashMap<Integer, TemperatureModel>();
		try {
			temperatureModelList = (List<TemperatureModel>) temperatureRepository.findLast24HoursData();
			temperatureData = temperatureService.findGraphData(temperatureModelList);
			logger.info("Output Data-->" + temperatureData.toString());

		} catch (Exception ex) {
			logger.info("Exception occurred-->" + ex);
		}
		logger.info("Exiting Method-->" + Thread.currentThread().getStackTrace()[1].getMethodName());
		return temperatureData;
	}

	@GetMapping(path = "/temperatureData/lastweek")
	public HashMap<Integer, TemperatureModel> getTemperatureDataForLastWeek() {
		logger.info("Entering Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
		List<TemperatureModel> temperatureModelList = new ArrayList<TemperatureModel>();
		HashMap<Integer, TemperatureModel> temperatureData = new HashMap<Integer, TemperatureModel>();
		try {

			temperatureModelList = (List<TemperatureModel>) temperatureRepository.findLastWeekData();
			temperatureData = temperatureService.findGraphData(temperatureModelList);
			logger.info("Output Data-->" + temperatureData.toString());

		} catch (Exception ex) {
			logger.info("Exception occurred-->" + ex);
		}
		logger.info("Exiting Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
		return temperatureData;
	}
}