> # Setup android application in your cell phone #

With this page you can setup your own android application in your smartphone phone.

  1. See http://code.google.com/p/fsnet/wiki/AndroidWithEclipse to install SDK and other tools to develop an application etc.
  1. See http://code.google.com/p/fsnet/wiki/CreateAndroidProject to make your first application.
  1. Connect your phone on your computer. In your settings : the phone must be in a debug USB mode.
  1. Check the phone is connected to your computer (in command line : lsusb).
  1. In command line :
```
   cd /etc/udev/rules.d/
   sudo gedit 51-android.rules
```
  1. Add the line to "51-android.rules" :
```
     SUBSYSTEM=="usb", SYSFS{idVendor}=="0bb4", MODE="0666" 
```
> > NB : here 0bb4 is for an htc smartphone.
> > If your phone is not htc go to http://developer.android.com/guide/developing/device.html#setting-up and find your code.
  1. Go to your folder android-sdk-linux\_x86 :
```
   cd [location]/android-sdk-linux_x86/platform-tools
   adb kill-server
   adb start-server
   adb devices
```
  1. Run your application in eclipse. Instead of an emulator runs application, the application is running in your phone.
