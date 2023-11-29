package br.inatel.labs.lab_mqtt.client;

import org.eclipse.paho.client.mqttv3.*;

import java.util.Random;
import java.util.UUID;

public class SensorTemperaturaPublisher {

    public static void main(String[] args) throws MqttException {

        IMqttClient publisher = null;

        try{
            // 1. Criar o publisher
            String publisherId = UUID.randomUUID().toString();
            publisher = new MqttClient(MyConstants.URI_BROKER, publisherId);

            // 3. Conecta
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            publisher.connect(options);

            for(int i = 1; i < 10; i++){
                // 2. Criar a mensagem
                MqttMessage msg = getTemperaturaMessage();
                msg.setQos(0);
                msg.setRetained(true);

                // 4. Publicar a mensagem
                publisher.publish(MyConstants.TOPIC_SENSOR, msg);

                Thread.sleep(2000);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                // 5. Disconeta
                publisher.disconnect();
                publisher.close();
            } catch (Exception e){
                //Não precisa fazer nada
            }
        }


    }

    private static MqttMessage getTemperaturaMessage(){
        Random r = new Random();
        double temperatura = 80 + r.nextDouble() * 20;
        String temperaturaStr = String.format("T:%04.2f",temperatura);
        byte[] payload = temperaturaStr.getBytes();
        return new MqttMessage(payload);
    }
}
