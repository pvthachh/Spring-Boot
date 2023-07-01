package com.example.BTL_WEB.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BTL_WEB.book.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	@Query("select c from Comment c where c.book.book_id = ?1 ORDER BY c.comment_id DESC")
	List<Comment> getCommentByBook_id(Integer book_id);
	
	@Modifying
    @Query(value = "DELETE FROM comment WHERE user_id = ?1", nativeQuery = true)
    void deleteCommentByUserID(Integer user_id);
	
	@Modifying
	@Query(value = "DELETE FROM comment WHERE book_id = ?1", nativeQuery = true)
	void deleteCommentByBookID(Integer book_id);
}
