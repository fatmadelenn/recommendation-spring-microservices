#!/bin/sh

cd eureka; docker build -t eureka .; cd ..
cd viewproducer; docker build -t viewproducer .; cd ..
cd streamreader; docker build -t streamreader .; cd ..
cd etl; docker build -t etlprocess .; cd ..
cd ecommerce; docker build -t ecommerce .; cd ..
cd client; docker build -t client .; cd ..