#!/bin/bash

echo '-==================================================-'
echo '-= CLEAN                                          =-'
echo '-==================================================-'

cd back
mvn clean
cd ..

cd front
rm -rf dist/*
rm -rf node_modules/*
cd ..

