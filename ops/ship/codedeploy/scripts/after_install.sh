#!/bin/bash
. $(dirname $0)/common_variables.sh
. $(dirname $0)/paramstore.sh > /srv/app/service/env

cp /srv/app/nginx/nginx.conf /etc/nginx
cp /srv/app/nginx/https.conf /etc/nginx/conf.d
cp /srv/app/nginx/http.conf /etc/nginx/conf.d
cp /srv/app/nginx/cert.crt /etc/nginx
cp /srv/app/nginx/cert.key /etc/nginx

cp /srv/app/service/env /srv/app
cp /srv/app/service/app.service /etc/systemd/system

if [ ! -d /srv/app/pids ]; then
  mkdir /srv/app/pids
fi

chown -hR ec2-user:ec2-user /srv/app
chown -hR ec2-user:ec2-user /srv/app/pids
