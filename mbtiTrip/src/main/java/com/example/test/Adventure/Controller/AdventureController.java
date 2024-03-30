package com.example.test.Adventure.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.Adventure.AdventureForm;
import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.Adventure.DTO.Adventure_CategoryDTO;
import com.example.test.Adventure.Service.AdventureService;
import com.example.test.Adventure.Service.Adventure_CategoryService;
import com.example.test.POST.DTO.AnswerForm;
import com.example.test.POST.DTO.Criteria;
import com.example.test.POST.DTO.PageMaker;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.DTO.PostForm;
import com.example.test.POST.DTO.Post_CategoryDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;

import jakarta.validation.Valid;

@Controller
public class AdventureController {

	@Autowired
	AdventureService adService;
	
	@Autowired
	Adventure_CategoryService adcService;
	
	@Autowired
	UserService userService;
	
	//게시물 목록을 조회하고, 페이징 처리를 위한 정보를 추가하여 뷰로 전달하는 메서드
		@RequestMapping("list")
	    public ModelAndView postList(ModelAndView mv, Criteria cri) throws Exception {

	        PageMaker pageMaker = new PageMaker();//이 객체는 페이징 처리를 위한 정보를 제공합니다.
	        pageMaker.setCri(cri); //page, perpagenum 셋팅, 현재 페이지 번호와 페이지당 게시물 수 등의 정보를 전달
	        pageMaker.setTotalCount(adService.listCount(cri)); //총 게시글 수 셋팅(전체 게시물 수를 기반으로 페이지 수 등의 정보를 계산)

	        //View에 페이징 처리를 위한 조건 및 그에 맞는 게시판 리스트 전송
	        mv.addObject("pageMaker", pageMaker);
	        mv.addObject("data", adService.list(cri)); 

	        mv.setViewName("/adventure/adventure_list");

	        return mv;
		}

	    @RequestMapping(value = "/detail/{id}")
	    public String detail(Model model, @PathVariable("id") Integer id, AdventureForm adForm) {
	        AdventureDTO post = this.adService.getAdventure(id);
	        model.addAttribute("adventure", post);
	        return "detail";
	    }

	    //게시물을 작성할 때도 카테고리를 선택해서 게시물을 생성해야 한다. 
	    //따라서 전체 카테고리 중에서 알맞는 카테고리를 선택할 수 있어야 한다. 즉, 아래와 같이 게시물 등록 폼에서 전체 카테고리를 조회한다.
	    //@PreAuthorize("isAuthenticated()")
	    @GetMapping("/create")
	    public String Create(AdventureForm adForm, Model model) {
	    	model.addAttribute("categoryList", adcService.getList());
	        return "form";
	    }

	    //컨트롤러에 넘어온 카테고리 이름으로 카테고리 엔티티를 조회하고, 
	    //조회한 카테고리 엔티티를 질문 엔티티에 넣어주고 저장하면 질문에 카테고리가 생기게 된다.
	    //@PreAuthorize("isAuthenticated()")
	    @PostMapping("/post/create")
	    public String Create(Model model, @Valid AdventureForm adForm, 
	    	BindingResult bindingResult, Principal principal) {
	        if (bindingResult.hasErrors()) {
	        	model.addAttribute("categoryList", adcService.getList());
	            return "ad_form";
	        }
	        UserDTO User = this.userService.getUser(principal.getName());
	        Adventure_CategoryDTO category = this.adcService.getCategory(adForm.getCategoryName());
	        this.adService.create(adForm.getTitle(), adForm.getContent(), User, category);
	        return "redirect:/adventure/list";
	        }
	    
	    //게시물 수정페이지를 열어줌
	    //@PreAuthorize("isAuthenticated()")
	    @GetMapping("/modify/{id}")
	    public String Modify(AdventureForm adForm, @PathVariable("id") Integer id, Principal principal) {
	        AdventureDTO adDto = this.adService.getAdventure(id);
	        if(!adDto.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	        }
	        adForm.setTitle(adDto.getAdventureName());
	        adForm.setContent(adDto.getAdventureContent());
	        return "form";
	    }
	    
	    //게시물 수정을 처리
	    //@PreAuthorize("isAuthenticated()")
	    @PostMapping("/modify/{id}")
	    public String Modify(@Valid AdventureForm adForm, BindingResult bindingResult, 
	            Principal principal, @PathVariable("id") Integer id) {
	        if (bindingResult.hasErrors()) {
	            return "form";
	        }
	        AdventureDTO adDto = this.adService.getAdventure(id);
	        if (!adDto.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	        }
	        this.adService.modify(adDto, adForm.getTitle(), adForm.getContent());
	        return String.format("redirect://detail/%s", id);
	    }
	    
	    //@PreAuthorize("isAuthenticated()")
	    @GetMapping("/delete/{id}")
	    public String Delete(Principal principal, @PathVariable("id") Integer id) {
	        AdventureDTO postDto = this.adService.getAdventure(id);
	        if (!postDto.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
	        }
	        this.adService.delete(postDto);
	        return "redirect:/";
	    }
	    
	    //@PreAuthorize("isAuthenticated()")
	    @GetMapping("/vote/{id}")
	    public String Vote(Principal principal, @PathVariable("id") Integer id) {
	        AdventureDTO adDto = this.adService.getAdventure(id);
	        UserDTO UserDto = this.userService.getUser(principal.getName());
	        this.adService.vote(adDto, UserDto);
	        return String.format("redirect://detail/%s", id);
	    }
  
  
    

}
