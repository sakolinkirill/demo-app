FROM python:2

ENV SUBDOMAIN_APP1 app1.local.com
ENV SUBDOMAIN_APP2 app2.local.com
ENV HOST_AUTHAPP host.docker.internal:8090
ENV HOST_APP1 host.docker.internal:8091
ENV HOST_APP2 host.docker.internal:8092

WORKDIR /cg/etc/

RUN pip install pyyaml jinja2

COPY . /cg

ENTRYPOINT ["/cg/docker-entrypoint.sh"]