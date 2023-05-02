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
@enduml