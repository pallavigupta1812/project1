package com.ibm.kafka


import org.apache.spark.streaming.dstream._
import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._

object stateFulWordCount {
def main(args: Array[String]) {
  
/*  
val conf = new SparkConf().setMaster("local").setAppName("KafkaReceiver")
val ssc = new StreamingContext(conf, Seconds(10))
/*
* Defingin the Kafka server parameters
*/
val kafkaParams = Map[String, Object]("bootstrap.servers" -> "localhost:9092,localhost:9092",
"key.deserializer" -> classOf[StringDeserializer],
"value.deserializer" -> classOf[StringDeserializer],
"group.id" -> "use_a_separate_group_id_for_each_stream",
"auto.offset.reset" -> "latest",
"enable.auto.commit" -> (false: java.lang.Boolean))
val topics = Array("kafka_connect_test") //topics list
val kafkaStream = KafkaUtils.createDirectStream[String, String](
ssc,
PreferConsistent,
Subscribe[String, String](topics, kafkaParams))
val splits = kafkaStream.map(record => (record.key(), record.value.toString)).
flatMap(x => scala.util.parsing.json.JSON.parseFull(x._2).get.asInstanceOf[Map[String, Any]].get("payload"))
val updateFunc = (values: Seq[Int], state: Option[Int]) => {
val currentCount = values.foldLeft(0)(_ + _)
val previousCount = state.getOrElse(0)
val updatedSum = currentCount+previousCount
Some(updatedSum)
}
//Defining a check point directory for performing stateful operations
ssc.checkpoint("hdfs://localhost:9000/WordCount_checkpoint")
val wordCounts = splits.flatMap(x => x.toString.split(" ")).map(x => (x, 1)).reduceByKey(_+_).updateStateByKey(updateFunc)
wordCounts.print() //prints the wordcount result of the stream
ssc.start()
ssc.awaitTermination()
}
*/ 
}
}