package com.tms.app.mqtt;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SubscribeCallback implements MqttCallback {

	@Override
    public void connectionLost(Throwable cause) {
		System.out.println("Lost Connection");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("Message arrived. Topic: " + topic + "  Message: " + message.toString());

        if ("/temperatureData/distributed".equals(topic)) {
	        CountDownLatch countDownLatch = new CountDownLatch(10);
        	JSONParser parser = new JSONParser();
        	JSONObject json = (JSONObject) parser.parse(new String(message.getPayload()));
        	System.out.println("JSON FORMAT-->"+json.toString());
        	sendPost(json);
			countDownLatch.countDown(); 
			countDownLatch.await(5000, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
    }
    
    private void sendPost(JSONObject json ) throws Exception {	
    	try {
		String       postUrl       = "http://localhost:4002/temperature_api/insertData";// put in your url
		HttpClient   httpClient    = HttpClientBuilder.create().build();
		HttpPost     post          = new HttpPost(postUrl);
		StringEntity postingString = new StringEntity(json.toString());//gson.tojson() converts your pojo to json
		post.setEntity(postingString);
		post.setHeader("Content-type", "application/json");
		HttpResponse  response = httpClient.execute(post);    		
		System.out.println(response.toString());
    	}
    	catch (Exception ex) {
    		System.out.println("***********ERROR Invoking "+ex);
    	}
  }
}