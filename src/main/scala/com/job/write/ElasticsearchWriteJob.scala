package com.job.write

import com.configuration.AppConfiguration
import com.job.SparkJob
import com.transform.DefaultTransformer
import org.apache.spark.sql.{Dataset, SparkSession}

abstract class ElasticsearchWriteJob[T](configuration: AppConfiguration) extends SparkJob {
  //Index records to ES
  protected def index(dataset: Dataset[T])

  //Define transformer
  protected def transformer: DefaultTransformer[T]

  override protected def init: SparkSession = {
    SparkSession.builder.master(configuration.spark.master)
      .appName("ElasticsearchWriteJob")
      .config("spark.es.nodes", configuration.elasticsearch.host)
      .config("spark.es.port", configuration.elasticsearch.port)
      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .config("spark.es.index.auto.create", "true")
      .getOrCreate
  }

  override protected def execute(): Unit = index(transformer.dataset())
}
