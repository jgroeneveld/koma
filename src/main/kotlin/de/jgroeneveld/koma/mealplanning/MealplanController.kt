package de.jgroeneveld.koma.mealplanning

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MealplanController(
        val mealplanFactory: MealplanFactory
) {
    @GetMapping("/api/mealplans")
    fun test(): Int {
        return -1
    }
}