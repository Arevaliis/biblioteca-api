# Primera etapa: construir la aplicación
# Utilizamos una imagen que contiene Maven y Java 21
FROM maven:3.9-eclipse-temurin-21 AS build

# Copiamos todo el proyecto al contenedor
COPY . .

# Compilamos el proyecto y generamos el archivo .jar sin ejecutar los test
RUN mvn clean package -DskipTests

# Segunda etapa: ejecutar la aplicación
# Utilizamos una imagen más ligera que solo contiene Java 21 JRE
FROM eclipse-temurin:21-jre

# Copiamos el .jar generado en la primera etapa
# desde la carpeta target/ al contenedor actual
COPY --from=build target/springboot-biblioteca-api-0.0.1-SNAPSHOT.jar app.jar

# Comando que se ejecutará cuando se inicie el contenedor
# Ejecuta nuestra aplicación Spring Boot mediante el archivo .jar
ENTRYPOINT ["java", "-jar", "app.jar"]

# Indicamos que la aplicación utilizará el puerto 8080
EXPOSE 8080