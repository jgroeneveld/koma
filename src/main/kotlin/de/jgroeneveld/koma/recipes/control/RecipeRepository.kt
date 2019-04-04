package de.jgroeneveld.koma.recipes.control

import de.jgroeneveld.koma.parsing.RecipeParser
import de.jgroeneveld.koma.recipes.entity.Recipe
import org.springframework.stereotype.Repository

@Repository
class RecipeRepository(
        val recipeFilesProvider: RecipeFilesProvider
) {
    fun findAll(): Collection<Recipe> {
        val recipes = mutableListOf<Recipe>()

        for (recipeFile in recipeFilesProvider.getFiles()) {
            val parsedRecipe = RecipeParser().parse(recipeFile.reader())

            recipes.add(Recipe(
                    title = parsedRecipe.title,
                    description = parsedRecipe.description,
                    ingredients = parsedRecipe.ingredients,
                    steps = parsedRecipe.steps,

                    id = "TODO ID",
                    ingredientsList = parsedRecipe.ingredientsList,
                    tags = parsedRecipe.tags
            ))
        }

        return recipes
    }
}