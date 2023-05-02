@startuml
autonumber
skinparam sequenceMessageAlign center

actor User as U
participant Cook as C
participant Gamescreen as GS
participant StationManager as SM

U -> C : User moves cook using directional arrows
C -> GS : Ask for current position
GS -> SM : Check tilemap for interacted tile type
SM --> GS : Return what station the cook is at
GS --> C : Check current cook item stack / give cook item
C -> GS : Return item to be placed if needed
GS -> SM : Pass on item to be placed
@enduml