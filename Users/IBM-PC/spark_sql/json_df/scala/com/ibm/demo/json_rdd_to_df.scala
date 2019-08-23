package com.ibm.demo
import org.apache.spark._
import org.apache.spark.SparkContext._

object json_rdd_to_df {
  def main(args: Array[String]): Unit= {
    val inputFile= "d:\\sample-data.json";
    val outputFile= "d:\\json_output";    System.setProperty("hadoop.home.dir", "d:/bin");
    val conf = new SparkConf().setAppName("SOME APP NAME").setMaster("local");
          // Create a Scala Spark Context.
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    
    //creating RDD
    val rdd1=sc.textFile(inputFile);
    rdd1.take(1).foreach(println);
    
     
}
  }