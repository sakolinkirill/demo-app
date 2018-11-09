FROM python:2

ENV SUBDOMAIN_APP1 app1.local.com
ENV SUBDOMAIN_APP2 app2.local.com
ENV HOST_AUTHAPP localhost:8090
ENV HOST_APP1 localhost:8091
ENV HOST_APP2 localhost:8092

WORKDIR /cg/etc/

RUN pip install pyyaml jinja2

COPY . /cg

ENTRYPOINT ["/cg/docker-entrypoint.sh"]