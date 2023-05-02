@startuml
package food{
class Ingredients{
+static Ingredient unformedPatty
+static Ingredient formedPatty
+static Ingredient cookedPatty
+static Ingredient burnedPatty
+static Ingredient lettuce
+static Ingredient tomato
+static Ingredient onion
+static Ingredient potato
+static Ingredient lettuceChopped
+static Ingredient tomatoChopped
+static Ingredient onionChopped
+static Ingredient potatoChopped
+static Ingredient tomatoSauce
+static Ingredient cookedPotato
+static Ingredient burnedPotato
+static Ingredient lettuceMushy
+static Ingredient tomatoMushy
+static Ingredient onionMushy
+static Ingredient potatoMushy
+static Ingredient bun
+static Ingredient cooked_bun
+static Ingredient cheese
+static Ingredient cheesechopped
+static Ingredient unformedDough
+static Ingredient formedDough
+static Ingredient cookedDough
+static Ingredient cookedRawPizza
}
class Ingredient{
+float cookedTime
+Enum Status
+Status status
+String name
+BitmapFont flipText
+boolean flip()
+boolean slice(SpriteBatch batch, float dT)
+double cook(float dT, SpriteBatch batch)
-void drawStatusBar(float percentage,float optimum)
}
class Menu{
-static final Map<Ingredient, String> BURGER_STEPS
-static final Map<Ingredient, String> BURGER_BURNED_STEPS
-static final Map<Ingredient, String> JACKET_POTATO_STEPS
-static final Map<Ingredient, String> PIZZA_STEPS
-static final Map<Ingredient, String> SALAD_STEPS
+static final Map<String, Recipe> RECIPES
+static final Map<Ingredient, Ingredient> INGREDIENT_PREP
}
class Recipe{
+final String name
+Map<Ingredient, String> ingredientInstructions
+void displayRecipe(Vector2 pos)
+boolean matches(Stack<Ingredient> givenIngredients)
-boolean contains(Ingredient checkIngredient, ArrayList<Ingredient> ingredients)
}
Enum Status{

}
Status -- Ingredient
Menu -- Ingredient
Menu --o Recipe
Recipe -- Ingredient
Ingredient o-- Ingredients
}

package entity{
class Entity{
...
}
}

entity +-- food

Ingredient -up-|> Entity

@enduml