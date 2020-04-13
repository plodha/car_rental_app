cd ../infra/local
docker-compose up -d
cd ../../backend
mvn test
cd ../infra/local
docker-compose down
