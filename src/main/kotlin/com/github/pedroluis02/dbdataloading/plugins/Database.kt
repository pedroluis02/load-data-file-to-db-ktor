package com.github.pedroluis02.dbdataloading.plugins

import io.ktor.server.application.*
import java.sql.DriverManager
import java.sql.SQLException

fun Application.configureDatabases() {
    configureH2()
}

private fun configureH2() {
    try {
        val url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;MODE=MYSQL"
        val connection = DriverManager.getConnection(url, "", "")

        val query = getResourceAsText("database-ddl.sql")
        println("ddl query: $query")

        val result = connection.createStatement().execute(query)
        println("query execution: $result")
    } catch (ex: SQLException) {
        println(ex.message)
    }
}

private fun getResourceAsText(path: String): String? =
    object {}.javaClass.classLoader.getResource(path)?.readText()