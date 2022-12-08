# CS611 - Assignment 4
## LegendsOfValor

Github Link: https://github.com/abhi6689-develop/MonstersAndHeroes/tree/master

Name - Abhishek Tiwari and Yiran Huang
Email - abhi6689@bu.edu and yrhuang@bu.edu
BUID - Abhishek Tiwari - U93786467
- Yiran Hunag - U71885452

##Files
--------------------------------------------------------------------
* Readme.txt

* Game: Have the base Game classes and parts of the game logic
  *GameBase.java: The base class for the game
  *GameBoard.java: The class that holds the basic game board logic
  *PlayerBase.java: The base class for the player

* Controller: Package containing all the controller classes
  *FightController.java: Controller class for the fight
  *ViewController.java: Controller class for the game (movement, market, etc)
  *Override the original controller and change the MoveActions() function to handle more actions

* Model: Package containing all the model classes
  *Armor.java: Class for the armor
  *Cell.java: Class for the cell (Abstract class)
  *Character.java: Class for the character (Abstract class)
  *CharacterFactory.java: Class for the character factory
  *CommmonCell.java: Class for the common cell
  *Dragon.java: Class for the dragon
  *Exoskeleton.java: Class for the exoskeleton
  *Factory.java: Interface for the factory (Interface)
  *Fight.java: Class for the fight
  *Fightable.java: Interface for the fightable (Interface)
  *Hero.java: Class for the hero
  *InaccessibleCell.java: Class for the inaccessible cell
  *Item.java: Class for the item (Abstract class)
  *ItemFactory.java: Class for the item factory
  *Main.java: Main class (Driver class)
  *Map.java: Class for the map
  *Market.java: Class for the market
  *Monster.java: Class for the monster
  *MonsterAndHeroes.java: Class for the game inherits the RpgGame class
  *Paladin.java: Class for the paladin hero
  *Player.java: Class for the player
  *Potions.java: Class for the potions
  *RpgGame.java: Class for the RPG game (Abstract class)
  *Shop.java: Class for the shop, implmented by characters that can buy and sell items (Interface)
  *Sorcerer.java: Class for the sorcerer hero
  *Spell.java: Class for the spell item
  *Spirit.java: Class for the spirit hero
  *Utilities.java: Class for the utilities
  *Validations.java: Class for the validations
  *Warrior.java: Class for the warrior hero
  *Weapon.java: Class for the weapon item
  *Added five new cells(CaveCell.java, CaveCell.java, KoulouCell.java, PlainCell.java that extended CommonCell.java; NexusCell.java that extended Cell.java)
  *Added LegendsOfValor.java that extends MonstersAndHeroes.java, changed StartGame() and implemented SpawnHeroes() and DropMonsters()
  *Added Map.java to achieve a different map with different numbers of groups of monsters and heros

* View: Package containing all the view classes
  *FightView.java: View class for the fight
  *BoardView.java: View class for the board
  *MarketView.java: View class for the market
  *Movement.java: View class for the movement
  *View.java: View class for the game (Interface)
  *WelcomePrompt.java: View class for the welcome prompts
  *Added FightController.java and WelcomeView.java both extend or implement original class or interface

* Utilities: Helper function generate colors and validating input
  *constants.java: All the text colors and background colors
  *InputValidations.java: Extending original InputValidations.java




##Bonus
--------------------------------------------------------------------
* Completely extendable board with colors
* Armor decreases damage taken by a hero
* Monsters are scaled according to the heroes


##How to compile and run
--------------------------------------------------------------------
1. After unzipping the file, go to the src directory
2. Compile the files using the following command :
   `javac *.java`
3. Run the program using the following command :
   `java Main`