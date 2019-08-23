package com.ibm.demo

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql.DataFrame

object json_df {
  def main(args: Array[String]): Unit={
    val conf = new SparkConf().setAppName("SOME APP NAME").setMaster("local");
    System.setProperty("hadoop.home.dir", "d:/bin");
    // Create a Scala Spark Context.
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    
  }
  
}