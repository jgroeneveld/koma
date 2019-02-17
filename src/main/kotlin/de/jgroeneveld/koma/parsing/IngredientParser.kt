package de.jgroeneveld.koma.parsing

import de.jgroeneveld.koma.recipes.entity.Ingredient
import de.jgroeneveld.koma.recipes.entity.Quantity

abstract class UnitIngredientParser(val unit: String, val quantity: Quantity) {
    val regex = Regex("""(\d+|\d\/\d)\s*$unit\s+(.*)""", RegexOption.IGNORE_CASE)

    fun matches(line: String): Boolean {
        return regex.matches(line)
    }

    fun parse(line: String): Ingredient {
        val matchResult = regex.matchEntire(line) ?: throw Exception("Does not match")

        val amount = convertAmountToFloat(matchResult.groups[1]!!.value)
        val name = matchResult.groups[2]!!.value

        return Ingredient(amount, quantity, name)
    }

    private fun convertAmountToFloat(amount: String): Float {
        if (amount.contains("/")) {
            val split = amount.split("/")
            return split[0].toFloat() / split[1].toFloat()
        }

        return amount.toFloat()
    }
}

class LiterParser() : UnitIngredientParser("l", Quantity.L)
class MilliliterParser() : UnitIngredientParser("ml", Quantity.Ml)
class KilogramsParser() : UnitIngredientParser("kg", Quantity.Kg)
class GramsParser() : UnitIngredientParser("g", Quantity.G)
class TeaspoonParser() : UnitIngredientParser("Tl", Quantity.Tsp)
class TablespoonParser() : UnitIngredientParser("El", Quantity.Tbsp)

class IngredientParser {
    fun parse(line: String): Ingredient {
        val parsers = listOf(
                LiterParser(),
                MilliliterParser(),
                KilogramsParser(),
                GramsParser(),
                TeaspoonParser(),
                TablespoonParser()
        )

        for (parser in parsers) {
            if (parser.matches(line)) {
                return parser.parse(line)
            }
        }

        throw Exception("No parser found for '$line'")
    }
}
