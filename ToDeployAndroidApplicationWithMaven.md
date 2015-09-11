# Introduction #

To deploy android application with maven  :  <br />

> <br />
# Details #

-> Install android sdk. <br />
-> Set a variable ANDROID\_HOME with the path from android-sdk <br />
-> In the directory /platforms-tools , copy the file "adb" . <br />
-> Go to the directory /tools and paste the file.  <br />
> <br />
> <br />
To launch the application (from line of command):  <br />
mvn clean install <br />
mvn android:deploy <br />
> <br />
> <br />
(Don't forget to launch the android emulator before deploy the application) <br />