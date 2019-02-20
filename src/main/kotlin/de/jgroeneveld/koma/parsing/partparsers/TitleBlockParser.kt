package de.jgroeneveld.koma.parsing.partparsers

import com.vladsch.flexmark.ast.Heading
import com.vladsch.flexmark.util.ast.Node
import de.jgroeneveld.koma.parsing.ParsedRecipe

class TitleBlockParser : BlockParser() {
    override fun startParsing(node: Node): Boolean {
        return node is Heading && node.level == 1
    }

    override fun processStartNode(result: ParsedRecipe, node: Node): ParsedRecipe {
        return result.copy(title = node.childChars.toString())
    }

    override fun processFullBlock(result: ParsedRecipe, fullBlock: String): ParsedRecipe {
        return result.copy(description = fullBlock)
    }
}