package de.jgroeneveld.koma

import de.jgroeneveld.koma.parsing.RecipeParser
import org.junit.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RecipeParserTest {
    @Test
    fun testZeug() {
        RecipeParser().parse()
    }
}