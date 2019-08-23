package com.ibm.demo
import org.apache.spark._
import org.apache.spark.SparkContext._

object textfile_dataframe {
  def main(args: Array[String]): Unit = {
    val inputFile = "d:\\people.text";
    val outputFile = "d:\\peopleoout";
       //hadoop directory
    System.setProperty("hadoop.home.dir","d:/bin")
       //spark context
    val conf = new SparkConf().setAppName("TEXTFILE DATAFRAME").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    
    import sqlContext.implicits._
        //creating RDD
    val people = sc.textFile(inputFile);
        //define Schema
    val schemaString = "name age gmail city"
        //import Row
    import org.apache.spark.sql.Row;
        //import spark sql data types
    import org.apache.spark.sql.types.{StructType,StructField,StringType};
        //Generate the schema based on the string of schema
    val schema = StructType(schemaString.split(" ").map(fieldName => StructField(fieldName, StringType, true)))
       // Convert records of the RDD (people) to Rows.
    val rowRDD = people.map(_.split(",")).map(p => Row(p(0),p(1),p(2),p(3).trim))
        // Apply the schema to the RDD.
    val peopleDataFrame = sqlContext.createDataFrame(rowRDD, schema)

        // Register the DataFrames as a table.
    peopleDataFrame.registerTempTable("people")

        // SQL statements can be run by using the sql methods provided by sqlContext.
    val results = sqlContext.sql("SELECT name FROM people")
    peopleDataFrame.printSchema();
    println(results);
    val name12 = sqlContext.sql("Select * from people where name like '%sharma'")
    name12.show()

    
  }
}