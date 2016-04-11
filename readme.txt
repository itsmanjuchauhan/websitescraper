In order to create this scraper, I have used JSOUP library. 

Steps to run this programme-

1) Build this project using Maven. It will build two jars, one with dependencies and other without dependencies.
2) In order to create the JSON document, either run the class Console in Eclipse IDE or run the following command on command prmpt
java -jar websitescraper-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.websitescraper.console.Console

In case user enters an incorrect website (not Sainsbury), exception will be logged and empty JSON document will be returned.

All the dependencies are mentioned in pom.xml