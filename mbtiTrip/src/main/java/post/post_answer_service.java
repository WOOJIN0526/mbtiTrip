package post;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class post_answer_service {

	@Autowired
	private post_answer_repository answerRepository;

	public void create(post_question q, String content, user.SiteUser author) {
		post_answer a = new post_answer();
		a.setContent(content);
		System.out.println(a.getContent());
		a.setCreateDate(LocalDateTime.now());
		a.setQuestion(q);
		a.setAuthor(author);
		this.answerRepository.save(a);	
	}

	public post_answer getAnswer(Integer id) {
		Optional<post_answer> answer = this.answerRepository.findById(id);
		if(answer.isPresent()) {
			return answer.get();
		}
		return null;
	}

	public void modify(post_answer answer, String content) {
		answer.setContent(content);
		answer.setModifyDate(LocalDateTime.now());
		this.answerRepository.save(answer);
	}
	
}
