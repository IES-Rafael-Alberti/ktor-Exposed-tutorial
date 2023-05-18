package com.example.models


import com.example.generator.IdGenerator
import com.example.models.Campos.autoIncrement
import com.example.models.Campos.uniqueIndex
import org.jetbrains.exposed.sql.*
import java.util.UUID


data class Campo(val id: String, val value: String, val name: String,
                 val descripcion: String, val sectionId:String, val order: Int)

object Campos : Table() {
    val id = varchar("id", 32).default(IdGenerator.randomID())
    val value = varchar("value", 1024)
    val name = varchar("name", 128)
    val descripcion = varchar("descripcion", 256)
    val sectionId = varchar("section", 32)
    val order = integer("order")

    override val primaryKey = PrimaryKey(id)
}

