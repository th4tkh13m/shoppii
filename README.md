# Shoppii
A version of Shoppee written in pure Java.

## Getting Started
### Prerequisite
- Java (JDK 11+)
- [Maven](https://maven.apache.org/download.cgi) (Not required if using Netbeans)
- [Apache Tomcat](https://tomcat.apache.org/download-90.cgi) (version 7+)

### Installation
1. Install Tomcat Server, add an `admin` account to `tomcat-users.xml` located at
`apache-tomcat-{version}/conf/` by adding the following line into `<tomcat-users>` tag:
```
<user password="admin" roles="manager-script,admin" username="admin"/>
```
2. Start Tomcat Server.
- Linux:
```
./apache-tomcat-{version}/bin/catalina.sh start
```
3. Running the Web App
- Clean and package the Maven project:
```
mvn -B clean package --file shoppii/pom.xml
```
- Deploy the Web App using Tomcat
```
mvn tomcat7:redeploy --file shoppii/pom.xml
```
### Development within Visual Studio Code
Since this project has already include the necessary files to running tasks (`tasks.json`) in VSCode, developers only need to use their `Run Build Tasks` keyboard shortcut to clean and redeploy the Web App.
