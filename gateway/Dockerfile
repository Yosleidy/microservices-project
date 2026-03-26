
FROM eclipse-temurin:17
WORKDIR /app
COPY target/*.jar app.jar
COPY wait-for-it-parallel.sh wait-for-it-parallel.sh

# Da permisos de ejecución
RUN chmod +x wait-for-it-parallel.sh


# Instala dependencias y da permisos al script
RUN apt-get update && \
    apt-get install -y netcat-openbsd ca-certificates && \
    chmod +x wait-for-it-parallel.sh

# Expone el puerto del gateway
EXPOSE 8080

# Espera a que los servicios dependientes estén disponibles y luego ejecuta el JAR
ENTRYPOINT ["./wait-for-it-parallel.sh", "user-service:8081", "product-service:8083", "order-service:8082", "--", "java", "-jar", "app.jar"]