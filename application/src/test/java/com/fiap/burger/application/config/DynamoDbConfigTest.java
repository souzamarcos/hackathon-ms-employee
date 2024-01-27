package com.fiap.burger.application.config;

import software.amazon.awssdk.enhanced.dynamodb.model.EnhancedGlobalSecondaryIndex;
import software.amazon.awssdk.services.dynamodb.model.*;
import com.fiap.burger.application.utils.AwsDynamoDbLocalTestUtils;
import com.fiap.burger.gateway.customer.model.CustomerModel;
import com.fiap.burger.usecase.misc.profiles.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import software.amazon.awssdk.services.dynamodb.paginators.ScanIterable;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Test
public class DynamoDbConfigTest {
    public static final long READ_CAPACITY_UNITS = 10L;
    public static final long CAPACITY_UNITS = 10L;

    private static DynamoDBProxyServer server;
    private static final String port = "8000";
    private static final String uri = "http://localhost:"+port;
    DynamoDbClient client;

    @Bean
    public DynamoDbEnhancedClient testDynamoDbEnhancedClient(@Value("${dynamodb.tablename}") String tableName) throws Exception {
        AwsDynamoDbLocalTestUtils.initSqLite();
        server = ServerRunner.createServerFromCommandLineArgs(
            new String[]{"-inMemory", "-port", port});
        server.start();

        System.setProperty("software.amazon.awssdk.http.service.impl","software.amazon.awssdk.http.urlconnection.UrlConnectionSdkHttpService");

        DynamoDbClient dynamoDbClient = getDynamoDbClient();

        DynamoDbEnhancedClient enhancedClient = getDynamoDbEnhancedClient(dynamoDbClient);

        createTable(enhancedClient, tableName);

        return enhancedClient;
    }

    public static DynamoDbClient getDynamoDbClient() {
        return DynamoDbClient.builder()
            .endpointOverride(URI.create(uri))
            .region(Region.US_EAST_1)
            .credentialsProvider(StaticCredentialsProvider.create(
                AwsBasicCredentials.create("fiap","fiap")))
            .build();
    }

    public DynamoDbEnhancedClient getDynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
            .dynamoDbClient(dynamoDbClient)
            .build();
    }

    public void createTable(DynamoDbEnhancedClient enhancedClient, String tableName) {
        DynamoDbTable<CustomerModel> table = enhancedClient.table(tableName, TableSchema.fromBean(CustomerModel.class));
        ProvisionedThroughput provisionedThroughput= ProvisionedThroughput.builder()
            .readCapacityUnits(READ_CAPACITY_UNITS)
            .writeCapacityUnits(CAPACITY_UNITS)
            .build();

        EnhancedGlobalSecondaryIndex enhancedGlobalSecondaryIndex = EnhancedGlobalSecondaryIndex.builder()
            .indexName("id-cpf")
            .provisionedThroughput(ProvisionedThroughput.builder().readCapacityUnits(READ_CAPACITY_UNITS).writeCapacityUnits(CAPACITY_UNITS).build())
            .projection(Projection.builder().projectionType(ProjectionType.ALL).build())
            .build();

        table.createTable(builder -> builder
            .provisionedThroughput(provisionedThroughput)
            .globalSecondaryIndices(enhancedGlobalSecondaryIndex)
        );
    }

    public static void cleanTable() {
        DynamoDbClient dynamoDbClient = getDynamoDbClient();

        ScanIterable scanIterable = dynamoDbClient.scanPaginator(ScanRequest.builder()
            .tableName("tf-customers-table")
            .build());
        for(ScanResponse scanResponse:scanIterable){
            for( Map<String, AttributeValue> item: scanResponse.items()){
                Map<String, AttributeValue> deleteKey = new HashMap<>();
                deleteKey.put("id",item.get("id"));
                deleteKey.put("cpf",item.get("cpf"));
                dynamoDbClient.deleteItem(DeleteItemRequest.builder()
                    .tableName("tf-customers-table")
                    .key(deleteKey).build());
            }
        }
    }

}
