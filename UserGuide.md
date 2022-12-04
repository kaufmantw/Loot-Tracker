# Loot Tracker User Guide
## Introduction
Welcome to the Loot Tracker! This tool gives people the capability to track their own drops from the Chamber of Xeric, a raid in the MMORPG Oldschool Runescape.
After tracking your drops, you have a few different ways of visualizing just how lucky, or unlucky, your account has been so far.

## Main Menu
Upon loading up the application, you will be greeted with a few different options. The Submit, Compare, and Log button all take you to different menus.
The Submit button will take you to a scene for you to submit new drops as you get them. The Compare button will take you to a scene where you can visually compare
the previous drops you have obtained with two different methods. The Log button takes the user to a scene where some general diagnostic information is held.

![image](https://user-images.githubusercontent.com/112425400/205517987-ea217756-ffc5-49b4-a66b-74795802e194.png)


## Submit Menu
This menu is how the user interacts with their drop database (a CSV file stored locally). On the top of the scene are instructions for how to submit, but this user
guide will also detail that. On the left side of the scene are buttons for each type of object you can submit; only one object can be chosen at a time. In the middle of
the scene are you submission options. The "Kill Count Obtained" field takes an integer value; if any other value or word is passed in the object will not be added
to your database. Toggle boxes "Challenge Mode", "Obtained Solo", and "Personally Obtained" are all assumed to be false unless ticked. Pressing the submit button will
take each of these values and create a new Loot object for your database. At the bottom of the screen is a button to return to the main menu.

![image](https://user-images.githubusercontent.com/112425400/205518030-196fb566-5955-49da-864a-f0488cbb2ce9.png)


## Compare Scene
This menu is how the user can visualize what is currently stored in their database (gain, the CSV file stored locally). The top half of the scene is your visual display; this can be toggled between a bar chart and pie chart with the "Toggle Graph" button towards the bottom right of the application. The bottom left section of the applicaiton is dedicated for buttons of each item you wish to compare. Unlike the Submit Scene, multiple buttons here can be active at once. To reset all of the active buttons, click the "Clear Selections" button towards the bottom right of the application. The bottom right of the application also holds some optional toggles to narrow
down on what you can see visually. The "Kill Count Range" shows items from a range of killcount values. Noted, these values must be integers and from smallest to largest. Additionally, the kill count range will be applied when toggling one of the other options; these options include the toggles for "Challenge Mode", "Obtained Solo", and "Personally Obtained". At the bottom of the application is a button to return to the main menu.

![image](https://user-images.githubusercontent.com/112425400/205518049-fb74a146-e8b7-4a7c-bf6a-7869810fdaf9.png)


## Log Scene
This menu is a one-stop shop for general information. On the left side of the application is a list of all items in your database. Underneath each option is a "Remove" button, clicking it will remove the object from your data. On the upper right side of the application is a list of total items collected; this is useful as a quick glance at your data. Underneath these totals are some more general information such as the killcount since the last Twisted Bow, the total personal drops you have received, and the total number of solo drops you have received. Finally, beneath that information is some statistical analysis on the selected item. To change selections, press a new item on the ChoiceBox directly underneath the three binomial probabilities shown. At the bottom of the application is a button to return to the main menu.

![image](https://user-images.githubusercontent.com/112425400/205518062-6726ef94-56dd-4d1e-850c-bd13c169a148.png)


## Saving
At the top left of the main menu is a save button. Press this to save your changes to the database. If you saved something and change your mind you can undo it in the log scene with the item removal functionality. If you wish to look at your database directly, navigate to "APPLICATION_NAME\\bin\\sheets\\SaveSheet.csv", with APPLICATION_NAME being whatever name you saved this project as. I would not recommend editing this file unless you strictly follow the object format of 
NAME, KC, isPERSONAL, isSOLO, isCM, TIMESTAMP. Adjusting this file haphazardly puts the program at risk so be cautious!

## Capabilities and Limitations
Our program is perfect (except when it isn't). Currently there is no direct way to completely clear the users database; users must click on each remove button in the Log Menu then save. Also the killcount fields in the Compare Menu don't change the display until a ToggleBox is clicked; these two functionalities shouldn't be linked in this way. However, overall the program functions as is required. Users can submit and save drops, then use the Log or Compare Menu to visualize their data in easily digestable ways.

## Our information
Mikey Hill () and Tim Kaufman (kaufmantw) collaborated on this project. Direct any questions you may have at either one of us. Thank you for using the Loot Tracker!
