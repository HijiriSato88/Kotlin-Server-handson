package com.book.manager.domain.repository

import com.book.manager.domain.model.Book
import com.book.manager.domain.model.BookWithRental
import java.time.LocalDate

interface BookRepository {
    fun findAllWithRental(): List<BookWithRental>//BookWithRentalクラスのリストを返すだけ
    fun findWithRental(id: Long): BookWithRental?
    fun register(book: Book)
    fun update(id: Long, title: String?, author: String?, releaseDate: LocalDate?)
    fun delete(id: Long)
}

/*
ドメイン層で使用されるリポジトリのインターフェースを定義。
findAllWithRental(): List<BookWithRental>メソッドを宣言し、書籍とレンタル情報を取得。
 */