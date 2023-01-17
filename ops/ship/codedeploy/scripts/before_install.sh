#!/bin/bash

yum list installed java-17-amazon-corretto-headless &>/dev/null
if [ $? != 0 ]; then
  yum install -y java-17-amazon-corretto-headless
fi

curl --silent --location https://rpm.nodesource.com/setup_16.x | bash -
yum -y install nodejs
npm install -g yarn

amazon-linux-extras enable nginx1.12
yum list installed nginx &>/dev/null
if [ $? != 0 ]; then
  yum install -y nginx
fi

if [ -d /srv/app ]; then
  rm -rf /srv/app
fi
