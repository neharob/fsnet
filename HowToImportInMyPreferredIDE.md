#This page describe how you can import fsnet in your IDE

# Introduction #

As you can see in the source repository there is no specific IDE files in. The main idea is to let each developer choose his preferred Integrated Development Environment.

# Using Eclipse (Juno 4.2) #

## Get the FSNET source code ##

  * Create a new workspace, and go into it.
  * Log in on Gmail and go to the fsnet google group (https://code.google.com/p/fsnet/).
  * Go to Source tab to get the fsnet source code.
  * Copy the link with your login in it.
  * Open a prompt and paste the command to get a local copy of the fsnet repository.

## Plugins for maven and git installation ##

  * Launch Eclise and go to Help->EclipseMarketplace.
    1. Search **EGit (EGit - Git Team Provider)** and click on Install.
    1. Click on Next to confirm.
    1. Accept the terms.
    1. Then click on Finish to install.
    1. Eclipse will restart to complete the installation.

  * Launch Eclise and go to Help->EclipseMarketplace.
    1. Search **Maven Integration for Eclipse** and click on Install.
    1. Same instructions to install.
    1. Eclipse will restart to complete the installation.

  * Launch Eclise and go to Help->EclipseMarketplace.
    1. Search **Maven Integration for Eclipse WTP (Incubation)** and click on Install.
    1. Same instructions to install.
    1. Eclipse will restart to complete the installation.

## Import the project ##

  * Firstly, we need to install the m2e-egit connector.
    1. Right click into your Project Explorer->Import->Import...
    1. Choose Maven->Check out Maven Projects from SCM->Next.
    1. Click on **Find more SCM connectors in the m2e Marketplace**.
    1. Find m2e-egit and choose it, click on Finish.
    1. A new window Install opens, click on Next two times.
    1. Accept the terms and click on on Finish.
    1. Eclipse will restart to complete the installation.

  * You can now install the project
    1. Project Explorer->Import->Import...
    1. Choose Maven->Check out Maven Projects from SCM->Next. Now you can choose **git** in SCM URL.
    1. In the textbox, copy paste the url : https://code.google.com/p/fsnet/, then click on Next.
    1. Once the project is downloaded, go to directory where fsnet has been installed, and launch the command **mvn install**.
    1. Go to trayDesktop on your Project Explorer. Browse it to go in target->generated-sources->jaxws-wsimport
    1. Right click on it, and go to **Build Path->Use as Source Folder**, to correct errors in WSConnector.java

  * Configure the tomcat server to run the webapp
  1. In tomcat/conf/tomcat-users.xml,add :
```
    <role rolename="fsnetadmin"/>
    <user username="admin" password="admin" roles="fsnetadmin"/>
```
  1. In tomcat/conf/server.xml you have to configure a DataSource :
```
    <GlobalNamingResources>
     ...
      <Resource 
          auth="Container" 
          driverClassName="org.apache.derby.jdbc.EmbeddedDriver" 
          maxActive="20" 
          maxIdle="10" 
          maxWait="-1" 
          name="ds/fsnetDS" 
          type="javax.sql.DataSource" 
          url="jdbc:derby:fsnetDB;create=true" 
      />
       ...
    </GlobalNamingResources>
    ...
```

## Git ##

  * Click on Open Perspective and choose Git Repository Exploring.
  * Click on Add an existing local Git repository.
  * Browse to choose your local Git repositery (the new workspace you have created for fsnet) then Finish.
  * Go back to your Project Explorer, select all projects and right click. Choose Team->Share project.
  * Choose git in the next window.

## Tomcat installation ##

  * Right click in your Project Explorer.
  * Select New->Server->Next.
  * Choose Apache->Tomcat v7.0 Server->Next.
  * Choose your apache directory.
  * Click on finish.


# Using Eclipse 3.5 (Galileo) JEE edition #

## Install some plugins for eclipse ##

  * [Subclipse](http://subclipse.tigris.org/servlets/ProjectProcess?pageID=p4wYuA)
  * [m2eclipse](http://m2eclipse.sonatype.org/installing-m2eclipse.html)
> > WARNING : Currently, m2eclipse project is divided in two different update sites ! You MUST install each one.
    * From m2eclipse core update site :
      * Maven integration for eclipse
      * Restart eclipse...
    * From m2eclipse extras update site :
      * Maven integration for WTP
      * Maven handler for Subclipse
      * Maven SCM integration
      * Project configurators for commonly used maven plugins
      * Restart eclipse...

## Check out sources ##

  * Perform a right click in the project explorer view
  * Choose Import -> maven -> Check out maven project from SCM
  * Choose "svn" for the select input.
  * Copy/paste in the text field this url : https://fsnet.googlecode.com/svn/trunk/
> > Note : if you're not a team member you can checkout using http instead of https
  * Click on finish and wait for a while (maven is downloading all the dependencies for you :-))
  * Perform a right click on the trayDesktop project
  * Select Run as -> Maven generate-sources
  * Perform again a right click on this project
  * Select Maven -> update project configuration (m2eclipse should add the target/generated-sources/ directory to your build path)



# Using Eclipse 3.6 (Indigo) JEE Edition #

## Required plugins for eclipse ##
  * [http://www.eclipse.org/egit/download/ Eclipse EGit](.md)
  * [m2eclipse](http://m2eclipse.sonatype.org/installing-m2eclipse.html)
> > You may need install extra plugins which may not be included in your m2eclipse release:
      * m2e - Maven Integration for Eclipse
      * m2e connector for build-helper-maven-plugin
      * m2e connector for mavenarchiver
      * Maven SCM Handler for EGit

## Checkout sources ##

  * Go to Git Repository Perspective
  * Paste your clone url available at http://code.google.com/p/fsnet/source/checkout. This will open a small window where you may have to put your user name and your password (if you're a team member you'll be able to commit your changes thanks to this account)
  * Click on next to choose the branch you wish to import
  * Finally click once again on next to check the final options and click on finish. Then wait for a while, maven is downloading the project.
  * You should now see the repository in the view.
  * Right click on it and choose to import your maven projects. Choose all of them and click on finish on the second frame.
  * Go back to the JAVA EE perspective. Choose all the projects, right click -> team -> share project. Choose git in the next window.
  * Finally click in the top checkbox "use or create repository in parent folder of project" and finish.

Now you should be ready to develop on fsnet and share your modifications.

# Install tomcat in Eclipse #

  * First, be sure that you are under the Java EE perspective
  * Locate the "Servers" view
  * Perform a right click on it
  * Select new -> server
  * in the server list choose : Apache -> Tomcat v6.0 Server
  * Click on next
  * Fill the tomcat installation directory
  * Click on finish

Now you should be ready to develop on fsnet.

# Using Netbeans 6.x #
Download the latest version of Netbeans Java on http://www.netbeans.org
While installing, check Tomcat in the server list.

Launch Netbeans, select Team -> Subversion -> Checkout in the menu.
Enter the following URL : https://fsnet.googlecode.com/svn/trunk/ and click next and finish.

Netbeans asks you if you want to open checked out projects, click yes, select the parent project and check the Open required box then click Finish.
In the project view, right click on the parent project named "Firm Social Network" and select _Clean and Build_.

That's all folks ! Now you can follow the tutorial on http://code.google.com/p/fsnet/wiki/HowToInstall