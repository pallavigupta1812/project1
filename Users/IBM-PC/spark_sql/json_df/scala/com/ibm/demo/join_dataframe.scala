package com.ibm.demo
import org.apache.spark._
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql._
import org.apache.spark.sql.SQLContext
import com.databricks.spark.csv
import org.apache.spark.sql.hive
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.hive.HiveContext

object join_dataframe {
  def main(args: Array[String]): Unit = {
    val inputfile1 = "d:\\dataset1.csv";
    val inputfile2 = "d:\\sample-data.json";
    val outputfile = "d:\\joinresult";
    
    //create scala spark context
    val conf = new SparkConf().setAppName("SOME APP NAME").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext= new org.apache.spark.sql.SQLContext(sc)

    //val hiveContext= new HiveContext(sc)
    //setting hadoop bin directory
    System.setProperty("hadoop.home.dir", "d:/bin");
    
    //creating csv dataframe 
    val csvDF = sqlContext.read
            .option("header","true")
            .option("inferSchema","true")
            .format("com.databricks.spark.csv")
            .load(inputfile1)
    //csvDF.printSchema()
    //csvDF.show()
    csvDF.registerTempTable("tweets")
    
    //creating json dataframe
     val jsonDF = sqlContext.read.json(inputfile2);
    //jsonDF.show()
    //jsonDF.printSchema()
    jsonDF.registerTempTable("sample")
    
    //joining dataframes
    val result = sqlContext.sql("select tweets.first_name, tweets.gender, sample.country, sample.designation from tweets INNER JOIN sample ON tweets.id = sample.id");
    result.show()
    
    result.write.mode("overwrite").format("orc").saveAsTable("mydb.join_res");
    
}
}