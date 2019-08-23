package com.ibm.demo

import org.apache.spark._
import org.apache.spark.SparkContext._

object word_count {
  def main(args: Array[String]): Unit ={
    val inputfile="d:\\abcd.txt";
    val outputfile="d:\\abcdoutputppppp";
        
    //spark context
    val conf = new SparkConf().setAppName("SOME APP NAME").setMaster("local")
    val sc = new SparkContext(conf)
    System.setProperty("hadoop.home.dir", "d:/bin");
    
    //wc prog
    val counts = sc.textFile(inputfile)
    .flatMap(line =>line.split(" "))
    .map(t=>(t,1))
    .reduceByKey{case (x,y)=> x+y}
    
    counts.saveAsTextFile(outputfile)
    
  }
}