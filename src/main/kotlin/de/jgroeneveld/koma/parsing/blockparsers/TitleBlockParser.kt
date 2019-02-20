package de.jgroeneveld.koma.parsing.blockparsers

import com.vladsch.flexmark.ast.Heading
import com.vladsch.flexmark.util.ast.Node
import de.jgroeneveld.koma.parsing.ParsedRecipe

class TitleBlockParser : BlockParser() {
    override fun startParsing(node: Node): Boolean {
        return node is Heading && node.level == 1
    }

    override fun processStartNode(recipe: ParsedRecipe, node: Node): ParsedRecipe {
        return recipe.copy(title = node.childChars.toString())
    }

    override fun processFullBlock(recipe: ParsedRecipe, fullBlock: String): ParsedRecipe {
        return recipe.copy(description = fullBlock)
    }
}