#!/bin/sh
wget https://raw.githubusercontent.com/AdamCurley/CS655PasswordCracker/main/src/Master.java
wget https://raw.githubusercontent.com/AdamCurley/CS655PasswordCracker/main/src/MasterHandler.java
wget https://raw.githubusercontent.com/AdamCurley/CS655PasswordCracker/main/src/Md5.java
wget https://raw.githubusercontent.com/AdamCurley/CS655PasswordCracker/main/src/TestForAny/TestForAny.java
javac *.java
java TestForAny