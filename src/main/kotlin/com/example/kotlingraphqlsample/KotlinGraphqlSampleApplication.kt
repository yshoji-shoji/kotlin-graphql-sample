package com.example.kotlingraphqlsample

import com.example.kotlingraphqlsample.handlers.graphql.types.CustomSchemaGeneratorHooks
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class KotlinGraphqlSampleApplication {
    @Bean
    fun customGraphQLSchemaGeneratorHooks() = CustomSchemaGeneratorHooks()
}

fun main(args: Array<String>) {
    @Suppress("SpreadOperator")
    runApplication<KotlinGraphqlSampleApplication>(*args)
}
