# CS655PasswordCracker
Password Cracker Mini Project for CS655


## Running on any node

1. Use the rspec file "lab666-yiran_request_rspec.xml" to reserve resources on GENI.

2. ssh into 5 worker nodes and 1 master node.

3. Use the script "setup.sh" to install jdk on every node:
```
wget https://raw.githubusercontent.com/AdamCurley/CS655PasswordCracker/main/scripts/setup.sh
bash setup.sh 
```

4. Run the server on worker1:
```
wget https://raw.githubusercontent.com/AdamCurley/CS655PasswordCracker/main/src/TestForAny/Worker.java
javac *.java
java Worker <port_number> 1 
```

5. Run the server on worker2:
```
wget https://raw.githubusercontent.com/AdamCurley/CS655PasswordCracker/main/src/TestForAny/Worker.java
javac *.java
java Worker <port_number> 2 
```

6. Run the server on worker3:
```
wget https://raw.githubusercontent.com/AdamCurley/CS655PasswordCracker/main/src/TestForAny/Worker.java
javac *.java
java Worker <port_number> 3 
```

7. Run the server on worker4:
```
wget https://raw.githubusercontent.com/AdamCurley/CS655PasswordCracker/main/src/TestForAny/Worker.java
javac *.java
java Worker <port_number> 4 
```

8. Run the server on worker5:
```
wget https://raw.githubusercontent.com/AdamCurley/CS655PasswordCracker/main/src/TestForAny/Worker.java
javac *.java
java Worker <port_number> 5 
```

9. Use script masterRunAny.sh to run client on master:
```
wget https://raw.githubusercontent.com/AdamCurley/CS655PasswordCracker/main/scripts/AnyNode/masterRunAny.sh
bash masterRunAny.sh 
```
