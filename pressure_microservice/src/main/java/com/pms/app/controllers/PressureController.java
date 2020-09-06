package com.pms.app.controllers;

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
import com.pms.app.PressureMicroserviceApplication;
import com.pms.app.models.PressureModel;
import com.pms.app.repository.PressureRepository;
import com.pms.app.service.PressureService;

@RestController
@RequestMapping(path = "/pressure_api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PressureController {

	private static final Logger logger = LoggerFactory.getLogger(PressureMicroserviceApplication.class);

	@Autowired
	PressureRepository pressureRepository;

	@Autowired
	PressureService pressureService;

	@Autowired
	private Environment env;

	@PostMapping(path = "/insertData")
	public PressureModel insertPressureData(@Valid @RequestBody JSONObject jsonobject) {
		logger.info("Entering Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
		PressureModel pressureModel = new PressureModel();
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setTimeZone(TimeZone.getTimeZone(env.getProperty("timezone")));
			pressureModel = mapper.readValue(jsonobject.toString(), PressureModel.class);
			pressureModel = pressureRepository.save(pressureModel);
			logger.info("Output Data-->" + pressureModel.toString());
		} catch (Exception ex) {
			logger.info("Exception occurred-->" + ex);
		}
		logger.info("Exiting Method-->" + Thread.currentThread().getStackTrace()[1].getMethodName());
		return pressureModel;

	}

	@GetMapping(path = "/pressureData/last24hours")
	public HashMap<Integer, PressureModel> getPressureDataForLast24Hours() {
		logger.info("Entering Method-->" + Thread.currentThread().getStackTrace()[1].getMethodName());
		List<PressureModel> pressureModelList = new ArrayList<PressureModel>();
		HashMap<Integer, PressureModel> pressureData = new HashMap<Integer, PressureModel>();
		try {
			pressureModelList = (List<PressureModel>) pressureRepository.findLast24HoursData();
			pressureData = pressureService.findGraphData(pressureModelList);
			logger.info("Output Data-->" + pressureData.toString());

		} catch (Exception ex) {
			logger.info("Exception occurred-->" + ex);
		}
		logger.info("Exiting Method-->" + Thread.currentThread().getStackTrace()[1].getMethodName());
		return pressureData;
	}

	@GetMapping(path = "/pressureData/lastweek")
	public HashMap<Integer, PressureModel> getPressureDataForLastWeek() {
		logger.info("Entering Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
		List<PressureModel> pressureModelList = new ArrayList<PressureModel>();
		HashMap<Integer, PressureModel> pressureData = new HashMap<Integer, PressureModel>();
		try {

			pressureModelList = (List<PressureModel>) pressureRepository.findLastWeekData();
			pressureData = pressureService.findGraphData(pressureModelList);
			logger.info("Output Data-->" + pressureData.toString());

		} catch (Exception ex) {
			logger.info("Exception occurred-->" + ex);
		}
		logger.info("Exiting Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
		return pressureData;
	}
}