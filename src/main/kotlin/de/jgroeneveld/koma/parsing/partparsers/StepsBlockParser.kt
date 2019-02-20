package de.jgroeneveld.koma.parsing.partparsers

import de.jgroeneveld.koma.parsing.ParsedRecipe

class StepsBlockParser : NamedBlockParser(headingText = "Zubereitung") {
    override fun processFullBlock(result: ParsedRecipe, fullBlock: String): ParsedRecipe {
        return result.copy(steps = fullBlock)
    }
}