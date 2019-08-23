package com.ibm.demo;


import java.sql.ResultSet;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaMysqlProducer {
	
	
	private static final String topicName="mysqltopic";
	

	public static void main(String[] args) {
		
		try
		{
		Properties props=new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		KafkaProducer<String,String> sampleProducer= new KafkaProducer<String,String>(props);
		
//		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName, value);		
//		sampleProducer.send(record);
		DataStreamMysql obj=new DataStreamMysql();
		ResultSet rs=obj.GetMysqlData();
		while(rs.next())
		{
			String line=rs.getString(1)+" "+rs.getString(2)+" "+" "+rs.getString(3)+" "+" "+rs.getString(4)+" "+" "+rs.getString(5)+" "+" "+rs.getString(6)+" "+" "+rs.getString(7)+" "+" "+rs.getString(8)+" ";	
			sampleProducer.send(new ProducerRecord<String, String>(topicName,line));
			Thread.sleep(1000);
		}   
		    
		    sampleProducer.close();
		    //Thread.sleep(1000);
       
	}catch(Exception e)
	{
		e.printStackTrace();
	}

}
}
