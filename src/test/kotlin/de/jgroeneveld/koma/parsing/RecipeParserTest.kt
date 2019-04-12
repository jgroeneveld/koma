package de.jgroeneveld.koma.parsing

import de.jgroeneveld.koma.values.Ingredient
import de.jgroeneveld.koma.values.Quantity
import de.jgroeneveld.koma.values.QuantityUnit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource
import java.io.Reader
import java.io.StringReader

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
        Assertions.assertThat(parsedRecipe.ingredientsList).contains(Ingredient("Eier", Quantity(3F, QuantityUnit.Pieces)))
        Assertions.assertThat(parsedRecipe.ingredientsList).contains(Ingredient("Mehl", Quantity(250F, QuantityUnit.G)))
    }

    @Test
    fun `parse File`() {
        val resource = ClassPathResource("recipes/chilli_oil.md")
        val inputStream = resource.inputStream

        val parsedRecipe = RecipeParser().parse(inputStream.reader())

        Assertions.assertThat(parsedRecipe.title).isEqualTo("Mildes Chilliöl")
        Assertions.assertThat(parsedRecipe.description).isEqualTo("Ein aromatisches, mildes Chilliöl. Auch sehr gut zum Dippen.")
        Assertions.assertThat(parsedRecipe.ingredientsList).isEqualTo(listOf(
                Ingredient("neutrales Öl", Quantity(200F, QuantityUnit.Ml)),
                Ingredient("Sesamöl", Quantity(50F, QuantityUnit.Ml)),
                Ingredient("Sternanis", Quantity(4F, QuantityUnit.Pieces)),
                Ingredient("Szechuan Pfefer", Quantity(2F, QuantityUnit.Tsp)),
                Ingredient("Knoblauchzehen", Quantity(2F, QuantityUnit.Pieces)),
                Ingredient("Lorbeer", Quantity(4F, QuantityUnit.Pieces)),
                Ingredient("Chilliflocken", Quantity(3F, QuantityUnit.Tbsp)),
                Ingredient("Salz", Quantity(1 / 2F, QuantityUnit.Tsp))
        ))

        inputStream.close()
    }
}