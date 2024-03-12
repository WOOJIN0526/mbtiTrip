package post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;




public interface post_question_repository extends JpaRepository<post_question, Integer>{

	post_question findBySubject(String subject);
	Page<post_question> findAll(Pageable pageable); 
	
	// 검색할때 키워드 입력시 해당키워드가 들어간 질문을 출력한다던데 아직 잘모르겠음. 0312 김현석
	List<post_question> findByTitleContaining(String keyword);
	
}
