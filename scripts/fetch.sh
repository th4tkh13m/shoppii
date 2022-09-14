#!/bin/sh
curl -fsSL https://dlcdn.apache.org/tomcat/tomcat-8/v8.5.82/bin/apache-tomcat-8.5.82.tar.gz
tar -xvf apache-tomcat-8.5.82.tar.gz
cp ./tomcat/tomcat-users.xml ./apache-tomcat-8.5.82/conf/
./apache-tomcat-8.5.82/bin/catalina.sh start