## Docker Local Developement
This file will only run a MySQL server locally.

### Credentials
- Username: root
- Password: Sup3rS3cr3tPassw0rd
- Database: car_rental

## Installation/Setup
### 1. Installing Docker

- Please have docker and docker-compose installed. You can use this https://docs.docker.com/compose/install/ to get started.
- You only need to run the following command the first time.
	- **docker network create ms_network**

###  2. Bring up services:
In 'local' directory, run: 

**docker-compose up -d**

### 3. Install MySQL CLI

Please go here: https://dev.mysql.com/downloads/shell/

### 4. Setup DB with metadata and test data
Please run the following to setup your Database with the correct metadata and data (only need to run the first time):
- mysql -h < hostname ('0.0.0.0' for local or '35.155.65.155' for prod) > -u root -p
    - Enter 'Sup3rS3cr3tPassw0rd' as password
- use car_rental;
- source < path to car_rental.sql >

And that's it! You're done! 

### 5. When done for the day, save your laptop by bring down services:
In 'local' directory, run: 

**docker-compose down**

### 6. If you ever want to view DB from GUI (Adminer)

While the MySQL DB is running, go to localhost:8010 and use the credentials above to login