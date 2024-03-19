package com.example.test.POST.Service;


import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.test.POST.Post;
import com.example.test.POST.DAO.PostDAO;
import com.example.test.POST.DTO.PostDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public  class PostServiceImpl implements PostService {

	@Autowired
	PostDAO postDAO;

	
}
