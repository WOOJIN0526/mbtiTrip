package com.example.test.Adventure.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.Adventure.Service.Adventure_ReviewService;
import com.example.test.paging.Criteria;
import com.example.test.paging.PageDTO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("/adventure/review/*")
@Controller
public class AdventureReviewController {

	@Autowired
	Adventure_ReviewService adrService;
	
			//전체목록조회
			@GetMapping("/main")
			public String list(Criteria cri, Model model) {
			model.addAttribute("list", adrService.getList(cri));
			//model.addAttribute("pageMaker", new PageDTO(cri, 123)); //123은 임의의 값임 아직 전체 데이터수정해지지 않음
					
			int total = adrService.getTotal(cri);
						
			model.addAttribute("pageMaker", new PageDTO(cri, total));
					
			return "Review_main";
						
			}
			
			//등록입력 페이지를 볼수 있도록
			@GetMapping("/write/form")
			@PreAuthorize("isAuthenticated()")
			public String register() {
				return "write_form";
			}
			
			//등록처리
			@PreAuthorize("isAuthenticated()")
			@PostMapping("/write/form") //등록작업이 끝난후 다시 목록화면으로 이동하기 위함(추가적으로 새롭게 등록된 게시물의 번호를 같이전달)
			public String register(Adventure_ReviewDTO adr, RedirectAttributes rttr) {
//				if(post.getAttachList() != null) {
//					post.getAttachList().forEach(attach -> log.info(attach));
//				}
				
				adrService.register(adr);
				rttr.addFlashAttribute("result", adr.getPno());
				return "redirect:/adventure/review/main";
			}
			
			//특정 번호의 게시물 조회, 수정/삭제 페이지로 이동
			@GetMapping({"/detail", "/modify"})
			public void get(@RequestParam("pno") Long pno,@ModelAttribute("cri") Criteria cri, Model model) {
				
				model.addAttribute("adventureReview", adrService.get(pno));
			}
			
			
			
			//수정처리
			@PreAuthorize("principal.username == #adventure.writer")
			@PostMapping("/modify")
			public String modify(Adventure_ReviewDTO adr, Criteria cri, RedirectAttributes rttr) {
				
				if(adrService.modify(adr)) {
					rttr.addFlashAttribute("result", "success");
				}
				
				return "redirect:/adventure/review/main" + cri.getListLink();
			}
			
			//삭제처리 후 다시 목록페이지로 이동
			@PreAuthorize("principal.username == #writer")
			@PostMapping("/remove")
			public String remove(@RequestParam("pno") Long pno, Criteria cri, RedirectAttributes rttr) {
				if(adrService.remove(pno)) {
					rttr.addFlashAttribute("result", "success");
				}
				
				return "redirect:/adventure/review/main" + cri.getListLink();
			}
			
			// 여기까지 게시물의 등록, 수정, 삭제, 조회 페이징 처리 완료
			
			// 조회수증가(쿠키기반)
			private void viewCountValidation(Adventure_ReviewDTO adr, HttpServletRequest request, HttpServletResponse response) {
		        Cookie[] cookies = request.getCookies();
		        Cookie cookie = null;
		        boolean isCookie = false;
		        // request에 쿠키들이 있을 때
		        for (int i = 0; cookies != null && i < cookies.length; i++) {
		        	// postView 쿠키가 있을 때
		            if (cookies[i].getName().equals("adventure_reviewView")) {
		            	// cookie 변수에 저장
		                cookie = cookies[i];
		                // 만약 cookie 값에 현재 게시글 번호가 없을 때
		                if (!cookie.getValue().contains("[" + adr.getUserId() + "]")) {
		                	// 해당 게시글 조회수를 증가시키고, 쿠키 값에 해당 게시글 번호를 추가
		                    adr.addViewCount();
		                    cookie.setValue(cookie.getValue() + "[" + adr.getUserId() + "]");
		                }
		                isCookie = true;
		                break;
		            }
		        }
		        // 만약 postView라는 쿠키가 없으면 처음 접속한 것이므로 새로 생성
		        if (!isCookie) { 
		            adr.addViewCount();
		            cookie = new Cookie("adventure_reviewView", "[" + adr.getUserId() + "]"); // oldCookie에 새 쿠키 생성
		        }
		        
		        // 쿠키 유지시간을 오늘 하루 자정까지로 설정
		        long todayEndSecond = LocalDate.now().atTime(LocalTime.MAX).toEpochSecond(ZoneOffset.UTC);
		        long currentSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
		        cookie.setPath("/"); // 모든 경로에서 접근 가능
		        cookie.setMaxAge((int) (todayEndSecond - currentSecond));
		        response.addCookie(cookie);
		    }


}
