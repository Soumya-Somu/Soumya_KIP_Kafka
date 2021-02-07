//import all the packages required to fetch the message from the cluster

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Consumer {

    public static void main(String[] args) {
        ConsumerListener c = new ConsumerListener();
        Thread thread = new Thread(c);
        thread.start();
    }

    public static void consumer() {
        Properties properties = new Properties();

        //method to run on the specific localhost and fetch from the cluster on specific host
        properties.put("bootstrap.servers", "localhost:9092");

        //deserializing the key using the kafka package
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        //deserializing the value to get the value from a cluster
        properties.put("value.deserializer", "UserDeserializer");

        //create a group-id
        properties.put("group.id", "test-group");

        KafkaConsumer<String, User> kafkaConsumer = new KafkaConsumer(properties);

        //create a variable to store the topics
        List topics = new ArrayList();

        //name a topic
        topics.add("user");

        //subscribe or call to the topic
        kafkaConsumer.subscribe(topics);
        try {
            while (true) {

                //create the poll to check for the message after every particular time
                ConsumerRecords<String, User> messages = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, User> message : messages) {
                    System.out.println(message.value().toString());

                    //enable the append to write into a file
                    FileWriter writer = new FileWriter("KafkaMessages.txt",true);
                    BufferedWriter buffer = new BufferedWriter(writer);
                    buffer.write(message.value()+"\n");
                    buffer.close();
                }
            }
        } catch (Exception e) {     //method to print the exception
            System.out.println(e.getMessage());
        } finally {
            kafkaConsumer.close();  //method to close the consumer server
        }
    }
}

class ConsumerListener implements Runnable {
    @Override
    public void run() {
        Consumer.consumer();
    }
}