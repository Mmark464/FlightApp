package com.flightManagementSystem.service;

import com.flightManagementSystem.dto.*;
import org.springframework.data.domain.Page;

public interface BookService {

    BookDto bookFlight (BookRequest bookRequest);

    Page<BookDto> getAllBookings(String fullName, int pageSize, int pageNumber);

    BookDto updateBooking(BookRequest bookRequest);

    void cancelBooking(BookRequest bookRequest);
}
