package com.example.test.POST.Service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.DAO.PostDAO;

import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.DTO.Post_CategoryDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;





@Service
public  class PostServiceImpl implements PostService {

	@Autowired
	PostDAO postDAO;
	
	//검색기능 기존 or 조건(검색어)에서 and 조건(원하는 카테고리만 조회)을 추가
	//즉, 검색 조건이 일치하고 카테고리도 일치하는 게시물들만 조회됨
//	private Specification<PostDTO> search(String kw, String categoryName) {
//        return new Specification<PostDTO>() {
//            private static final long serialVersionUID = 1L;
//            
//            @Override
//            public Predicate toPredicate(Root<PostDTO> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                query.distinct(true);  // 중복을 제거 
//                Join<PostDTO, UserDTO> u1 = q.join("author", JoinType.LEFT);
//                Join<PostDTO, AnswerDTO> a = q.join("answerList", JoinType.LEFT);
//                Join<PostDTO, Post_CategoryDTO> c = q.join("category", JoinType.LEFT);
//                Join<AnswerDTO, UserDTO> u2 = a.join("author", JoinType.LEFT);
//                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제목 
//                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용 
//                        cb.like(u1.get("username"), "%" + kw + "%"),    // 질문 작성자 
//                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용 
//                        cb.like(u2.get("username"), "%" + kw + "%"),   // 답변 작성자 
//                		cb.like(c.get("name"), "%" + categoryName + "%"));	// 카테고리 이름
//            }
//        };
//    }



	//해당게시글 가져옴, 조회수 증가
//	@Override
//	public PostDTO getPost(Integer userid) {
//		  Optional<PostDTO> postdto = this.postDAO.findById(userid);
//		  if (postdto.isPresent()) {
//	        	PostDTO post = postdto.get();        	
//	        	post.setViews(post.getViews()+1);        	
//	        	this.postDAO.save(post);
//	            	return post;
//	        } else {
//	            throw new DataNotFoundException("question not found");
//	        }	
//		  }

	

	@Override
	public void register(PostDTO post) {
		// TODO Auto-generated method stub
		postDAO.insertSelectKey(post);
	}



	@Override
	public PostDTO get(Long pno) {
		// TODO Auto-generated method stub
		return postDAO.read(pno);
	}



	@Override
	public boolean modify(PostDTO post) {
		// TODO Auto-generated method stub
		return postDAO.update(post) == 1;
	}



	@Override
	public boolean remove(Long pno) {
		// TODO Auto-generated method stub
		return postDAO.delete(pno) == 1;
	}



	@Override
	public List<PostDTO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return postDAO.getListWithPaging(cri);
	}



	@Override
	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return postDAO.getTotalCount(cri);
	}

	


	//추천수 기능
//	@Override
//	public PostDTO vote(PostDTO postDto, UserDTO userDto) {
//			postDto.getVoter().add(userDto);
//	        
//	        return this.postDAO.save2(postDto);
//	}

	// 게시물을 조회하고 조회수 증가
//	@Transactional
//	public PostDTO detail(Integer id, HttpServletRequest request, HttpServletResponse response) {
//		 Cookie oldCookie = null;
//		    Cookie[] cookies = request.getCookies();
//		    if (cookies != null)
//		        for (Cookie cookie : cookies)
//		            if (cookie.getName().equals("postView"))
//		                oldCookie = cookie;
//
//		    if (oldCookie != null) {
//		        if (!oldCookie.getValue().contains("[" + id.toString() + "]")) {
//		            postDAO.updateCount(id);
//		            oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
//		            oldCookie.setPath("/");
//		            oldCookie.setMaxAge(60 * 60 * 24);
//		            response.addCookie(oldCookie);
//		        }
//		    }
//		    else {
//		        postDAO.updateCount(id);
//		        Cookie newCookie = new Cookie("postView","[" + id + "]");
//		        newCookie.setPath("/");
//		        newCookie.setMaxAge(60 * 60 * 24);
//		        response.addCookie(newCookie);
//		    }
//
//		    return postDAO.findById(id).orElseThrow(() -> {
//		        return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
//		    });
//		}


	
	
    

}
