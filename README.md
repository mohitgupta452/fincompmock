1. Add dependency in pom.xml

        <dependency>
			<groupId>org.mock-server</groupId>
			<artifactId>mockserver-client-java</artifactId>
			<version>5.3.0</version>
		</dependency>

2. To run this project, below are the steps needed to be performed in cmd:
$ download mockserver-netty-5.8.1-jar-with-dependencies.jar into a location
$ run mockserver with port as : java -jar mockserver-netty-5.8.1-jar-with-dependencies.jar -serverPort <port>

3. 
## Create new mock user To create new mock user, below are the steps needed to be performed:
$ Make an input json file (See jsons in /resources/mock-users/ for reference)
$ Refer template files in /resources/user-templates/ directory when doing above step
$ add the user to List<String> userFiles in WASCombinedExpectation.java class
$ Spin up the server and run MockServerClientApp as mentioned in ##Setup step


What port we define in step 2, same port define in edit configurations in arguments
like localhost:8083

Application port will be different in application.properties file