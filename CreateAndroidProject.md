export PATH=$PATH:[[...]]/android-sdk-linux\_x86/tools/:[[...]]/android-sdk-linux\_x86/platform-tools/

emulator -avd my\_avd &

Create "Test" project with Eclipse

cd [[...]]/workspace

android create project -t 4 -n nomTest -p ./Test -a activity -k packageName.test // /!\ Same names as those entered in eclipse !

cd Test/

ant debug // optionnal, eclipse compiles only

adb start-server

adb install bin/Test.apk // Launch application on the emulator the first time

adb install -r bin/Test.apk // The next time

// If you open the build.xml file, an error on the first line informs us that the project does not exist...

// But the project is in place!

// Eclipse bug?