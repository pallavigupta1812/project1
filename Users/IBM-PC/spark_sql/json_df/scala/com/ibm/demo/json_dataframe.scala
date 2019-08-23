package com.ibm.demo
import org.apache.spark._
import org.apache.spark.SparkContext._

object json_dataframe {
  def main(args: Array[String]): Unit= {
    val inputFile= "d:\\sample-data.json";
    val outputFile= "d:\\json_output";    System.setProperty("hadoop.home.dir", "d:/bin");
    val conf = new SparkConf().setAppName("SOME APP NAME").setMaster("local");
          // Create a Scala Spark Context.
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
         // Load our input data.
     val df = sqlContext.read.json(inputFile);
    df.show()
    df.printSchema()
    df.registerTempTable("sample")
    val res = sqlContext.sql("select * from sample")
    //res.show()
    val res1 = sqlContext.sql("select count(first_name) as ppl,gender from sample group by gender")
    //res1.show()
  }
}