FROM amazoncorretto:21.0.1

WORKDIR /app

# Amazon Linux OS
RUN yum update -y && \
    yum upgrade -y && \
    yum install -y procps-ng


