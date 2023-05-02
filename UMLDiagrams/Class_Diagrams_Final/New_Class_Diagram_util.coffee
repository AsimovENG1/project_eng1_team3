@startuml
package util{
class CollisionTile{
+Rectangle returnRect()
}
class Control{
+boolean up,down,left,right
+boolean del
+boolean interact,drop,flip
+boolean tab,shift
+boolean keyDown(int keyCode)
+boolean keyUp(int keyCode)
+boolean keyTyped(int keyCode)
+boolean touchDown(int screenX, int ScreenY,int pointer, int button)
+boolean touchUp(int screenX, int ScreenY,int pointer, int button)
+boolean touchDragged(int screenX, int ScreenY,int pointer)
+boolean mouseMoved(int screenX, int screenY)
+boolean scrolled(int amount)
}
class EndlessMode{
-final int numberOfChefs
-final long modeTime
+int getNumberOfWaves()
+int getNumberOfChefs()
+boolean showTutorial()
+long getModeTime()
+int getNumberOfCustomersInWave()
}
class ScenarioMode{
-final int numberOfWaves
-final int numberOfChefs
-final int getNumberOfCustomersInWave
-final long modeTime
+int getNumberOfWaves()
+int getNumberOfChefs()
+boolean showTutorial()
+long getModeTime()
+int getNumberOfCustomersInWave()
}
class TutorialMode{
+int getNumberOfWaves()
+int getNumberOfChefs()
+boolean showTutorial()
+long getModeTime()
+int getNumberOfCustomersInWave()
}
Interface GameMode{
int getNumberOfWaves()
int getNumberOfChefs()
boolean showTutorial()
long getModeTime()
int getNumberOfCustomersInWave()
}
}



package mainGameClass{
}
mainGameClass +-- util
EndlessMode --|> GameMode
ScenarioMode --|> GameMode
TutorialMode --|> GameMode
@enduml