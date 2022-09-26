#!/bin/sh
mkdir $HOME/.aws
cat << EOF > $HOME/.aws/config
[default]
aws_access_key_id=$AWS_ACCESS_KEY_ID
aws_secret_access_key=$AWS_SECRET_ACCESS_KEY
region=$AWS_REGION
EOF
