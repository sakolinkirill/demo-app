FROM openresty/openresty:alpine-fat

WORKDIR /mcdms/etc/

RUN /usr/local/openresty/luajit/bin/luarocks install lua-resty-jwt

EXPOSE 8080

COPY . /mcdms/etc

ENTRYPOINT ["/mcdms/etc/docker-entrypoint.sh"]
