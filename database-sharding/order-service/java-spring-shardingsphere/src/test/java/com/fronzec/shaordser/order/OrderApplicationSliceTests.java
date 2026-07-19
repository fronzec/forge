package com.fronzec.shaordser.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderApplicationSliceTests {

  @Mock
  private OrderRepository orderRepository;

  @Test
  void serviceSavesAnOrder() {
    Order order = order(2L);
    when(orderRepository.save(order)).thenReturn(order);

    Order result = new OrderService(orderRepository).createOrder(order);

    assertSame(order, result);
  }

  @Test
  void serviceReturnsAnExistingOrder() {
    Order order = order(1L);
    when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

    Order result = new OrderService(orderRepository).getOrder(1L);

    assertSame(order, result);
  }

  @Test
  void serviceRejectsAMissingOrder() {
    when(orderRepository.findById(99L)).thenReturn(Optional.empty());

    IllegalArgumentException error = assertThrows(
        IllegalArgumentException.class,
        () -> new OrderService(orderRepository).getOrder(99L));

    assertEquals("Order not found", error.getMessage());
  }

  @Test
  void controllerDelegatesCreateAndReadOperations() {
    Order order = order(3L);
    OrderService orderService = org.mockito.Mockito.mock(OrderService.class);
    when(orderService.createOrder(order)).thenReturn(order);
    when(orderService.getOrder(3L)).thenReturn(order);
    OrderController controller = new OrderController(orderService);

    assertSame(order, controller.createOrder(order).getBody());
    assertSame(order, controller.getOrder(3L).getBody());
    verify(orderService).createOrder(order);
    verify(orderService).getOrder(3L);
  }

  private static Order order(Long id) {
    return new Order(
        id,
        10L,
        new BigDecimal("12.50"),
        Status.PROCESSING,
        LocalDate.of(2026, 7, 18),
        "1 Forge Way");
  }
}
