package com.kb.workflow.helper;

import com.kb.common.dto.users.UsersResponseDto;
import com.kb.common.dto.workflow.CreateOrderDto;
import com.kb.workflow.client.UserServiceClient;
import com.kb.workflow.exceptions.WorkflowException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersClientHelper {
    private final UserServiceClient userServiceClient;

    public UsersClientHelper(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    public UsersResponseDto getUser(final CreateOrderDto order) {
        final ResponseEntity<List<UsersResponseDto>> response = userServiceClient.getUsers();

        if (response.getStatusCode() != HttpStatus.OK)
            throw new WorkflowException("Failed to get user list");

        return filterUser(response.getBody(), order);
    }

    private static UsersResponseDto filterUser(final List<UsersResponseDto> users, final CreateOrderDto order) {
        return users.stream()
                .filter(user -> user.userName().equals(order.userName()))
                .findFirst()
                .orElseThrow(() -> new WorkflowException("Invalid user [" + order.userName() + "] specified"));
    }

}
