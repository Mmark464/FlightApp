package com.flightManagementSystem.service.impl;

import com.flightManagementSystem.dto.BookDto;
import com.flightManagementSystem.dto.BookRequest;
import com.flightManagementSystem.entity.Book;
import com.flightManagementSystem.exception.NoAvailableSeatsException;
import com.flightManagementSystem.mapper.BookMapper;
import com.flightManagementSystem.repository.BookRepository;
import com.flightManagementSystem.repository.FlightRepository;
import com.flightManagementSystem.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class BookServiceImpl implements BookService {

    BookMapper bookMapper;
    BookRepository bookRepository;
    FlightRepository flightRepository;

    @Override
    public BookDto bookFlight(BookRequest bookRequest) {

        Book book = bookMapper.requestToEntity(bookRequest);

        if(book.getFlight().getEmptySeats() >= book.getTickets()){

            book.getFlight()
                    .setEmptySeats(
                            book.getFlight().getEmptySeats() - book.getTickets()
                    );
            flightRepository.save(book.getFlight());

            return bookMapper.entityToDto(
                    bookRepository.save(book));
        }else{
            throw new NoAvailableSeatsException("No available seats on the flight");
        }
    }

    @Override
    public Page<BookDto> getAllBookings(String fullName, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return bookRepository.findAllByIsEnabledTrue(pageable)
                .map(bookMapper::entityToDto);
    }

    @Override
    public BookDto updateBooking(BookRequest bookRequest) {
        return null;
    }

    @Override
    public void cancelBooking(BookRequest bookRequest) {

    }
}
