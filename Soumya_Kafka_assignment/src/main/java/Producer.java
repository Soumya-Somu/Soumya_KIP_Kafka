//import all the packages required to send the value to a cluster
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class Producer {
    public static void main(String[] args){

        Properties properties = new Properties();

        //method to run on the specific localhost and fetch from the cluster on specific host
        properties.put("bootstrap.servers", "localhost:9092");

        //serializing the key using the kafka package
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //serializing the value to send the value to a cluster
        properties.put("value.serializer", "UserSerializer");

        //method to store the properties and to produce it
        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        try{
            for(int i = 1; i < 3; i++){
                User user = new User("13", "User "+i, 23, "Maven");
                System.out.println(user.toString());
                kafkaProducer.send(new ProducerRecord("user",  user));
            }
        }catch (Exception e){

            //print the exception message if any
            e.printStackTrace();

        }finally {

            //finally close the producer after sending the message to the cluster
            kafkaProducer.close();
        }
    }
}