package de.jgroeneveld.koma.mealplanning

import de.jgroeneveld.koma.values.Ingredient
import de.jgroeneveld.koma.values.Quantity
import de.jgroeneveld.koma.values.QuantityUnit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class NormalizedIngredientTest {
    @Nested
    inner class Combine {
        @Test
        fun `all given`() {
            val a = NormalizedIngredient("Mehl", NormalizedQuantity(50F, NormalizedQuantityUnit.G))
            val b = NormalizedIngredient("Mehl", NormalizedQuantity(100F, NormalizedQuantityUnit.G))

            val c = a.combine(b)

            Assertions.assertThat(c.quantity.amount).isEqualTo(150F)
            Assertions.assertThat(c.quantity.unit).isEqualTo(NormalizedQuantityUnit.G)
            Assertions.assertThat(c.name).isEqualTo("Mehl")
        }

        @Test
        fun `both unknown amount`() {
            val a = NormalizedIngredient("Mehl", NormalizedQuantity(0F, NormalizedQuantityUnit.Unknown))
            val b = NormalizedIngredient("Mehl", NormalizedQuantity(0F, NormalizedQuantityUnit.Unknown))

            val c = a.combine(b)

            Assertions.assertThat(c.quantity.amount).isEqualTo(0F)
            Assertions.assertThat(c.quantity.unit).isEqualTo(NormalizedQuantityUnit.Unknown)
            Assertions.assertThat(c.name).isEqualTo("Mehl")
        }

        @Test
        fun `differing quantity units`() {
            val a = NormalizedIngredient("Mehl", NormalizedQuantity(50F, NormalizedQuantityUnit.G))
            val b = NormalizedIngredient("Mehl", NormalizedQuantity(0F, NormalizedQuantityUnit.Unknown))

            Assertions.assertThatThrownBy { a.combine(b) }.isInstanceOf(IllegalArgumentException::class.java)
        }

        @Test
        fun `differing names`() {
            val a = NormalizedIngredient("Mehl", NormalizedQuantity(50F, NormalizedQuantityUnit.G))
            val b = NormalizedIngredient("Zucker", NormalizedQuantity(100F, NormalizedQuantityUnit.G))

            Assertions.assertThatThrownBy { a.combine(b) }.isInstanceOf(IllegalArgumentException::class.java)
        }
    }

    @Nested
    inner class IsCompatibleWith {
        @Test
        fun `all given`() {
            val a = NormalizedIngredient("Mehl", NormalizedQuantity(50F, NormalizedQuantityUnit.G))
            val b = NormalizedIngredient("Mehl", NormalizedQuantity(100F, NormalizedQuantityUnit.G))

            Assertions.assertThat(a.isCompatibleWith(b)).isTrue()
        }

        @Test
        fun `both unknown amount`() {
            val a = NormalizedIngredient("Mehl", NormalizedQuantity(0F, NormalizedQuantityUnit.Unknown))
            val b = NormalizedIngredient("Mehl", NormalizedQuantity(0F, NormalizedQuantityUnit.Unknown))

            Assertions.assertThat(a.isCompatibleWith(b)).isTrue()
        }

        @Test
        fun `differing quantity units`() {
            val a = NormalizedIngredient("Mehl", NormalizedQuantity(50F, NormalizedQuantityUnit.G))
            val b = NormalizedIngredient("Mehl", NormalizedQuantity(0F, NormalizedQuantityUnit.Unknown))

            Assertions.assertThat(a.isCompatibleWith(b)).isFalse()
        }

        @Test
        fun `differing names`() {
            val a = NormalizedIngredient("Mehl", NormalizedQuantity(50F, NormalizedQuantityUnit.G))
            val b = NormalizedIngredient("Zucker", NormalizedQuantity(100F, NormalizedQuantityUnit.G))

            Assertions.assertThat(a.isCompatibleWith(b)).isFalse()
        }
    }

    @Nested
    inner class NormalizeIngredient {
        @Test
        fun `Grams`() {
            Assertions.assertThat(
                    normalizeIngredient(Ingredient("Mehl", Quantity(50F, QuantityUnit.G)))
            ).isEqualTo(
                    NormalizedIngredient("Mehl", NormalizedQuantity(50F, NormalizedQuantityUnit.G))
            )
        }

        @Test
        fun `Kilograms`() {
            Assertions.assertThat(
                    normalizeIngredient(Ingredient("Mehl", Quantity(1F, QuantityUnit.Kg)))
            ).isEqualTo(
                    NormalizedIngredient("Mehl", NormalizedQuantity(1000F, NormalizedQuantityUnit.G))
            )
        }

        fun `Milliliters`() {
            Assertions.assertThat(
                    normalizeIngredient(Ingredient("Wasser", Quantity(50F, QuantityUnit.Ml)))
            ).isEqualTo(
                    NormalizedIngredient("Wasser", NormalizedQuantity(50F, NormalizedQuantityUnit.Ml))
            )
        }

        @Test
        fun `Liters`() {
            Assertions.assertThat(
                    normalizeIngredient(Ingredient("Wasser", Quantity(1F, QuantityUnit.L)))
            ).isEqualTo(
                    NormalizedIngredient("Wasser", NormalizedQuantity(1000F, NormalizedQuantityUnit.Ml))
            )
        }

        fun `Unknown`() {
            Assertions.assertThat(
                    normalizeIngredient(Ingredient("Wasser", Quantity(0F, QuantityUnit.Unknown)))
            ).isEqualTo(
                    NormalizedIngredient("Wasser", NormalizedQuantity(0F, NormalizedQuantityUnit.Unknown))
            )
        }

        fun `Pieces`() {
            Assertions.assertThat(
                    normalizeIngredient(Ingredient("Eier", Quantity(6F, QuantityUnit.Pieces)))
            ).isEqualTo(
                    NormalizedIngredient("Eier", NormalizedQuantity(6F, NormalizedQuantityUnit.Pieces))
            )
        }
    }

    @Nested
    inner class DenormalizeIngredient {
        @Test
        fun `Grams`() {
            Assertions.assertThat(
                    denormalizeIngredient(NormalizedIngredient("Mehl", NormalizedQuantity(50F, NormalizedQuantityUnit.G)))
            ).isEqualTo(
                    Ingredient("Mehl", Quantity(50F, QuantityUnit.G))
            )
        }

        @Test
        fun `Kilograms`() {
            Assertions.assertThat(
                    denormalizeIngredient(NormalizedIngredient("Mehl", NormalizedQuantity(1500F, NormalizedQuantityUnit.G)))
            ).isEqualTo(
                    Ingredient("Mehl", Quantity(1500F, QuantityUnit.G))
            )
        }

        fun `Milliliters`() {
            Assertions.assertThat(
                    denormalizeIngredient(NormalizedIngredient("Wasser", NormalizedQuantity(50F, NormalizedQuantityUnit.Ml)))
            ).isEqualTo(
                    Ingredient("Wasser", Quantity(50F, QuantityUnit.Ml))
            )
        }

        @Test
        fun `Liters`() {
            Assertions.assertThat(
                    denormalizeIngredient(NormalizedIngredient("Wasser", NormalizedQuantity(1500F, NormalizedQuantityUnit.Ml)))
            ).isEqualTo(
                    Ingredient("Wasser", Quantity(1500F, QuantityUnit.Ml))
            )
        }

        fun `Unknown`() {
            Assertions.assertThat(
                    denormalizeIngredient(NormalizedIngredient("Wasser", NormalizedQuantity(0F, NormalizedQuantityUnit.Unknown)))
            ).isEqualTo(
                    Ingredient("Wasser", Quantity(0F, QuantityUnit.Unknown))
            )
        }

        fun `Pieces`() {
            Assertions.assertThat(
                    denormalizeIngredient(NormalizedIngredient("Eier", NormalizedQuantity(6F, NormalizedQuantityUnit.Pieces)))
            ).isEqualTo(
                    Ingredient("Eier", Quantity(6F, QuantityUnit.Pieces))
            )
        }
    }
}