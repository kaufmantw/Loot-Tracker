# Loot-Tracker
An application to track loot from the Chambers of Xeric, a raid in the popular MMO Oldschool Runescape

## Design Choices
1. We structured our different scenes to inherit from a general SceneManager. The SceneManager can load up the main menu (which should probably be its own class), and its children load up their respective scenes (LogScene, CompareScene, and SubmitScene)
2. The Loot object has many subclasses to specify the rarity of each object. Ultimately, this could have been done differently without needing to make many different object types for this implementation.
3. SheetManager manages the SaveSheet.csv and ArrayList of Loot objects. It is passed around to each of the scenes as needed.
4. StatTracker is a member variable of the LogScene, and is predominantly used to perform calculations on the ArrayList of Loot objects. Some of these calculations include binomial probability and counting through the ArrayList with different parameters.
5. Test is a leftover file leftover from the testing stage of different classes. It shouldn't be in the final product, but here we are. Test isn't accessed by any of the other classes. Sorry.

## Known Bugs
1. The charts in the Compare Scene don't generate properly immediately. The first option that is chosen displays all of the objects as one column or pie slice. This is most likely due to the Compare Scene relying on deprecated functionality and wasn't updated as needed.
2. The Log Scene's binomial probabilities don't add up to 100%. This is most likely due to rounding errors, or issues with doubles a little imprecise.
3. Currently, users can input negative numbers for killcount values in the Submit Scene. This is an oversight and easily correctible in the future.

## Debugging Collaborators
No outside sources were used for collaboration, however various Geeks4Geeks articles and JavaDocs were used for reference when learning syntax for different implementations.

## Hours spent
Tim spent anywhere from 20 to 25 hours working on the project, excluding any time thinking or stressing about the product away from the keyboard.

Mikey spent roughly 20 hours on the project, excluding UML documentation and other document creation, and practicing for the presentation.

## Work Done
Tim
1. 100% of the SheetManager class, working with the CSV file(s)
2. 100% of the StatTracker class, calculating things based off the ArrayList
3. 100% of the Main Menu
4. 50% of the Loot class and its subclasses
5. 50% of the Log Scene
6. 20% of the Compare Scene

Mikey
1. 100% of the Submit Scene
2. 80% of the Compare Scene
3. 50% of the Log Scene
4. 50% of the Loot class and its subclasses

While the itemized list is more in favor of Tim, some items within the list such as the Main Menu and Loot subclasses are not large contributions.
