package com.github.pedroluis02.dbdataloading.repository

import java.sql.Connection
import java.sql.DriverManager

object DataSourceProvider {

    private var url: String = ""
    private var username: String = ""
    private var password: String = ""

    private var connection: Connection? = null

    val current: Connection?
        get() {
            if (connection != null && connection?.isClosed == false) {
                return connection
            }

            try {
                connection = DriverManager.getConnection(url, username, password)
            } catch (ex: Exception) {
                println(ex.message ?: "")
                connection = null
            }

            return connection
        }

    fun setup(jdbUrl: String, user: String, pass: String): Connection? {
        url = jdbUrl
        username = user
        password = pass

        return current
    }
}