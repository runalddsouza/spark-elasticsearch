package com.job.write

import com.configuration.{AppConfiguration, Configuration}
import com.model.Employee
import com.transform.{DefaultTransformer, EmployeeTransformer}
import org.apache.spark.sql.Dataset
import org.elasticsearch.spark.sql.sparkDatasetFunctions

class EmployeeWriteJob(configuration: AppConfiguration) extends ElasticsearchWriteJob[Employee](configuration: AppConfiguration) {

  override def index(dataset: Dataset[Employee]): Unit = {
    dataset.toDF().saveToEs(configuration.elasticsearch.index, Map("es.mapping.id" -> "id"))
  }

  override def transformer: DefaultTransformer[Employee] = new EmployeeTransformer(spark, configuration)
}

object EmployeeWriteJob {
  def main(args: Array[String]): Unit = new EmployeeWriteJob(Configuration.load)
}
