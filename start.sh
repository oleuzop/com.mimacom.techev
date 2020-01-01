#!/bin/bash


echo '-==================================================-'
echo '-= BACK                                           =-'
echo '-==================================================-'
cd back
mvn clean install
cd ..


echo '-==================================================-'
echo '-= FRONT                                          =-'
echo '-==================================================-'

cd front
npm install
#npm run build -c production
cd ..



echo '-==================================================-'
echo '-= DOCKER                                         =-'
echo '-==================================================-'

docker-compose build
docker-compose up


