package com.job.read

import com.configuration.AppConfiguration
import com.job.SparkJob
import com.transform.DefaultTransformer
import org.apache.spark.sql.{Dataset, SparkSession}

abstract class ElasticsearchReadJob[T](configuration: AppConfiguration) extends SparkJob {
  //Read and log to console
  protected def read(dataset: Dataset[T])

  //Define transformer
  protected def transformer: DefaultTransformer[T]

  override protected def init: SparkSession = {
    SparkSession.builder.master(configuration.spark.master)
      .appName("ElasticsearchReadJob")
      .config("spark.es.nodes", configuration.elasticsearch.host)
      .config("spark.es.port", configuration.elasticsearch.port)
      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .getOrCreate
  }

  override protected def execute(): Unit = read(transformer.dataset())
}