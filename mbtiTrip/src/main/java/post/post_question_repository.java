package post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;




public interface post_question_repository extends JpaRepository<post_question, Integer>{

	post_question findBySubject(String subject);
	Page<post_question> findAll(Pageable pageable); 
}
