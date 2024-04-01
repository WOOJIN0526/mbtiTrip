package com.example.test.POST.Controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.AnswerPageDTO;
import com.example.test.POST.Service.AnswerService;
import com.example.test.POST.Service.PostService;
import com.example.test.User.Service.UserService;
import com.example.test.paging.Criteria;



@RequestMapping("/answer")
@Controller
public class AnswerController {

		@Autowired
		AnswerService answerService;
		
		
	
		//등록
		@PreAuthorize("isAuthenticated()")
		@PostMapping(value = "/new", consumes = "application/json",
					 produces = {MediaType.TEXT_PLAIN_VALUE})
		public ResponseEntity<String> create(@RequestBody AnswerDTO answer){
			
			int insertCount = answerService.register(answer);
			
			return insertCount == 1
			? new ResponseEntity<>("success", HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			// 삼향 연산자 처리
		}
		
		//특정게시물의 댓글 목록 확인
		@GetMapping(value = "/pages/{pno}/{page}",
				produces = {
						MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_UTF8_VALUE})
		public ResponseEntity<AnswerPageDTO> getList(@PathVariable("page") int page,
													   @PathVariable("pno") Long pno){
			Criteria cri = new Criteria(page, 10);
			return new ResponseEntity<>(answerService.getListPage(cri, pno), HttpStatus.OK);
		}
		
		//댓글 조회
		@GetMapping(value = "/{ano}", 
				produces = {MediaType.APPLICATION_XML_VALUE,
						   MediaType.APPLICATION_JSON_UTF8_VALUE})
		public ResponseEntity<AnswerDTO> get(@PathVariable("ano") Long ano){
			return new ResponseEntity<>(answerService.get(ano), HttpStatus.OK);
		}
		
		//댓글 삭제
		@PreAuthorize("principal.username == #answer.replyer")
		@DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
		public ResponseEntity<String> remove(@PathVariable("ano") Long ano){
			return answerService.remove(ano) == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		
		//댓글 수정
		@PreAuthorize("principal.username == #answer.replyer")
		@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
				value = "/{ano}",
				consumes = "application/json",
				produces = {MediaType.TEXT_PLAIN_VALUE})
		public ResponseEntity<String> modify(@RequestBody AnswerDTO answer,
											 @PathVariable("ano") Long ano){
			answer.setAno(ano);
			
			return answerService.modify(answer) == 1
					? new ResponseEntity<>("success", HttpStatus.OK)
					: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}

}
