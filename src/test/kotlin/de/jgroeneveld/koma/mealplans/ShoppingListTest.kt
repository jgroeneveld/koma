package de.jgroeneveld.koma.mealplans

import de.jgroeneveld.koma.mealplans.entity.ShoppingList
import de.jgroeneveld.koma.values.Ingredient
import de.jgroeneveld.koma.values.Quantity
import de.jgroeneveld.koma.values.QuantityUnit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class ShoppingListTest {
    val builder = ShoppingList.Builder()

    @Test
    fun `adding one ingredient`() {
        builder.add(Ingredient("Mehl", Quantity(50F, QuantityUnit.G)))

        val shoppingList = builder.build()

        Assertions.assertThat(shoppingList.ingredients).hasSize(1)
    }

    @Test
    fun `adding two different ingredients`() {
        builder.add(Ingredient("Mehl", Quantity(50F, QuantityUnit.G)))
        builder.add(Ingredient("Zucker", Quantity(100F, QuantityUnit.G)))

        val shoppingList = builder.build()

        Assertions.assertThat(shoppingList.ingredients).hasSize(2)
    }

    @Test
    fun `adding same ingredient twice`() {
        builder.add(Ingredient("Mehl", Quantity(50F, QuantityUnit.G)))
        builder.add(Ingredient("Mehl", Quantity(100F, QuantityUnit.G)))

        val shoppingList = builder.build()

        Assertions.assertThat(shoppingList.ingredients).containsOnly(
                Ingredient("Mehl", Quantity(150F, QuantityUnit.G))
        )
    }

    @Test
    fun `adding same named ingredient with incompatible quantity units`() {
        builder.add(Ingredient("Mehl", Quantity(50F, QuantityUnit.G)))
        builder.add(Ingredient("Mehl", Quantity(0F, QuantityUnit.Unknown)))

        val shoppingList = builder.build()

        Assertions.assertThat(shoppingList.ingredients).containsOnly(
                Ingredient("Mehl", Quantity(50F, QuantityUnit.G)),
                Ingredient("Mehl", Quantity(0F, QuantityUnit.Unknown))
        )
    }

    @Test
    fun `adding same named ingredient with compatible quantity units`() {
        builder.add(Ingredient("Mehl", Quantity(50F, QuantityUnit.G)))
        builder.add(Ingredient("Mehl", Quantity(1F, QuantityUnit.Kg)))

        val shoppingList = builder.build()

        Assertions.assertThat(shoppingList.ingredients).containsOnly(
                Ingredient("Mehl", Quantity(1050F, QuantityUnit.G))
        )
    }

    @Test
    @Disabled("pending")
    fun `adding strangely named ingredients??`() {
        // "etwas mehl"
        // "1 EL Mehl" + "500g Mehl" -> actually have a lookup table volume to weight for specific ingredients?
    }
}