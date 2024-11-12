package com.github.pedroluis02.dbdataloading.plugins

import com.github.pedroluis02.dbdataloading.domain.DataLoadingRepository
import com.github.pedroluis02.dbdataloading.repository.NativeDataLoadingRepository
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
                val file = File("${System.currentTimeMillis()}-$name").apply {
                    writeBytes(part.provider().readRemaining().readByteArray())
                }
                part.dispose()

                val repo: DataLoadingRepository = NativeDataLoadingRepository()
                repo.load("load-data", file.path)
            }

            call.respond(HttpStatusCode.OK)
        }
    }
}
