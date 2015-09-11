# Introduction #

Install Maven Android Plugin.


# Details #

<ol>
<li>
Make sure you have made all the installations which are explained in the wiki's page: <a href='http://code.google.com/p/fsnet/wiki/AndroidWithEclipse'>http://code.google.com/p/fsnet/wiki/AndroidWithEclipse</a>
</li>
<li>
Download Maven Android SDK Deployer :<a href='http://github.com/mosabua/maven-android-sdk-deployer'>http://github.com/mosabua/maven-android-sdk-deployer</a> and unzip it.<br>
</li>
<li>
Change directories to this new maven-android-sdk directory:<br>
<br>
<code>cd mosabua-maven-android-sdk-deployer-df824df</code>
</li>
<li>
In order to install the android API jar files from the different platform revisions into your local repository run the command:<br>
<br>
<code>mvn clean install.</code>
</li>
<li>
Add in ~/.m2/repository/settings.xml:<br>
<pre><code>&lt;pluginGroups&gt;<br>
   &lt;pluginGroup&gt;<br>
     com.jayway.maven.plugins.android.generation2<br>
   &lt;/pluginGroup&gt;<br>
&lt;/pluginGroups&gt;<br>
</code></pre>
</li>
<li>
Create a file pom.xml in the root folder of the project:<br>
<pre><code>&lt;?xml version="1.0" encoding="UTF-8"?&gt;<br>
&lt;project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd"&gt;<br>
    &lt;modelVersion&gt;4.0.0&lt;/modelVersion&gt;<br>
    &lt;groupId&gt;com.simpligility.android&lt;/groupId&gt;<br>
    &lt;artifactId&gt;helloflashlight&lt;/artifactId&gt;<br>
    &lt;version&gt;1.0.0-SNAPSHOT&lt;/version&gt;<br>
    &lt;packaging&gt;apk&lt;/packaging&gt;<br>
    &lt;name&gt;HelloFlashlight&lt;/name&gt;<br>
<br>
    &lt;dependencies&gt;<br>
        &lt;dependency&gt;<br>
            &lt;groupId&gt;com.google.android&lt;/groupId&gt;<br>
            &lt;artifactId&gt;android&lt;/artifactId&gt;<br>
            &lt;version&gt;1.6_r2&lt;/version&gt;<br>
            &lt;scope&gt;provided&lt;/scope&gt;<br>
        &lt;/dependency&gt;<br>
    &lt;/dependencies&gt;<br>
<br>
    &lt;build&gt;<br>
        &lt;finalName&gt;${project.artifactId}&lt;/finalName&gt;<br>
        &lt;sourceDirectory&gt;src&lt;/sourceDirectory&gt;<br>
        &lt;plugins&gt;<br>
            &lt;plugin&gt;<br>
                &lt;groupId&gt;com.jayway.maven.plugins.android.generation2&lt;/groupId&gt;<br>
                &lt;artifactId&gt;maven-android-plugin&lt;/artifactId&gt;<br>
                &lt;version&gt;2.8.4&lt;/version&gt;<br>
                &lt;configuration&gt;<br>
                    &lt;sdk&gt;<br>
                        &lt;!-- platform or api level (api level 4 = platform 1.6)--&gt;<br>
                        &lt;platform&gt;4&lt;/platform&gt;<br>
                    &lt;/sdk&gt;<br>
                    &lt;emulator&gt;<br>
                        &lt;!-- the name of the avd device to use for starting the emulator --&gt;<br>
                        &lt;avd&gt;16&lt;/avd&gt;<br>
                    &lt;/emulator&gt;<br>
                    &lt;deleteConflictingFiles&gt;true&lt;/deleteConflictingFiles&gt;<br>
                    &lt;undeployBeforeDeploy&gt;true&lt;/undeployBeforeDeploy&gt;<br>
                &lt;/configuration&gt;<br>
                &lt;extensions&gt;true&lt;/extensions&gt;<br>
            &lt;/plugin&gt;<br>
            &lt;plugin&gt;<br>
                &lt;artifactId&gt;maven-compiler-plugin&lt;/artifactId&gt;<br>
                &lt;!-- version 2.3 defaults to java 1.5, so no further configuration needed--&gt;<br>
                &lt;version&gt;2.3&lt;/version&gt;<br>
            &lt;/plugin&gt;<br>
        &lt;/plugins&gt;<br>
    &lt;/build&gt;<br>
&lt;/project&gt;<br>
</code></pre>
<ul>
<li>Change <code>&lt;groupId&gt;</code>, <code>&lt;artifactId&gt;</code> and <code>&lt;name&gt;</code> to your own</li>
<li>Set a <code>&lt;version&gt;</code> tag for your project</li>
<li>You could also change the platform version you want to be compatible with as a minimum, the name of the avd</li>
</ul>
</li>
<li>
To build the application and run it on an already started emulator you could use<br>
<br>
<code>mvn clean install android:deploy</code></li>
</ol>
For more details:<ul><li><a href='http://www.sonatype.com/books/mvnref-book/reference/android-dev.html'>http://www.sonatype.com/books/mvnref-book/reference/android-dev.html</a></li>
<li><a href='http://code.google.com/p/maven-android-plugin/wiki/GettingStarted'>http://code.google.com/p/maven-android-plugin/wiki/GettingStarted</a></li>
<li><a href='http://www.sonatype.com/books/mvnref-book_fr/reference/android-dev.html'>http://www.sonatype.com/books/mvnref-book_fr/reference/android-dev.html</a> (in French)</li>
</ul>