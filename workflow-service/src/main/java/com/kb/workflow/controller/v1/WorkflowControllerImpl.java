package com.kb.workflow.controller.v1;

import com.kb.common.dto.orders.OrdersResponseDto;
import com.kb.common.dto.workflow.CreateOrderDto;
import com.kb.common.dto.workflow.StatusDto;
import com.kb.workflow.controller.WorkflowController;
import com.kb.workflow.service.WorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workflow/order")
public class WorkflowControllerImpl implements WorkflowController {
    private final WorkflowService workflowService;

    @Override
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersResponseDto> createOrder(final CreateOrderDto order) {
        order.validate();
        return ResponseEntity.status(HttpStatus.CREATED).body(workflowService.createOrder(order));
    }

    @Override
    @PutMapping(value = "/{id}/change-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersResponseDto> changeOrderStatus(@RequestBody StatusDto order, @PathVariable("id") Integer id) {
        order.validate();
        return ResponseEntity.status(HttpStatus.OK).body(workflowService.changeOrderStatus(order, id));
    }
}
