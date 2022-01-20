/**
 * 
 */
package com.polarisdigitech.bookstore.Repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.polarisdigitech.bookstore.Entity.Book;


/**
 * @author Famous B
 *
 */
@Repository
public interface LibraryRepository extends JpaRepository<Book, Long>{

	@Query("select b from Book b where b.id =?1")
	Book getById(Long id);
	
	Book findByTitle(String title);
	
	@Query(value="select * from book where id > 0 ORDER BY create_date DESC LIMIT 5 OFFSET ?1 ", nativeQuery=true)
	List<Book> getAllBooks(int page);
	
	@Query(value="select count(*) from book where id > 0 ORDER BY create_date DESC", nativeQuery=true)
	BigInteger getTotalBooksCount();
	
	@Query(value="select * from book where id > 0 and ( title=?1 OR author=?2) ORDER BY create_date DESC LIMIT 5 OFFSET ?3 ", nativeQuery=true)
	List<Book> getByTitleOrAuthor(String title, String author,int page);
	
	@Query(value="select count(*) from book where id > 0 and ( title=?1 OR author=?2) ORDER BY create_date DESC ", nativeQuery=true)
	BigInteger getTotalCountForTitleOrAuthor(String title, String author);
}
