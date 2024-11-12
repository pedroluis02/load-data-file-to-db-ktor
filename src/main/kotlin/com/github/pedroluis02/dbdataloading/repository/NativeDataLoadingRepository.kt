package com.github.pedroluis02.dbdataloading.repository

import com.github.pedroluis02.dbdataloading.domain.DataLoadingException
import com.github.pedroluis02.dbdataloading.domain.DataLoadingRepository
import com.github.pedroluis02.dbdataloading.util.getResourceAsText
import java.sql.Connection
import java.sql.SQLException

class NativeDataLoadingRepository : DataLoadingRepository {

    override fun load(queryResource: String, filePath: String) {
        val connection = DataSourceProvider.current
        try {
            connection?.autoCommit = false
            if (connection != null) {
                loadData(connection, queryResource, filePath)
            }
            connection?.commit()
        } catch (ex: SQLException) {
            rollback(connection)
            throw DataLoadingException(ex.message ?: "")
        } catch (ex: Exception) {
            throw DataLoadingException(ex.message ?: "")
        } finally {
            enableAutocommit(connection)
        }
    }

    private fun rollback(connection: Connection?) {
        try {
            connection?.rollback()
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    private fun enableAutocommit(connection: Connection?) {
        try {
            connection?.autoCommit = true
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    private fun loadData(connection: Connection, queryResource: String, filePath: String) {
        val loadQuery = getResourceAsText("$queryResource.sql")

        val query = connection.prepareStatement(loadQuery)
        query.setString(1, filePath)

        val rows = query.executeUpdate()
        println("query update: $rows")
    }
}