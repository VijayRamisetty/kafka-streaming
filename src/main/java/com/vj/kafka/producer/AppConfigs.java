package com.vj.kafka.producer;

public class AppConfigs {

    final static String APP_ID ="HelloProducer" ;
    final static String BOOT_STRAP_SERVERS ="localhost:9093,localhost:9094" ;
    final static String TOPIC_NAME ="hello-producer-topic" ;
    final static  int NUM_EVENTS = 1000;

    // using external properties file
    final static String KAFKA_APP_PROPS_LOC ="kafka-application.properties" ;

}

