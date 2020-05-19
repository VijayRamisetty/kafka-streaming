KAFKA_HOME=/Users/rami/developer/opt/runtime/confluent-5.4.2
$KAFKA_HOME/bin/kafka-topics --create --topic myTopic  --partitions 3 --replication-factor 1 --bootstrap-server localhost:9093