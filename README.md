## Automation Demo ##

This is an automation demo project to demonstrate opportunities of maven and allure.

## Environment configuration ##

To run the tests you need to download and install Maven 3.1.1 or higher https://maven.apache.org/download.cgi and setup your IDE to use it instead of bundled version (for IDEA: Settings -> Build -> Build Tools -> Maven )

## How to use ##

* Download and build solution.
* In command prompt navigate to the directory with built solution and run one of the commands from examples below.
* View execution report in HTML format which is located at <project_directory>\report\

## Examples ##

To run tests:
```
 mvn install
```
To clean generated content: 
```
mvn clean
```
To generate allure report: 
```
mvn site
```
To zip report:
```
mvn assembly:single 
```
And send it to email:
```
mvn postman:send-mail
```
Or u can run them alltogether: 
```
mvn clean install site assembly:single postman:send-mail
```