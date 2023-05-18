package com.example.dao.campo

import com.example.models.Campo

interface DaoFacadeCampo {
    suspend fun allCampos(): List<Campo>
    suspend fun campo(id: String): Campo?
    suspend fun addNewCampo(value: String, name: String, descripcion: String,sectionId:String, order :Int ): Campo?
    suspend fun editCampo(id: String,value: String, name: String, descripcion: String,
                          sectionId:String, order:Int ): Boolean
    suspend fun deleteCampo(id: String): Boolean
}