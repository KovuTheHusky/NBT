#!/bin/bash

if [ $TRAVIS_PULL_REQUEST == "true" ]; then
    exit 0
fi

set -e

rm -rf _site
mkdir _site

git clone https://${GITHUB_TOKEN}@github.com/KovuTheHusky/NBT.git --branch gh-pages _site

gradle javadoc publish

cd _site

cd javadoc
echo -n "<!DOCTYPE html><html lang=\"en\"><head><title>NBT</title></head><body><h1>NBT</h1><hr><ul>" > index.html
for D in *; do
    if [ -d "${D}" ]; then
        echo -n "<li><a href=\"${D}\">${D}/</a></li>" >> index.html
    fi
done
echo "</ul><hr></body></html>" >> index.html
cd ..

git add --all
git commit -a -m "Travis #$TRAVIS_BUILD_NUMBER"
git push --force origin gh-pages
