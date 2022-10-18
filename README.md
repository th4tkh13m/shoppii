# Shoppii
A version of Shoppee written in pure Java.

## I. Getting Started
### 1. Prerequisite
- Java (JDK 11+)
- [Maven](https://maven.apache.org/download.cgi) (Not required if using Netbeans)
- [Apache Tomcat](https://tomcat.apache.org/download-90.cgi) (version 7+)

### 2. Installation
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
4. Access the Web App at http://localhost:8080/shoppii/api

## II. API Usage
### 1. Register
Access at http://localhost:8080/shoppii/api/user/register
- Method: `POST`

Parameters:

| Key      | Values           
| ------------- |:-------------:|
| `phone`     | User's phone to register. Must be not exist on the system's DB | 
| `password`      | User's password to register      | 
| `rePassword` | Re Enter user's password     | 

### 2. Login with Google
Access at http://localhost:8080/shoppii/api//user/googleAuthentication
- Method: `POST`

Parameters:

| Key      | Values           
| ------------- |:-------------:|
| `name`     | User's Google's account name | 
| `email`     | User's Google's account email address. Must be not exists on the system's DB. | 
| `password`      | User's password to register      | 
| `rePassword` | Re Enter user's password     | 
## III. Development within Visual Studio Code
Since this project has already include the necessary files to running tasks (`tasks.json`) in VSCode, developers only need to use their `Run Build Tasks` keyboard shortcut to clean and redeploy the Web App.

