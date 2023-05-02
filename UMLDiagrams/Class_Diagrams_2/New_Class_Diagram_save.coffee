@startuml
package save{
class Leaderboard{
+float getTime()
+float getScore()
+String getDifficulty()
}
class unlocked{
+static purchasedCooks
+static purchased stations
}
}
@enduml