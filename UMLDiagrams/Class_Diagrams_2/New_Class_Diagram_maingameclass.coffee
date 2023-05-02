@startuml
package mainGameClass{
class MainGameClass{
+static SpriteBatch batch
+static BitmapFont font
+Music mainScreenMusic
+static Music gameMusic
-MainScreen mainScreen1
-GameScreen gameScreen1
-LeaderBoard leaderBoardScreen1
+void resetGameScreen()
}
}

package screen{
class MainScreen{
...
}
class LeaderBoard{
...
}
class GameScreen{
...
}
}

MainGameClass -> MainScreen : Stores one instance of
MainGameClass -> LeaderBoard : Stores one instance of
GameScreen <- MainGameClass : Stores one instance of
@enduml