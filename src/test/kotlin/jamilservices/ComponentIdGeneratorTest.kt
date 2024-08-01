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