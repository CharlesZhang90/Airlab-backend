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
2. Create a docker file.
3. Build a docker image.
4. Create a docker hub repository, and push docker image to it.


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
