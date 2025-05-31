FROM mcr.microsoft.com/azure-functions/java:4-java17-build AS installer-env

COPY . /build/java-function-app
RUN cd /build/java-function-app && \
    mkdir -p /home/site/wwwroot && \
    mvn clean package -Dmaven.test.skip=true && \
    cd ./target/azure-functions/ && \
    cd $(ls -d */|head -n 1) && \
    cp -a . /home/site/wwwroot

FROM mcr.microsoft.com/azure-functions/java:4-java17
COPY --from=installer-env ["/home/site/wwwroot", "/home/site/wwwroot"]
EXPOSE 80

