#!/usr/bin/env bash

VERSION=1.5.0-SNAPSHOT

rm -rf ./core/verticle/*
cp ../acme-forms-adapter-multistep/build/libs/acme-forms-adapter-multistep-${VERSION}.jar ./core/verticle/
cp ../acme-forms-adapter-http/build/libs/acme-forms-adapter-http-${VERSION}.jar ./core/verticle/

rsync ~/.m2/repository/io/knotx/knotx-forms-*/1.5.0-SNAPSHOT/knotx-forms-*-${VERSION}.jar ./core/verticle/

docker-compose up  --force-recreate --build
