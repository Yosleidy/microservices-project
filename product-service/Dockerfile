FROM eclipse-temurin:17-jdk-jammy

# Carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR generado por Maven
COPY target/*.jar app.jar

# Expone el puerto de product-service
EXPOSE 8083

# Comando para ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]