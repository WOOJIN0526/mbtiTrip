package com.example.test.Adventure.Controller;

import java.security.Principal;
import java.util.List;

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



import com.example.test.Adventure.Service.AdventureService;

import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;

import com.example.test.item.ItemType;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.paging.Page;
import com.example.test.replace.Service.ReplaceService;


@Controller
public class AdventureController {
	
	@Autowired
	ReplaceService rpService;
	
	@Autowired
	AdventureService adService;
	
	@Autowired
	UserService userService;
	
	
	
	//게시글 목록 화면
	@RequestMapping(value = "/adventure/list", method = RequestMethod.GET)
	public String list(Model model, Page page) throws Exception{

		Integer totalCount = null;
		Integer rowPerPage = null;
		Integer pageCount = null;
		Integer pageNum = page.getPageNum();
		String keyword = page.getKeyword();

		// 조회된 전체 게시글 수
		if( page.getTotalCount() == 0 )
			totalCount =adService.totalCount();
		
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
			List<ItemDTO> list = adService.list(page);
			model.addAttribute("list", list);
		} else {
			page.setKeyword(keyword);
			model.addAttribute("list", adService.search(page));
		}

		model.addAttribute("page", page);
		return "itemList";

	}
	

	
	//게시글 읽기 화면
	@RequestMapping(value = "/adventure/detail/{itemId}", method = RequestMethod.GET)
	public String read(Model model,@PathVariable("itemId") Integer itemId, Principal principal) throws Exception{

		ItemDTO item = adService.getPost(itemId, principal);
		
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
    @RequestMapping(value = "/adventure/create", method = RequestMethod.GET)
    public String Create(Model model, ItemDTO item, Principal user) throws Exception{
    	
    	String userName = "";
    	if(user !=null){
    		userName = user.getName();
    		model.addAttribute("userName", userName);
    	}
    	
        return "adventure_form";
    }

   
    //@PostMapping("/create")
    @RequestMapping(value ="/adventure/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> create(ItemDTO itemdto,Principal princiapl) throws Exception{
    	itemdto.setType(ItemType.adventure);
    	
    	String userId = princiapl.getName();
    	
    	UserDTO userDTO = userService.getUser(userId);
    	
    	itemdto.setUsername(userDTO);
    	this.rpService.create(itemdto);
    	int resultImg = rpService.createImg(itemdto);
    	
    	
    	return ResponseEntity.ok().body("Success message");
    }
    
   
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_BIS')")
    @RequestMapping(value = "/adventure/modify", method = RequestMethod.GET)
    public String Modify(Model model, Integer itemId, Principal user, Principal princiapl) throws Exception{
        ItemDTO item = this.adService.getPost(itemId);
        item.setType(ItemType.adventure);
        if(!item.getUsername()
        		.equals(user.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        
        model.addAttribute("item", item);
        
        return "adventure_form";
    }
    
    //수정
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_BIS')")
    @PostMapping("/adventure/modify/")
    public String Modify(Model model, BindingResult bindingResult, Principal principal, 
    		@PathVariable("id") ItemDTO item) throws Exception {
        if (bindingResult.hasErrors()) {
            return "adventure_form";
        }
       
        if (!item.getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        try {
			this.adService.modify(item);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return String.format("redirect:/adventure/detail/%s", item);
    }
    
    //삭제
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_BIS')")
    @RequestMapping(value = "/adventure/remove", method = RequestMethod.POST)
    public String Delete(Principal principal, @PathVariable("id") Integer itemID) throws Exception {
        ItemDTO itemDto = this.adService.getPost(itemID);
        
        
        //여기 사용자 이름이랑 맞는지 CK 필요, userDAO에서 getuserNamebyuserID였나 사용해주세요 
        if (!itemDto.getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        adService.remove(itemID);
        return "redirect:/";
    }
    
    //추천
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/adventure/suggestion/{id}")
    @ResponseBody
    public ResponseEntity<String> suggestion(Principal principal, @PathVariable("id") Integer itemID) {
        try {
            int result = adService.suggestion(itemID, principal);
            if (result != 0) {
                return ResponseEntity.ok().body("성공하였습니다.");
            } else {
                return ResponseEntity.badRequest().body("실패하였습니다.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에서 오류가 발생했습니다.");
        }
    }	
    

}
