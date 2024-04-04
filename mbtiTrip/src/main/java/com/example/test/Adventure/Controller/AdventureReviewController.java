package com.example.test.Adventure.Controller;

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



import com.example.test.Adventure.AdventureReviewForm;
import com.example.test.Adventure.DTO.Adventure_CategoryDTO;
import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.Adventure.Service.Adventure_CategoryService;
import com.example.test.Adventure.Service.Adventure_ReviewService;
import com.example.test.POST.Controller.PageDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;
import com.example.test.paging.Criteria;


import jakarta.validation.Valid;

@RequestMapping("/adventureReview/")
@Controller
public class AdventureReviewController {

	@Autowired
	Adventure_ReviewService adrService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	Adventure_CategoryService adCategoryService;
	
	@RequestMapping("/list")
	public ModelAndView List(ModelAndView mv, Criteria cri) throws Exception {

	    PageDTO pageMaker = new PageDTO();
	    pageMaker.setCri(cri); //page, perpagenum 셋팅
	    pageMaker.setTotalCount(adrService.listCount(cri)); //총 게시글 수 셋팅

	    //View에 페이징 처리를 위한 조건 및 그에 맞는 게시판 리스트 전송
	    mv.addObject("pageMaker", pageMaker);
	    mv.addObject("data", adrService.list(cri)); //현재페이지에 표시할 게시글 목록 가져옴

	    mv.setViewName("adventureReview_list");

	    return mv;
	    }
	
	
    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer adventureReviewID) {
        Adventure_ReviewDTO adr = this.adrService.getPost(adventureReviewID);
        model.addAttribute("adventure", adr);
        return "adventureReview_detail";
    }

    //게시물을 작성할 때도 카테고리를 선택해서 게시물을 생성해야 한다. 
    //따라서 전체 카테고리 중에서 알맞는 카테고리를 선택할 수 있어야 한다. 즉, 아래와 같이 게시물 등록 폼에서 전체 카테고리를 조회한다.
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String Create(AdventureReviewForm Form, Model model) {
    	model.addAttribute("categoryList", adCategoryService.getList());
        return "adventureReview_form";
    }

    //컨트롤러에 넘어온 카테고리 이름으로 카테고리 엔티티를 조회하고, 
    //조회한 카테고리 엔티티를 질문 엔티티에 넣어주고 저장하면 질문에 카테고리가 생기게 된다.
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String Create(Model model, @Valid AdventureReviewForm Form, 
    	BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("categoryList", adCategoryService.getList());
            return "adventureReview_form";
        }
        UserDTO User = this.userService.getUser(principal.getName());
        Adventure_CategoryDTO category = this.adCategoryService.getCategory(Form.getAdventureCategoryID());
        this.adrService.create(Form.getTitle(),Form.getContent(), User,category);
        return "redirect:/adventureReview/list";
        }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String Modify(AdventureReviewForm postForm, @PathVariable("id") Integer adventureID, Principal principal) {
        Adventure_ReviewDTO adrDto = this.adrService.getPost(adventureID);
        if(!adrDto.getUser().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        postForm.setTitle(adrDto.getReviewTitle());
        postForm.setContent(adrDto.getContent());
        return "adventureReview_form";
    }
    
    //수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String Modify(@Valid AdventureReviewForm postForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer adventureID) {
        if (bindingResult.hasErrors()) {
            return "adventureReview_form";
        }
        Adventure_ReviewDTO adrDto = this.adrService.getPost(adventureID);
        if (!adrDto.getUser().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.adrService.modify(adrDto, postForm.getTitle(), postForm.getContent());
        return String.format("redirect:/adventureReview/detail/%s", adventureID);
    }
    
    //삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String Delete(Principal principal, @PathVariable("id") Integer adventureID) {
        Adventure_ReviewDTO adrDto = this.adrService.getPost(adventureID);
        if (!adrDto.getUser().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.adrService.delete(adrDto);
        return "redirect:/";
    }
    
    //추천
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/suggestion/{id}")
    public String Suggestion(Principal principal, @PathVariable("id") Integer adventureID) {
        Adventure_ReviewDTO adrDto = this.adrService.getPost(adventureID);
        UserDTO user = this.userService.getUser(principal.getName());
        this.adrService.suggestion(adrDto, user);
        return String.format("redirect:/adventureReview/detail/%s", adventureID);
    }


}
