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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.GCSService.GCSService;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;
import com.example.test.User.Service.AdminService;
import com.example.test.item.ItemType;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.paging.Criteria;
import com.example.test.paging.Page;
import com.example.test.paging.PageDTO;
import com.example.test.replace.ReplaceForm;


import com.example.test.replace.Service.ReplaceService;


import jakarta.validation.Valid;


@Controller
public class ReplaceController { //파일첨부쪽 로직, 게시물등록자(admin?) 모르겠음

	@Autowired
	ReplaceService rpService;
	
	@Autowired
	GCSService gcsService;
	
	
	@Autowired
	UserService userService;
	
	
	
	//게시글 목록 화면
	@RequestMapping(value = "/replace/list", method = RequestMethod.GET)
	public String list(Model model, Page page) throws Exception{

			Integer totalCount = null;
			Integer rowPerPage = null;
			Integer pageCount = null;
			Integer pageNum = page.getPageNum();
			String keyword = page.getKeyword();

			// 조회된 전체 게시글 수
			if( page.getTotalCount() == 0 )
				totalCount =rpService.totalCount();
			
			 else 
			    totalCount = page.getTotalCount();

			//페이지 당 노출 게시글 수
			if( page.getRowsPerPage() == 0 )
				rowPerPage = 10;
			
			else 
				rowPerPage = page.getRowsPerPage();

			//노출 페이지수
			if(page.getPageCount() == 0)
				pageCount = 10;
			else
				pageCount = page.getPageCount();

			if(page.getPageNum() == 0){
				page = new Page(1, rowPerPage, totalCount, pageCount); 
			} else{
				page = new Page(pageNum, rowPerPage, totalCount, pageCount);
			}
			
			if(keyword == null || keyword == ""){
				page.setKeyword("");
				model.addAttribute("list", rpService.list(page));
			} else {
				page.setKeyword(keyword);
				model.addAttribute("list", rpService.search(page));
			}

			model.addAttribute("page", page);
			return "itemList";

	}
	

	
	
	//게시글 읽기 화면
	@RequestMapping(value = "/replace/detail/{itemId}", method = RequestMethod.GET)
	public String read(Model model,@PathVariable("itemId") Integer itemId, Principal principal) throws Exception{

	ItemDTO item = rpService.getPost(itemId, principal);
			
	String userName = "";
	if( principal !=null ){
	userName = principal.getName();
	UserDTO user = userService.getUser(userName);
				
	model.addAttribute("userName", userName);
	}

	model.addAttribute("item", item);
			

	return "adventure_detail";
	}

	@PreAuthorize("isAuthenticated() and hasRole('ROLE_BIS')")
	@RequestMapping(value = "/replace/create", method = RequestMethod.GET)
	public String Create(Model model, ItemDTO item, Principal user) throws Exception{
	    	
	String userName = "";
	if(user !=null){
	   userName = user.getName();
	   model.addAttribute("userName", userName);
	}
	    	
	   return "replace_form";
	}

    
    //@PostMapping("/create")
	@RequestMapping(value ="/replace/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> create(ItemDTO itemdto,Principal principal) throws Exception{
    	itemdto.setType(ItemType.replace);
    	//itemDTO에 userName이 UserDTO 타입이여서 이렇게 작성함
    	String userId = principal.getName();
    	System.out.println(userId);
    	UserDTO userDTO = userService.getUser(userId);
    	System.out.println(userDTO.toString());
    	itemdto.setUsername(userDTO);
    	this.rpService.create(itemdto);
    	int resultImg = rpService.createImg(itemdto);
    	System.out.println(resultImg);
//    	System.out.println(itemdto.toString());
//    	for(MultipartFile file : itemdto.getFile()) {
//    		System.out.println(file.getOriginalFilename());
//    		String url =gcsService.uploadObject(file);
//    		
//    	}
    	return ResponseEntity.ok().body("Success message");
    }
    
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_BIS')")
    @RequestMapping(value = "/replace/modify", method = RequestMethod.GET)
    public String Modify(Model model, Integer itemId, Principal user) throws Exception{
        ItemDTO item = this.rpService.getPost(itemId, user);
        item.setType(ItemType.adventure);
        if(!item.getUsername()
        		.equals(user.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        
        model.addAttribute("item", item);
        
        return "replace_form";
    }
    
    //수정
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_BIS')")
    @PostMapping("/replace/modify/")
    public String Modify(Model model, BindingResult bindingResult, Principal principal, 
    		@PathVariable("id") Integer item) throws Exception {
        if (bindingResult.hasErrors()) {
            return "replace_form";
        }
        ItemDTO itemDto = this.rpService.getPost(item, principal);
        if (!itemDto.getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.rpService.modify(itemDto);
        return String.format("redirect:/replace/detail/%s", item);
    }
    
    //삭제
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_BIS')")
    @RequestMapping(value = "/replace/remove", method = RequestMethod.POST)
    public String Delete(Principal principal, @PathVariable("id") Integer itemID) throws Exception {
        ItemDTO itemDto = this.rpService.getPost(itemID, principal);
        if (!itemDto.getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.rpService.remove(itemID);
        return "redirect:/";
    }
    
    //추천
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/replace/suggestion/{id}")
    public String Suggestion(Principal principal, @PathVariable("id") Integer itemID) throws Exception {
        ItemDTO itemDto = this.rpService.getPost(itemID, principal);
        UserDTO user = this.userService.getUser(principal.getName());
        this.rpService.suggestion(itemDto, user);
        return String.format("redirect:/replace/detail/%s", itemID);
    }	
	
}
