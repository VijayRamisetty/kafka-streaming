KAFKA_HOME=/Users/rami/developer/opt/runtime/confluent-5.4.2
$KAFKA_HOME/bin/kafka-console-consumer  --bootstrap-server  localhost:9093 --topic myTopic --from-beginning --group myGroup