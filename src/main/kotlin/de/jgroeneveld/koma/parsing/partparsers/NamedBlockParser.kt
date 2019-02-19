package de.jgroeneveld.koma.parsing.partparsers

import com.vladsch.flexmark.ast.Heading
import com.vladsch.flexmark.util.ast.Node

// NamedBlockParser looks for a headline and processes until next headline of same or higher level occurs
abstract class NamedBlockParser(val headingText: String) : BlockParser() {
    override fun startParsing(node: Node): Boolean {
        return node is Heading && node.childChars.toString() == headingText
    }
}