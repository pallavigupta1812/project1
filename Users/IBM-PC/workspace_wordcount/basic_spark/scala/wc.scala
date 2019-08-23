
import org.apache.spark._
import org.apache.spark.SparkContext._

object wc {
  def main(args: Array[String]): Unit = {
      
      val inputFile = "d:\\abcd.txt"
      val outputFile ="d:\\abcdOutput33333"
      val conf = new SparkConf().setAppName("SOME APP NAME").setMaster("local");
              //setting hadoop conf dir  
      System.setProperty("hadoop.home.dir", "d:/bin");
             // Create a Scala Spark Context.
      val sc = new SparkContext(conf)
             // Load our input data.
      val input =  sc.textFile(inputFile)
             // Split up into words.
      val words = input.flatMap(line => line.split(" "))
             // Transform into word and count.
      val counts = words.map(word => (word, 1)).reduceByKey{case (x, y) => x + y}
             // Save the word count back out to a text file, causing evaluation.
      counts.saveAsTextFile(outputFile)

  }

}