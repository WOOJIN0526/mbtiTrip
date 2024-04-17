package com.example.test.User.Service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.item.DAO.ItemDAO;

@Service
public class UXServiceImpl implements UXService{
	@Autowired
	ItemDAO itemDAO;
	@Override
	public List<HashMap<String, Object>> insertUrls(List<HashMap<String, Object>> myItem) {
		for(HashMap<String, Object> item :myItem) {
			int itemID = (Integer) item.get("itemID");
			 List<String> url = itemDAO.getUrl(itemID);
			 //등록된 이미지가 없을 경우
			 if(url.isEmpty()) {
				 url.add("0");
			 }
			 String[] ImgeUrl = url.toArray(new String[0]); // 리스트를 배열로 변환
		     item.put("ImgeUrl", ImgeUrl); // 아이템에 이미지 URL 배열 추가
		}
		return myItem;
	}

}
