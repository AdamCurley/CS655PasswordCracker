#!/bin/sh
printf '' | sudo add-apt-repository ppa:openjdk-r/ppa
sudo apt-get update
printf 'y' | sudo apt-get install openjdk-8-jdk