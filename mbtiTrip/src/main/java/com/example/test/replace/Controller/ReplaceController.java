package com.example.test.replace.Controller;

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

import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;
import com.example.test.User.Service.adminService;
import com.example.test.item.ItemType;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.paging.Criteria;
import com.example.test.paging.PageDTO;
import com.example.test.replace.ReplaceForm;

import com.example.test.replace.Service.ReplaceCategoryService;
import com.example.test.replace.Service.ReplaceService;


import jakarta.validation.Valid;

@RequestMapping("/replace")
@Controller
public class ReplaceController { //파일첨부쪽 로직, 게시물등록자(admin?) 모르겠음

	@Autowired
	ReplaceService rpService;
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	ReplaceCategoryService rpCategoryService;
	
	@GetMapping("/list")
	public String list(Criteria cri, Model model) {
		
		model.addAttribute("list", rpService.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(rpService.getTotal(cri), 10, cri));
		return "replace_main";
	}
	

	
	
    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer itemID,
    		Principal principla) {
        ItemDTO item = this.rpService.getPost(itemID, principla);
        model.addAttribute("replace", item);
        return "replace_detail";
    }

    //게시물을 작성할 때도 카테고리를 선택해서 게시물을 생성해야 한다. 
    //따라서 전체 카테고리 중에서 알맞는 카테고리를 선택할 수 있어야 한다. 즉, 아래와 같이 게시물 등록 폼에서 전체 카테고리를 조회한다.
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String Create(ReplaceForm Form, Model model) {
    	//model.addAttribute("categoryList", rpCategoryService.getList());
        return "replace_form";
    }

    //컨트롤러에 넘어온 카테고리 이름으로 카테고리 엔티티를 조회하고, 
    //조회한 카테고리 엔티티를 질문 엔티티에 넣어주고 저장하면 질문에 카테고리가 생기게 된다.
	/*
	 * @PreAuthorize("isAuthenticated()")
	 * 
	 * @PostMapping("/create") public String Create(Model model, @Valid ReplaceForm
	 * postForm, BindingResult bindingResult, Principal principal) { if
	 * (bindingResult.hasErrors()) { model.addAttribute("categoryList",
	 * rpCategoryService.getList()); return "replace_form"; } UserDTO User =
	 * this.userService.getUser(principal.getName()); ReplaceCategoryDTO category =
	 * this.rpCategoryService.getCategory(postForm.getReplaceCategoryID());
	 * this.rpService.create(postForm.getPostCategoryID(), postForm.getMbtiID(),
	 * postForm.getCityID(), postForm.getReplaceType(),
	 * postForm.getReplaceLocation(), postForm.getReplaceName(),
	 * postForm.getReplacePrice(), postForm.getReplaceContents(), postForm.getTel(),
	 * User,category); return "redirect:/replace/list"; }
	 */
    
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/create")
//    public String Create(@Valid ReplaceForm replaceForm, 
//            BindingResult bindingResult, Principal principal, ItemDTO itemdto) {
//    	itemdto.setType(ItemType.replace);
//        if (bindingResult.hasErrors()) {
//            return "replace_form";
//        }
//        UserDTO admin = this.userService.getUser(principal.getName());
//    
//        this.rpService.create(replaceForm.getType(), replaceForm.getMbtiID(), admin,replaceForm.getReplacePrice(), replaceForm.getReplaceName(), replaceForm.getReplaceContents(), 
//        					  replaceForm.getTel(), replaceForm.getReplaceLocation(),  replaceForm.getFile() );
//        return "redirect:/replace/list";
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
        ItemDTO itemDto = this.rpService.getPost(itemID);
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
        ItemDTO itemDto = this.rpService.getPost(itemID);
        if (!itemDto.getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.rpService.modify(itemDto, postForm.getType(),postForm.getMbtiID(), postForm.getReplacePrice(), postForm.getReplaceName(),
        					postForm.getReplaceLocation(), postForm.getTel(), postForm.getReplaceContents(), postForm.getFile());
        return String.format("redirect:/replace/detail/%s", itemID);
    }
    
    //삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String Delete(Principal principal, @PathVariable("id") Integer itemID) {
        ItemDTO itemDto = this.rpService.getPost(itemID);
        if (!itemDto.getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.rpService.delete(itemDto);
        return "redirect:/";
    }
    
    //추천
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/suggestion/{id}")
    public String Suggestion(Principal principal, @PathVariable("id") Integer itemID) {
        ItemDTO itemDto = this.rpService.getPost(itemID);
        UserDTO user = this.userService.getUser(principal.getName());
        this.rpService.suggestion(itemDto, user);
        return String.format("redirect:/replace/detail/%s", itemID);
    }	
	
}
