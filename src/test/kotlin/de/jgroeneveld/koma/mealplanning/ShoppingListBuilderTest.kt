package de.jgroeneveld.koma.mealplanning

import de.jgroeneveld.koma.recipes.entity.Ingredient
import de.jgroeneveld.koma.recipes.entity.Quantity
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ShoppingListBuilderTest {
    val builder = ShoppingListBuilder()

    @Test
    fun `adding one ingredient`() {
        builder.add(Ingredient(50F, Quantity.G, "Mehl"))
        val shoppingList = builder.build()

        Assertions.assertThat(shoppingList.ingredients).hasSize(1)
    }

    @Test
    fun `adding two different ingredients`() {
    }

    @Test
    fun `adding same ingredient twice`() {
    }

    @Test
    fun `adding same named ingredient with different quantity units`() {
    }

    @Test
    fun `adding strangely named ingredients??`() {
    }
}