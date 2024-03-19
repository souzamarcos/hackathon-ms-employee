package com.fiap.hackathon.application.config;

import com.fiap.hackathon.gateway.employee.model.EmployeeModel;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.services.dynamodb.model.*;
import com.fiap.hackathon.application.utils.AwsDynamoDbLocalTestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
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
public class DynamoDbConfigTest {
    public static final long READ_CAPACITY_UNITS = 10L;
    public static final long CAPACITY_UNITS = 10L;

    private static DynamoDBProxyServer server;
    private static final String port = "8000";
    private static final String uri = "http://localhost:"+port;

    @Bean
    @Primary
    public DynamoDbEnhancedClient testDynamoDbEnhancedClient(@Value("${dynamodb.tablename}") String employeeTableName) throws Exception {
        if(server != null) {
            server.stop();
        }

        AwsDynamoDbLocalTestUtils.initSqLite();
        server = ServerRunner.createServerFromCommandLineArgs(new String[]{"-inMemory", "-port", port});
        server.start();
        System.setProperty("software.amazon.awssdk.http.service.impl","software.amazon.awssdk.http.urlconnection.UrlConnectionSdkHttpService");

        DynamoDbClient dynamoDbClient = getDynamoDbClient();

        DynamoDbEnhancedClient enhancedClient = getDynamoDbEnhancedClient(dynamoDbClient);

        createTableEmployee(enhancedClient, employeeTableName);

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

    public void createTableEmployee(DynamoDbEnhancedClient enhancedClient, String tableName) {
        DynamoDbTable<EmployeeModel> table = enhancedClient.table(tableName, TableSchema.fromBean(EmployeeModel.class));
        ProvisionedThroughput provisionedThroughput= ProvisionedThroughput.builder()
            .readCapacityUnits(READ_CAPACITY_UNITS)
            .writeCapacityUnits(CAPACITY_UNITS)
            .build();
        table.createTable(builder -> builder
            .provisionedThroughput(provisionedThroughput)
        );
    }

    public static void cleanTableEmployee() {
        DynamoDbClient dynamoDbClient = getDynamoDbClient();

        ScanIterable scanIterable = dynamoDbClient.scanPaginator(ScanRequest.builder()
            .tableName("tf-employees-table")
            .build());
        for(ScanResponse scanResponse:scanIterable){
            for( Map<String, AttributeValue> item: scanResponse.items()){
                Map<String, AttributeValue> deleteKey = new HashMap<>();
                deleteKey.put("id",item.get("id"));
                dynamoDbClient.deleteItem(DeleteItemRequest.builder()
                    .tableName("tf-employees-table")
                    .key(deleteKey).build());
            }
        }
    }

}
