@startuml
package powerUps{
class PowerUp{
+final float duration
+String name+float timeElapsed
+static void addTime(float delta)
+static boolean completed()
}
class PowerUps{
+static PowerUp constructionCostReduce()
+static PowerUp cookingSpeedReduce()
+static PowerUp customerTimeIncrease()
+static PowerUp increasePay()
+static PowerUp speedBoost()
}
class PowerUpsService{
-final Array<PowerUp> activePowerUps
-final Random random
+static void addPowerUp(PowerUp powerup)
+Array<PowerUp> getActivePowerUp()
+void spawnPowerUp(PowerUp powerUp)
+static void Interact(int x, int y)
}
PowerUps -up-|> PowerUp
}
package entity{
class Entity
}
PowerUp -up-|> Entity
@enduml