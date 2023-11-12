###################### VARIABLES ######################
API-SERVICE-NAME = application-api
DB-SERVICE-NAME = application-postgres
API-CONTAINER-IMAGE-NAME = marvinrodr-application-api
DB-CONTAINER-IMAGE-NAME = marvinrodr-application-postgres
DB-USER-NAME = application_api_username
DB-NAME = application_api_database
API-JAR-PATH = target/skeleton-0.0.1-SNAPSHOT.jar
JAVAR-JAR = java -jar $(API-JAR-PATH)

###################### COMMONS ######################
.PHONY: all
all: build

.PHONY: build
build:
	make package
	@docker compose build --no-cache

.PHONY: package
package:
	@mvn clean
	@mvn package -DskipTests

.PHONY: docker-up-d
docker-up-d:
	@docker	compose up -d

.PHONY: run
run:
	@make docker-up-d
	@make run-api

###################### API ######################
.PHONY: docker-run-api
docker-run-api:
	-@docker stop $(API-CONTAINER-IMAGE-NAME)
	@docker compose up $(API-SERVICE-NAME) -d
	@make run-api

.PHONY: run-api
run-api:
	-@make kill-api
	@docker exec $(API-CONTAINER-IMAGE-NAME) $(JAVAR-JAR)

.PHONY: is-api-running
is-api-running:
	@docker exec $(API-CONTAINER-IMAGE-NAME) ps aux

.PHONY: kill-api
kill-api:
	-@docker exec $(API-CONTAINER-IMAGE-NAME) pkill java

.PHONY: docker-test
docker-test:
	@docker exec $(API-CONTAINER-IMAGE-NAME) ./mvnw test

###################### DATABASE - POSTGRES ######################
DB-CONTAINER-IMAGE-NAME = marvinrodr-application-postgres
DB-USER-NAME = application_api_username
DB-NAME = application_api_database

.PHONY: docker-ping-database
docker-ping-database:
	@docker exec $(DB-CONTAINER-IMAGE-NAME) pg_isready -h "127.0.0.1" -U $(DB-USER-NAME) -d $(DB-NAME) -t 1

###################### DANGEROUS ZONE! ######################
.PHONY: docker-clean-project
docker-clean-project:
	@docker stop $(API-CONTAINER-IMAGE-NAME)
	@docker stop $(DB-CONTAINER-IMAGE-NAME)
	@docker container rm $(DB-CONTAINER-IMAGE-NAME)
	@docker container rm $(API-CONTAINER-IMAGE-NAME)
	@docker rmi $(API-CONTAINER-IMAGE-NAME)
	@docker rmi $(DB-CONTAINER-IMAGE-NAME)
