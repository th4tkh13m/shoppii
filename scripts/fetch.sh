#!/bin/sh
wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.68/bin/apache-tomcat-9.0.68.tar.gz
tar -xvf apache-tomcat-9.0.68.tar.gz
cp ./tomcat/tomcat-users.xml ./apache-tomcat-9.0.68/conf/
./apache-tomcat-9.0.68/bin/catalina.sh start
