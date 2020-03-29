## Docker Local Developement
This file will only run a MySQL server locally.

### Setup

- Please have docker and docker-compose installed. You can use this https://docs.docker.com/compose/install/ to get started.
- You only need to run the following command once.

	**docker network create ms_network**

### Credentials
- Username: root
- Password: Sup3rS3cr3tPassw0rd
- Database: car_rental

### Adminer (GUI)
Use the crednetials above and go to: localhost:8010.

### DB Connection Programatically
Use the same credentials as above and use localhost:3306 as the host.

### How to bring up services:
In 'local' directory, run: 

**docker-compose up -d **
### How to bring down services:
In 'local' directory, run: 

**docker-compose down**