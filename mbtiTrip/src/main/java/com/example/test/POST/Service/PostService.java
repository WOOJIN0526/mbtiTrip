package com.example.test.POST.Service;

import java.util.List;




import com.example.test.POST.DTO.PostDTO;
import com.example.test.paging.Criteria;


public interface PostService {

	 
	public void register(PostDTO post);
	
	public PostDTO get(Long pno);
	
	public boolean modify(PostDTO post);
	
	public boolean remove(Long pno);
	
	public List<PostDTO> getList(Criteria cri);
	
	//postDAO의 gettotalcnt 호출용
	public int getTotal(Criteria cri);
}
