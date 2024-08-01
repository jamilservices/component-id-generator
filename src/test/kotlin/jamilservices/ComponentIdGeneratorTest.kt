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
import com.jamilservices.ComponentIdGenerator
import org.junit.Test
import kotlin.test.assertEquals


import com.google.gson.JsonObject
import java.io.File
import java.util.UUID

import com.google.gson.Gson

class ComponentIdGeneratorTest {

    @Test
    fun `tthis generation of IDs for components`() {

        val inputFile = File("input.json")
        inputFile.writeText("[\"component1\", \"component2\", \"component3\"]")

        val outputFile = File("output.json")

        ComponentIdGenerator.main(arrayOf("input.json", "output.json"))

        val outputJson = outputFile.readText()
        val gson = Gson()
        val outputObject: JsonObject = gson.fromJson(outputJson, JsonObject::class.java)

        assertEquals(3, outputObject.size())
        assertEquals(true, outputObject.has("component1"))
        assertEquals(true, outputObject.has("component2"))
        assertEquals(true, outputObject.has("component3"))

        val component1Id = outputObject.get("component1").asString
        val component2Id = outputObject.get("component2").asString
        val component3Id = outputObject.get("component3").asString
        assertEquals(true, UUID.fromString(component1Id)!= null)
        assertEquals(true, UUID.fromString(component2Id)!= null)
        assertEquals(true, UUID.fromString(component3Id)!= null)

        inputFile.delete()
        outputFile.delete()
    }
}
