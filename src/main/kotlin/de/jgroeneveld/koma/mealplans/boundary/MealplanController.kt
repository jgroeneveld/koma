package de.jgroeneveld.koma.mealplans.boundary

import de.jgroeneveld.koma.mealplans.entity.Mealplan
import de.jgroeneveld.koma.mealplans.control.MealplanFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MealplanController(
        val mealplanFactory: MealplanFactory
) {
    @GetMapping("/api/mealplans")
    fun mealplan(): MealplanResponse {
        val mealplan = mealplanFactory.create(5)

        return responseForMealplan(mealplan)
    }
}

data class MealplanResponse(
        val recipes: Collection<String>,
        val shoppingList: Collection<String>
)

private fun responseForMealplan(mealplan: Mealplan): MealplanResponse {
    val recipes = mealplan.recipes.map { it.title }
    val shoppingList = mealplan.shoppingList().ingredients.map { it.toReadable() }

    return MealplanResponse(
            recipes = recipes,
            shoppingList = shoppingList
    )
}