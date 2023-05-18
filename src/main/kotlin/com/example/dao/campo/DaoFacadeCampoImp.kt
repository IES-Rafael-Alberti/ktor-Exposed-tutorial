package com.example.dao.campo


import com.example.models.Campo
import com.example.dao.DatabaseFactory.dbQuery
import com.example.generator.IdGenerator
import com.example.models.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DaoFacadeCampoImp : DaoFacadeCampo {

    private fun resultRowToCampo(row: ResultRow) = Campo(
        id = row[Campos.id],
        value = row[Campos.value],
        name = row[Campos.name],
        descripcion = row[Campos.descripcion],
        sectionId = row[Campos.sectionId],
        order = row[Campos.order]
    )

    override suspend fun allCampos(): List<Campo> = dbQuery {
        Campos.selectAll().map(::resultRowToCampo)
    }
    override suspend fun campo(id: String): Campo? = dbQuery {
        Campos
            .select { Campos.id eq id }
            .map(::resultRowToCampo)
            .singleOrNull()
    }

    override suspend fun addNewCampo(value: String, name: String, descripcion: String,sectionId:String, order :Int ): Campo? = dbQuery {
        val insertStatement = Campos.insert {
            it[id] = IdGenerator.randomID()
            it[Campos.value] = value
            it[Campos.name] = name
            it[Campos.descripcion] = descripcion
            it[Campos.sectionId] = sectionId
            it[Campos.order] = order
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToCampo)
    }

    override suspend fun editCampo(id: String,value: String, name: String, descripcion: String, sectionId:String, order :Int ) = dbQuery {
        Campos.update({ Campos.id eq id }) {
            it[Campos.value] = value
            it[Campos.name] = name
            it[Campos.descripcion] = descripcion
            it[Campos.sectionId] = sectionId
            it[Campos.order] = order
        } > 0
    }

    override suspend fun deleteCampo(id: String): Boolean = dbQuery {
        Campos.deleteWhere { Campos.id eq id } > 0
    }

}

val daoCampo: DaoFacadeCampo = DaoFacadeCampoImp().apply {
    runBlocking {
        if(allCampos().isEmpty()) {
            addNewCampo("The drive to develop!", "...it's what keeps me going.","Even when i am tired  ","1",0)
        }
    }
}