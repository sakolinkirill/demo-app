#!/bin/sh

export JWT_SECRET=$(cat "/mcdms/etc/key.pub")

echo "Starting nginx..."
/usr/local/openresty/bin/openresty -g 'daemon off;' -c /mcdms/etc/nginx.conf