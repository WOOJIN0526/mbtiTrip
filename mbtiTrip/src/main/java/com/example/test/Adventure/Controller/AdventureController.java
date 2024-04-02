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


import com.example.test.Adventure.AdventureForm;
import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.Adventure.DTO.Adventure_CategoryDTO;
import com.example.test.Adventure.Service.AdventureService;
import com.example.test.Adventure.Service.Adventure_CategoryService;
import com.example.test.POST.Controller.PageDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;
import com.example.test.paging.Criteria;


import jakarta.validation.Valid;


@RequestMapping("/adventure")
@Controller
public class AdventureController {

	@Autowired
	AdventureService adService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	Adventure_CategoryService adCategoryService;
	
	@RequestMapping("list")
	public ModelAndView List(ModelAndView mv, Criteria cri) throws Exception {

	    PageDTO pageMaker = new PageDTO();
	    pageMaker.setCri(cri); //page, perpagenum 셋팅
	    pageMaker.setTotalCount(adService.listCount(cri)); //총 게시글 수 셋팅

	    //View에 페이징 처리를 위한 조건 및 그에 맞는 게시판 리스트 전송
	    mv.addObject("pageMaker", pageMaker);
	    mv.addObject("data", adService.list(cri)); 

	    mv.setViewName("adventure_list");

	    return mv;
	    }
	
	
    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer adventureID) {
        AdventureDTO ad = this.adService.getPost(adventureID);
        model.addAttribute("adventure", ad);
        return "adventure_detail";
    }

    //게시물을 작성할 때도 카테고리를 선택해서 게시물을 생성해야 한다. 
    //따라서 전체 카테고리 중에서 알맞는 카테고리를 선택할 수 있어야 한다. 즉, 아래와 같이 게시물 등록 폼에서 전체 카테고리를 조회한다.
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String Create(AdventureForm Form, Model model) {
    	model.addAttribute("categoryList", adCategoryService.getList());
        return "adventure_form";
    }

    //컨트롤러에 넘어온 카테고리 이름으로 카테고리 엔티티를 조회하고, 
    //조회한 카테고리 엔티티를 질문 엔티티에 넣어주고 저장하면 질문에 카테고리가 생기게 된다.
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String Create(Model model, @Valid AdventureForm postForm, 
    	BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("categoryList", adCategoryService.getList());
            return "adventure_form";
        }
        UserDTO User = this.userService.getUser(principal.getName());
        Adventure_CategoryDTO category = this.adCategoryService.getCategory(postForm.getAdventureCategoryID());
        this.adService.create(postForm.getPostCategoryID(), postForm.getMbtiID(), postForm.getCityID(), postForm.getAdventureTypeId(),
        		postForm.getAdventureLocation(), postForm.getAdventureName(), postForm.getAdventurePrice(), postForm.getAdventureContent(), postForm.getTel(), User,category);
        return "redirect:/adventure/list";
        }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String Modify(AdventureForm postForm, @PathVariable("id") Integer adventureID, Principal principal) {
        AdventureDTO adDto = this.adService.getPost(adventureID);
        if(!adDto.getAdventureAdmin().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        postForm.setAdventureName(adDto.getAdventureName());
        postForm.setAdventureContent(adDto.getAdventureContent());
        return "adventure_form";
    }
    
    //수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String Modify(@Valid AdventureForm postForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer adventureID) {
        if (bindingResult.hasErrors()) {
            return "adventure_form";
        }
        AdventureDTO adDto = this.adService.getPost(adventureID);
        if (!adDto.getAdventureAdmin().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.adService.modify(adDto, postForm.getPostCategoryID(), postForm.getMbtiID(), postForm.getCityID(), postForm.getAdventureTypeId(), postForm.getAdventureLocation(),
        		postForm.getAdventureName(), postForm.getAdventurePrice(), postForm.getAdventureContent(), postForm.getTel());
        return String.format("redirect:/adventure/detail/%s", adventureID);
    }
    
    //삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String Delete(Principal principal, @PathVariable("id") Integer adventureID) {
        AdventureDTO adDto = this.adService.getPost(adventureID);
        if (!adDto.getAdventureAdmin().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.adService.delete(adDto);
        return "redirect:/";
    }
    
    //추천
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/suggestion/{id}")
    public String Suggestion(Principal principal, @PathVariable("id") Integer adventureID) {
        AdventureDTO adDto = this.adService.getPost(adventureID);
        UserDTO user = this.userService.getUser(principal.getName());
        this.adService.suggestion(adDto, user);
        return String.format("redirect:/adventure/detail/%s", adventureID);
    }
    

}
