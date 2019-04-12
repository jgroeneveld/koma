package de.jgroeneveld.koma.recipes.control

import java.io.File

interface RecipeFilesProvider {
    fun getFiles(): Iterable<File>
}