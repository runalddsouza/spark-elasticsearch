package com.job.read

import com.configuration.{AppConfiguration, Configuration}
import com.model.Employee
import com.transform.{DefaultTransformer, EmployeeESTransformer}
import org.apache.spark.sql.Dataset

class EmployeeReadJob(configuration: AppConfiguration) extends ElasticsearchReadJob[Employee](configuration: AppConfiguration) {
  override protected def read(dataset: Dataset[Employee]): Unit = dataset.show(false)

  override protected def transformer: DefaultTransformer[Employee] = new EmployeeESTransformer(spark, configuration)
}

object EmployeeReadJob {
  def main(args: Array[String]): Unit = new EmployeeReadJob(Configuration.load).run()
}
