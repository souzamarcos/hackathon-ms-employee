#!/bin/bash
awslocal dynamodb create-table \
   --table-name tf-customers-table \
   --attribute-definitions AttributeName=cpf,AttributeType=S \
   --key-schema AttributeName=cpf,KeyType=HASH \
   --provisioned-throughput ReadCapacityUnits=2,WriteCapacityUnits=2