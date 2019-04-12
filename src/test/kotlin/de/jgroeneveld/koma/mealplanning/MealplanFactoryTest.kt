package de.jgroeneveld.koma.mealplanning

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MealplanFactoryTest {
    @Autowired
    lateinit var mealplanFactory: MealplanFactory

    @Test
    fun `creates plans with number given`() {
        Assertions.assertThat(mealplanFactory.create(recipeCount = 2).recipes).hasSize(2)
    }
}