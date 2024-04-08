package com.example.test.Adventure.Controller;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;



import com.example.test.Adventure.Service.AdventureService;
import com.example.test.Adventure.Service.Adventure_CategoryService;
import com.example.test.POST.Controller.PageDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;
import com.example.test.User.Service.adminService;
import com.example.test.item.ItemType;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.paging.Criteria;
import com.example.test.replace.ReplaceForm;

import jakarta.validation.Valid;


@RequestMapping("/adventure")
@Controller
public class AdventureController {

	@Autowired
	AdventureService adService;
	
	@Autowired
	adminService admService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	Adventure_CategoryService adCategoryService;
	
	@RequestMapping("/list")
	public ModelAndView List(ModelAndView mv, Criteria cri) throws Exception {

	    PageDTO pageMaker = new PageDTO();
	    pageMaker.setCri(cri); //page, perpagenum 셋팅
	    pageMaker.setTotalCount(adService.listCount(cri)); //총 게시글 수 셋팅

	    //View에 페이징 처리를 위한 조건 및 그에 맞는 게시판 리스트 전송
	    mv.addObject("pageMaker", pageMaker);
	    mv.addObject("data", adService.list(cri)); //현재페이지에 표시할 게시글 목록 가져옴

	    mv.setViewName("adventure_list");

	    return mv;
	    }
	
	
    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer itemID) {
        ItemDTO item = this.adService.getPost(itemID);
        model.addAttribute("replace", item);
        return "adventure_detail";
    }

    //게시물을 작성할 때도 카테고리를 선택해서 게시물을 생성해야 한다. 
    //따라서 전체 카테고리 중에서 알맞는 카테고리를 선택할 수 있어야 한다. 즉, 아래와 같이 게시물 등록 폼에서 전체 카테고리를 조회한다.
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String Create(ReplaceForm Form, Model model) {
    	//model.addAttribute("categoryList", rpCategoryService.getList());
        return "replace_form";
    }

   
    
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/create")
//    public String Create(@Valid ReplaceForm replaceForm, 
//            BindingResult bindingResult, Principal principal, ItemDTO itemdto) {
//    	itemdto.setType(ItemType.replace);
//        if (bindingResult.hasErrors()) {
//            return "replace_form";
//        }
//        AdminDTO admin = this.adService.getadmin(principal.getName());
//    
//        this.rpService.create(replaceForm.getType(), replaceForm.getMbtiID(), admin,replaceForm.getReplacePrice(), replaceForm.getReplaceName(), replaceForm.getReplaceContents(), 
//        					  replaceForm.getTel(), replaceForm.getReplaceLocation(),  replaceForm.getFile() );
//        return "redirect:/question/list";
//    }
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<String> create(ItemDTO itemdto){
    	itemdto.setType(ItemType.replace);
    	
    	System.out.println(itemdto.toString());
    	for(MultipartFile file : itemdto.getFile()) {
    		System.out.println(file.getOriginalFilename());
    	}
    	return ResponseEntity.ok().body("Success message");
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String Modify(ReplaceForm postForm, @PathVariable("id") Integer itemID, Principal principal) {
        ItemDTO itemDto = this.adService.getPost(itemID);
        if(!itemDto.getUsername()
        		.equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        postForm.setReplaceName(itemDto.getItemName());
        postForm.setReplaceContents(itemDto.getContents());
        postForm.setMbtiID(itemDto.getMbti());
        postForm.setReplaceLocation(itemDto.getLocation());
        postForm.setTel(itemDto.getTel());
        postForm.setReplacePrice(itemDto.getPrice());
        postForm.setType(itemDto.getType());
        postForm.setFile(itemDto.getImgeUrl());
        
        return "replace_form";
    }
    
    //수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String Modify(@Valid ReplaceForm postForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer itemID) {
        if (bindingResult.hasErrors()) {
            return "replace_form";
        }
        ItemDTO itemDto = this.adService.getPost(itemID);
        if (!itemDto.getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.adService.modify(itemDto, postForm.getType(),postForm.getMbtiID(), postForm.getReplacePrice(), postForm.getReplaceName(),
        					postForm.getReplaceLocation(), postForm.getTel(), postForm.getReplaceContents(), postForm.getFile());
        return String.format("redirect:/adventure/detail/%s", itemID);
    }
    
    //삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String Delete(Principal principal, @PathVariable("id") Integer itemID) {
        ItemDTO itemDto = this.adService.getPost(itemID);
        if (!itemDto.getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.adService.delete(itemDto);
        return "redirect:/";
    }
    
    //추천
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/suggestion/{id}")
    public String Suggestion(Principal principal, @PathVariable("id") Integer itemID) {
        ItemDTO itemDto = this.adService.getPost(itemID);
        UserDTO user = this.userService.getUser(principal.getName());
        this.adService.suggestion(itemDto, user);
        return String.format("redirect:/adventure/detail/%s", itemID);
    }	
    

}
