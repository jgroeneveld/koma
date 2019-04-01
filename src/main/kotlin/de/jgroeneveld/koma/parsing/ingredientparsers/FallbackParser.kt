package de.jgroeneveld.koma.parsing.ingredientparsers

import de.jgroeneveld.koma.recipes.entity.Ingredient
import de.jgroeneveld.koma.recipes.entity.Quantity

class FallbackParser(): LineParser() {
    override fun matches(line: String) = true

    override fun parse(line: String) = Ingredient(null, Quantity.Pieces, line)

}