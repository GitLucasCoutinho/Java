# Usamos uma imagem base com Java 21
FROM openjdk:21-jdk-slim

# Copiamos o JAR gerado pelo Maven
COPY target/ProjetoFinalDeGenericsJavaPuro-1.0-SNAPSHOT.jar app.jar

# Comando de inicialização
ENTRYPOINT ["java","-jar","/app.jar"]