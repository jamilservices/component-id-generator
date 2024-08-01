/*-
 * #%L
 * component-id-generator
 * %%
 * Copyright (C) 2024 Jamil Services
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */
package com.jamilservices

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.io.File
import java.util.UUID
/**
 * Class responsible for generating unique IDs for components from an input JSON file.
 */
class ComponentIdGenerator {

    companion object {
        /**
         * Main method for running the class.
         *
         * @param args array of strings containing the input arguments:
         *              - args[0]: path to the input JSON file
         *              - args[1]: path to the output JSON file
         */
        @JvmStatic
        fun main(args: Array<String>) {
            if (args.size < 2) {
                println("Usage: ComponentIdGenerator <inputJsonFile> <outputJsonFile>")
                return
            }
            /**
             * Path to the input JSON file.
             */
            val inputJsonFile = args[0]
            /**
             * Path to the output JSON file.
             */
            val outputJsonFile = args[1]
            /**
             * Array of component names read from the input JSON file.
             */
            val componentNames: JsonArray = Gson().fromJson(File(inputJsonFile).readText(), JsonArray::class.java)
            /**
             * JSON object used to store the generated unique IDs.
             */
            val schema = JsonObject()
            /**
             * Iterates over the component names and generates a unique ID for each one.
             */
            componentNames.forEach { element ->
                /**
                 * Name of the current component.
                 */
                val name = element.asString
                /**
                 * Unique ID generated for the component.
                 */
                val value = UUID.randomUUID().toString()
                /**
                 * Adds the property to the output JSON object.
                 */
                schema.addProperty(name, value)
            }
            /**
             * Writes the output JSON object to the specified file.
             */
            File(outputJsonFile).writeText(Gson().toJson(schema))
        }
    }
}
