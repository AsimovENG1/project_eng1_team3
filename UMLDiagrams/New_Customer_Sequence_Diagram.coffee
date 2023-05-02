@startuml
autonumber
GameScreen -> CustomerController : Wants to create customer
CustomerController -> Customer : Create customer
Customer --> CustomerController : Confirm Arrival
GameScreen -> CustomerController : Serve customer

GameScreen -> CustomerController : Wants to remove customer
CustomerController -> Customer : Remove customer
@enduml