package de.jgroeneveld.koma.parsing.ingredientparsers

import de.jgroeneveld.koma.values.Ingredient

abstract class LineParser() {
    abstract fun matches(line: String): Boolean

    abstract fun parse(line: String): Ingredient
}