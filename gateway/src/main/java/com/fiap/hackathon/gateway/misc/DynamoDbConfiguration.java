package com.fiap.hackathon.gateway.misc;

import com.fiap.hackathon.usecase.misc.profiles.Production;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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

//    @Bean
//    @NotTest
//    @Primary
//    public DynamoDbEnhancedClient localStackDynamoDbEnhancedClient(@Value("${dynamodb.employee.tablename}") String tableName) {
//        DynamoDbClient dynamoDbClient =
//            DynamoDbClient.builder()
//                .region(Region.US_EAST_1)
//                .endpointOverride(URI.create(LOCALSTACK_ENDPOINT))
//                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("fiap", "fiap")))
//                .build();
//        return DynamoDbEnhancedClient.builder()
//            .dynamoDbClient(dynamoDbClient)
//            .build();
//    }

}
