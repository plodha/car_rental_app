cd ../../
cp -r frontend frontend-2
cd ./infra/prod
cp ~/application.properties ../../backend/src/main/resources/application.properties
cd ../../backend
mvn clean install -DskipTests
cd ../infra/prod
cp ~/acme.json traefik_letsencrypt/acme.json
chmod 600 traefik_letsencrypt/acme.json
docker-compose build
docker-compose up -d