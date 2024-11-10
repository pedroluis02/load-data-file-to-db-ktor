package com.github.pedroluis02.dbdataloading.plugins

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.utils.io.*
import kotlinx.io.readByteArray
import java.io.File

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }

        post("/api/v1/customer") {
            val multiPart = call.receiveMultipart()
            val part = multiPart.readPart()
            if (part is PartData.FileItem) {
                val name = part.originalFileName as String
                File("${System.currentTimeMillis()}-$name")
                    .writeBytes(part.provider().readRemaining().readByteArray())
                part.dispose()
            }

            call.respond(HttpStatusCode.OK)
        }
    }
}
