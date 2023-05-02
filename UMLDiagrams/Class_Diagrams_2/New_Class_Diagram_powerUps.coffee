@startuml
package powerUps{
class PowerUp{
+static void addTime(float delta)
+static boolean completed()
}
class PowerUps{
+static PowerUp constructionCostReduce()
+static PowerUp cookingSpeedReduce()
+static PowerUp customerTimeIncrease()
+static PowerUp increasePay()
+static PowerUp speedBoost()
+static void addPowerUp(PowerUp powerup)
+static void Interact(int x, int y)
}
PowerUps -up-|> PowerUp
}
package entity{
class Entity
}
PowerUp -up-|> Entity
@enduml