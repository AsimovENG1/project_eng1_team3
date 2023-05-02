@startuml
package screen{
class GameScreen{
+MainGameClass game
+MainScreen ms
+Enum STATE
+static Customer currentWaitingCustomer
+static STATE state1
+TiledMapRenderer 
+Cook[] cooks
+CustomerController cc
+StationManager stationManager
+void drawHeldItems()
-void checkCookSwitch()
+void changeScreen(STATE state1)
+void musicVolumeUpdate()
+void gameVolumeUpdate()
-void calculateBoxMaths()
-void constructCollisionData(TiledMap mp)
+void checkInteraction(Cook ck, ShapeRenderer sr)
}
class MainScreen{
+final MainGameClass game
+Enum STATE
+void show()
+changeScreen(STATE state)
+void musicVolumeUpdate()
+void gameVolumeUpdate()
}
class LeaderBoard{
+final MainGameClass game
+MainScreen ms
+playerData
+void readPlayerData()
+void sortPlayerData()
+ addLeaderBoardData(String name,int score)
+void changeScreenToMain()
}
class Tutorial{
-static List<PosTextPair> stages
-static final BitmapFont
-static GlyphLayout layout
-static String curText
+static void drawBox(float dT)
}
class PosTextPair{
+Vector2 pos
+String text
PosTextPair(Vector2 pos, String text)
}

Tutorial -- GameScreen
Tutorial *-- PosTextPair
}

package util{
class CollisionTile{
...
}
}

package station{
class StationManager{
...
}
}

package entity{
class Cook{
...
}
class CustomerController{
...
}
}

package mainGameClass{
class MainGameClass{
...
}
}

GameScreen -up-> CollisionTile : Stores many instances of
GameScreen -up-> CustomerController : Stores one instance of
GameScreen -up-> StationManager : Stores one instance of
GameScreen -up-> Cook : Stores max 3 instances


GameScreen <- MainGameClass : Stores one instance of

@enduml