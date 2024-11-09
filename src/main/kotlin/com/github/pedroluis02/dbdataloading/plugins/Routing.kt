package com.github.pedroluis02.dbdataloading.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}
