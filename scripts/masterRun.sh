#!/bin/sh
wget https://raw.githubusercontent.com/AdamCurley/CS655PasswordCracker/main/src/Master.java
wget https://raw.githubusercontent.com/AdamCurley/CS655PasswordCracker/main/src/MasterHandler.java
wget https://raw.githubusercontent.com/AdamCurley/CS655PasswordCracker/main/src/Md5.java
wget https://raw.githubusercontent.com/AdamCurley/CS655PasswordCracker/main/src/Test.java
javac *.java
java Test