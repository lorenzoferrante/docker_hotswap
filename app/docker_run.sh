#!/bin/sh
mvn clean package && docker build -t jetbrains/runtime:jbr17env .
docker run -ti --rm -p 8080:8080 -p 8000:8000 -v `pwd`/target/classes:/extra_class_path -v /var/run/docker.sock:/var/run/docker.sock jetbrains/runtime:jbr17env
