package com.example.test.POST.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.Service.PostService;

@Controller
public class PostController {

	@Autowired
	PostService postService;

    @PostMapping("/a/a/a")
    public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO) {
        postService.createPost(postDTO);
        return new ResponseEntity<>("게시물 생성 성공", HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Integer postId, @RequestBody PostDTO postDTO) {
        postDTO.setPostID(postId);
        postService.updatePost(postDTO);
        return new ResponseEntity<>("게시물 업데이트 성공", HttpStatus.OK);
    }// URL 경로로부터 postId를 받아와서 postDTO에 설정합니다.
    // 업데이트 성공 메시지와 HTTP 상태 코드를 반환합니다.

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>("게시물 삭제 성공", HttpStatus.OK);
    }//postId에 해당하는 게시물을 삭제합니다.

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
        PostDTO postDTO = postService.getPostById(postId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }//postId에 해당하는 게시물을 조회합니다.

    @GetMapping("/a/a/a")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> postDTOList = postService.getAllPosts();
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }//모든 게시물을 조회합니다.

    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> searchPostsByTitle(@RequestParam String title) {
        List<PostDTO> postDTOList = postService.searchPostsByTitle(title);
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }//제목에 해당하는 게시물을 검색합니다.
}
