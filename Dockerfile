FROM maven:3.8.8-eclipse-temurin-17 AS builder

# define space to work 
WORKDIR /app

# Copy .pom.xml to get dependences first
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copy code
COPY . .

# contruct spring image 
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk AS runner

#Configure app to working on it
WORKDIR /app

# Copy JAR
COPY --from=builder /app/target/products-0.0.1-SNAPSHOT.jar app.jar

#configure port to deploy
EXPOSE 8080

#execute container
ENTRYPOINT ["java", "-jar", "app.jar"]
