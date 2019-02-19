package de.jgroeneveld.koma.parsing.partparsers

import com.vladsch.flexmark.ast.Heading
import com.vladsch.flexmark.util.ast.Node
import de.jgroeneveld.koma.parsing.ParsedRecipe

class TitleParser : PartParser() {
    override fun startParsing(node: Node): Boolean {
        return node is Heading && node.level == 1
    }

    override fun parse(result: ParsedRecipe, startNode: Node): Pair<ParsedRecipe, Node?> {
        val title = startNode.childChars.toString()

        var descriptionMd = ""
        var node: Node? = startNode.next
        while (node != null && !(node is Heading && node.level <= 2)) {
            descriptionMd += "${node.chars}\n"
            node = node.next
        }
        descriptionMd = descriptionMd.trim()

        val recipe = result.copy(
                title = title,
                descriptionMd = descriptionMd

        )

        return Pair(recipe, node)
    }
}