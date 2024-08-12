package com.book.manager.domain.repository

import com.book.manager.domain.model.Book
import com.book.manager.domain.model.BookWithRental
import java.time.LocalDate

interface BookRepository {
    fun findAllWithRental(): List<BookWithRental>
}