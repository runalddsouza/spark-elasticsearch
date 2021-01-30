package com.transform

import com.configuration.AppConfiguration
import com.model.Employee
import org.apache.spark.sql._

class EmployeeESTransformer(spark: SparkSession, configuration: AppConfiguration) extends DefaultTransformer[Employee] {
  override val enc: Encoder[Employee] = Encoders.product[Employee]

  override def dataset(): Dataset[Employee] = loadESDataFrame.as[Employee](enc)

  private def loadESDataFrame: DataFrame = spark.read.format("org.elasticsearch.spark.sql").load(configuration.elasticsearch.index)
}
