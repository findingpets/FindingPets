# FindingPets

Finding Pets(找你的寵物)

User story(must):

1. Main activity
 (1) grid-like layout shows all animals awaiting to be adopted.
 (2) toolbar with action buttons:
   a. search: launch a dialog to setup filter conditions
   
2. Detail activity(launched by clicking on an item view in main activity)
 (1) demonstrate an animal detail(photo, spices, color, body attribute, ..., etc).
 (2) a share button(through Intent.ACTION_SHARE)
 (3) a button to launch a map view activity, which indicates the animal's position on map.

 
User story(optional)

1. Main activity
 (1) toolbar with action buttons:
   a. map: launch a map view activity, indicates animals location around current user position.

2. MapView actiity: mark recent animal shelters' locations and number of animals inside the mark.
3. Firebase:
 (1) Parse data into Firebase.
 (2) This app querys data from Firebase.
 (3) Firebase sends notification about new animal infomation to user(needs user sign-up).
 (4) User can sign-up with social-network account(Facebook, Google or Twitter).
 (5) User Firebase adapter for recycler view.
4. A banner adverticement view at the bottom of each activity.
5. A widget in launcher, which can show random awaiting-adopted animal information.

[.gif for wireframe](https://files.slack.com/files-pri/T351FCRRA-F4H019Z6E/wireframe.gif)

