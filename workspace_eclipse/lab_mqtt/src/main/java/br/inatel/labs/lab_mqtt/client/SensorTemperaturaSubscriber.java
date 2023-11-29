package br.inatel.labs.lab_mqtt.client;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import java.util.UUID;

public class SensorTemperaturaSubscriber {

    public static void main(String[] args) {

        String subscriberId = UUID.randomUUID().toString();
        IMqttClient subscriber = null;

        try{
            subscriber = new MqttClient(MyConstants.URI_BROKER, subscriberId);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            subscriber.connect(options);
            System.out.println("Subscriber conectado e aguardando mensagens...");

            // Recebendo mensagens
            subscriber.subscribe(
                    MyConstants.TOPIC_SENSOR, (topic, msg) -> {
                        System.out.println("Payload recebido: " + msg);
                        System.out.println("Topic específico: " + topic + "\n");
                    }
            );

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                subscriber.close();
            } catch (Exception e){

            }
        }
    }
}
