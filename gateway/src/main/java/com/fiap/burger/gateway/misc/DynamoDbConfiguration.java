package com.fiap.burger.gateway.misc;

import com.fiap.burger.usecase.misc.profiles.NotTest;
import com.fiap.burger.usecase.misc.profiles.Production;
import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDbConfiguration {

    @Value("${localstack.url}")
    private String LOCALSTACK_ENDPOINT;

    @Bean
    @Production
    @Primary
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(@Value("${dynamodb.tablename}") String tableName) {
        DynamoDbClient dynamoDbClient =
            DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .build();
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

    @Bean
    @NotTest
    public DynamoDbEnhancedClient localStackDynamoDbEnhancedClient(@Value("${dynamodb.tablename}") String tableName) {
        DynamoDbClient dynamoDbClient =
            DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(URI.create(LOCALSTACK_ENDPOINT))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("fiap", "fiap")))
                .build();
        return DynamoDbEnhancedClient.builder()
            .dynamoDbClient(dynamoDbClient)
            .build();
    }

}
