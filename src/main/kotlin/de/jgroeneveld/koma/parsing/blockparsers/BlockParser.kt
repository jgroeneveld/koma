package de.jgroeneveld.koma.parsing.blockparsers

import com.vladsch.flexmark.ast.Heading
import com.vladsch.flexmark.util.ast.Node
import de.jgroeneveld.koma.parsing.ParsedRecipe

abstract class BlockParser {
    val PART_HEADING_LEVEL = 2

    // startParsing checks if the parser is interested in this particular node to start parsing.
    abstract fun startParsing(node: Node): Boolean

    // parse parses the node and iterates until it finds a headline with PART_HEADING_LEVEL
    // Returns the next finished data and next Node.
    fun parse(recipe: ParsedRecipe, startNode: Node): Pair<ParsedRecipe, Node?> {
        var resultRecipe = processStartNode(recipe, startNode)

        var node: Node? = startNode.next
        var blockText = ""

        while (node != null && !(node is Heading && node.level <= PART_HEADING_LEVEL)) {
            blockText += "${node.chars}\n"
            resultRecipe = processChildNode(resultRecipe, node)
            node = node.next
        }

        resultRecipe = processFullBlock(resultRecipe, blockText.trim())

        return Pair(resultRecipe, node)
    }

    open fun processStartNode(recipe: ParsedRecipe, node: Node): ParsedRecipe {
        return recipe
    }

    open fun processChildNode(recipe: ParsedRecipe, node: Node): ParsedRecipe {
        return recipe
    }

    open fun processFullBlock(recipe: ParsedRecipe, fullBlock: String): ParsedRecipe {
        return recipe
    }
}