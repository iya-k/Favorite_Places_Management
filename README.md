# Favorite_Location_Management (FLM)


# Description

The goal of **FLM** is to provide the user with a way to mark down **his favorite places and upcoming events** on multiple maps that he can **share** with his friends.

# Starting the project

### 1) Launching elasticsearch

  Go to your elasticsearch folder, navigate to the bin folder inside it and execute :

  > On Windows :
  elasticsearch.bat

> On Linux :
./elasticsearch


### 2) Start Jenkins

Navigate to your Jenkins folder and execute :

> java -jar jenkins.war --httpPort=8080

### 3) Launching the actual code

In eclipse, launch the application as a java application.
You can then navigate to
>localhost:8082

to view the project.

In case of an update of the code, you can right-click on the project and do :
Maven -> Update Project.

# What you can currently do

* List of available pages (TODO):

| Page          | Function                             |
| ------------- |:------------------------------------:|
| map_show      | Homepage - View your current map     |
| ___           | ____                                 |
| ____          | ___                                  |


* Clicking on the **Plus Sign** in the bottom right hand corner of the screen will display a **menu** where you'll be able to select one of the following :

| Menu Icon     | Meaning                              |
| ------------- |:------------------------------------:|
| Bolt          | Settings                             |
| Car           | Plan your trip                       |
| Marker        | Create a new place / event on a map  |
| Pen           | Determine who can see your maps      |
| Map           | Select the currently displayed map   |
