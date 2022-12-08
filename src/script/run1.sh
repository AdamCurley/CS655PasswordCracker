#!/bin/sh
wget https://raw.githubusercontent.com/AdamCurley/CS655PasswordCracker/master/src/worker/Worker.java
javac *.java
java Worker 58888 1