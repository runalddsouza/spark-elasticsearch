package com.job.write

import com.configuration.{AppConfiguration, Configuration}
import com.model.Employee
import com.transform._
import org.apache.spark.sql.Dataset
import org.elasticsearch.spark.sql.sparkDatasetFunctions

class EmployeeWriteJob(configuration: AppConfiguration) extends ElasticsearchWriteJob[Employee](configuration: AppConfiguration) {
  override protected def index(dataset: Dataset[Employee]): Unit = {
    dataset.toDF().saveToEs(configuration.elasticsearch.index, Map("es.mapping.id" -> "id", "es.mapping.version" -> "updateDate"))
  }

  override protected def transformer: DefaultTransformer[Employee] = new EmployeeCSVTransformer(spark, configuration)
}

object EmployeeWriteJob {
  def main(args: Array[String]): Unit = new EmployeeWriteJob(Configuration.load).run()
}
