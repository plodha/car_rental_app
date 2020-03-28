cd ../backend
mvn clean install
cd ../infra
chmod 600 traefik_letsencrypt/acme.json
docker-compose build
docker-compose up -d