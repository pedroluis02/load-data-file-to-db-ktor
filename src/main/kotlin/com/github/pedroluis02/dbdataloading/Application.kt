package com.github.pedroluis02.dbdataloading

import com.github.pedroluis02.dbdataloading.plugins.configureDatabases
import com.github.pedroluis02.dbdataloading.plugins.configureRouting
import com.github.pedroluis02.dbdataloading.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.jetty.jakarta.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureDatabases()
    configureRouting()
}
