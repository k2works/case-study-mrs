profile=
environment="STAGING"
list="DB_MYSQL_USERNAME DB_MYSQL_PASSWORD DB_LEGACY_MYSQL_USERNAME DB_LEGACY_MYSQL_PASSWORD APP_RAILS_SECRET_KEY_BASE APP_LEGACY_RAILS_SECRET_KEY_BASE APP_LEGACY_SSH_KEY APP_SSH_KEY APP_SSL_CERT APP_SSL_CERT_KEY API_SSH_KEY API_SSL_CERT API_SSL_CERT_KEY"
for key in $list; do
  value=`aws ssm get-parameters --names /$environment/$key --with-decryption --query "Parameters[*].{Value:Value}" --output text --region ap-northeast-1 --profile=$profile`
  echo "$key=$value"
done
