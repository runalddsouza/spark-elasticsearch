package com.transform

import com.configuration.AppConfiguration
import com.model.Employee
import org.apache.spark.sql._

class EmployeeTransformer(spark: SparkSession, configuration: AppConfiguration) extends DefaultTransformer[Employee] {
  override val enc: Encoder[Employee] = Encoders.product[Employee]

  override def dataset(): Dataset[Employee] = {
    loadCsvDataFrame
      .map(row => Employee(
        id = row.getAs[String]("Emp ID").toLong,
        name = null,
        personalDetails = null,
        employmentInfo = null,
        location = null)
      )(enc)
  }

  private def loadCsvDataFrame: DataFrame = spark.read.option("header", "true").csv(configuration.sourceData)
}
