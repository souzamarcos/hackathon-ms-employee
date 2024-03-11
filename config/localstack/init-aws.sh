#!/bin/bash
aws dynamodb create-table --endpoint-url http://localhost:4566 --table-name tf-customers-table --attribute-definitions AttributeName=cpf,AttributeType=S --key-schema AttributeName=cpf,KeyType=HASH --provisioned-throughput ReadCapacityUnits=2,WriteCapacityUnits=2
