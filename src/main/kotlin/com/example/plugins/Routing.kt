package com.example.plugins

import com.example.dao.articulo.daoArt
import com.example.dao.campo.daoCampo
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Application.configureRouting() {
    routing {
        static("/static") {
            resources("files")
        }
        get("/") {
            // redirige todos las request a el path /articles
            call.respondRedirect("articles")
        }

        route("articles") {
            get {
                // muestra todos los articulos guardados en la base de datos
                call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to daoArt.allArticles())))
            }
            get("new") {
                // llama al modelo new.ftl para crear un nuevo elemento en el dirio
                call.respond(FreeMarkerContent("new.ftl", model = null))
            }
            post {
                // Recive el titulo y el nombre de la entrada al diario y lo añade al diario
                val formParameters = call.receiveParameters()
                val title = formParameters.getOrFail("title")
                val body = formParameters.getOrFail("body")
                val article = daoArt.addNewArticle(title, body)
                call.respondRedirect("/articles/${article?.id}")
            }
            get("{id}") {
                // abrir un artículo en específico de los guardados
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("show.ftl", mapOf("article" to daoArt.article(id))))
            }
            get("{id}/edit") {
                // editar un articulo de los guardados
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("edit.ftl", mapOf("article" to daoArt.article(id))))
            }
            post("{id}") {
                // permite eliminar o actulizar un archivo
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val formParameters = call.receiveParameters()
                when (formParameters.getOrFail("_action")) {
                    "update" -> {
                        val title = formParameters.getOrFail("title")
                        val body = formParameters.getOrFail("body")
                        daoArt.editArticle(id, title, body)
                        call.respondRedirect("/articles/$id")
                    }
                    "delete" -> {
                        daoArt.deleteArticle(id)
                        call.respondRedirect("/articles")
                    }
                }
            }
        }

    }
}

fun Application.configureRoutingCampo() {
    routing {
        static("/static") {
            resources("files")
        }
        get("/") {
            // redirige todos las request a el path /articles
            call.respondRedirect("articles")
        }
        route("campos") {
            get {
                // muestra todos los articulos guardados en la base de datos
                call.respond(FreeMarkerContent("indexCampo.ftl", mapOf("campos" to daoCampo.allCampos())))
            }
            get("new") {
                // llama al modelo new.ftl para crear un nuevo elemento en el dirio
                call.respond(FreeMarkerContent("newCampo.ftl", model = null))
            }
            post {
                // Recive el titulo y el nombre de la entrada al diario y lo añade al diario
                val formParameters = call.receiveParameters()
                val name = formParameters.getOrFail("name")
                val value = formParameters.getOrFail("value")
                val descripcion = formParameters.getOrFail("descripcion")
                val sectionId = formParameters.getOrFail("sectionId")
                val order = formParameters.getOrFail("order").toInt()
                val campo = daoCampo.addNewCampo(name, value,descripcion,sectionId,order)
                call.respondRedirect("/campos/${campo?.id}")
            }
            get("/{id}") {
                val id = call.parameters.getOrFail<String>("id").toString()
                val campo = daoCampo.campo(id)
                if (campo != null) {
                    call.respond(FreeMarkerContent("showCampo.ftl", mapOf("campo" to campo), ""))
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
            get("/{id}/edit") {
                val id = call.parameters.getOrFail<String>("id").toString()
                val campo = daoCampo.campo(id)
                if (campo != null) {
                    call.respond(FreeMarkerContent("editCampo.ftl", mapOf("campo" to campo), ""))
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
            post("/{id}") {
                val id = call.parameters.getOrFail<String>("id").toString()
                val formParameters = call.receiveParameters()
                println("tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt")
                val actionCampo = formParameters["_actionCampo"]
                println(actionCampo)
                if (actionCampo != null) {
                    when (actionCampo) {
                        "update" -> {
                            val name = formParameters.getOrFail("name")
                            val value = formParameters.getOrFail("value")
                            val descripcion = formParameters.getOrFail("descripcion")
                            val sectionId = formParameters.getOrFail("sectionId")
                            val order = formParameters.getOrFail("order").toInt()
                            println("Updating campo: id=$id, name=$name, value=$value, descripcion=$descripcion, sectionId=$sectionId, order=$order")
                            daoCampo.editCampo(id, name, value, descripcion, sectionId, order)
                            println("Campo after update: ${daoCampo.campo(id)}")
                            call.respondRedirect("/campos/$id")
                        }
                        "delete" -> {
                            println("Deleting campo: id=$id")
                            daoCampo.deleteCampo(id)
                            call.respondRedirect("/campos")
                        }
                        else -> {
                            call.respond(HttpStatusCode.BadRequest, "Invalid action")
                        }
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Missing action")
                }
            }

        }
    }
}