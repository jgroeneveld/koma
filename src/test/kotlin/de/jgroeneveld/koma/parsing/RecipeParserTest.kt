package de.jgroeneveld.koma.parsing

import de.jgroeneveld.koma.recipes.entity.Ingredient
import de.jgroeneveld.koma.recipes.entity.Quantity
import org.assertj.core.api.Assertions
import org.junit.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import java.io.Reader
import java.io.StringReader

@SpringBootTest
class RecipeParserTest {
    @Test
    fun parse() {
        val recipeText = "# Brot\n Ein leckeres Brot\n## Zutaten\n- 250g Mehl\n- 3 Eier\n## Zubereitung\n1. Mischen\n2. Backen"

        val reader: Reader = StringReader(recipeText)

        val parsedRecipe = RecipeParser().parse(reader)

        Assertions.assertThat(parsedRecipe.title).isEqualTo("Brot")
        Assertions.assertThat(parsedRecipe.description).isEqualTo("Ein leckeres Brot")
        Assertions.assertThat(parsedRecipe.ingredients).isEqualTo("- 250g Mehl\n" +
                "- 3 Eier")
        Assertions.assertThat(parsedRecipe.steps).isEqualTo("1. Mischen\n" +
                "2. Backen")

        Assertions.assertThat(parsedRecipe.ingredientsList).hasSize(2)
        Assertions.assertThat(parsedRecipe.ingredientsList).contains(Ingredient(250F, Quantity.G, "Mehl"))
        Assertions.assertThat(parsedRecipe.ingredientsList).contains(Ingredient(3F, Quantity.Pieces, "Eier"))
    }

    @Test
    fun parseFile() {
        val resource = ClassPathResource("chilli_oil.md")
        val inputStream = resource.inputStream

        val parsedRecipe = RecipeParser().parse(inputStream.reader())

        Assertions.assertThat(parsedRecipe.title).isEqualTo("Mildes Chilliöl")
        Assertions.assertThat(parsedRecipe.description).isEqualTo("Ein aromatisches, mildes Chilliöl. Auch sehr gut zum Dippen.")
        Assertions.assertThat(parsedRecipe.ingredientsList).isEqualTo(listOf(
                Ingredient(200F, Quantity.Ml, "neutrales Öl"),
                Ingredient(50F, Quantity.Ml, "Sesamöl"),
                Ingredient(4F, Quantity.Pieces, "Sternanis"),
                Ingredient(2F, Quantity.Tsp, "Szechuan Pfefer"),
                Ingredient(2F, Quantity.Pieces, "Knoblauchzehen"),
                Ingredient(4F, Quantity.Pieces, "Lorbeer"),
                Ingredient(3F, Quantity.Tbsp, "Chilliflocken"),
                Ingredient(1 / 2F, Quantity.Tsp, "Salz")
        ))

        inputStream.close()
    }
}