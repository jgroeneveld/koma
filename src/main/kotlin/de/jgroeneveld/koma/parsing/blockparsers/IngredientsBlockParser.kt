package de.jgroeneveld.koma.parsing.blockparsers

import com.vladsch.flexmark.ast.BulletList
import com.vladsch.flexmark.util.ast.Node
import de.jgroeneveld.koma.parsing.ParsedRecipe
import de.jgroeneveld.koma.parsing.ingredientparsers.IngredientParser
import de.jgroeneveld.koma.values.Ingredient

class IngredientsBlockParser : NamedBlockParser(headingText = "Zutaten") {
    val ingredientParser = IngredientParser()
    val parsedIngredients = mutableListOf<Ingredient>()

    override fun processChildNode(recipe: ParsedRecipe, node: Node): ParsedRecipe {
        if (node is BulletList) {
            for (item in node.children) {
                val ingredient = ingredientParser.parse(item.childChars.trim().toString())
                parsedIngredients.add(ingredient)
            }
        }

        return recipe
    }

    override fun processFullBlock(recipe: ParsedRecipe, fullBlock: String): ParsedRecipe {
        return recipe.copy(
                ingredients = fullBlock,
                ingredientsList = parsedIngredients
        )
    }
}