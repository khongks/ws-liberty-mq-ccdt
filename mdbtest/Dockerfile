# docker build -t mdbtest .
# FROM icr.io/appcafe/open-liberty:kernel-slim-java11-openj9-ubi
FROM websphere-liberty:kernel
COPY --chown=1001:0 src/main/liberty/config /config
# RUN features.sh
COPY --chown=1001:0  target/*.war /config/dropins/
# COPY --chown=1001:0 target/*.war /config/apps
RUN configure.sh
# FROM websphere-liberty:kernel
# COPY --chown=1001:0  Sample1.war /config/dropins/
# COPY --chown=1001:0  server.xml /config/
# RUN configure.sh
