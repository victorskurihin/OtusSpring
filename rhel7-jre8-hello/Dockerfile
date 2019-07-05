# A Java 8 runtime example
# The official Red Hat registry and the base image
FROM registry.redhat.io/redhat-openjdk-18/openjdk18-openshift
USER root
# Expose port to listen to
EXPOSE 8080
# Copy the MicroProfile starter app
COPY gs-serving-web-content-0.1.0.jar /app/
# Copy the script from the source; run-java.sh has specific parameters to run a Thorntail app from the command line in a container. More on the script can be found at https://github.com/sshaaf/rhel7-jre-image/blob/master/run-java.sh
COPY run-java.sh /app/
# Setting up permissions for the script to run
RUN chmod 755 /app/run-java.sh
# Finally, run the script
CMD [ "/app/run-java.sh" ]
