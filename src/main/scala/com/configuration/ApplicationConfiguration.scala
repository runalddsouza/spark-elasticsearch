package com.configuration

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory

object Configuration {
  def load: AppConfiguration = {
    new ObjectMapper(new YAMLFactory)
      .readValue(getClass.getClassLoader.getResourceAsStream("config.yml"), classOf[AppConfiguration])
  }
}

case class AppConfiguration(@JsonProperty("spark") spark: Spark, @JsonProperty("elasticsearch") elasticsearch: Elasticsearch,
                            @JsonProperty("sourceData") sourceData: String)

case class Elasticsearch(@JsonProperty("host") host: String, @JsonProperty("port") port: String, @JsonProperty("index") index: String)

case class Spark(@JsonProperty("master") master: String)


