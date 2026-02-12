package com.vidhyoday.gyan.jyoti.spark.sutra.day_three

import org.apache.spark.TaskContext
import org.apache.spark.SparkEnv
import org.apache.spark.sql.SparkSession

object DriverExecutorCode {

  def main(args: Array[String]): Unit = {

    // 1. Create SparkSession
    val spark = SparkSession.builder()
      .appName("DriverVsExecutor")
      .master("local[2]")
      .getOrCreate()

    println("This runs on DRIVER")

    val data = spark.sparkContext.parallelize(List(1, 2, 3, 4))

    data.map { x =>
      println(s"Processing $x")
      x * 2
    }.collect()

    println(s"[DRIVER] Thread = ${Thread.currentThread().getName}")
    println(org.apache.spark.SPARK_VERSION)

    spark.sparkContext.parallelize(1 to 4, 2)
      .map { x =>
        val executorId = SparkEnv.get.executorId
        val partitionId = TaskContext.get().partitionId()
        val thread = Thread.currentThread().getName

        println(
          s"""
             |Value: $x
             |Executor: $executorId
             |Partition: $partitionId
             |Thread: $thread
       """.stripMargin)

        x
      }
      .collect()


    // 4. Stop Spark
    spark.stop()
  }

}
