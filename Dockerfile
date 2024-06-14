# Estágio de construção
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

# Copia o pom.xml primeiro para evitar baixar dependências novamente se não houver mudanças
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte e compila o projeto
COPY src ./src
RUN mvn package

# Estágio de produção
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia o JAR construído do estágio anterior
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]