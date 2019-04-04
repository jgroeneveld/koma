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
        Assertions.assertThat(mealplanFactory.create(recipeCount = 2)).hasSize(2)
    }

    @Test
    fun `creates different plans each time`() {
        val planA = mealplanFactory.create(1)
        val planB = mealplanFactory.create(1)
        Assertions.assertThat(planA.first()).isNotEqualTo(planB.first())
    }
}