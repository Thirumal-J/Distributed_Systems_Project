package com.lms.app.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lms.app.LivedataMicroserviceApplication;

@Service
public class LivedataService {

	private static FileWriter fileWriter;
	
	private static FileReader fileReader;
	private static final Logger logger = LoggerFactory.getLogger(LivedataMicroserviceApplication.class);

//	@SuppressWarnings("unchecked")
	public String saveLiveData(@Valid JSONObject jsonobject, File file) {
		logger.info("Entering Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
//		JSONArray liveDataJson = new JSONArray();
		String response = "";
		try {
//			liveDataJson.add(jsonobject);
			fileWriter = new FileWriter(file);
			fileWriter.write(jsonobject.toJSONString());
            response = "Successfully stored";
		}
		catch(Exception ex) {
			response = "Error occurred";
			logger.info("Exception occurred-->" + ex);
		}
		 finally {
	            try {
	            	fileWriter.flush();
	            	fileWriter.close();
	            } catch (IOException e) {
	    			logger.info("Exception occurred-->" + e);
	            }
		 }
        logger.info("Exiting Method-->" +Thread.currentThread().getStackTrace()[1].getMethodName());
		return response;
		
	}

	public JSONObject getLiveData(JSONObject jsonobject, File file) {
		logger.info("Entering Method-->" + Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			fileReader = new FileReader(file);
			JSONParser parser= new JSONParser();
			jsonobject = (JSONObject) parser.parse(fileReader);
		} catch (FileNotFoundException e) {
			logger.info("Exception occurred-->" + e);
		} catch (IOException e) {
			logger.info("Exception occurred-->" + e);
		} catch (ParseException e) {
			logger.info("Exception occurred-->" + e);
		}
		 finally {
	            try {
	            	fileReader.close();
	            } catch (IOException e) {
	    			logger.info("Exception occurred-->" + e);
	            }
		 }
		logger.info("Exiting Method-->" + Thread.currentThread().getStackTrace()[1].getMethodName());
		return jsonobject;

	}
}
