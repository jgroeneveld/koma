package de.jgroeneveld.koma.parsing.partparsers

import com.vladsch.flexmark.util.ast.Node
import de.jgroeneveld.koma.parsing.ParsedRecipe

class StepsBlockParser : BlockParser(headingLevel = 2, headingText = "Zubereitung") {
    override fun processLine(result: ParsedRecipe, node: Node): ParsedRecipe {
        return result
    }

    override fun processFullBlock(result: ParsedRecipe, fullBlock: String): ParsedRecipe {
        return result.copy(stepsMd = fullBlock)
    }
}