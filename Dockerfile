FROM java:8
VOLUME /tmp

COPY build/libs/order-service-*.jar /work/app.jar
COPY run.sh /

ENTRYPOINT ["/run.sh"]
