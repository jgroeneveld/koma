package de.jgroeneveld.koma

import de.jgroeneveld.koma.recipes.control.RecipeFilesProvider
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import java.io.File
import java.nio.file.Paths

@Component
@Primary
class TestRecipeFilesProvider : RecipeFilesProvider {
    override fun getFiles(): Iterable<File> {
        val folderPath = Paths.get("src", "test", "resources", "recipes")
        return folderPath.toFile().listFiles().toList()
    }
}