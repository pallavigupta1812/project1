package com.ibm.demo
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkConf


object csv_rdd_to_df {
  case class student_record(id:Int, fname:String, lname:String, email:String, m1:Int, m2:Int, m3:Int, m4:Int, m5:Int);
  def main(args: Array[String]): Unit= {
    val inputFile= "d:\\student_record.csv";
    val outputFile= "d:\\json_output";    System.setProperty("hadoop.home.dir", "d:/bin");
    val conf = new SparkConf().setAppName("SOME APP NAME").setMaster("local");
          // Create a Scala Spark Context.
    val sc = new SparkContext(conf)
    val sqlContext= new org.apache.spark.sql.SQLContext(sc)
      import sqlContext.implicits._
      import org.apache.spark.sql.Row
    //creating RDD
    val rdd1=sc.textFile(inputFile);
    rdd1.take(1).foreach(println);
   // val df = rdd1.map(t=> t.split(" ")).toDF
 }
}