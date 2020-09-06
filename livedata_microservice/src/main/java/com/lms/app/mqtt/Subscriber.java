package com.lms.app.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.lms.app.utils.Utils;

public class Subscriber {

    public static final String BROKER_URL = "tcp://mqtt.eclipse.org:1883";

    //We have to generate a unique Client id.
    String clientId = Utils.getMacAddress() + "-sub-livedata";
    private MqttClient mqttClient;

    public Subscriber() {

        try {
            mqttClient = new MqttClient(BROKER_URL, clientId);


        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void start() {
        try {

            mqttClient.setCallback(new SubscribeCallback());
            mqttClient.connect();

            //Subscribe to all subtopics of home
            final String topic = "/liveData/distributed";
            mqttClient.subscribe(topic);

            System.out.println("Subscriber is now listening to "+topic);

        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String... args) {
        final Subscriber subscriber = new Subscriber();
        subscriber.start();
    }

}
