#!/usr/bin/env bash

rm -rf ./core/verticle/*
cp ../acme-forms-adapter-multistep/build/libs/acme-forms-adapter-multistep-1.5.0-SNAPSHOT.jar ./core/verticle/
docker-compose up  --force-recreate --build
