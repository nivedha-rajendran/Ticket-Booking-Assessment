package com.assessment.ticket.booking.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) representing a ticket booking for input/output operations.
 */
@Data
public class TicketBookingDto {

    /**
     * Unique identifier for the ticket.
     */
    private Integer ticketId;

    /**
     * The source location of the journey.
     */
    private String from;

    /**
     * The destination location of the journey.
     */
    private String to;

    /**
     * The username associated with the ticket booking.
     */
    private String userName;

    /**
     * The email address of the user who booked the ticket.
     */
    private String userEmail;

    /**
     * The amount paid for the ticket.
     */
    private BigDecimal pricePaid;

    /**
     * The section of the seating area (e.g., A, B, etc.).
     */
    private String section;

    /**
     * The seat number allocated for the ticket within the specified section.
     */
    private Integer seatNumber;
}
