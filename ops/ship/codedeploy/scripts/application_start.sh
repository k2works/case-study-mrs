#!/bin/bash

systemctl stop app.service
systemctl daemon-reload
systemctl start app.service
systemctl restart nginx
