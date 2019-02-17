package de.jgroeneveld.koma.parsing.partparsers

import com.vladsch.flexmark.util.ast.Node
import de.jgroeneveld.koma.parsing.ParsedRecipe

class IngredientsBlockParser : BlockParser(headingLevel = 2, headingText = "Zutaten") {
    override fun processLine(result: ParsedRecipe, node: Node) {
        // nothing
    }

    override fun processFullBlock(result: ParsedRecipe, fullBlock: String) {
        result.ingredientsMd = fullBlock
    }
}