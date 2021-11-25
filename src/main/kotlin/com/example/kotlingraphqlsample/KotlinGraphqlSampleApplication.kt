package com.example.kotlingraphqlsample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinGraphqlSampleApplication

fun main(args: Array<String>) {
    @Suppress("SpreadOperator")
    runApplication<KotlinGraphqlSampleApplication>(*args)
}
