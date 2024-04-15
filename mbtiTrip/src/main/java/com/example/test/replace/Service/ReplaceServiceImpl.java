package com.example.test.replace.Service;


import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.GCSDTO.GCSDTO;
import com.example.test.GCSService.GCSService;
import com.example.test.POST.Service.DataNotFoundException;
import com.example.test.User.DAO.UserHistoryDAO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserHistoryService;
import com.example.test.item.DAO.ItemDAO;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.paging.Page;





@Service
public class ReplaceServiceImpl implements ReplaceService{

	@Autowired
	ItemDAO itemDAO;
	@Autowired
	UserHistoryDAO userhistoryDAO;
	@Autowired
	GCSService gcsService;
	@Autowired
	UserHistoryService userHistoryService;
	
	@Override
	public List<ItemDTO> list(Page page) throws Exception {
	    // 아이템 목록을 가져옵니다.
	    List<ItemDTO> items = itemDAO.replaceList(page);
	    
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
		// TODO Auto-generated method stub
		itemDAO.create(post);
	}

	@Override
	public ItemDTO getPost(Integer itemId, Principal principal) throws Exception {
		Optional<ItemDTO> adventure = this.itemDAO.findById(itemId);
		  if (adventure.isPresent()) {
	        	ItemDTO itemDto = adventure.get();        	
	        	itemDto.setView(itemDto.getView()+1);        	
	        	this.itemDAO.create(itemDto);
	        	userHistoryService.userViewItem(itemDto, principal);
	        	
	            	return itemDto;
	        } else {
	            throw new DataNotFoundException("question not found");
	        }	
	}

	@Override
	public void modify(ItemDTO post) throws Exception {
		// TODO Auto-generated method stub
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
        
        this.itemDAO.create(item);
	}


	@Override
	public int createImg(ItemDTO itemdto) {
	int lastIdx =userhistoryDAO.lastIdxItem();
	for(MultipartFile file : itemdto.getFile()) {
		String url =gcsService.uploadObject(file);
		GCSDTO img =  new GCSDTO();
		img.setItemID(lastIdx);
		img.setCurrentURL(url);
		int result =itemDAO.createImg(img);
		}
		return 0;
	}




		
	
	
	
	
}
