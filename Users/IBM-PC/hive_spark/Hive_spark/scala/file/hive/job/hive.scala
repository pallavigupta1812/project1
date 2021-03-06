package file.hive.job
import org.apache.spark.sql.SparkSession

object hive {
  val spark = SparkSEssion.builder()
  .master("local")
  .appName("sample job")
  .config("spark.sql.warehouse.dir","/user/hive/warehouse")
  .enableHiveSupport()
  .getOrCreate()

  val df = spark.read.option("header","true")
  .option("inferSchema","true")
  .csv("d://student_record.csv")
  
  df.write.saveAsTable(practice.student_record)
}
