package com.assessment.ticket.booking.service;

import com.assessment.ticket.booking.common.CustomApiResponse;
import com.assessment.ticket.booking.domain.TicketBooking;
import com.assessment.ticket.booking.dto.TicketBookingDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class TicketBookingServiceTest {

    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    TicketBookingService ticketBookingService;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testCreateTicketBookingWithASectionSuccess(){
        TicketBookingDto ticketBookingDto = new TicketBookingDto();
        ticketBookingDto.setTicketId(1);
        ticketBookingDto.setFrom("chennai");
        ticketBookingDto.setTo("south korea");
        ticketBookingDto.setUserEmail("nivedha@gmail.com");
        ticketBookingDto.setUserName("nivedha");
        ticketBookingDto.setPricePaid(BigDecimal.valueOf(20));
        ticketBookingDto.setSection("A");
        TicketBooking ticketBooking = new TicketBooking();
        ticketBooking.setTicketId(1);
        ticketBooking.setFrom("chennai");
        ticketBooking.setTo("south korea");
        ticketBooking.setUserEmail("nivedha@gmail.com");
        ticketBooking.setUserName("nivedha");
        ticketBooking.setPricePaid(BigDecimal.valueOf(20));
        ticketBooking.setSection("A");
        Map<Integer, TicketBooking> ticketBookingMap = new ConcurrentHashMap<>();
        ReflectionTestUtils.setField(ticketBookingService, "ticketBookingMap", ticketBookingMap);
        when(modelMapper.map(any(), eq(TicketBooking.class))).thenReturn(ticketBooking);
        when(modelMapper.map(any(), eq(TicketBookingDto.class))).thenReturn(ticketBookingDto);
        CustomApiResponse<TicketBookingDto> response = ticketBookingService.purchaseTicket(ticketBookingDto);
        assertEquals(ticketBookingDto, response.getData());

    }

    @Test
    void testCreateTicketBookingWithBSectionSuccess(){
        TicketBookingDto ticketBookingDto = new TicketBookingDto();
        ticketBookingDto.setTicketId(1);
        ticketBookingDto.setFrom("chennai");
        ticketBookingDto.setTo("south korea");
        ticketBookingDto.setUserEmail("nivedha@gmail.com");
        ticketBookingDto.setUserName("nivedha");
        ticketBookingDto.setPricePaid(BigDecimal.valueOf(20));
        ticketBookingDto.setSection("B");
        TicketBooking ticketBooking = new TicketBooking();
        ticketBooking.setTicketId(1);
        ticketBooking.setFrom("chennai");
        ticketBooking.setTo("south korea");
        ticketBooking.setUserEmail("nivedha@gmail.com");
        ticketBooking.setUserName("nivedha");
        ticketBooking.setPricePaid(BigDecimal.valueOf(20));
        ticketBooking.setSection("B");
        Map<Integer, TicketBooking> ticketBookingMap = new ConcurrentHashMap<>();
        ReflectionTestUtils.setField(ticketBookingService, "ticketBookingMap", ticketBookingMap);
        when(modelMapper.map(any(), eq(TicketBooking.class))).thenReturn(ticketBooking);
        when(modelMapper.map(any(), eq(TicketBookingDto.class))).thenReturn(ticketBookingDto);
        CustomApiResponse<TicketBookingDto> response = ticketBookingService.purchaseTicket(ticketBookingDto);
        assertEquals(ticketBookingDto, response.getData());

    }
}