package de.jgroeneveld.koma.recipes.control

import org.springframework.stereotype.Component
import java.io.File
import java.nio.file.Paths

@Component
class RecipeFilesProviderImpl : RecipeFilesProvider {
    override fun getFiles(): Iterable<File> {
        val folderPath = Paths.get("src", "main", "resources", "recipes")
        return folderPath.toFile().listFiles().toList()
    }
}