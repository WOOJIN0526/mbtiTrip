package com.example.test.POST.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;


import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.Service.PostService;

@Controller
public class PostController {

	@Autowired
	PostService postService;

	@PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO) {
        postService.createPost(postDTO);
        return new ResponseEntity<>("게시물 생성 성공", HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
        PostDTO postDTO = postService.getPostById(postId);
        if (postDTO != null) {
            return new ResponseEntity<>(postDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> postDTOList = postService.getAllPosts();
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }
}
