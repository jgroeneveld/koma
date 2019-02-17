package de.jgroeneveld.koma.parsing

import org.assertj.core.api.Assertions
import org.junit.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.Reader
import java.io.StringReader

@SpringBootTest
class RecipeParserTest {
    val recipeText = "# Brot\n Ein leckeres Brot\n## Zutaten\n- Mehl\n- Eier\n### Optional\n- Hafer\n- Nüsse\n## Zubereitung\n1. Mischen\n2. Backen"

    @Test
    fun testParse() {
        val reader: Reader = StringReader(recipeText)

        val parsedRecipe = RecipeParser().parse(reader)

        Assertions.assertThat(parsedRecipe.title).isEqualTo("Brot")
        Assertions.assertThat(parsedRecipe.descriptionMk).isEqualTo("Ein leckeres Brot")
        Assertions.assertThat(parsedRecipe.ingredientsMk).isEqualTo("- Mehl\n" +
                "- Eier\n" +
                "\n" +
                "### Optional\n" +
                "- Hafer\n" +
                "- Nüsse")
        Assertions.assertThat(parsedRecipe.stepsMk).isEqualTo("1. Mischen\n" +
                "2. Backen")
    }
}