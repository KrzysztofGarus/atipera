
# Rekrutacja Atipera

### to run this app from a ready .jar file:

- make sure You have java installed - project uses Java 21

- download this jar file -> https://drive.google.com/file/d/1OEygx8bU4Nkes4aX9_lcyzhOL1Et0YA_/view?usp=sharing

- go to the jar file directory and open terminal

- type: 
$env:githubtoken = "github_token_from_github_api" 
use Your api token https://github.com/settings/tokens -> generate new token (https://docs.github.com/en/rest)

- type: 
java -jar atipera_app.jar

### to run the application from a Docker container:

- make sure You have docker installed
- download docker image - > 
- go to the docker file directory and open terminal
- type:  docker load -i atipera_api.tar  to import
- type:  docker run -p 8080:8080 -e githubtoken=YOUR_TOKEN helikon_api

## to use the app:
- You can open Your browser and type http://localhost:8080/repositories/{owner_login} -> example http://localhost:8080/repositories/krzysztofgarus
