#!/bin/sh
wget https://raw.githubusercontent.com/AdamCurley/CS655PasswordCracker/main/src/worker/Worker.java
javac *.java
java Worker 58888 5