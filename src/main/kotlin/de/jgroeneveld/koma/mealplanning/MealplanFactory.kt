package de.jgroeneveld.koma.mealplanning

import de.jgroeneveld.koma.recipes.control.RecipeRepository
import de.jgroeneveld.koma.recipes.entity.Recipe
import org.springframework.stereotype.Service

@Service
class MealplanFactory(
        val recipeRepository: RecipeRepository
) {
    fun create(recipeCount: Int): Mealplan {
        val recipes = recipeRepository.findAll().shuffled().take(recipeCount)
        return Mealplan(recipes)
    }
}