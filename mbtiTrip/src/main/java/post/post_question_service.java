package post;

import java.time.LocalDateTime;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import mbti.mbti;

@Service
public class post_question_service {
	@Autowired
	private post_question_repository qR;

	public Page<post_question> getList(int page){
		Pageable pageable = PageRequest.of(page, 10);
		return this.qR.findAll(pageable);
	}
	
	//아직 페이지당 항목수는 몇개인지 안정해짐 일단은 10개로 해둠
	//0308 김현석 수정
	public post_question getQuestion(Integer id) {
		
		Optional<post_question> oq = this.qR.findById(id);
		return oq.get();
	}
	
	public void create(String subject, String content, user.SiteUser author, mbti mbti) {
		post_question q1 = new post_question();
		q1.setSubject(subject);
		q1.setContent(content);
		q1.setAuthor(author);
		q1.setMbti(mbti);
		q1.setCreateDate(LocalDateTime.now());
		this.qR.save(q1);
	}
	// create에 조회수는 생성할필요 없는거 같아서 뺏음 0312 김현석
	
	public void modify(post_question question, String subject, String content, mbti mbti) {
		
		question.setSubject(subject);
		question.setContent(content);
		question.setMbti(mbti);
		question.setModifyDate(LocalDateTime.now());
		this.qR.save(question);
	}

	public void delete(post_question question) {
		this.qR.delete(question);
		
	}
	

	

	
	
}
