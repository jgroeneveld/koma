package de.jgroeneveld.koma.parsing.ingredientparsers

import de.jgroeneveld.koma.values.Ingredient
import de.jgroeneveld.koma.values.Quantity
import de.jgroeneveld.koma.values.QuantityUnit

class UnitLineParser(val unit: String, val quantity: QuantityUnit): LineParser() {
    val regex = Regex("""(\d+|\d\/\d)\s*$unit\s+(.*)""", RegexOption.IGNORE_CASE)

    override fun matches(line: String): Boolean {
        return regex.matches(line)
    }

    override fun parse(line: String): Ingredient {
        val matchResult = regex.matchEntire(line) ?: throw Exception("Does not match")

        val amount = convertAmountToFloat(matchResult.groups[1]!!.value)
        val name = matchResult.groups[2]!!.value

        return Ingredient(name = name, quantity = Quantity(amount, quantity))
    }

    private fun convertAmountToFloat(amount: String): Float {
        if (amount.contains("/")) {
            val split = amount.split("/")
            return split[0].toFloat() / split[1].toFloat()
        }

        return amount.toFloat()
    }
}