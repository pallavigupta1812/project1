package com.ibm.kafka

import org.apache.spark._
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.SparkConf

object WordCount {
  
  
  def main( args:Array[String] ){
    print("Application is running*******")
    val conf = new SparkConf().setAppName("SOME APP NAME").setMaster("local");
    print("Application is running*******")
    val ssc = new StreamingContext(conf, Seconds(10))
    val kafkaStream = KafkaUtils.createStream(ssc, "localhost:2181","spark-streaming-consumer-group", Map("acadgild-topic" -> 5))
//need to change the topic name and the port number accordingly
    val words = kafkaStream.flatMap(x =>  x._2.split(" "))
    val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)
    kafkaStream.print()  
    wordCounts.print()   
    ssc.start()
    ssc.awaitTermination()
  }
}