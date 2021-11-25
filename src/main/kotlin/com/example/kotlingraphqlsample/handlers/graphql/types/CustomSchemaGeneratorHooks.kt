package com.example.kotlingraphqlsample.handlers.graphql.types

import com.expediagroup.graphql.hooks.SchemaGeneratorHooks
import graphql.schema.Coercing
import graphql.schema.CoercingParseLiteralException
import graphql.schema.CoercingParseValueException
import graphql.schema.CoercingSerializeException
import graphql.schema.GraphQLScalarType
import graphql.schema.GraphQLType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue

class CustomSchemaGeneratorHooks : SchemaGeneratorHooks {
    override fun willGenerateGraphQLType(type: KType): GraphQLType? = when (type.classifier as? KClass<*>) {
        LocalDateTime::class -> graphqlDateTimeType
        else -> null
    }
}

internal val graphqlDateTimeType: GraphQLScalarType = GraphQLScalarType.newScalar()
    .name("DateTime")
    .description("Custom scalar type representing java.time.LocalDateTime")
    .coercing(LocalDateTimeCoercing)
    .build()

private object LocalDateTimeCoercing : Coercing<LocalDateTime, String> {
    override fun serialize(dataFetcherResult: Any): String = runCatching {
        dataFetcherResult.toString()
    }.getOrElse {
        throw CoercingSerializeException("Data fetcher result $dataFetcherResult cannot be serialized to a String")
    }

    override fun parseValue(input: Any): LocalDateTime = runCatching {
        LocalDateTime.parse(serialize(input), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    }.getOrElse {
        throw CoercingParseValueException("Expected valid LocalDateTime but was $input")
    }

    override fun parseLiteral(input: Any): LocalDateTime {
        val localDateTimeString = (input as? StringValue)?.value

        return runCatching {
            LocalDateTime.parse(localDateTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        }.getOrElse {
            throw CoercingParseLiteralException("Expected valid LocalDateTime literal but was $localDateTimeString")
        }
    }
}
