package com.assessment.ticket.booking.service;

import com.assessment.ticket.booking.common.ApiResponseMessages;
import com.assessment.ticket.booking.common.CustomApiResponse;
import com.assessment.ticket.booking.domain.TicketBooking;
import com.assessment.ticket.booking.dto.TicketBookingDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Service class handling various ticket booking operations.
 */
@Service
public class TicketBookingService {

    @Autowired
    ModelMapper modelMapper;

    private final Map<Integer, TicketBooking> ticketBookingMap = new ConcurrentHashMap<>();
    private Map<String, Integer> sectionACounter = new HashMap<>();
    private Map<String, Integer> sectionBCounter = new HashMap<>();

    /**
     * Purchase a ticket and allocate a seat based on the provided TicketBookingDto.
     *
     * @param ticketBookingDto The DTO containing ticket booking information.
     * @return CustomApiResponse with information about the ticket booking operation.
     */
    public CustomApiResponse<TicketBookingDto> purchaseTicket(TicketBookingDto ticketBookingDto) {
        Integer ticketId = generateTicketId();
        String section = ticketBookingDto.getSection();
        Integer seatNumber = allocateSeat(section);
        ticketBookingDto.setTicketId(ticketId);
        ticketBookingDto.setSeatNumber(seatNumber);
        TicketBooking ticketBooking = modelMapper.map(ticketBookingDto, TicketBooking.class);
        ticketBookingMap.put(ticketBooking.getTicketId(), ticketBooking);
        return new CustomApiResponse<>(HttpStatus.OK.value(),
                ApiResponseMessages.TICKET_BOOKING_SUCCESSFULLY,
                modelMapper.map(ticketBooking, TicketBookingDto.class));
    }

    /**
     * Generate a new ticket ID.
     *
     * @return The generated ticket ID.
     */
    private Integer generateTicketId() {
        return ticketBookingMap.size() + 1;
    }

    /**
     * Allocate a seat based on the provided section.
     *
     * @param section The section for which a seat needs to be allocated.
     * @return The allocated seat number.
     * @throws IllegalArgumentException if an invalid section is provided.
     */
    private int allocateSeat(String section) {
        Integer seatNumber = null;
        if ("A".equalsIgnoreCase(section)) {
             seatNumber = sectionACounter.getOrDefault(section, 0) + 1;
            sectionACounter.put(section, seatNumber);
            return seatNumber;
        } else if ("B".equalsIgnoreCase(section)) {
             seatNumber = sectionBCounter.getOrDefault(section, 100) + 1;
            sectionBCounter.put(section, seatNumber);
            return seatNumber;
        } else {
            throw new IllegalArgumentException("Invalid section: " + section);
        }
    }

    /**
     * Get user receipt details based on the provided ticket ID.
     *
     * @param ticketId The ID of the ticket for which receipt details are requested.
     * @return CustomApiResponse with information about the user receipt details.
     */
    public CustomApiResponse<TicketBookingDto> getUserReceiptDetails(Integer ticketId) {

        TicketBookingDto ticketBookingDto =
                modelMapper.map(ticketBookingMap.get(ticketId),TicketBookingDto.class);
        if(Optional.ofNullable(ticketBookingDto).isPresent()){
            return new CustomApiResponse<>(HttpStatus.OK.value(),
                    ApiResponseMessages.USER_RECEIPT_FETCHED_SUCCESSFUL, ticketBookingDto);
        } else {
            return new CustomApiResponse<>(HttpStatus.OK.value(),
                    ApiResponseMessages.TICKET_NOT_FOUND, null);
        }
    }

    /**
     * Get all tickets associated with a user's email.
     *
     * @param email The email of the user for whom tickets are requested.
     * @return CustomApiResponse with information about the ticket list.
     */
    public CustomApiResponse<List<TicketBookingDto>> getAllTickets(String email) {

        List<TicketBookingDto> ticketBookingDto = ticketBookingMap.values().stream()
                .filter(tickets -> tickets.getUserEmail().equals(email))
                .map(tickets -> modelMapper.map(tickets,TicketBookingDto.class)).toList();
        if(!ticketBookingDto.isEmpty()){
            return new CustomApiResponse<>(HttpStatus.OK.value(),
                    ApiResponseMessages.TICKET_LIST_FOUND, ticketBookingDto);
        } else {
            return new CustomApiResponse<>(HttpStatus.OK.value(),
                    ApiResponseMessages.TICKET_LIST_NOT_FOUND, ticketBookingDto);
        }
    }

    /**
     * Update the seat allocation for a user's ticket based on the provided ticket ID and seat number.
     *
     * @param ticketId   The ID of the ticket to be updated.
     * @param seatNumber The new seat number to be assigned.
     * @return CustomApiResponse with information about the seat update operation.
     */

    public CustomApiResponse<TicketBookingDto> updateUserSeatAllocation
            (Integer ticketId ,Integer seatNumber){
        TicketBooking oldUserTicketDetails = ticketBookingMap.get(ticketId);
        if(Optional.ofNullable(oldUserTicketDetails).isPresent()){
            oldUserTicketDetails.setSeatNumber(seatNumber);
            ticketBookingMap.put(ticketId, oldUserTicketDetails);
            return new CustomApiResponse<>(HttpStatus.OK.value(),
                    ApiResponseMessages.USER_DETAIL_UPDATED_SUCCESSFUL,
                    modelMapper.map(oldUserTicketDetails, TicketBookingDto.class));

        } else {
            return new CustomApiResponse<>(HttpStatus.OK.value(),
                    ApiResponseMessages.FAILED_TO_UPDATE_USER_DETAIL,  null);

        }

    }

    /**
     * Delete user details based on the provided username.
     *
     * @param userName The username of the user to be deleted.
     * @return CustomApiResponse with information about the user deletion operation.
     */
    public CustomApiResponse<TicketBookingDto> deleteUserDetail(String userName){

        List<TicketBooking> ticketBookingList = new ArrayList<>();
        for (TicketBooking ticketBooking : ticketBookingMap.values()){
            if(ticketBooking.getUserName().equals(userName)){
                ticketBookingList.add(ticketBooking);
                ticketBookingMap.remove(ticketBooking.getTicketId());
            }
        }
        return new CustomApiResponse<>(HttpStatus.OK.value(),
                ApiResponseMessages.USER_DELETED_SUCCESSFULLY,  modelMapper.map(ticketBookingList.get(0), TicketBookingDto.class));

    }

}
