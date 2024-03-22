#!/bin/bash

# Re run docker containeer with redis and postgresql volumes
docker-compose down
docker-compose -f docker-compose.yaml up -d --build

# Run apring boot applicaton from target directory
ScriptDir=$(pwd)
cd $ScriptDir/target
java -jar app-3.1.3.jar
cd $ScriptDir


