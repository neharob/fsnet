# Prerequisites: #

Depending on your System, visit [OpenFire Web Site](http://www.igniterealtime.org/projects/openfire/) then click on the [Downloads](http://www.igniterealtime.org/downloads/index.jsp) tab then select your Operating system and download it.

# Installation: #

**Windows**

Run the Openfire installer. The application will be installed to c:\Program Files\Openfire by default.

**Linux/Unix**

Choose either the RPM or tar.gz build. If using the RPM, run it using your package manager to install Openfire to /opt/openfire:

```

rpm -ivh openfire_3_7_1.rpm

```

If using the .tar.gz, extract the archive to /opt or /usr/bin:

```

tar -xzvf openfire_3_7_1.tar.gz

mv openfire /opt

```

Note: the .tar.gz build does not contain a bundled Java runtime (JRE). Therefore, you must have JDK or JRE 1.5.0 (Java 5) or later installed on your system. You can check your java version by typing "java -version" at the command line and (if necessary) upgrade your Java installation by visiting http://java.sun.com.

### Setup Overview ###

To complete the installation of Openfire, you'll need to perform each of the following steps:

  * Database - if you choose to use an external database, you must prepare your database for Openfire.
  * Setup - Use the built-in web-based setup tool to setup and verify the server configuration.
  * Admin Console - use the web-based admin tool to manage the server.



Files in the Distribution

The files in your distribution should be as follows (some sub-directories omitted for brevity):
```
openfire/
 |- readme.html
 |- license.html
 |- conf/
 |- bin/
 |- jre/
 |- lib/
 |- plugins/
     |- admin/
 |- resources/
     |-database/
     |-security/
 |- documentation/
```

  * The conf directory is where Openfire stores configuration files.
  * The bin directory contains the server executables. Depending on which distribution you installed, different executables will be available.
  * The jre directory contains a Java 5 runtime that is bundled with the Windows and RPM versions of Openfire.
  * The lib directory contains libraries necessary for running Openfire.
  * The plugins directory contains server plugins. By default, Openfire ships with a web-based admin console plugin.
  * The resources/database directory contains SQL schema files to create new Openfire databases, as well as upgrade scripts for existing installations.
  * The resources/security directory is where Openfire maintains keystores to support SSL connection security.
  * The documentation directory contains server documentation.

#### Setup the Database ####

Openfire can store its data in an embedded database or you can choose to use an external database such as MySQL or Oracle. If you would like to use an external database, you must prepare it before proceeding with installation.

We are using MySQL, here is how to configure it on your working space :
JDBC Drivers

The JDBC driver for MySQL is bundled with Openfire, so you do not need to download and install a separate driver.

In the Openfire setup tool, use the following values:
```
    driver: com.mysql.jdbc.Driver
    server: jdbc:mysql://[YOUR_HOST]/[DATABASE_NAME] 
```
where **_YOUR\_HOST_** and **_DATABASE\_NAME_** are the actual values for you server. In many cases localhost is a suitable value for **_YOUR\_HOST_** when your database is running on the same server as your webserver.

#### Setup instructions ####
> - Make sure that you are using MySQL 4.1.18 or later (5.x recommended) ¹.

> - Create a database for the Openfire tables:
    * mysqladmin create [databaseName](databaseName.md)
> > (note: "databaseName" can be something like 'openfire')

> - Import the schema file from the resources/database directory of the installation folder:
```
    Unix/Linux: cat openfire_mysql.sql | mysql [databaseName];
    Windows   : type openfire_mysql.sql | mysql [databaseName];
```
> - Start the Openfire setup tool, and use the appropriate JDBC connection settings.

Setup the Server

A web-based, "wizard" driven setup and configuration tool is built into Openfire. Simply launch Openfire (platform-specific instructions below) and use a web browser to connect to the admin console. The default port for the web-based admin console is 9090. If you are on the same machine as Openfire, the following URL will usually work:
```
http://127.0.0.1:9090  
Or
http://localhost:9090  
```


Initial setup and administration can also be done from a remote computer using LAN IP address instead or hostname if it is resolvable by the remote computer. Windows Server administrators should add
```
http://127.0.0.1 
```
address to Internet Explorer's Trusted Sites list, if Enhanced Security configuration is enabled in Internet Explorer. Otherwise they will get a blank screen.

Openfire can store its data in an embedded database or you can choose to use an external database such as MySQL or Oracle. If you would like to use an external database, you must prepare it before proceeding with installation.

### Configure OpenFire / FSNET ###
While configuring OpenFire with the mentioned wizard please respect those instructions :
```
xmpp.server=localhost
xmpp.domain=fsnet
xmpp.server.port=5222
xmpp.password=fsnetpassword;


```
## Running Openfire in Windows ##

If you used the Openfire installer, a shortcut for starting the a graphical launcher is provided in your Start Menu. Otherwise, run openfire.exe in the bin/ directory of your Openfire installation.

Windows Service

If you're running Openfire on Windows, you will likely want to run Openfire as a standard Windows service after initial setup. If you used the Windows installer, a openfire-service.exe file will be in the bin directory of the installation. You can use this executable to install and control the Openfire service.
From a console window, you can run the following commands:

```

    openfire-service /install -- installs the service.
    openfire-service /uninstall -- uninstalls the service.
    openfire-service /start -- starts the service
    openfire-service /stop -- stops the service. 
```
Running Openfire in Linux/Unix
If you are running on a Red Hat or Red Hat like system (CentOS, Fedora, etc), we recommend using the RPM as it contains some custom handling of the standard Red Hat like environment. Assuming that you have used the RPM, you can start and stop Openfire using the /etc/init.d/openfire script.
```
# /etc/init.d/openfire
Usage /etc/init.d/openfire {start|stop|restart|status|condrestart|reload}
# /etc/init.d/openfire start
Starting openfire: 
```
> If you would like to install Openfire as a service, two scripts are provided in the bin/extra directory:

- **redhat-postinstall.sh** -- automatically installs Openfire as a service on Red Hat. It does so by creating a "jive" user and then copying the openfired script to your init.d directory. This script must be run as root. Please see the comments in the script for additional information.


- **openfired** -- script to run Openfire as a service. You must manually configure this script. See the comments in the script for additional details.

**It is not recommended that you use either of these scripts if you installed via RPM. The RPM has already taken care of what these scripts take care of.**