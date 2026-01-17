package com.vidhyoday.gyan.jyoti.spark.sutra

import org.apache.spark.sql.SparkSession

object SparkMain {

  def main(args: Array[String]): Unit = {

    // 1. Create SparkSession
    val spark = SparkSession
      .builder()
      .appName("Spark Sutra - Basic App")
      .master("local[*]")   // remove this when running on cluster
      .getOrCreate()

    // 2. Simple Spark operation
    val data = Seq(1, 2, 3, 4, 5)
    val rdd = spark.sparkContext.parallelize(data)

    val sum = rdd.reduce(_ + _)

    // 3. Output
    println(s"Sum computed by Spark = $sum")

    // 4. Stop Spark
    spark.stop()
  }
}
