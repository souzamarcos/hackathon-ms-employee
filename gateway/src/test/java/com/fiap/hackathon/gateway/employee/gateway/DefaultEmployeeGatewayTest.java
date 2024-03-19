package com.fiap.hackathon.gateway.employee.gateway;

import com.fiap.hackathon.entity.employee.Employee;
import com.fiap.hackathon.gateway.employee.model.EmployeeModel;
import com.fiap.hackathon.gateway.misc.EmployeeBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class DefaultEmployeeGatewayTest {

    @Mock
    DynamoDbEnhancedClient enhancedEmployee;

    @Mock
    DynamoDbTable<EmployeeModel> table;

    DefaultEmployeeGateway gateway;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        when(enhancedEmployee.table("tf-employees-table", TableSchema.fromBean(EmployeeModel.class))).thenReturn(table);
        gateway = new DefaultEmployeeGateway(enhancedEmployee, "tf-employees-table");
    }

    @Nested
    class findById {
        @Test
        void shouldFindById() {
            var id = "ABC001";
            var employee = new EmployeeBuilder().withId(id).build();
            var expected = new EmployeeBuilder().withId(id).build();

            when(table.getItem(Key.builder().partitionValue(id).build())).thenReturn(new EmployeeModel(employee));

            Employee actual = gateway.findById(id);

            assertEquals(expected, actual);

            verify(table, times(1)).getItem(Key.builder().partitionValue(id).build());
        }

        @Test
        void shouldReturnNullWhenRegisterNotFound() {
            var id = "ABC001";

            when(table.getItem(Key.builder().partitionValue(id).build())).thenReturn(null);

            Employee actual = gateway.findById(id);

            assertNull(actual);

            verify(table, times(1)).getItem(Key.builder().partitionValue(id).build());
        }
    }

    @Nested
    class save {
        @Test
        void shouldSave() {
            var employee = new EmployeeBuilder().build();
            var expected = new EmployeeBuilder().build();

            Employee actual = gateway.save(employee);

            assertEquals(expected, actual);

            verify(table, times(1)).putItem(new EmployeeModel(employee));
        }
    }
}
