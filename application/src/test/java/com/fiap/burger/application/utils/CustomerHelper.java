package com.fiap.burger.application.utils;

import com.fiap.burger.api.dto.customer.request.CustomerInsertRequestDto;

public class CustomerHelper {
    public static CustomerInsertRequestDto createCustomerRequest() {
        return new CustomerInsertRequestDto("48353597047", "email@email.com", "name");
    }

}
