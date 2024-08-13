package com.book.manager.domain.repository

import com.book.manager.domain.model.Book
import com.book.manager.domain.model.BookWithRental
import java.time.LocalDate

interface BookRepository {
    fun findAllWithRental(): List<BookWithRental>//BookWithRentalクラスのリストを返すだけ
    fun findWithRental(id: Long): BookWithRental?
    fun register(book: Book)
}

/*
ドメイン層で使用されるリポジトリのインターフェースを定義。
findAllWithRental(): List<BookWithRental>メソッドを宣言し、書籍とレンタル情報を取得。
 */