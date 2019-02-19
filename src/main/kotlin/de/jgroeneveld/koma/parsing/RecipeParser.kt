package de.jgroeneveld.koma.parsing

import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.options.MutableDataSet
import de.jgroeneveld.koma.parsing.partparsers.IngredientsBlockParser
import de.jgroeneveld.koma.parsing.partparsers.PartParser
import de.jgroeneveld.koma.parsing.partparsers.StepsBlockParser
import de.jgroeneveld.koma.parsing.partparsers.TitleParser
import java.io.Reader

class RecipeParser {
    fun parse(reader: Reader): ParsedRecipe {
        var parsedRecipe = ParsedRecipe()

        val options = MutableDataSet()
        val parser = Parser.builder(options).build()
        val document = parser.parseReader(reader)

        var node = document.firstChild
        val partParsers: List<PartParser> = listOf(TitleParser(), IngredientsBlockParser(), StepsBlockParser())

        while (node != null) {
            val partParser = partParsers.find { it.startParsing(node) }

            if (partParser == null) {
                node = node.next
            } else {
                val pair = partParser.parse(parsedRecipe, node)
                parsedRecipe = pair.first
                node = pair.second
            }
        }

        return parsedRecipe
    }
}