# FSNET #

# Needs #

### In order to use FSNET, you will have to install : ###
  * A java 6 compliant JVM
  * A servlet container (like Apache Tomcat)

# How to install FSNET #

> ### Theses explanations are for apache tomcat 6.x users : ###

  1. Download fsnet-0.8.0-bin.zip
  1. Unzip the fsnet-0.8.0-bin.zip archive. you will find three folders : wars/ , tray/ and libs/
  1. Copy libs/derby-`*`.jar dependency in the tomcat/lib/ directory
  1. Copy the wars/admin.war, wars/fsnetWeb.war and wars/webservice.war files in the tomcat/webapps
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
  1. In tomcat/conf/server.xml you have to force UTF-8 encoding :
```
    <Connector port="8080" protocol="HTTP/1.1" 
               connectionTimeout="20000" 
               redirectPort="8443"
               URIEncoding="UTF-8" />
```
  1. Run Tomcat
  1. Run the project in your web browser :
    * http://localhost:8080/admin for the administrator (with login & password)
    * http://localhost:8080/fsnetWeb for users
    * http://localhost:8080/webservice for the webservice used by tray

  1. In order to run the tray application you will find the trayDesktop.jar in tray/ directory. So, to execute this application you have to run it using :
```
 java -jar trayDesktop.jar
```

> ### Note ###

The previous explanation show how to configure fsnet to use an embedded DB (derby)
You can easily configure fsnet to use a [mySQL](http://www.mysql.com/) or [MariaDB](https://mariadb.org/) instance :
  * In the 3rd step above, copy mysql-connector-java-`*`.jar instead of derby-**.jar
  * In 7th step above, configure your server.xml using this template :
```
    <GlobalNamingResources>
     ...
      <Resource 
          auth="Container" 
          driverClassName="com.mysql.jdbc.Driver" 
          maxActive="20" 
          maxIdle="10" 
          maxWait="-1" 
          name="ds/fsnetDS" 
          password="YOUR_PASSWORD" 
          type="javax.sql.DataSource" 
          url="jdbc:mysql://localhost/YOUR_DATABASE" 
          username="YOUR_USER"/>
       ...
    </GlobalNamingResources>
    ...
```**

# How to use FSNET #
After you have installed FSNET, you have to join the admin part.
At this time, you should configure the SMTP server access because when you add
a new user, a password is generated and sent in the confirmation email.

For example to configure SMTP server using a gmail account :
  * SMTP Server : smtp.gmail.com
  * Port : 465
  * Sender : yourname@gmail.com
  * Enable TLS : checked
  * Enable SSL : checked
  * Enable authentication : checked
  * Username : yourname
  * Password : `******`
  * Fsnet address : the address of fsnet's public part that will be sent in the confirmation mail


# How to build from sources #

In order to build fsnet you must install :
  * Apache Maven = 3.0.2

In the university we use a Nexus Proxy (hosted on SMASTER). You can use this template of .m2/settings.xml :

```
<settings>
<mirrors>
    <mirror>
      <id>Nexus_SMASTER</id>
      <name>Nexus Public Mirror on SMATER</name>
      <url>http://192.168.64.1:8081/nexus/content/groups/public</url>
      <mirrorOf>*</mirrorOf>
    </mirror>
</mirrors>
<proxies>
   <proxy>
      <active>true</active>
      <protocol>http</protocol>
      <host>cache-etu.univ-artois.fr</host>
      <port>3128</port>
      <nonProxyHosts>192.168.64.1</nonProxyHosts>
    </proxy>
  </proxies>
</settings>

```

Instructions :

  1. Checkout the last revision of fsnet using : svn checkout http://fsnet.googlecode.com/svn/trunk/ fsnet-read-only
  1. cd fsnet-read-only/
  1. mvn package
  1. wait for a while
  1. you can take deliverables in assembler/target/FSnet-$version$-bin.zip

