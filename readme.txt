README: DTex

Members:
	Adam Soto	ajs3459
	Rene Trevino	hrt277

Instructions:
	The main screen of the app is where the user can choose how they want to search for drink specials. There are 4 different options the user can choose from. The first option the user has is to see the drink specials for the day. Depending on what day it is, the list may change. The second option is to search by drink specials for any day of the week. When a user selects this option, they are then presented with a screen that allows them to choose which day of the week they want drink specials for. From here they can select select day, then they can view all the drink specials for that day. Another option they are presented with is to search by areas. When a user selects this option, they are presented with a list of different well known areas in Austin. From here, the user can select which area they would like to search and they are presented with a list of bars in that area have specials for the current day. The last option the user has is to search by bar. When the user selects this option, they a presented with a list of all the bars in our database. When they choose a bar from this list, it should present them with any specials that bar has.

List of features we have completed:
	- DTex uses an SQLite Database to store all our information for every particular bar in our database as well as information for every particular drink special
	- Our app includes a parser that will parse an excel spreadsheet and use that file to populate our database.
	- The basic UI is fully operational. The navigation is well designed and very user friendly.
	- DTex allows the user to organize the information stored in our database to locate any particular information such as bars and drink specials.
	- The DTex database contains 75 bar entities and over 600 drink special entities. Expect that number to increase to at least 100 bar entities and 1000 drink special entities.
	- DTex contains a menu bar to navigate straight to the home screen, search the DTex database for particular bar entities and Quit the application.


List of features we have not completed:
	A key feature we have not quite finished yet, is the database. We are still in the process of collecting data, so the list will be full of various type of bars. We still receive the results we need, but we plan to make this list more visually appealing. Another feature we have not completed is the menu options for the app. This menu allows the users to go back to a previous page and also straight to the main menu of the app. We plan to implement a google map feature into the application as well. When the user clicks on a specific bar on a map, we will present them with some basic information of the bar and hopefully a map of where the bar is located. DTex will also allow the user to rate a particular bar or drink special and have those entities saved within the database for future reference by tapping the favorites button which will be available for the beta.

List of features we have added that were not in the prototype:
	A feature we added that wasn't in the prototype was the menu bar at the top of the application. We felt this was essential for the navigation through the application. We did not want the user to feel like they were stuck in the app. As they navigate and get further away from the main menu, without the menu bar, the only way to get back to the main menu was to press the back button until they got there. Now that we added this feature they are able to just hit the home button and get back to the main menu. A feature we are planning to add that wasn't in the prototype is the map feature. We feel this feature would be a great addition to our application. Most of the time if you don't know where something is located you have to copy the address from the app you are in and paste it in your map app and then search for it. If you have the location already mapped in your app (D-Tex), then it saves you time and makes finding the location easier.

List of the classes and major chunks of code you obtained from other sources and reference:
	- DTexBar.java
	- DTexDrink.java
	- DTexDatabase.java
	- DrinkList.java
	- BarList.java
	- BarInfo.java

List of the classes and major chunks of code you completed yourself:
	- All classes above were implemented ourselves

