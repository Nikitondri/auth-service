package com.zakharenko.dbmigrations.config

import liquibase.integration.spring.SpringLiquibase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
open class LiquibaseConfig(
  private val springDataSource: DataSource,
) {

  @Bean
  open fun liquibase(): SpringLiquibase {
    return SpringLiquibase().apply {
      dataSource = springDataSource
      changeLog = "classpath:db/changelog/master.xml"
    }
  }
}
