#!/bin/bash
awslocal dynamodb create-table \
   --table-name tf-customers-table \
   --attribute-definitions AttributeName=id,AttributeType=S \
                           AttributeName=cpf,AttributeType=S \
   --key-schema AttributeName=id,KeyType=HASH \
   --provisioned-throughput ReadCapacityUnits=2,WriteCapacityUnits=2 \
    --global-secondary-index "[
        {
      \"IndexName\": \"cpf\",
      \"KeySchema\": [
        {
           \"AttributeName\": \"cpf\",
          \"KeyType\": \"HASH\"
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