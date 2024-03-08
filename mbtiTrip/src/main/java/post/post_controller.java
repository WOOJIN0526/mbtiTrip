package post;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;



import jakarta.validation.Valid;



@Controller
public class post_controller {

	@Autowired
	private post_question_service questionService;
	@Autowired
	private user.UserService userService;
	
	@GetMapping("/")
	public String list(Model model,
			@RequestParam(value="page", defaultValue = "0")int page) {
		Page<post_question> paging = this.questionService.getList(page);
		model.addAttribute("paging" ,paging);
		return ""; 
	}
	
	@GetMapping(value = "/")
	public String detail(Model model, 
			@PathVariable("id") Integer id,
			post_answer_form answerForm) {
		post_question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("")
	public String questionCreate(post_question_form questionForm) {
		
		return "";
		
	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("")
	public String questionCreate(@Valid post_question_form questionForm, 
							BindingResult bindingResult,
							Principal principal) 
	{
		if(bindingResult.hasErrors()) {
			return "";
		}
		
		user.SiteUser siteUser = this.userService.getUser(principal.getName());
		this.questionService.create(questionForm.getSubject(), 
									questionForm.getContent(),
									siteUser,
									questionForm.getMbti());
		return "redirect:/";
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("")
	public String questionModify(@PathVariable("id") Integer id,
								  post_question_form questionForm,
								  Principal principal) {

		
		post_question question = this.questionService.getQuestion(id);
		if(!question.getAuthor().getUsername()
				.equals(principal.getName())) {
			throw new 
			ResponseStatusException(
					HttpStatus.BAD_REQUEST, "수정권한 없음");
			
		}
		questionForm.setSubject(question.getSubject());
		questionForm.setContent(question.getContent());
		
		return "";
		
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("")
	public String questionModify(@PathVariable("id") Integer id,
								  @Valid post_question_form questionForm,
								  BindingResult bindingResult,
								  Principal principal) {
		if(bindingResult.hasErrors()) {
			return "";
		}
		
		post_question question = this.questionService.getQuestion(id);
		if(!question.getAuthor().getUsername()
				.equals(principal.getName())) {
			throw new 
			ResponseStatusException(
					HttpStatus.BAD_REQUEST, "수정권한 없음");
			
		}
		this.questionService.modify(question, 
				questionForm.getSubject(), questionForm.getContent(), questionForm.getMbti());
		
		return String.format("redirect:", id);
		
	}	
	
	
	
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("")
	public String questionDelete(Principal principal, 
			@PathVariable("id") Integer id){
		post_question question = this.questionService.getQuestion(id);
		if(!question.getAuthor().getUsername()
				.equals(principal.getName())) {
			throw new 
			ResponseStatusException(
					HttpStatus.BAD_REQUEST, "삭제권한 없음");
			
		}
		this.questionService.delete(question);
		return "redirect:/";
	}
}
// 포스트 컨트롤러 포스트맵핑, 겟맵핑, 리다이렉트 부분 url 필요
// 0308 김현석