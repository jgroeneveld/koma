package de.jgroeneveld.koma.parsing.partparsers

import com.vladsch.flexmark.ast.BulletList
import com.vladsch.flexmark.util.ast.Node
import de.jgroeneveld.koma.parsing.ParsedRecipe
import de.jgroeneveld.koma.parsing.ingredientparsing.IngredientParser
import de.jgroeneveld.koma.recipes.entity.Ingredient

class IngredientsBlockParser : BlockParser(headingLevel = 2, headingText = "Zutaten") {
    val ingredientParser = IngredientParser()
    val parsedIngredients = mutableListOf<Ingredient>()

    override fun processLine(result: ParsedRecipe, node: Node): ParsedRecipe {
        if (node is BulletList) {
            for (item in node.children) {
                val ingredient = ingredientParser.parse(item.childChars.trim().toString())
                parsedIngredients.add(ingredient)
            }
        }

        return result
    }

    override fun processFullBlock(result: ParsedRecipe, fullBlock: String): ParsedRecipe {
        return result.copy(
                ingredientsMd = fullBlock,
                ingredients = parsedIngredients
        )
    }
}