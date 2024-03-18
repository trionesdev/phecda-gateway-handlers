import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test

class JacksonTest {

    @Test
    fun readBytes(){
        val ss = "aas"
        println(ss)
        val objectMapper = ObjectMapper()
        println(objectMapper.writeValueAsString(ss))
        println(objectMapper.writeValueAsString(1.2))
    }

}