package com.example.test.replace.Controller;


import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;



import com.example.test.POST.Controller.PageDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;
import com.example.test.paging.Criteria;
import com.example.test.replace.ReplaceReviewForm;
import com.example.test.replace.DTO.ReplaceCategoryDTO;
import com.example.test.replace.DTO.ReplaceReviewDTO;
import com.example.test.replace.Service.ReplaceCategoryService;
import com.example.test.replace.Service.ReplaceReviewService;


import jakarta.validation.Valid;

@RequestMapping("/replace/review/*")
@Controller
public class ReplaceReviewContoller {

	@Autowired
	ReplaceReviewService rprService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ReplaceCategoryService rpCategoryService;
	
	@RequestMapping("list")
	public ModelAndView List(ModelAndView mv, Criteria cri) throws Exception {

	    PageDTO pageMaker = new PageDTO();
	    pageMaker.setCri(cri); //page, perpagenum 셋팅
	    pageMaker.setTotalCount(rprService.listCount(cri)); //총 게시글 수 셋팅

	    //View에 페이징 처리를 위한 조건 및 그에 맞는 게시판 리스트 전송
	    mv.addObject("pageMaker", pageMaker);
	    mv.addObject("data", rprService.list(cri)); 

	    mv.setViewName("replaceReview_list");

	    return mv;
	    }
	
	
    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer replaceReviewID) {
        ReplaceReviewDTO rpr = this.rprService.getPost(replaceReviewID);
        model.addAttribute("replace", rpr);
        return "replaceReview_detail";
    }

    //게시물을 작성할 때도 카테고리를 선택해서 게시물을 생성해야 한다. 
    //따라서 전체 카테고리 중에서 알맞는 카테고리를 선택할 수 있어야 한다. 즉, 아래와 같이 게시물 등록 폼에서 전체 카테고리를 조회한다.
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String Create(ReplaceReviewForm Form, Model model) {
    	model.addAttribute("categoryList", rpCategoryService.getList());
        return "replaceReview_form";
    }

    //컨트롤러에 넘어온 카테고리 이름으로 카테고리 엔티티를 조회하고, 
    //조회한 카테고리 엔티티를 질문 엔티티에 넣어주고 저장하면 질문에 카테고리가 생기게 된다.
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String Create(Model model, @Valid ReplaceReviewForm Form, 
    	BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("categoryList", rpCategoryService.getList());
            return "replaceReview_form";
        }
        UserDTO User = this.userService.getUser(principal.getName());
        ReplaceCategoryDTO category = this.rpCategoryService.getCategory(Form.getAdventureCategoryID());
        this.rprService.create(Form.getTitle(),Form.getContent(), User,category);
        return "redirect:/replaceReview/list";
        }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String Modify(ReplaceReviewForm postForm, @PathVariable("id") Integer replaceReviewID, Principal principal) {
        ReplaceReviewDTO adrDto = this.rprService.getPost(replaceReviewID);
        if(!adrDto.getUserId().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        postForm.setTitle(adrDto.getReviewTitle());
        postForm.setContent(adrDto.getContent());
        return "adventureReview_form";
    }
    
    //수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String Modify(@Valid ReplaceReviewForm postForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer replaceReviewID) {
        if (bindingResult.hasErrors()) {
            return "adventureReview_form";
        }
        ReplaceReviewDTO rprDto = this.rprService.getPost(replaceReviewID);
        if (!rprDto.getUserId().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.rprService.modify(rprDto, postForm.getTitle(), postForm.getContent());
        return String.format("redirect:/adventureReview/detail/%s", replaceReviewID);
    }
    
    //삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String Delete(Principal principal, @PathVariable("id") Integer replaceReviewID) {
        ReplaceReviewDTO rprDto = this.rprService.getPost(replaceReviewID);
        if (!rprDto.getUserId().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.rprService.delete(rprDto);
        return "redirect:/";
    }
    
    //추천
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/suggestion/{id}")
    public String Suggestion(Principal principal, @PathVariable("id") Integer replaceReviewID) {
        ReplaceReviewDTO rprDto = this.rprService.getPost(replaceReviewID);
        UserDTO user = this.userService.getUser(principal.getName());
        this.rprService.suggestion(rprDto, user);
        return String.format("redirect:/adventureReview/detail/%s", replaceReviewID);
    }
}
