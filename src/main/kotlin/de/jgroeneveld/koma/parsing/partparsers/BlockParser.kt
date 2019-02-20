package de.jgroeneveld.koma.parsing.partparsers

import com.vladsch.flexmark.ast.Heading
import com.vladsch.flexmark.util.ast.Node
import de.jgroeneveld.koma.parsing.ParsedRecipe

abstract class BlockParser {
    val PART_HEADING_LEVEL = 2

    // startParsing checks if the parser is interested in this particular node to start parsing.
    abstract fun startParsing(node: Node): Boolean

    // parse parses the node and iterates until it finds a headline with PART_HEADING_LEVEL
    // Returns the next finished data and next Node.
    fun parse(result: ParsedRecipe, startNode: Node): Pair<ParsedRecipe, Node?> {
        var recipe = processStartNode(result, startNode)

        var node: Node? = startNode.next
        var blockText = ""

        while (node != null && !(node is Heading && node.level <= PART_HEADING_LEVEL)) {
            blockText += "${node.chars}\n"
            recipe = processChildNode(recipe, node)
            node = node.next
        }

        recipe = processFullBlock(recipe, blockText.trim())

        return Pair(recipe, node)
    }

    open fun processStartNode(result: ParsedRecipe, node: Node): ParsedRecipe {
        return result
    }

    open fun processChildNode(result: ParsedRecipe, node: Node): ParsedRecipe {
        return result
    }

    open fun processFullBlock(result: ParsedRecipe, fullBlock: String): ParsedRecipe {
        return result
    }
}