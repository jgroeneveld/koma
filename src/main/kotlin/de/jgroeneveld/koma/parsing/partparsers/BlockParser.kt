package de.jgroeneveld.koma.parsing.partparsers

import com.vladsch.flexmark.ast.Heading
import com.vladsch.flexmark.util.ast.Node
import de.jgroeneveld.koma.parsing.ParsedRecipe

// BlockParser looks for a headline and processes until next headline of same or higher level occurs
abstract class BlockParser(val headingLevel: Int, val headingText: String) : PartParser() {
    override fun startParsing(node: Node): Boolean {
        return node is Heading && node.level == headingLevel && node.childChars.toString() == headingText
    }

    override fun parse(result: ParsedRecipe, startNode: Node): Pair<ParsedRecipe, Node?> {
        var recipe = result

        var node: Node? = startNode.next
        var blockText = ""

        while (node != null && !(node is Heading && node.level <= headingLevel)) {
            blockText += "${node.chars}\n"
            recipe = processLine(recipe, node)
            node = node.next
        }

        recipe = processFullBlock(recipe, blockText.trim())
        return Pair(recipe, node)
    }

    abstract fun processLine(result: ParsedRecipe, node: Node): ParsedRecipe
    abstract fun processFullBlock(result: ParsedRecipe, fullBlock: String): ParsedRecipe
}