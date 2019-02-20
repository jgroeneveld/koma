package de.jgroeneveld.koma.parsing.ingredientparsers

import de.jgroeneveld.koma.recipes.entity.Quantity
import org.assertj.core.api.Assertions
import org.junit.Test

class IngredientParserTest {
    val parser = IngredientParser()

    @Test
    fun parseLiter() {
        val line = "51L Wasser"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.Amount).isEqualTo(51F)
        Assertions.assertThat(ingredient.quantity).isEqualTo(Quantity.L)
        Assertions.assertThat(ingredient.name).isEqualTo("Wasser")
    }

    @Test
    fun parseMl() {
        val line = "50ml Water"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.Amount).isEqualTo(50F)
        Assertions.assertThat(ingredient.quantity).isEqualTo(Quantity.Ml)
        Assertions.assertThat(ingredient.name).isEqualTo("Water")
    }

    @Test
    fun parseGrams() {
        val line = "50g Mehl"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.Amount).isEqualTo(50F)
        Assertions.assertThat(ingredient.quantity).isEqualTo(Quantity.G)
        Assertions.assertThat(ingredient.name).isEqualTo("Mehl")
    }

    @Test
    fun parseKilograms() {
        val line = "1 Kg Mehl"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.Amount).isEqualTo(1F)
        Assertions.assertThat(ingredient.quantity).isEqualTo(Quantity.Kg)
        Assertions.assertThat(ingredient.name).isEqualTo("Mehl")
    }

    @Test
    fun parseTeaspoons() {
        val line = "2 Tl Hefe"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.Amount).isEqualTo(2F)
        Assertions.assertThat(ingredient.quantity).isEqualTo(Quantity.Tsp)
        Assertions.assertThat(ingredient.name).isEqualTo("Hefe")
    }

    @Test
    fun parseHalfTeaspoons() {
        val line = "1/2 Tl Hefe"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.Amount).isEqualTo(0.5F)
        Assertions.assertThat(ingredient.quantity).isEqualTo(Quantity.Tsp)
        Assertions.assertThat(ingredient.name).isEqualTo("Hefe")
    }

    @Test
    fun parseTablespoons() {
        val line = "3El Zucker"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.Amount).isEqualTo(3F)
        Assertions.assertThat(ingredient.quantity).isEqualTo(Quantity.Tbsp)
        Assertions.assertThat(ingredient.name).isEqualTo("Zucker")
    }

    @Test
    fun parsPieces() {
        val line = "3 Äpfel"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.Amount).isEqualTo(3F)
        Assertions.assertThat(ingredient.quantity).isEqualTo(Quantity.Pieces)
        Assertions.assertThat(ingredient.name).isEqualTo("Äpfel")
    }

    @Test
    fun parseWithMissingQuantity() {
        val line = "Zucker"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.Amount).isNull()
        Assertions.assertThat(ingredient.quantity).isEqualTo(Quantity.Pieces)
        Assertions.assertThat(ingredient.name).isEqualTo("Zucker")
    }

}