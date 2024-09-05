package com.flightManagementSystem.repository;

import com.flightManagementSystem.entity.Book;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    Optional<Book> findByIdAndIsEnabledTrue(Long id);
    Page<Book> findAllByIsEnabledTrue(Pageable pageable);
}
