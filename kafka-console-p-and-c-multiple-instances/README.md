
Planning 
=================

     1 - Producer
     3 - Brokers / Kafka servers
     2 - Consumers [ group Name = myGroup ]
     
     Topic Name = myTopic

     partitions = 3

Kafka defaults
================
-   zookeeper  - 2181
-   kafka-server - 9092
-   create topic - test ( run only once ) 
-   kafka console producer
-   kafka console consumer 

-   /tmp/kafka-logs/   -  contains the data 

cleanup ( note : cleaning this will delete all past data)
=========================================================

    rm -r /tmp/zookeeper 
    rm -r /kafka-logs

To run multiple Kafka brokers on same machine
=============================================
    cd  /Users/rami/developer/opt/runtime/confluent-5.4.2/etc/kafka
    
    cp server.properties server-1.properties 
    cp server.properties server-2.properties 
    cp server.properties server-3.properties 


    vim server-11.poperties
    
    broker.id=1
    listeners=PLAINTEXT://:9093
    log.dirs=/tmp/kafka-logs-broker-1
    

| file              | broker.id   | listeners                   | log.dirs                          |
|-------------------|-------------|-----------------------------|-----------------------------------|
| server1.properties | broker.id=1 | listeners=PLAINTEXT://:9093 | log.dirs=/tmp/kafka-logs-broker-1 |
| server2.properties | broker.id=2 | listeners=PLAINTEXT://:9094 | log.dirs=/tmp/kafka-logs-broker-2 |
| server3.properties | broker.id=3 | listeners=PLAINTEXT://:9095 | log.dirs=/tmp/kafka-logs-broker-3 |


Output
======

4_kafka_console_consumer-1.sh 

        Sample Line - 1
        Sample Line - 2
        .
        .
        Sample Line - 657
        ^CProcessed a total of 657 messages
4_kafka_console_consumer-2.sh 

        Sample Line - 658
        Sample Line - 659
        .
        .
        Sample Line - 1000
        ^CProcessed a total of 343 messages
        
        
check 
======
KAFKA_HOME=/Users/rami/developer/opt/runtime/confluent-5.4.2
    
    Vijays-MacBook-Air:myTopic-1 rami$ /Users/rami/developer/opt/runtime/confluent-5.4.2/bin/kafka-dump-log  --files /tmp/kafka-logs-broker-1/myTopic-*/000*.log
    Dumping /tmp/kafka-logs-broker-1/myTopic-1/00000000000000000000.log
    Starting offset: 0
    baseOffset: 0 lastOffset: 342 count: 343 baseSequence: -1 lastSequence: -1 producerId: -1 producerEpoch: -1 partitionLeaderEpoch: 0 isTransactional: false isControl: false position: 0 CreateTime: 1589892573138 size: 8573 magic: 2 compresscodec: NONE crc: 521185611 isvalid: true
    
    Vijays-MacBook-Air:myTopic-1 rami$ /Users/rami/developer/opt/runtime/confluent-5.4.2/bin/kafka-dump-log  --files /tmp/kafka-logs-broker-2/myTopic-*/000*.log
    Dumping /tmp/kafka-logs-broker-2/myTopic-2/00000000000000000000.log
    Starting offset: 0
    baseOffset: 0 lastOffset: 656 count: 657 baseSequence: -1 lastSequence: -1 producerId: -1 producerEpoch: -1 partitionLeaderEpoch: 0 isTransactional: false isControl: false position: 0 CreateTime: 1589892573116 size: 16362 magic: 2 compresscodec: NONE crc: 2922455919 isvalid: true
    
    Vijays-MacBook-Air:myTopic-1 rami$ /Users/rami/developer/opt/runtime/confluent-5.4.2/bin/kafka-dump-log  --files /tmp/kafka-logs-broker-3/myTopic-*/000*.log
    Dumping /tmp/kafka-logs-broker-3/myTopic-0/00000000000000000000.log
    Starting offset: 0

Partitions
==========

    /Users/rami/developer/opt/runtime/confluent-5.4.2/bin/kafka-topics --describe --bootstrap-server localhost:9093 --topic myTopic
    
    Topic: myTopic	PartitionCount: 3	ReplicationFactor: 1	Configs: segment.bytes=1073741824
    
    Topic: myTopic	Partition: 0	Leader: 3	Replicas: 3	Isr: 3
    Topic: myTopic	Partition: 1	Leader: 1	Replicas: 1	Isr: 1
    Topic: myTopic	Partition: 2	Leader: 2	Replicas: 2	Isr: 2
    

 notes:
 =======
/tmp/kafka-logs-broker-1/myTopic-1/00000000000000000000.log
 
    partition    = myTopic-1
    segment file = 00000000000000000000.log
    
- maximum segment size : 1 GB or 1 week data (which ever is smaller)

Connecting to Zookeeper to list nodes
=====================================
    Vijays-MacBook-Air:myTopic-1 rami$ /Users/rami/developer/opt/runtime/confluent-5.4.2/bin/zookeeper-shell localhost:2181
    Connecting to localhost:2181
    Welcome to ZooKeeper!
    JLine support is disabled
    
    WATCHER::
    
    WatchedEvent state:SyncConnected type:None path:null
    
    ls /
    [admin, brokers, cluster, config, consumers, controller, controller_epoch, isr_change_notification, latest_producer_id_block, log_dir_event_notification, zookeeper]
    ls /brokers
    [ids, seqid, topics]
    ls /brokers/topics
    [__confluent.support.metrics, __consumer_offsets, myTopic]
    ls /brokers/ids
    [1, 2, 3]
    
controller
---------

    get /controller
    {"version":1,"brokerid":1,"timestamp":"1589895719945"}