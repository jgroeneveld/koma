package de.jgroeneveld.koma.mealplanning

import de.jgroeneveld.koma.recipes.entity.Recipe
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

data class MealplanResponse(
        val recipes: Collection<Recipe>,
        val shoppingList: ShoppingList
)

fun mealplanResponseFrom(mealplan: Mealplan): MealplanResponse {
    return MealplanResponse(
            recipes = mealplan.recipes,
            shoppingList = mealplan.shoppingList()
    )
}

@RestController
class MealplanController(
        val mealplanFactory: MealplanFactory
) {
    @GetMapping("/api/mealplans")
    fun test(): MealplanResponse {
        return mealplanResponseFrom(mealplanFactory.create(3))
    }
}