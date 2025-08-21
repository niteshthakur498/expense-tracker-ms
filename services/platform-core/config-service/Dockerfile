# Use a base image with Java runtime
FROM eclipse-temurin:21-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/*.jar app.jar
	
# Install curl for healthcheck and clean apt cache to reduce image size
RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*
    
# Create a non-root user
#RUN useradd -m springuser && chown springuser:springuser /app/app.jar
#USER springuser

# Create logs directory and set ownership
#RUN mkdir -p /app/logs && chown -R springuser:springuser /app/logs

# Expose the port the Spring Boot application runs on
EXPOSE 7001

HEALTHCHECK --interval=30s --timeout=5s --start-period=10s \
  CMD curl -f http://localhost:7001/actuator/health || exit 1
  
# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]

