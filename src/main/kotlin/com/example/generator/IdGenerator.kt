package com.example.generator

import java.util.*

object IdGenerator {
    fun randomID(): String {
        return UUID.randomUUID().toString().replace("-", "")
    }
}