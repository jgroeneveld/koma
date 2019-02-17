package de.jgroeneveld.koma.parsing.partparsers

import com.vladsch.flexmark.ast.Heading
import com.vladsch.flexmark.util.ast.Node
import de.jgroeneveld.koma.parsing.ParsedRecipe

class TitleParser : PartParser() {
    override fun startParsing(node: Node): Boolean {
        return node is Heading && node.level == 1
    }

    override fun parse(result: ParsedRecipe, startNode: Node): Node? {
        result.title = startNode.childChars.toString()

        var node: Node? = startNode.next
        while (node != null && !(node is Heading && node.level <= 2)) {
            result.descriptionMk += "${node.chars}\n"
            node = node.next
        }

        return node
    }
}