package de.jgroeneveld.koma.parsing.blockparsers

import de.jgroeneveld.koma.parsing.ParsedRecipe

class StepsBlockParser : NamedBlockParser(headingText = "Zubereitung") {
    override fun processFullBlock(recipe: ParsedRecipe, fullBlock: String): ParsedRecipe {
        return recipe.copy(steps = fullBlock)
    }
}