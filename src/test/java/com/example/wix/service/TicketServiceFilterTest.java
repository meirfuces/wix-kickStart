package com.example.wix.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TicketServiceFilterTest {
    @Mock
    TicketServiceLoader ticketServiceLoader;
    @InjectMocks
    TicketServiceFilter ticketServiceFilter;
    @Test

    void filterTickets() {

    }

    @Test
    void filterTicketByfield() {
    }

    @Test
    void filterTicketsByTime() {
    }
}