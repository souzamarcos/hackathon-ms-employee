#!/bin/bash
awslocal dynamodb create-table \
   --table-name tf-customers-table \
   --attribute-definitions AttributeName=id,AttributeType=S \
                           AttributeName=cpf,AttributeType=S \
   --key-schema AttributeName=id,KeyType=HASH \
                AttributeName=cpf,KeyType=RANGE \
   --provisioned-throughput ReadCapacityUnits=2,WriteCapacityUnits=2 \
    --global-secondary-index "[
        {
      \"IndexName\": \"id-cpf\",
      \"KeySchema\": [
        {
           \"AttributeName\": \"cpf\",
          \"KeyType\": \"HASH\"
        },
        {
           \"AttributeName\": \"id\",
          \"KeyType\": \"RANGE\"
        }
      ],
      \"Projection\": {
        \"ProjectionType\": \"ALL\"
      },
      \"ProvisionedThroughput\": {
        \"ReadCapacityUnits\": 1,
        \"WriteCapacityUnits\": 1
      }
    }
    ]"