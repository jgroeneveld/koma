package de.jgroeneveld.koma.parsing

import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.options.MutableDataSet
import de.jgroeneveld.koma.parsing.partparsers.IngredientsBlockParser
import de.jgroeneveld.koma.parsing.partparsers.BlockParser
import de.jgroeneveld.koma.parsing.partparsers.StepsBlockParser
import de.jgroeneveld.koma.parsing.partparsers.TitleBlockParser
import java.io.Reader

class RecipeParser {
    fun parse(reader: Reader): ParsedRecipe {
        var parsedRecipe = ParsedRecipe()

        val options = MutableDataSet()
        val parser = Parser.builder(options).build()
        val document = parser.parseReader(reader)

        var node = document.firstChild
        val blockParsers: List<BlockParser> = listOf(TitleBlockParser(), IngredientsBlockParser(), StepsBlockParser())

        while (node != null) {
            val blockParser = blockParsers.find { it.startParsing(node) }

            if (blockParser == null) {
                node = node.next
            } else {
                val pair = blockParser.parse(parsedRecipe, node)
                parsedRecipe = pair.first
                node = pair.second
            }
        }

        return parsedRecipe
    }
}