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


import com.example.test.Adventure.AdventureForm;
import com.example.test.Adventure.Service.AdventureService;

import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;

import com.example.test.item.ItemType;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.paging.Criteria;
import com.example.test.paging.PageDTO;


import jakarta.validation.Valid;


@RequestMapping("/adventure")
@Controller
public class AdventureController {

	@Autowired
	AdventureService adService;
	
	@Autowired
	UserService userService;
	
	//@Autowired
	//Adventure_CategoryService adCategoryService;
	
	@GetMapping("/list") 
	public String list(Criteria cri, Model model) {
		
		model.addAttribute("list", adService.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(adService.getTotal(cri), 10, cri));
		return "adventure";
	}
	

	
    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer itemID,
    					Principal principal) {
        ItemDTO item = this.adService.getPost(itemID, principal);
        model.addAttribute("replace", item);
        return "adventure_detail";
    }

    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String Create(AdventureForm Form, Model model) {
    	
        return "adventure_form";
    }

   
    
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/create")
//    public String Create(@Valid AdventureForm replaceForm, 
//            BindingResult bindingResult, Principal principal, ItemDTO itemdto) {
//    	itemdto.setType(ItemType.replace);
//        if (bindingResult.hasErrors()) {
//            return "replace_form";
//        }
//        UserDTO bis = this.userService.getUser(principal.getName());
//    
//        this.adService.create(replaceForm.getType(), replaceForm.getMbtiID(), bis,replaceForm.getAdventurePrice(), replaceForm.getAdventureName(), replaceForm.getAdventureContent(), 
//        					  replaceForm.getTel(), replaceForm.getAdventureLocation(),  replaceForm.getFile() );
//        return "redirect:/question/list";
//    }
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<String> create(ItemDTO itemdto){
    	itemdto.setType(ItemType.adventure);
    	
    	System.out.println(itemdto.toString());
    	for(MultipartFile file : itemdto.getFile()) {
    		System.out.println(file.getOriginalFilename());
    	}
    	return ResponseEntity.ok().body("Success message");
    }
    
   
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String Modify(AdventureForm postForm, @PathVariable("id") Integer itemID, Principal principal) {
        ItemDTO itemDto = this.adService.getPost(itemID);
        itemDto.setType(ItemType.adventure);
        if(!itemDto.getUsername()
        		.equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        postForm.setAdventureName(itemDto.getItemName());
        postForm.setAdventureContent(itemDto.getContents());
        postForm.setMbtiID(itemDto.getMbti());
        postForm.setAdventureLocation(itemDto.getLocation());
        postForm.setTel(itemDto.getTel());
        postForm.setAdventurePrice(itemDto.getPrice());
        postForm.setType(itemDto.getType());
        postForm.setFile(itemDto.getImgeUrl());
        
        return "adventure_form";
    }
    
    //수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String Modify(@Valid AdventureForm postForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer itemID) {
        if (bindingResult.hasErrors()) {
            return "adventure_form";
        }
        ItemDTO itemDto = this.adService.getPost(itemID);
        itemDto.setType(ItemType.adventure);
        if (!itemDto.getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.adService.modify(itemDto, postForm.getType(),postForm.getMbtiID(), postForm.getAdventurePrice(), postForm.getAdventureName(),
        					postForm.getAdventureLocation(), postForm.getTel(), postForm.getAdventureContent(), postForm.getFile());
        return String.format("redirect:/adventure/detail/%s", itemID);
    }
    
    //삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String Delete(Principal principal, @PathVariable("id") Integer itemID) {
        ItemDTO itemDto = this.adService.getPost(itemID);
        itemDto.setType(ItemType.adventure);
        if (!itemDto.getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.adService.delete(itemDto);
        return "redirect:/";
    }
    
    //추천
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/suggestion/{id}")
//    public String Suggestion(Principal principal, @PathVariable("id") Integer itemID) {
//        ItemDTO itemDto = this.adService.getPost(itemID);
//        UserDTO user = this.userService.getUser(principal.getName());
//        this.adService.suggestion(itemDto, user);
//        return String.format("redirect:/adventure/detail/%s", itemID);
//    }	
    

}
