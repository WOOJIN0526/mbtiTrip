 package com.example.test.User.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.DAO.UserDAO;
import com.example.test.User.DAO.UserHistoryDAO;
import com.example.test.User.DTO.QnADTO;
import com.example.test.User.DTO.UserHistoryDTO;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.replace.DTO.ReplaceDTO;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
public class UserHistroyServiceImpl implements UserHistoryService{

	@Autowired
	UserHistoryDAO userhistoryDAO;
	
	@Autowired
	UserDAO userDAO;
	
	// 사용자의 userView에 조회한 ITem 정보 삽입
	public void userViewItem(ItemDTO itemDTO, Principal principal) {
		UserHistoryDTO userItemView = new UserHistoryDTO();
		userItemView.setItemId(itemDTO.getItemID());
		userItemView.setUserName(principal.getName());
		userhistoryDAO.viewCkItem(userItemView);
		//viewItem에 viewRating 값 증가 
		userhistoryDAO.viewRatingIT(itemDTO);
	}
	
	// 사용자의 userView에 조회한 Post 정보 삽입
	public void userViewPost(PostDTO PostDTO, Principal principal) {
		UserHistoryDTO userPostView = new UserHistoryDTO();
		userPostView.setPostid(PostDTO.getPostID());
		userPostView.setUserName(principal.getName());
		userhistoryDAO.viewCkPO(userPostView);
		//viewPost에 viewRating 값 증가 
		userhistoryDAO.viewRatingPO(PostDTO);
	}
	
	
	/*사용자가 가장 많이 조회한 mbti 유형을 분석하고, 그에 따라 replace 추천*/
	@Override
	public List<HashMap<String,Object>> uxReplace(String userName) {
		List<HashMap<String, Object>> ULM = userhistoryDAO.uxMbti(userName);
		String largestMbti = (String) ULM.get(0).get("mbti");
		log.info("mbti 값 고정 되어 있음 변경 필요 ");
		List<HashMap<String,Object>> adRutin = userhistoryDAO.rutinREByUx(largestMbti);
		return adRutin;
	}

	/*사용자가 가장 많이 조회한 mbti 유형을 분석하고, 그에 따라 Adventure 추천*/
	@Override
	public List<HashMap<String,Object>> uxAdventure(String userName) {
		List<HashMap<String, Object>> ULM = userhistoryDAO.uxMbti(userName);
		String largestMbti = (String) ULM.get(0).get("mbti");
		List<HashMap<String,Object>> adRutin = userhistoryDAO.rutinADByUx(largestMbti);
		return adRutin;
	}
	
	//사용자가 가장 많이 조호한 mbti 유형을 기준으로 4일치의 루틴 제공
	@Override
	public List<HashMap<String, Object>> uxRutin(String userName) {
		List<HashMap<String, Object>> ULM = userhistoryDAO.uxMbti(userName);
		//가장 많이 검색 된 상위 3개의 mbti가 저장된 리스트 
		String largestMbti = (String) ULM.get(0).get("mbti");
		
		log.info("아래 rutinbyUX MBTI 값 수정해야 됨");
		List<HashMap<String, Object>> rutin = userhistoryDAO.rutinByUx(largestMbti);		
		return rutin;
	}
	
	
	/*user가 작성한 Post*/
	@Override
	public List<HashMap<String, Object>> selectUserPost(Principal principal) {
		String userName = principal.getName();
		List<HashMap<String, Object>> userPost = userhistoryDAO.userCreatePost(userName);
		return userPost;
	}

	
	/*user가 작성한 QnA*/
	@Override
	public List<HashMap<String, Object>> selectUserQnA(Principal principal) {
		String userName = principal.getName();
		List<HashMap<String, Object>> userQnA = userhistoryDAO.userCreateQnA(userName);
		return userQnA;
	}
	
	
	//viewPost, viewItem 테이블에 각각의 정보가 새로 생길 때마다 새로 생긴 Pk 값을 저장
	@Override
	public void ViewCreateItem() {
		ItemDTO creatTarget = new ItemDTO();
		int lastIdx =userhistoryDAO.lastIdxItem();
		creatTarget.setItemID(lastIdx);
		userhistoryDAO.insertViewTableItem(creatTarget);
	}
	
	@Override
	public void ViewCreatePost() {
		PostDTO postDTO = new PostDTO();
		int lastIdx = userhistoryDAO.lastIdxPost();
		postDTO.setPostID(lastIdx);
		userhistoryDAO.insertViewTablePost(postDTO);
	}
	
	
	/*사용자가 Item 조회시 기록*/
	@Override
	public List<HashMap<String, Object>> viewRating(Principal principal) {
		List<HashMap<String, Object>> rating = userhistoryDAO.getRatingItem(principal.getName());
		return rating;
	}
	
	
	/*
	 * 작업자 신성진
	 * @Param : 유저의 principal 정보 
	 * @Return : 사용자가 조회한 모든 조건의 결과 
	 * */
	@Override
	public List<List<?>> userViewInfo(Principal principal) {
		log.info("userHisotry userViewInfo principal null isuue  == >{}", principal.getName());
		String userName = principal.getName();
		List<List<?>> userViewInfo = new ArrayList<>();
		log.info("userName , userHistoyservice. userViewInfo   =>{}", userName);
		List<ItemDTO> userViewReplace = userhistoryDAO.viewReturnRE(userName);
		List<ItemDTO> userViewAdventure =userhistoryDAO.viewReturnAD(userName);
		List<PostDTO> userViewPost = userhistoryDAO.viewReturnPO(userName);
		userViewInfo.add(userViewReplace);
		userViewInfo.add(userViewAdventure);
		userViewInfo.add(userViewPost);
		return userViewInfo;
	}

	

}
