package de.jgroeneveld.koma.recipes.boundary

import de.jgroeneveld.koma.recipes.control.RecipeRepository
import de.jgroeneveld.koma.recipes.entity.Recipe
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RecipeController {

    @GetMapping("/api/recipes")
    fun listRecipes(): Iterable<Recipe> {
        return RecipeRepository().findAll()
    }

}