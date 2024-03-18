package com.example.test.POST.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.Service.PostService;

@Controller
public class PostController {

	@Autowired
    private PostService postservice; //서비스와 연결

    @RequestMapping("") //노테이션의 값으로 주소 지정
    public ModelAndView openBoardList() throws Exception{
    	//templates 폴더 아래있는 /.html을 의미함. Thymeleaf와 같은 템플릿엔진을 사용할 경우 스프링 부트의 자동 설정 기능으로 '.html'과 같은 접미사 생략 가능
    	ModelAndView mv = new ModelAndView(""); 
        //게시글 목록을 조회하기 위해 ServiceImpl 클래스의 selectBoardList 메서드 호출
        List<PostDTO> list = postservice.selectBoardList();  
        mv.addObject("list", list);

        return mv;
    }
}
