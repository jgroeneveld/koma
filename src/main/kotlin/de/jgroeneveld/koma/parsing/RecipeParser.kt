package de.jgroeneveld.koma.parsing

import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.options.MutableDataSet
import de.jgroeneveld.koma.parsing.partparsers.IngredientsParser
import de.jgroeneveld.koma.parsing.partparsers.PartParser
import de.jgroeneveld.koma.parsing.partparsers.StepsParser
import de.jgroeneveld.koma.parsing.partparsers.TitleParser

class RecipeParser {
    fun parse() {
        val recipeDoc = ParsedRecipe()

        val options = MutableDataSet()
        val parser = Parser.builder(options).build()
        val document = parser.parse("# Brot\n Ein leckeres Brot\n## Zutaten\n- Mehl\n- Eier\n### Optional\n- Hafer\n- Nüsse\n## Zubereitung\n1. Mischen\n2. Backen")

        var node = document.firstChild
        val partParsers: List<PartParser> = listOf(TitleParser(), IngredientsParser(), StepsParser())

        while (node != null) {
            val partParser = partParsers.find { partParser -> partParser.startParsing(node) }
            when (partParser) {
                null -> node = node.next
                else -> node = partParser.parse(recipeDoc, node)
            }
        }
        println("processed: $recipeDoc")
    }
}