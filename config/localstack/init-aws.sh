#!/usr/bin/env bash

set -euo pipefail

awslocal dynamodb create-table \
   --table-name tf-employee-table \
   --attribute-definitions AttributeName=id,AttributeType=S \
                           AttributeName=email,AttributeType=S \
   --key-schema AttributeName=id,KeyType=HASH \
   --provisioned-throughput ReadCapacityUnits=2,WriteCapacityUnits=2 \
    --global-secondary-index "[
        {
      \"IndexName\": \"email\",
      \"KeySchema\": [
        {
           \"AttributeName\": \"email\",
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
