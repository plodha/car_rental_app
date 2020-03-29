## Docker Prod 
Please do not run this locally. EC2 only.

## Migrating Changes to Prod

### Setup

If you need to deploy your changes to prod, please make sure:
- EC2 instance is running
- Docker services are not running on EC2
- You have downloaded 'cpe_202.pem' and 'upload_latest_to_ec2.sh' from https://drive.google.com/drive/u/2/folders/0AGYFc8cspeiVUk9PVA
- Make sure 'chmod 600 cpe_202.pem' is executed so that the file has the correct permissions
- And make sure 'chmod +x upload_latest_to_ec2.sh' is executed so that the file has the correct permissions
- Make sure both the 'cpe_202.pem' and 'upload_latest_to_ec2.sh' are in the same directory as the repo directory (if repo is in 'Documents' folder, put those two files in the 'Documents' folder, not in the repo).

### Copy Latest Changes From Master to EC2
Run the following
- ./upload_latest_to_ec2.sh

## Bring up Docker Services On EC2 
You will need to do the following:
- ssh -i cpe_202.pem ubuntu@35.155.65.155
- cd ~/sp20-cmpe-202-sec-49-team-project-themeansquares/infra/prod
- ./build_deploy.sh

## Bring down Docker Services On EC2 
You will need to do the following:
- ssh -i cpe_202.pem ubuntu@35.155.65.155
- cd ~/sp20-cmpe-202-sec-49-team-project-themeansquares/infra/prod
- docker-compose down