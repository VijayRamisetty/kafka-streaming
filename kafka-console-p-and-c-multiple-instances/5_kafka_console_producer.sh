KAFKA_HOME=/Users/rami/developer/opt/runtime/confluent-5.4.2
#$KAFKA_HOME/bin/kafka-console-producer  --topic myTopic  --broker-list localhost:9093
$KAFKA_HOME/bin/kafka-console-producer  --topic myTopic  --broker-list localhost:9093  < Sample_Input.txt
