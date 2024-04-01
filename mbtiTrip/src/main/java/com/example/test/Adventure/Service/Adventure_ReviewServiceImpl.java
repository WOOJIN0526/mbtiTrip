package com.example.test.Adventure.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.Adventure.DTO.Adventure_CategoryDTO;
import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.AdventureDAO.Adventure_ReviewDAO;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.Service.DataNotFoundException;
import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
public class Adventure_ReviewServiceImpl implements Adventure_ReviewService {

	@Autowired
	Adventure_ReviewDAO adrDAO;

	@Override
	public void register(Adventure_ReviewDTO adr) {
		// TODO Auto-generated method stub
		adrDAO.insertSelectKey(adr);
	}

	@Override
	public Adventure_ReviewDTO get(Long pno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modify(Adventure_ReviewDTO adr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Long pno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Adventure_ReviewDTO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
