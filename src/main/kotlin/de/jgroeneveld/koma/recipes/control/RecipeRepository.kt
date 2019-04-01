package de.jgroeneveld.koma.recipes.control

import de.jgroeneveld.koma.recipes.entity.Recipe

interface RecipeRepository {
    fun findAll(): Iterable<Recipe>
}