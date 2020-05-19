package com.vj.kafka.producer;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.log4j.Logger;

import java.util.Properties;

public class HelloProducer {
    public static final Logger logger = Logger.getLogger(HelloProducer.class);

    public static void main(String[] args) {

        logger.info(" Creating Kafka Producer");
        Properties props = new Properties();

        props.put(ProducerConfig.CLIENT_ID_CONFIG , AppConfigs.APP_ID); // to track source of message
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.BOOT_STRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<Integer,String> producer  = new KafkaProducer<Integer, String>(props) ;

        logger.info("Producer Started Sending messages...");

        for (int i = 1 ;  i <= AppConfigs.NUM_EVENTS; i++) {
            //logger.info("Simple Message-"+i);
            producer.send(new ProducerRecord<>(AppConfigs.TOPIC_NAME,i,"Simple Message-"+i));
        }

        logger.info("Finished Sending messages to Kafka. Closing Producer");

        producer.close();

    }
}
