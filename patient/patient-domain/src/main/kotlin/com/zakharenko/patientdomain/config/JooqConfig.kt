package com.zakharenko.patientdomain.config

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultDSLContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
open class JooqConfig(
  private val dataSource: DataSource,
) {

  @Bean
  open fun dslContext(): DSLContext {
    return DefaultDSLContext(configuration())
  }

  private fun configuration(): org.jooq.Configuration {
    return DefaultConfiguration()
      .derive(SQLDialect.MYSQL)
      .set(dataSource)
  }
}
