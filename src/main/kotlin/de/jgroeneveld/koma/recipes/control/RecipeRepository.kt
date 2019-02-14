package de.jgroeneveld.koma.recipes.control

import de.jgroeneveld.koma.recipes.entity.Ingredient
import de.jgroeneveld.koma.recipes.entity.Quantity
import de.jgroeneveld.koma.recipes.entity.Recipe

class RecipeRepository {
    fun findAll(): Iterable<Recipe> {
        return listOf(
                Recipe(
                        id = "0000:0000",
                        title = "Muffins",
                        ingredients = listOf(
                                Ingredient(125F, Quantity.G, "Weiche Butter"),
                                Ingredient(125F, Quantity.G, "Zucker"),
                                Ingredient(1F, Quantity.Pieces, "Päckchen Vanillezucker"),
                                Ingredient(2F, Quantity.Pieces, "Eier"),
                                Ingredient(200F, Quantity.Ml, "Milch"),
                                Ingredient(200F, Quantity.G, "Mehl"),
                                Ingredient(0.5F, Quantity.Pieces, "Backpulver")
                        ),
                        steps = listOf(
                                "Mische immer zuerst die feuchten Zutaten wie Butter und Eier mit dem Zucker durch.",
                                "Danach werden die trockenen Zutaten wie Mehl, Backpulver und Kakao in einer separaten Schüssel miteinander vermengt.",
                                "Im letzten Schritt kommt dann beides zusammen.",
                                "Muffins brauchen im Ofen meistens nur 15 Minuten"
                        ),
                        text = "Keine weiteren Infos",
                        tags = listOf("Süß", "Backen")
                ),
                Recipe(
                        id = "0000:0001",
                        title = "Gemüsesuppe",
                        ingredients = listOf(
                                Ingredient(500F, Quantity.G, "Weizenmehl Typ 1050"),
                                Ingredient(0.5F, Quantity.Pieces, "Würfel frische Hefe"),
                                Ingredient(1.5F, Quantity.Tsp, "Salz"),
                                Ingredient(2F, Quantity.Tbsp, "Zucker"),
                                Ingredient(250F, Quantity.Ml, "Wasser")

                        ),
                        steps = listOf(
                                "Die Hefe in 250 ml lauwarmes Wasser bröckeln und so lange verrühren, bis sie sich aufgelöst hat.",
                                "Das Mehl in eine große Schüssel geben. Den Wasser-Hefe-Mix, Salz und Zucker dazugeben und alles zunächst mit den Knethaken des Handrührgerätes, anschließend mit den Händen, ca. 10 Minuten kräftig kneten, bis ein geschmeidiger Teig entsteht.",
                                "Den Brotteig zugedeckt an einem warmen Ort ca. 45 Minuten gehen lassen. Das Volumen des Teiges sollte sich dabei in etwa verdoppeln.",
                                "Den Teig nochmals kurz durchkneten und anschließend zu einem länglichen oder runden Laib formen.",
                                "Mit einem Messer mehrmals einschneiden und für weitere 20 Minuten gehen lassen.",
                                "Den Brotlaib auf ein mit Backpapier ausgelegtes Backblech legen und nach Belieben mit Kernen bestreuen.",
                                "Das Brot im vorgeheizten Ofen (E-Herd: 200 °C/Umluft: 175 °C/Gas: Stufe 3) 40-45 Minuten backen."
                        ),
                        text = "Gut zu wissen:  Der Brotteig ist fertig gebacken, sobald das Brot hohl klingt, wenn du von unten darauf klopfst.",
                        tags = listOf("Brot", "Backen")
                )
        )
    }
}