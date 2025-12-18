package com.kb.workflow.helper;

import com.kb.common.dto.users.UsersResponseDto;
import com.kb.common.dto.workflow.CreateOrderDto;
import com.kb.workflow.client.UserServiceClient;
import com.kb.workflow.exceptions.DownstreamException;
import com.kb.workflow.exceptions.WorkflowException;
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
        final List<UsersResponseDto> users;

        try {
            final ResponseEntity<List<UsersResponseDto>> response = userServiceClient.getUsers();
            users = response.getBody();
        } catch (DownstreamException ex) {
            throw new WorkflowException("Failed to get user list, Details: " + ex.getMessage());
        }

        return filterUser(users, order);
    }

    private static UsersResponseDto filterUser(final List<UsersResponseDto> users, final CreateOrderDto order) {
        return users.stream()
                .filter(user -> user.userName().equals(order.userName()))
                .findFirst()
                .orElseThrow(() -> new WorkflowException("Invalid user [" + order.userName() + "] specified"));
    }

}
