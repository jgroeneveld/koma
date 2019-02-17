package de.jgroeneveld.koma.parsing.partparsers

import com.vladsch.flexmark.util.ast.Node
import de.jgroeneveld.koma.parsing.ParsedRecipe

abstract class PartParser {
    // startParsing checks if the parser is interested in this particular node to start parsing.
    abstract fun startParsing(node: Node): Boolean

    // parse parses the node and iterates until it is finished.
    // Results are written into result
    // Returns the next Node.
    abstract fun parse(result: ParsedRecipe, startNode: Node): Node?
}