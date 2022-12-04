# Loot-Tracker
An application to track loot from the Chambers of Xeric, a raid in the popular MMO Oldschool Runescape

## Design Choices
1. We structured our different scenes to inherit from a general SceneManager. The SceneManager can load up the main menu (which should probably be its own class), and its children load up their respective scenes (LogScene, CompareScene, and SubmitScene)
2. The Loot object has many subclasses to specify the rarity of each object. Ultimately, this could have been done differently without needing to make many different object types for this implementation.
3. SheetManager manages the SaveSheet.csv and ArrayList of Loot objects. It is passed around to each of the scenes as needed.
4. StatTracker is a member variable of the LogScene, and is predominantly used to perform calculations on the ArrayList of Loot objects. Some of these calculations include binomial probability and counting through the ArrayList with different parameters.
5. 
