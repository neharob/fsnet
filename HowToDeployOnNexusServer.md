# Introduction #

Deployment of JAR and WAR can be performed in this project. This how to summarize the process.

## Maven settings.xml ##

The settings.xml file must be edited to indicate the nexus access :

```
<settings>
...
 <servers>
  <server>
	  <id>nexus-dev-smaster</id>
	  <username>admin</username>
	  <password>admin123</password>
  </server>
  </servers>
...
<settings>
```

In this example, default nexus's accesses is given (replace with your own one).

## Maven setting ##

In your master pom.xml you should specify some information. Please refer to [the nexus guide](http://www.sonatype.com/books/nexus-book/reference/staging-sect-deployment.html#staging-sect-deployment-nexus-staging-maven-plugin).

In our case, we use the nexus plugin :

```
<build>
...
		<plugins>
			<plugin>
				<groupId>org.sonatype.plugins</groupId>
				<artifactId>nexus-staging-maven-plugin</artifactId>
				<version>1.2</version>
				<extensions>true</extensions>
				<configuration>
					<serverId>nexus-dev-smaster</serverId>
					<nexusUrl>${nexus.url}</nexusUrl>
				</configuration>
			</plugin>
...
	</build>
```

**NOTE : the "serverId" tag must refers to the maven server id.**

To finish, we have to refer the "distributionManagement" section:

```
<distributionManagement>

		<!-- Publish versioned releases here -->
		<repository>
			<id>nexus-dev-smaster</id>
			<name>nexus-smaster-releases</name>
			<url>${nexus.url}/content/repositories/releases</url>
		</repository>

		<!-- Publish snapshots here -->
		<snapshotRepository>
			<id>nexus-dev-smaster</id>
			<name>nexus-smaster-snapshot</name>
			<url>${nexus.url}/content/repositories/snapshots</url>
		</snapshotRepository>
	</distributionManagement>
```

**NOTE : the "id" tag must refer to the maven server id.**

## Deployment ##

To deploy you simply have to run :

```
mvn deploy
```