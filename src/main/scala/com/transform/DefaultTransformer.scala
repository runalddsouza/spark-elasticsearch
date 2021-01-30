package com.transform

import org.apache.spark.sql.{Dataset, Encoder}

trait DefaultTransformer[T] {
  val enc: Encoder[T]

  def dataset(): Dataset[T]
}
