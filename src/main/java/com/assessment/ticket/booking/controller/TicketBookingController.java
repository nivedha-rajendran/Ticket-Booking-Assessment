package com.assessment.ticket.booking.controller;

import com.assessment.ticket.booking.common.ApiDocumentationTags;
import com.assessment.ticket.booking.common.ApiResponseMessages;
import com.assessment.ticket.booking.common.CustomApiResponse;
import com.assessment.ticket.booking.dto.TicketBookingDto;
import com.assessment.ticket.booking.service.TicketBookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller class handling various endpoints related to ticket booking operations.
 * Provides RESTful APIs for creating, retrieving, updating, and deleting ticket and user-related information.
 */
@RestController
@RequestMapping("/ticket-booking")
public class TicketBookingController {

    @Autowired
    private TicketBookingService ticketBookingService;


    /**
     * Endpoint for purchasing a ticket.
     *
     * @param ticketBookingDto The DTO containing ticket booking information.
     * @return ResponseEntity containing the API response for the ticket booking operation.
     */
    @PostMapping("/")
    @Operation(summary = ApiDocumentationTags.CREATE_TICKET_BOOKING_REQUEST,
            description = ApiDocumentationTags.TICKET_BOOKING_DESCRIPTION,
            tags = ApiDocumentationTags.TICKET_BOOKING)
    @ApiResponse(responseCode = "200", description = ApiResponseMessages.TICKET_BOOKING_SUCCESSFULLY)
    @ApiResponse(responseCode = "500", description = ApiResponseMessages.TICKET_BOOKING_FAILED)
    public ResponseEntity<CustomApiResponse<TicketBookingDto>> purchaseTicket(
            @RequestBody TicketBookingDto ticketBookingDto) {
        CustomApiResponse<TicketBookingDto> response = ticketBookingService.purchaseTicket(ticketBookingDto);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint for retrieving user receipt details.
     *
     * @param ticketId The ID of the ticket for which the receipt details are requested.
     * @return ResponseEntity containing the API response for fetching user receipt details.
     */
    @GetMapping("/user-receipt-detail")
    @Operation(summary = ApiDocumentationTags.FETCH_USER_RECEIPT_DETAIL,
            description = ApiDocumentationTags.FETCH_USER_RECEIPT_DESCRIPTION,
            tags = ApiDocumentationTags.TICKET_BOOKING)
    @ApiResponse(responseCode = "200", description = ApiResponseMessages.USER_RECEIPT_FETCHED_SUCCESSFUL)
    @ApiResponse(responseCode = "500", description = ApiResponseMessages.TICKET_NOT_FOUND)
    public ResponseEntity<CustomApiResponse<TicketBookingDto>> getUserReceiptDetails(
            @RequestParam Integer ticketId) {
        CustomApiResponse<TicketBookingDto> response = ticketBookingService.getUserReceiptDetails(ticketId);
        return ResponseEntity.ok(response);
    }


    /**
     * Endpoint for fetching all tickets associated with a user's email.
     *
     * @param email The email of the user for whom the ticket list is requested.
     * @return ResponseEntity containing the API response for fetching all tickets.
     */
    @GetMapping("/ticket-list")
    @Operation(summary = ApiDocumentationTags.FETCH_TICKET_LIST_BY_USER_EMAIL,
            description = ApiDocumentationTags.FETCH_TICKET_LIST_BY_USER_EMAIL_DESCRIPTION,
            tags = ApiDocumentationTags.TICKET_BOOKING)
    @ApiResponse(responseCode = "200", description = ApiResponseMessages.TICKET_LIST_FOUND)
    @ApiResponse(responseCode = "500", description = ApiResponseMessages.TICKET_LIST_NOT_FOUND)
    public ResponseEntity<CustomApiResponse<List<TicketBookingDto>>> getAllTicket(@RequestParam String email) {
        CustomApiResponse<List<TicketBookingDto>> response = ticketBookingService.getAllTickets(email);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint for updating the seat number of a user's ticket.
     *
     * @param ticketId   The ID of the ticket to be updated.
     * @param seatNumber The new seat number to be assigned.
     * @return ResponseEntity containing the API response for updating user seat allocation.
     */
    @PutMapping("/update-seat-number/{ticketId}")
    @Operation(summary = ApiDocumentationTags.UPDATE_USER_DETAIL,
            description = ApiDocumentationTags.UPDATE_USER_DETAIL_DESCRIPTION,
            tags = ApiDocumentationTags.TICKET_BOOKING)
    @ApiResponse(responseCode = "200", description = ApiResponseMessages.USER_DETAIL_UPDATED_SUCCESSFUL)
    @ApiResponse(responseCode = "500", description = ApiResponseMessages.FAILED_TO_UPDATE_USER_DETAIL)
    public ResponseEntity<CustomApiResponse<TicketBookingDto>> updateUserSeatAllocationDetails(
            @PathVariable Integer ticketId, @RequestParam Integer seatNumber) {
        CustomApiResponse<TicketBookingDto> response = ticketBookingService.updateUserSeatAllocation(ticketId, seatNumber);
        return ResponseEntity.ok(response);
    }


    /**
     * Endpoint for deleting a user.
     *
     * @param userName The username of the user to be deleted.
     * @return ResponseEntity containing the API response for the user deletion operation.
     */
    @DeleteMapping("/delete-user")
    @Operation(summary = ApiDocumentationTags.USER_DELETE,
            description = ApiDocumentationTags.USER_DELETE_DESCRIPTION,
            tags = ApiDocumentationTags.TICKET_BOOKING)
    @ApiResponse(responseCode = "200", description = ApiResponseMessages.USER_DELETED_SUCCESSFULLY)
    @ApiResponse(responseCode = "500", description = ApiResponseMessages.USER_DELETE_FAILED)
    public ResponseEntity<CustomApiResponse<TicketBookingDto>> deleteUserDetails(
            @RequestParam String userName) {
        CustomApiResponse<TicketBookingDto> response = ticketBookingService.deleteUserDetail(userName);
        return ResponseEntity.ok(response);
    }


}
