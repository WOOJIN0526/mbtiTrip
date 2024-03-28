package com.example.test.replace.Controller;

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


import com.example.test.User.Service.UserService;
import com.example.test.replace.ReplaceForm;
import com.example.test.replace.DTO.ReplaceCategoryDTO;
import com.example.test.replace.DTO.ReplaceDTO;
import com.example.test.replace.Service.ReplaceCategoryService;
import com.example.test.replace.Service.ReplaceService;

import jakarta.validation.Valid;

@Controller
public class ReplaceController {

	@Autowired
	ReplaceService rps;
	
	@Autowired
	ReplaceCategoryService rpc;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/replace/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "kw", defaultValue = "") String kw) {
        
        Page<ReplaceDTO> paging = this.rps.getList(page, kw, "판매(ex 지역 or mbti)");
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "list";
    }

    @RequestMapping(value = "/rdetail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, ReplaceForm replaceForm) {
        ReplaceDTO post = this.rps.getPost(id);
        model.addAttribute("post", post);
        return "detail";
    }

    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/rcreate")
    public String replaceCreate(ReplaceForm replaceForm, Model model) {
    	model.addAttribute("categoryList", rpc.getList());
        return "form";
    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping("/rcreate")
    public String replaceCreate(@Valid ReplaceForm replaceForm, 
            BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        //UserDTO admin = this.userService.getUser(principal.getName()); 관리자여야함
        ReplaceCategoryDTO category = this.rpc.getCategory(replaceForm.getCategoryName());
        //this.adService.create(adventureForm.getTitle(), adventureForm.getContent(), UserDTO);
        return "redirect://list";
    }
    
    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/rmodify/{id}")
    public String adModify(ReplaceForm replaceForm, @PathVariable("id") Integer id, Principal principal) {
        ReplaceDTO rpDto = this.rps.getPost(id);
        if(!rpDto.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        replaceForm.setTitle(rpDto.getReplaceName());
        replaceForm.setContent(rpDto.getReplaceContents());
        return "form";
    }
    
    //@PreAuthorize("isAuthenticated()")
    @PostMapping("/rmodify/{id}")
    public String adModify(@Valid ReplaceForm rpForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        ReplaceDTO rpDto = this.rps.getPost(id);
        if (!rpDto.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.rps.modify(rpDto, rpForm.getTitle(), rpForm.getContent());
        return String.format("redirect://detail/%s", id);
    }
    
    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/rdelete/{id}")
    public String adDelete(Principal principal, @PathVariable("id") Integer id) {
        ReplaceDTO adDto = this.rps.getPost(id);
        if (!adDto.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.rps.delete(adDto);
        return "redirect:/";
    }
    
    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/rvote/{id}")
    public String adVote(Principal principal, @PathVariable("id") Integer id) {
        //ReplaceDTO replaceDto = this.rps.getReplace(id);
        //UserDTO siteUserDto = this.userService.findByUserName(principal.getName());
        //this.rps.vote(replaceDto, siteUserDto);
        return String.format("redirect://detail/%s", id);
    }
    
  @GetMapping("/replace_review/list")
  public String reviewList(Model model, @RequestParam(value="page", defaultValue="0") int page,
  	@RequestParam(value = "kw", defaultValue = "") String kw) {
      
      Page<ReplaceDTO> paging = this.rps.getList(page, kw, "숙소리뷰");
      model.addAttribute("paging", paging);
      model.addAttribute("kw", kw);
      return "list";
  }
	
}
