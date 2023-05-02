@startuml
package station{
class BakingStation{
-final static Ingredient[] ALLOWED_INGREDIENTS
+boolean place(Ingredient ingredient)
}
class CookingStation{
+ParticleEffect[] pES
#void createParticleEmitter(Vector2 pos, String particlePath)
+void drawParticles(SpriteBatch batch, int slotIndex)
}
class StationManager{
+static Map<Vector2, Station> stations
+void handleStations()
+void checkInteracterTile(int id, Vector2 pos)
-void placeIngredientStation(Vector2 pos)
-void takeIngredientStation(Vector2 pos,Ingredient ingredient)
}
class CuttingStation{
+final static Ingredient[] ALLOWED_INGREDIENT
+void interact(SpriteBatch batch, float time)
+boolean lockCook()
}
class FryingStation{
-final static Ingredient[] ALLOWED_INGREDIENT
}
class IngredientStation{
+Ingredient ingredient
}
class PrepStation{
+float progress = 0
+boolean lockCook()
+void updateProgress(float delta)
-Ingredient ingredientMatch(Ingredient toMatch)
}
class ServingStation{
+String[] possibleOrders
+static Ingredient[] allowedIngredients
+boolean serveCustomer()
}

Station *-left- StationManager
Station <|-right- CookingStation
Station <|-down- CuttingStation
Station <|-left- IngredientStation
Station <|-down- PrepStation
Station <|-down- ServingStation

CookingStation <|-- BakingStation
CookingStation <|-- FryingStation
}

package food{
class Ingredient{
...
}
}

package screen{
class GameScreen{
...
}
}
Station -up-> Ingredient : Can require ingredients or give


station -down-+ GameScreen
@enduml