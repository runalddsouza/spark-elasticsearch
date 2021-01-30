package com.transform

import com.configuration.AppConfiguration
import com.model._
import org.apache.spark.sql._

import java.time.Instant

class EmployeeCSVTransformer(spark: SparkSession, configuration: AppConfiguration) extends DefaultTransformer[Employee] {
  override val enc: Encoder[Employee] = Encoders.product[Employee]

  override def dataset(): Dataset[Employee] = {
    loadCsvDataFrame
      .map(row => Employee(
        id = row.getAs[String]("Emp ID").toLong,
        updateDate = Instant.now().getEpochSecond,
        name = Name(
          prefix = row.getAs[String]("Name Prefix"),
          firstName = row.getAs[String]("First Name"),
          lastName = row.getAs[String]("Last Name"),
          fatherName = row.getAs[String]("Father's Name"),
          motherName = row.getAs[String]("Mother's Name")
        ),
        personalDetails = PersonalDetails(
          gender = row.getAs[String]("Gender"),
          dateOfBirth = row.getAs[String]("Date of Birth"),
          email = row.getAs[String]("E Mail"),
          phone = row.getAs[String]("Phone No. ")
        ),
        employmentInfo = EmploymentInfo(
          dateOfJoining = row.getAs[String]("Date of Joining"),
          salary = row.getAs[String]("Salary"),
          userId = row.getAs[String]("User Name")
        ),
        address = Address(
          city = row.getAs[String]("City"),
          state = row.getAs[String]("State"),
          zip = row.getAs[String]("Zip")
        ))
      )(enc)
  }

  private def loadCsvDataFrame: DataFrame = spark.read.option("header", "true").csv(configuration.sourceData)
}
