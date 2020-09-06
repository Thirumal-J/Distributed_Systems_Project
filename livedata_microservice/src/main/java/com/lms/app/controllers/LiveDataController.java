package com.lms.app.controllers;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.app.LivedataMicroserviceApplication;
import com.lms.app.service.LivedataService;


@RestController
@RequestMapping(path = "/livedata_api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LiveDataController {

	private static final Logger logger = LoggerFactory.getLogger(LivedataMicroserviceApplication.class);

	@Autowired
	LivedataService livedataService;

	@PostMapping(path = "/insertData")
	public String insertTemperatureData(@Valid @RequestBody JSONObject jsonobject) {
		logger.info("Entering Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
//		LivedataModel livedataModel = new LivedataModel();
		String response = "";
		try {
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.setTimeZone(TimeZone.getTimeZone(env.getProperty("timezone")));
//			livedataModel = mapper.readValue(jsonobject.toString(), LivedataModel.class);
//			livedataModel = livedataService.saveLiveData(livedataModel);
			response = livedataService.saveLiveData(jsonobject,LivedataMicroserviceApplication.file);
		} catch (Exception ex) {
			logger.info("Exception occurred-->" + ex);
		}
		logger.info("Exiting Method-->" + Thread.currentThread().getStackTrace()[1].getMethodName());
		return response;

	}

	@GetMapping(path = "/getLiveData")
	public JSONObject getLiveData() {
		logger.info("Entering Method-->" + Thread.currentThread().getStackTrace()[1].getMethodName());
		JSONObject jsonobject = new JSONObject();
		jsonobject = livedataService.getLiveData(jsonobject,LivedataMicroserviceApplication.file);
		logger.info("Exiting Method-->" + Thread.currentThread().getStackTrace()[1].getMethodName());
		return jsonobject;
	}
}