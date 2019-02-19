package de.jgroeneveld.koma.parsing.ingredientparsing

import de.jgroeneveld.koma.recipes.entity.Ingredient

abstract class LineParser() {
    abstract fun matches(line: String): Boolean

    abstract fun parse(line: String): Ingredient
}