@startuml
package save{
class ChefInfo{
+float x
+float y
+int cookNum
+IngredientInfo[] ingredients
}
class CustomerInfo{
+int custNum
+String possibleOrders
+int x
+int y
+int target
}
class GameInfo{
+ModeInfo gameMode
+ChefInfo[] chefs
+CustomerInfo[] customers
+StationInfo[] stations
+PowerUpInfo[] activePowerUps
+PowerUpInfo[] spawnPowerUps
+int money
+long timerOffset
+int currentwave
+int reputation
+Date createdAt
}
class IngredientInfo{
+int slices
+int idealSlices
+float cookedTime
+float idealCookedTime
+boolean usable
+Status status
+boolean cooking
+boolean slicing
+boolean flipped
+boolean rolling
+String name
+float x
+float y
+float width
+float height
}
class ModeInfo{
+String name
+int numberOfWaves
+int numberOfChefs
+int numberOfCustomersInWave
+long modeTime
+boolean showTutorial
+int getNumberOfWaves()
+int getNumberOfChefs()
+int getNumberOfCustomersInWave()
+long getModeTime()
+boolean showTutorial()
}
class PowerUpInfo{
+float duration
+String name
+float timeElapsed
+float x
+float y
+float width
+float height
}
class SaveService{
+Array<GameInfo> getSavedGames(
+void saveGame(GameInfo game)
)
}
class StationInfo{
+float x
+float y
+boolean active
+StationType Type
+IngredientInfo[] ingredients
}
Enum StationType{
}
}
@enduml