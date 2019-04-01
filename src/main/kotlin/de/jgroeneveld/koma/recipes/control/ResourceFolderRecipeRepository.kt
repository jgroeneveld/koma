package de.jgroeneveld.koma.recipes.control

import de.jgroeneveld.koma.parsing.RecipeParser
import de.jgroeneveld.koma.recipes.entity.Recipe
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository

@Repository
class ResourceFolderRecipeRepository: RecipeRepository {
    override fun findAll(): Iterable<Recipe> {
        val folderResource = ClassPathResource("recipes")
        val files = folderResource.file.listFiles()
        val recipes = mutableListOf<Recipe>()

        for (recipeFile in files) {
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