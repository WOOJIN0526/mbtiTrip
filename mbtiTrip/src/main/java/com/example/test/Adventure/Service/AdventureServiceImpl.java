package com.example.test.Adventure.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.Service.DataNotFoundException;
import com.example.test.User.DTO.AdminDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserHistoryService;
import com.example.test.item.ItemType;
import com.example.test.item.DAO.ItemDAO;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.paging.Criteria;
import com.example.test.paging.Page;
import com.example.testExcepion.Item.ItemException;
import com.example.testExcepion.Item.ItemExceptionEnum;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import java.util.regex.Pattern;

@Service
public class AdventureServiceImpl implements AdventureService{

	@Autowired
	ItemDAO itemDAO;

	@Autowired
	UserHistoryService userHistoryService;
	
	
	@Override
	public List<ItemDTO> list(Page page) throws Exception {
	    // 아이템 목록을 가져옵니다.
	    List<ItemDTO> items = itemDAO.adventureList(page);
	    
	    // 각 아이템에 대해 이미지 URL을 가져와서 설정합니다.
	    for (ItemDTO item : items) {
	        int itemID = item.getItemID();
	        List<String> url = itemDAO.getUrl(itemID); // 아이템의 이미지 URL을 가져옵니다.
	        
	        // 등록된 이미지가 없을 경우 "0"을 추가합니다.
	        if (url.isEmpty()) {
	            url.add("0");
	        }
	        
	        // 리스트를 배열로 변환하여 아이템에 이미지 URL 배열을 설정합니다.
	        String[] imageUrlArray = url.toArray(new String[0]);
	        System.out.println(imageUrlArray.toString()+"HERE!!!");
	        item.setImgeUrl(imageUrlArray);
	    }
	    
	    return items;
	}

	@Override
	public void create(ItemDTO post) throws Exception {
		ItemException.validationItem(post);
		try {
			itemDAO.create(post);
		}
		catch(Exception e) {
			throw new ItemException(ItemExceptionEnum.ITEM_UNKNOWN_ERROR);
		}
		
	}

	@Override
	public ItemDTO getPost(Integer itemId) throws Exception {
		if(itemId == null) {
			throw new ItemException(ItemExceptionEnum.ITEM_NOT_FOUND);
		}
		ItemDTO adventure = this.itemDAO.findById(itemId);
  	
		adventure.setView(adventure.getView()+1); 
		int itemID = adventure.getItemID();
        List<String> url = itemDAO.getUrl(itemID); // 아이템의 이미지 URL을 가져옵니다.
        
        // 등록된 이미지가 없을 경우 "0"을 추가합니다.
        if (url.isEmpty()) {
            url.add("0");
        }
        
        // 리스트를 배열로 변환하여 아이템에 이미지 URL 배열을 설정합니다.
        String[] imageUrlArray = url.toArray(new String[0]);
        System.out.println(imageUrlArray.toString()+"HERE!!!");
        adventure.setImgeUrl(imageUrlArray);
    	this.itemDAO.create(adventure);
        	return adventure;

	
	}
	
	
	@Override
	public ItemDTO getPost(Integer itemId, Principal principal) throws Exception {
		if(itemId == null) {
			throw new ItemException(ItemExceptionEnum.ITEM_NOT_FOUND);
		}
		ItemDTO adventure = this.itemDAO.findById(itemId);
		    	
		adventure.setView(adventure.getView()+1);        	
    	this.itemDAO.update(adventure);
    	
		int itemID = adventure.getItemID();
        List<String> url = itemDAO.getUrl(itemID); // 아이템의 이미지 URL을 가져옵니다.
        
        // 등록된 이미지가 없을 경우 "0"을 추가합니다.
        if (url.isEmpty()) {
            url.add("0");
        }
        
        // 리스트를 배열로 변환하여 아이템에 이미지 URL 배열을 설정합니다.
        String[] imageUrlArray = url.toArray(new String[0]);
        System.out.println(imageUrlArray.toString()+"HERE!!!");
        adventure.setImgeUrl(imageUrlArray);
    	userHistoryService.userViewItem(adventure, principal);
        return adventure;

	}

	@Override
	public void modify(ItemDTO post) throws Exception {
		ItemException.validationItem(post);
		itemDAO.update(post);
	}

	@Override
	public void remove(Integer itemId) throws Exception {
		// TODO Auto-generated method stub
		itemDAO.deleteItem(itemId);
	}

	@Override
	public List<ItemDTO> search(String keyword) {
		// TODO Auto-generated method stub
		return itemDAO.search(keyword);
	}
	
	

	@Override
	public List<ItemDTO> search(Page page) throws Exception {
		// TODO Auto-generated method stub
		return itemDAO.search(page);
	}

	@Override
	public Integer totalCount() throws Exception {
		// TODO Auto-generated method stub
		return itemDAO.getTotal();
	}

	@Override
	public void suggestion(ItemDTO item, UserDTO user) {
		item.getUprating().add(user);
        
        this.itemDAO.update(item);
	}






//			// 게시물을 조회하고 조회수 증가
//			@Transactional
//			public ItemDTO detail(Integer itemID, HttpServletRequest request, HttpServletResponse response) {
//				 Cookie oldCookie = null;
//				    Cookie[] cookies = request.getCookies();
//				    if (cookies != null)
//				        for (Cookie cookie : cookies)
//				            if (cookie.getName().equals("ReplaceView"))
//				                oldCookie = cookie;
//
//				    if (oldCookie != null) {
//				        if (!oldCookie.getValue().contains("[" + itemID.toString() + "]")) {
//				            itemDAO.updateCount(itemID);
//				            oldCookie.setValue(oldCookie.getValue() + "_[" + itemID + "]");
//				            oldCookie.setPath("/");
//				            oldCookie.setMaxAge(60 * 60 * 24);
//				            response.addCookie(oldCookie);
//				        }
//				    }
//				    else {
//				        itemDAO.updateCount(itemID);
//				        Cookie newCookie = new Cookie("ReplaceView","[" + itemID + "]");
//				        newCookie.setPath("/");
//				        newCookie.setMaxAge(60 * 60 * 24);
//				        response.addCookie(newCookie);
//				    }
//
//				    return itemDAO.findById(itemID).orElseThrow(() -> {
//				        return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
//				    });
//				}

			

		
		

			
	
	
}
