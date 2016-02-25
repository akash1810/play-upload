#!/bin/bash

NGINX_VERSION=1.8.1
NGINX_LOCATION=$PWD/nginx

rm -rf ./nginx-${NGINX_VERSION}
rm -rf ${NGINX_LOCATION}

wget http://nginx.org/download/nginx-${NGINX_VERSION}.tar.gz
tar -xvzf nginx-${NGINX_VERSION}.tar.gz

ln -s ./nginx-${NGINX_VERSION} ${NGINX_LOCATION}

pushd ${NGINX_LOCATION}

./configure --prefix=${NGINX_LOCATION}
make

mkdir logs
chmod +w logs
touch logs/access.log
touch logs/error.log

popd

rm -f ${NGINX_LOCATION}/conf/nginx.conf
ln -s $PWD/nginx.conf ${NGINX_LOCATION}/conf/nginx.conf

rm -f nginx-${NGINX_VERSION}.tar.gz*