Development:
1.Connect to the Open ATMS API using the provided API key

2.Retrieve the list of airports and their ICAO codes

3.For a selected airport, retrieve its list of SIDs or STARs

4.Parse the SIDs/STARs to extract the list of waypoints

5.Count the occurrence of each waypoint in the SIDs/STARs

6.Select the top 2 waypoints with the highest occurrence

7.Display the results in the desired JSON format.


Build docker image:
1. Add maven plugin
<properties>
   <java.version>17</java.version>
   <docker.image.prefix>zhangnaiyuan47</docker.image.prefix>
   <docker.image.exposed.port>8080</docker.image.exposed.port>
   <docker.image.dockerfile.dir>${basedir}</docker.image.dockerfile.dir>
   <docker.image.dockerize.version>v0.6.1</docker.image.dockerize.version>
   <docker.plugin.version>1.2.0</docker.plugin.version>
</properties>

<!-- dockerfile maven plugin -->
<plugin>
   <groupId>com.spotify</groupId>
   <artifactId>dockerfile-maven-plugin</artifactId>
   <version>1.4.12</version>
   <configuration>
      <repository>${docker.image.prefix}/${project.artifactId}</repository>
      <buildArgs>
         <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
      </buildArgs>
   </configuration>
</plugin>
<!-- dockerfile maven plugin -->

2. Create a docker file.
FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} spring-boot-docker.jar
ENTRYPOINT ["java","-jar","/spring-boot-docker.jar"]

3. Build a docker image.
mvn clean install dockerfile:build

4. Create a docker hub repository, and push docker image to it.
run cmd
docker login
docker images
docker push zhangnaiyuan47/arilab


Setup Kubernates deployment:
1. Sign in Google cloud
2. Create a cluster
3. Create a k8s deployment yaml
4. Connect cluster via cloud shell
5. upload yaml file
6. kubectl apply -f arilab.yaml
7. expose update target port 8080
8. service type load balance to external ip
9. http://34.72.185.29/
