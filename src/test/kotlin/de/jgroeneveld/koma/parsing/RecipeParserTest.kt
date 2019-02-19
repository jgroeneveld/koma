package de.jgroeneveld.koma.parsing

import de.jgroeneveld.koma.recipes.entity.Ingredient
import de.jgroeneveld.koma.recipes.entity.Quantity
import org.assertj.core.api.Assertions
import org.junit.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.Reader
import java.io.StringReader

@SpringBootTest
class RecipeParserTest {
    val recipeText = "# Brot\n Ein leckeres Brot\n## Zutaten\n- 250g Mehl\n- 3 Eier\n## Zubereitung\n1. Mischen\n2. Backen"

    @Test
    fun parse() {
        val reader: Reader = StringReader(recipeText)

        val parsedRecipe = RecipeParser().parse(reader)

        Assertions.assertThat(parsedRecipe.title).isEqualTo("Brot")
        Assertions.assertThat(parsedRecipe.descriptionMd).isEqualTo("Ein leckeres Brot")
        Assertions.assertThat(parsedRecipe.ingredientsMd).isEqualTo("- 250g Mehl\n" +
                "- 3 Eier")
        Assertions.assertThat(parsedRecipe.stepsMd).isEqualTo("1. Mischen\n" +
                "2. Backen")

        Assertions.assertThat(parsedRecipe.ingredients).hasSize(2)
        Assertions.assertThat(parsedRecipe.ingredients).contains(Ingredient(250F, Quantity.G, "Mehl"))
        Assertions.assertThat(parsedRecipe.ingredients).contains(Ingredient(3F, Quantity.Pieces, "Eier"))
    }
}