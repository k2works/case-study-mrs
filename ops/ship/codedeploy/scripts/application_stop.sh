#!/bin/bash
SERVICE="/etc/systemd/system/app.service"

if [ -e $SERVICE ]; then
  systemctl stop app.service
fi
