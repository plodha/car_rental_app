cd ../backend
mvn clean install
cd ../infra
cp ~/acme.json traefik_letsencrypt/acme.json
chmod 600 traefik_letsencrypt/acme.json
docker-compose build
docker-compose up -d