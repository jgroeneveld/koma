package de.jgroeneveld.koma.mealplans.control

import de.jgroeneveld.koma.mealplans.entity.Mealplan
import de.jgroeneveld.koma.recipes.control.RecipeRepository
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