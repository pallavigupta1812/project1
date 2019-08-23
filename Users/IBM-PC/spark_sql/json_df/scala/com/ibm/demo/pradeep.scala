package com.ibm.demo
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.SQLContext._
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row
import org.apache.spark.sql.hive.HiveContext

object pradeep {
  def main(args: Array[String]): Unit = {
    val inputFile = "people.txt"
    val inputFile2 = "emp.txt"
    val outputFile = "d:\\software\\Hadoop_Dummy\\output"

System.setProperty("hadoop.home.dir", "C:/Shivani/prady/hadoop");
val conf = new SparkConf().setAppName("Hive").setMaster("local");     
               // Create a Scala Spark Context.
      val sc = new SparkContext(conf)
      val sqlCtx = new org.apache.spark.sql.hive.HiveContext(sc)
      
      val peopleRDD =  sc.textFile(inputFile)
      val schemaString = "name age email";
      val fields = schemaString.split(" ")
    .map(fieldName => StructField(fieldName, StringType, nullable = true))
    val schema = StructType(fields)
    
    val newRDD = peopleRDD.map(_.split(",")).map(attr=>Row(attr(0), attr(1).trim, attr(2).trim))
    val peopleDF = sqlCtx.createDataFrame(newRDD, schema)
    peopleDF.show()
    peopleDF.registerTempTable("people")
    val peoples = sqlCtx.sql("Select * from people where name like '%sharma'")
    peoples.show()

        //To Process 2nd File
    val empRDD = sc.textFile(inputFile2)
    val empColumns = "id name country salary age";
    val empcolArr = empColumns.split(" ")
      .map(fieldName => StructField(fieldName, StringType, nullable = true))
    val empSchema = StructType(empcolArr)

    val empMapRDD = empRDD.map(_.split(",")).map(attr => Row(attr(0), attr(1).trim, attr(2).trim, attr(3).trim, attr(4).trim))
    val empDF = sqlCtx.createDataFrame(empMapRDD, empSchema)
    import sqlCtx.implicits._ 
    val joinDF = empDF.as("p").join(peopleDF.as("q"), $"p.age" === $"q.age").select($"p.*", $"q.email")
    joinDF.show()
    joinDF.printSchema()
    joinDF.registerTempTable("tempEmployee")
    sqlCtx.sql("show tables").show();
    sqlCtx.sql("use default")
//    sqlCtx.sql("CREATE TABLE IF NOT EXISTS employee as select * from tempEmployee")
    joinDF.write.insertInto("employee")
    
      /*// Split up into words.
      val words = input.flatMap(line => line.split(" "))
      // Transform into word and count.
      val counts = words.map(word => (word, 1)).reduceByKey{case (x, y) => x + y}
      // Save the word count back out to a text file, causing evaluation.
      counts.saveAsTextFile(outputFile)*/
  
  }

}