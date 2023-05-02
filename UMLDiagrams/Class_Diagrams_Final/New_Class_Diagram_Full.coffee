@startuml
package entity{
class Cook{
+Stack<Ingredient> heldItems
+int cookno
+boolean locked
+float stateTime
+boolean holding
+Animation<TextureRegion> walkAnimation
+void pickUpItem(Ingredient item)
+boolean full()
+Ingredient dropItem()
+Boolean checkCollision(float cookx, float cooky, CollisionTile[][] cltiles)
}
class Customer{
+boolean locked
+String order
+void stepTarget()
}
class CustomerController{
+int lockout
+amountActiveCustomers
+ArrayList<ArrayList<Integer>> customerCells
+Customer[] customers
+Customer[] leavingcustomers
+void spawnCustomer()
+void delCustomer(int num)
+void delCustomer(Customer customer)
+void updateCustomers(Control ctrls)
+Customer isCustomerAtPos(Vector2 pos)
}
class Entity{
+Vector2 pos
+Texture texture
+float width
+float height
+float speed
+float dirX
+float dirY
+void draw(SpriteBatch batch)
}

class Cook extends Entity

Customer -- CustomerController
}

package mainGameClass{
}

package food{
class Ingredient{
...
}
}

package station{
class ServingStation{
...
}
}


mainGameClass +-- entity
Cook -> Ingredient : Stores some ingredients
Customer -> ServingStation : Check customer to serve




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
MainGameClass -> GameScreen : Stores one instance of



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
class DifficultyScreen{

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
MainGameClass -> GameScreen : Stores one instance of





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