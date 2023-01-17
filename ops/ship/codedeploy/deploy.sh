aws deploy push \
--application-name HealthyCompany-3G-HCOSS-Staging \
--s3-location s3://healthycompany-hcoss-3g-staging-deploy-bucket/app.zip \
--source ./ops/ship/codedeploy \
--ignore-hidden-files \
--profile k2works \
--region ap-northeast-1
