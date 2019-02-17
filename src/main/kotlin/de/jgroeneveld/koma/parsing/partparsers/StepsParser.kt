package de.jgroeneveld.koma.parsing.partparsers

import com.vladsch.flexmark.util.ast.Node
import de.jgroeneveld.koma.parsing.ParsedRecipe

class StepsParser : BlockParser(headingLevel = 2, headingText = "Zubereitung") {
    override fun processLine(result: ParsedRecipe, node: Node) {
        // nothing
    }

    override fun processFullBlock(result: ParsedRecipe, fullBlock: String) {
        result.stepsMk = fullBlock
    }
}