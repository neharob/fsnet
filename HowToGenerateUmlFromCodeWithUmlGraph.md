# UmlGraph #

![http://img16.imageshack.us/img16/8420/graphc.png](http://img16.imageshack.us/img16/8420/graphc.png)

## Needs : ##

#### In order to use UMLGraph, you will have to access _tools.jar_. ####

## Introduction : ##
> UMLGraph allows the declarative specification and drawing of UML class and sequence diagrams. The specification is done in text diagrams, that are then transformed into the appropriate graphical representations.

## Install : ##
  1. Download UMLGraph [UmlGraph-5.4.jar](http://www.umlgraph.org/download.html)
  1. To view the converted file (.dot to png,gif,...), install graphviz
```
      apt-get install graphviz.
```
### Running Java : ###
    * There are two ways to use UMLGraph which generates a file (.dot):
      1. If the umlGraph.jar file is in the directory that also contains the Java SDK tools.java.
```
	   java -jar /path/to/UmlGraph.jar -all -private yourfile1.java
```
      1. In a general case, we can use that :
```
           java -classpath '/path/to/UmlGraph.5.4.jar:/path/to/tools.jar'
 org.umlgraph.doclet.UmlGraph -all -private yourfile1.java
```
> For more options, http://www.umlgraph.org/doc/cd-opt.html
    * To convert the file (.dot) into an image :
```
	    dot -Tpng -ograph.png graph.dot
```
> In this example, the generated file is “graph.png”.

### Running Ant: ###
There are two ways to generate UML diagrams :
  * Just generate the diagram uml,
  * Or generate diagram uml with javadoc

To run the UMLGraph doclet from ant, use a rule, like the following(Only UML class).

```
<target name="uml" depends="prepare">
       <property name="uml.dir" value="${basedir}/uml"/>
       <property name="src.uml.dir" value="${src.dir}/uml"/>
       <mkdir dir="${uml.dir}"/>
       <path id="uml.source.path">
           <pathelement path="${src.uml.dir}/"/>
           <pathelement path="${src.java.dir}"/>
         </path>
       <javadoc sourcepathref="uml.source.path" packagenames="*" package="true">
           <doclet name="org.umlgraph.doclet.UmlGraph" path="${basedir}/lib/UmlGraph.jar">
               <param name="-d" value="${uml.dir}"/>
			<param name="-all"/> 
			<param name="-constructors"/>
           </doclet>
       </javadoc>
       <apply executable="dot" dest="${uml.dir}" parallel="false">
         <arg value="-Tpng"/>
         <arg value="-o"/>
         <targetfile/>
         <srcfile/>
         <fileset dir="${uml.dir}" includes="*.dot"/>
         <mapper type="glob" from="*.dot" to="*.png"/>
       </apply>
   </target>
```
In build.xml
```
    ant  uml
```


To run the umlGraphDoc doclet from ant, use a rule, like the following (UMLClass and javaDoc).

```
<target name="javadocs" depends="compile">
        <javadoc sourcepath="${src}" packagenames="org.umlgraph.doclet.*" destdir="${javadoc}" 
                 private="true">
                <doclet name="org.umlgraph.doclet.UmlGraphDoc" path="${lib}/UMLGraph.jar">
                  <param name="-all"/> 
			<param name="-constructors"/>
                </doclet>
        </javadoc>
</target>
```
In build.xml
```
    ant javadocs
```

### Running Maven: ###

```
 <reporting>

    <plugins>

      <plugin>

        <groupId>org.apache.maven.plugins</groupId>

        <artifactId>maven-javadoc-plugin</artifactId>

        <version>2.7</version>

        <configuration>

          <doclet>org.umlgraph.doclet.UmlGraphDoc</doclet>

          <docletArtifact>

            <groupId>org.umlgraph</groupId>

            <artifactId>doclet</artifactId>

            <version>5.1</version>

          </docletArtifact>

					<show>private</show>

          <additionalparam>-all -constructors</additionalparam>

          <useStandardDocletOptions>false</useStandardDocletOptions>

        </configuration>

      </plugin>

    </plugins>

  </reporting>
```
In pom.xml
```
 mvn site
```

## Simple Example : ##
Here is a simple to add relationship between classes.
Comments, used int this classes, generate, properly, the uml diagram.

```
   /**
 * @has 1.2 has * Child
 */
public class Parent extends Personne {
	private List<Personne> childs;
	public Parent(String lastname, String firstname) {
		super(lastname, firstname);
		childs = new ArrayList<Personne>();
	}
	public List<Personne> getChilds() {
		return childs;
	}
	public void setChilds(List<Personne> childs) {
		this.childs = childs;
	}
}
```

```
    /**
 *@has 1.2 has * Pet 
 */
public class Personne {
	private String lastname;
	private String firstname;
	private List<Pet> pets;
	public Personne(String lastname, String firstname) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public List<Pet> getPets() {
		return pets;
	}
	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
}
```

```
   public class Child extends Personne {
	private Parent mom;
	private Parent dad;
	public Child(String lastname, String firstname,Parent mom,Parent dad) {
		super(lastname, firstname);
		this.mom = mom;
		this.dad = dad;
	}
	public Parent getMom() {
		return mom;
	}
	public void setMom(Parent mom) {
		this.mom = mom;
	}
	public Parent getDad() {
		return dad;
	}
	public void setDad(Parent dad) {
		this.dad = dad;
	}
}
```

```
    /**
 * @composed 1 has * Limb
 */
public class Pet {
	private String nom;
	private List<Limb> limb;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Limb> getLimb() {
		return limb;
	}
	public void setLimb(List<Limb> limb) {
		this.limb = limb;
	}	
}
```

```
    public class Limb {
	public String nom;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
}
```

After the use of previous orders, we have this class diagram :

![http://img156.imageshack.us/img156/3485/imagewl.png](http://img156.imageshack.us/img156/3485/imagewl.png)


The UMLGraph class diagrams allows you to model
  * stereotypes (using the @stereotype name tag)
  * tagged values (using the @tagvalue name value tag)
  * implementation relationships (specified using the Java implements declaration)
  * generalization relationships (specified using the Java extends     declaration or (for multiple inheritance) the javadoc @extends tag)
  * association relationships (specified using the javadoc @assoc tag)
  * navigatable (directed) association relationships (specified using the javadoc @navassoc tag)
  * aggregation relationships (specified using the javadoc @has tag)
  * composition relationships (specified using the javadoc @composed tag)
  * dependency relationships (specified using the javadoc @depend tag)



In our example, we have a composition relationships between 'Pet' and 'Limb' which is entitled 'has' :
```
/**
 * @composed 1 has * Limb
 */
public class Pet {
...
}
```
We have an aggregation relationships between 'Pet' and 'Limb' which is
entitled 'has' :
```
/**
 *@has 1.2 has * Pet 
 */
public class Personne {
...
}
```