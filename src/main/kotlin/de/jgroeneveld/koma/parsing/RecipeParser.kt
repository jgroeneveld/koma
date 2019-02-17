package de.jgroeneveld.koma.parsing

import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.options.MutableDataSet
import de.jgroeneveld.koma.parsing.partparsers.IngredientsParser
import de.jgroeneveld.koma.parsing.partparsers.PartParser
import de.jgroeneveld.koma.parsing.partparsers.StepsParser
import de.jgroeneveld.koma.parsing.partparsers.TitleParser
import java.io.Reader

class RecipeParser {
    fun parse(reader: Reader): ParsedRecipe {
        val parsedRecipe = ParsedRecipe()

        val options = MutableDataSet()
        val parser = Parser.builder(options).build()
        val document = parser.parseReader(reader)

        var node = document.firstChild
        val partParsers: List<PartParser> = listOf(TitleParser(), IngredientsParser(), StepsParser())

        while (node != null) {
            val partParser = partParsers.find { partParser -> partParser.startParsing(node) }
            when (partParser) {
                null -> node = node.next
                else -> node = partParser.parse(parsedRecipe, node)
            }
        }

        return parsedRecipe
    }
}