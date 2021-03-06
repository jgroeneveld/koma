package de.jgroeneveld.koma.parsing.ingredientparsers

import de.jgroeneveld.koma.values.Ingredient
import de.jgroeneveld.koma.values.Quantity
import de.jgroeneveld.koma.values.QuantityUnit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class IngredientParserTest {
    val parser = IngredientParser()

    @Test
    fun `parse Liter`() {
        val line = "51L Wasser"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.quantity.amount).isEqualTo(51F)
        Assertions.assertThat(ingredient.quantity.unit).isEqualTo(QuantityUnit.L)
        Assertions.assertThat(ingredient.name).isEqualTo("Wasser")
    }

    @Test
    fun `parse Ml`() {
        val line = "50ml Water"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.quantity.amount).isEqualTo(50F)
        Assertions.assertThat(ingredient.quantity.unit).isEqualTo(QuantityUnit.Ml)
        Assertions.assertThat(ingredient.name).isEqualTo("Water")
    }

    @Test
    fun `parse Grams`() {
        val line = "50g Mehl"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.quantity.amount).isEqualTo(50F)
        Assertions.assertThat(ingredient.quantity.unit).isEqualTo(QuantityUnit.G)
        Assertions.assertThat(ingredient.name).isEqualTo("Mehl")
    }

    @Test
    fun `parse Kilograms`() {
        val line = "1 Kg Mehl"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.quantity.amount).isEqualTo(1F)
        Assertions.assertThat(ingredient.quantity.unit).isEqualTo(QuantityUnit.Kg)
        Assertions.assertThat(ingredient.name).isEqualTo("Mehl")
    }

    @Test
    fun `parse Teaspoons`() {
        val line = "2 Tl Hefe"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.quantity.amount).isEqualTo(2F)
        Assertions.assertThat(ingredient.quantity.unit).isEqualTo(QuantityUnit.Tsp)
        Assertions.assertThat(ingredient.name).isEqualTo("Hefe")
    }

    @Test
    fun `parse half teaspoons`() {
        val line = "1/2 Tl Hefe"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.quantity.amount).isEqualTo(0.5F)
        Assertions.assertThat(ingredient.quantity.unit).isEqualTo(QuantityUnit.Tsp)
        Assertions.assertThat(ingredient.name).isEqualTo("Hefe")
    }

    @Test
    fun `parse tablespoons`() {
        val line = "3El Zucker"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.quantity.amount).isEqualTo(3F)
        Assertions.assertThat(ingredient.quantity.unit).isEqualTo(QuantityUnit.Tbsp)
        Assertions.assertThat(ingredient.name).isEqualTo("Zucker")
    }

    @Test
    fun `pars pieces`() {
        val line = "3 Äpfel"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.quantity.amount).isEqualTo(3F)
        Assertions.assertThat(ingredient.quantity.unit).isEqualTo(QuantityUnit.Pieces)
        Assertions.assertThat(ingredient.name).isEqualTo("Äpfel")
    }

    @Test
    fun `parse with missing quantity`() {
        val line = "Zucker"

        val ingredient = parser.parse(line)

        Assertions.assertThat(ingredient.quantity.amount).isEqualTo(0F)
        Assertions.assertThat(ingredient.quantity.unit).isEqualTo(QuantityUnit.Unknown)
        Assertions.assertThat(ingredient.name).isEqualTo("Zucker")
    }

}