FROM jetbrains/runtime:jbr17env

RUN curl -L -o /jdk17.0.1.tar.gz "https://cache-redirector.jetbrains.com/intellij-jbr/jbr_dcevm-17-linux-x64-b135.1.tar.gz"
RUN tar -xvf /jdk17.0.1.tar.gz
RUN mv /jbr /jdk17.0.1
#RUN mkdir -p /jdk17.0.1/lib/dcevm
#COPY /jdk17.0.1/lib/server/libjvm.so /jdk17.0.1/lib/dcevm/

# Set Java 17.0.0
ENV JAVA_HOME="/jdk17.0.1"
ENV PATH="${JAVA_HOME}/bin:$PATH"

# Install HotSwap Agent
RUN mkdir -p /opt/hotswap-agent/ \
    && curl -L -o /opt/hotswap-agent/hotswap-agent-1.4.2-SNAPSHOT.jar "https://github.com/HotswapProjects/HotswapAgent/releases/download/1.4.2-SNAPSHOT/hotswap-agent-1.4.2-SNAPSHOT.jar" \
    && ln -f -s /opt/hotswap-agent/hotswap-agent-1.4.2-SNAPSHOT.jar /opt/hotswap-agent/hotswap-agent.jar

# Set up Tomcat
ENV ARCHIVE apache-tomcat-8.5.73
ENV INSTALL_DIR /opt
ENV SERVER_HOME ${INSTALL_DIR}/${ARCHIVE}
RUN curl -o ${SERVER_HOME}.zip -L http://apache.mirror.iphh.net/tomcat/tomcat-8/v8.5.73/bin/apache-tomcat-8.5.73.zip
RUN unzip ${SERVER_HOME}.zip -d /opt
RUN chmod a+x ${SERVER_HOME}/bin/catalina.sh
ENV DEPLOYMENT_DIR ${SERVER_HOME}/webapps/
ENTRYPOINT ${SERVER_HOME}/bin/catalina.sh run
EXPOSE 8080

# Set up DCEVM
#RUN mkdir -p /jdk17.0.0/lib/dcevm/
#COPY /jdk17.0.0/lib/server/libjvm.so /jdk17.0.0/lib/dcevm/

RUN mkdir -p /extra_class_path
COPY ./target/demo-1.0-SNAPSHOT.war ${DEPLOYMENT_DIR}
EXPOSE 8000
#ENV JAVA_OPTS="-XXaltjvm=dcevm -agentlib:jdwp=transport=dt_socket,server=y,address=8000,suspend=n -javaagent:/opt/hotswap-agent/hotswap-agent.jar -Dextra.class.path=/extra_class_path -Dwebapp.dir=/webapp_dir"
ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,address=8000,suspend=n -javaagent:/opt/hotswap-agent/hotswap-agent.jar -Dextra.class.path=/extra_class_path -Dwebapp.dir=/webapp_dir --add-opens java.base/jdk.internal.loader=ALL-UNNAMED --add-opens java.desktop/com.sun.beans.introspect=ALL-UNNAMED"



