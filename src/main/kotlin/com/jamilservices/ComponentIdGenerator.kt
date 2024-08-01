package com.jamilservices

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.io.File
import java.util.UUID

class ComponentIdGenerator {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            if (args.size < 2) {
                println("Usage: ComponentIdGenerator <inputJsonFile> <outputJsonFile>")
                return
            }

            val inputJsonFile = args[0]
            val outputJsonFile = args[1]

            val componentNames: JsonArray = Gson().fromJson(File(inputJsonFile).readText(), JsonArray::class.java)
            val schema = JsonObject()

            componentNames.forEach { element ->
                val name = element.asString
                val value = UUID.randomUUID().toString()
                schema.addProperty(name, value)
            }

            File(outputJsonFile).writeText(Gson().toJson(schema))
        }
    }
}