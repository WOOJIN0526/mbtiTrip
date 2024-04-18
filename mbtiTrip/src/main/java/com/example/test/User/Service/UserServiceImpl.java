package com.example.test.User.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.GCSService.GCSService;
import com.example.test.User.DAO.UserCartDAO;
import com.example.test.User.DAO.UserDAO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.item.DAO.ItemDAO;
import com.example.test.item.DTO.ItemDTO;
import com.example.testExcepion.SignUP.SignUpException;
import com.example.testExcepion.SignUP.SignUpExceptionEunm;
import com.example.testExcepion.login.LoginException;
import com.example.testExcepion.login.LoginExceptionEnum;
import com.example.testExcepion.updated.UpdateException;
import com.example.testExcepion.updated.UpdateExceptionEnum;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Service 
public class UserServiceImpl implements UserService{

	/**
	 * @autor 신성진
	 * */
	
	
	@Autowired
	private GCSService gcsService;
	
	private BCryptPasswordEncoder bcrypasswordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private ItemDAO itemDao;
	
	
	
	//SignUp한 사용자를 등록합니다. 
	@Override
	public int createUser(UserDTO userDTO) throws SignUpException{
		//signUP validtionCk
		vaildationUser(userDTO);
		//사용자가 입력한 password 를 인코딩해 저장합니다
		String userPassword = userDTO.getPassword();
		String encodePassword = bcrypasswordEncoder.encode(userPassword);
		userDTO.setPassword(encodePassword);
		
		//insert 쿼리 구문의 결과에 따라 예외처리 진행
		int result = this.userDao.insert(userDTO);
		if(result == 0) {
			throw new SignUpException(SignUpExceptionEunm.SIGN_INTERNAL_ERROR);
		}
		return result;
	
	}
	//위와 상동
	@Override
	public int createBis(UserDTO userdto) {
		vaildationUser(userdto);
		String userPassword = userdto.getPassword();
		String encodePassword = bcrypasswordEncoder.encode(userPassword);
		userdto.setPassword(encodePassword);
		int result = this.userDao.insertBis(userdto);
		if(result ==0) {
			throw new SignUpException(SignUpExceptionEunm.SIGN_INTERNAL_ERROR);
		}
		return result;
	}
	
	/** @deprecated security 도입으로 인해 더이상 사용하지 않습니다.
 	 * */
	@Override
	public  Map<String, Object> login(UserDTO userdto) {
		//login validationCK
		return userDao.login(userdto);
	}

	//사용자 정보 업데이트 method입니다. 
	@Override
	public int userUpdate(UserDTO userdto, Principal principal) {
		if(principal.getName() == null) {
			throw new LoginException(LoginExceptionEnum.LOGIN_NOTFOUND_MEMBER);
		}
		Integer UID = princeUID(principal);
		userdto.setUID(UID);
		userdto.setPassword(bcrypasswordEncoder.encode(userdto.getPassword()));
		
		//update 결과에 따라 반환합니다. 
		int result =userDao.userUpdate(userdto);
		if(result == 0) {
			throw new UpdateException(UpdateExceptionEnum.UPDATE_FAIL_SERVER);
		}
		return result;
	}
	
	//상동
	@Override
	public int BisUpdate(UserDTO userdto) {
		int result =userDao.BisUpdate(userdto);
		if(result == 0 ) {
			throw new UpdateException(UpdateExceptionEnum.UPDATE_FAIL_SERVER);
		}
		return result;
	}

	//사용자의 종합적인 정보를 불러 오는 method 입니다. 
	@Override
	public Map<String, Object> getInfo(Integer uID) {
		 Map<String, Object> userInfo= userDao.getInfo(uID);
		 return userInfo;
	}
	
	//사용자의 principal 객체를 활요해 사용자의 이름을 불러오고, userName을 통해 UID를 불러오는 Method입니다. 
	// Get Info와 기능을 합칠 수 있었겠지만, 일정이 촉박하여, 새로운 method를 만들고 도입하는 형식으로 진행했습니다. 
	// userName 또한 pk로 되어있기에 크게 상관없을 것 같다고 판단하였습니다. 

	//기존 security 도입 전, @pathvaliable 를 사용할 때 주로 사용 되었습니다.
	@Override
	public Integer findByUID(Principal principal) {
		String userName = userDao.getUserNameByuserID(principal.getName());
		Integer UID = userDao.getUID(userName);
		return UID;
	}

	
	//사용자 ID를 기준으로 정보를 로딩 합니다. 
	// 마찬가지로 Security 도입전 많이 활용하였지만, 현재 활용성이 떨어지는 method입니다.
	@Override
	public UserDTO getUser(String id) {
		UserDTO siteUser = this.userDao.getByUserId(id);
		return siteUser;
	}
	
	//사용자의 name을 기반으로 사용자 정보 전체를 로딩합니다.
	@Override
	public UserDTO getUserByUserName(String name) {
		UserDTO siteUser = this.userDao.getByUserName(name);
		return siteUser;
	}
	
	//Principal를 통해 UID를 불러옵니다.
	public Integer princeUID(Principal principal) {
		Integer UID = findByUID(principal);
		return UID;
	}

	//Password검사를 진행하는 method입니다.
	public boolean passwordCK(Principal principal, String inputPw) {

//		String userName = userDao.getUserNameByuserID(principal.getName());

		String userName = principal.getName();
		String target = getUser(userName).getPassword();
		boolean ck = bcrypasswordEncoder.matches(inputPw, target);
		return ck;
	}

	
	/*이 아래 bis 관련 기능 */
	@Override
	public List<HashMap<String, Object>> getMyItem(Principal principal) {
		String userName = userDao.getUserNameByuserID(principal.getName());
		//userName ="testUser4";
		List<HashMap<String, Object>> myItem = userDao.getMyItem(userName);
		// item들을 불러올떄 itemID값을 통해 등록된 item의 imgURl을 가져와넣는 작업
		List<String> urlList = new ArrayList<>();
		for(HashMap<String, Object> item :myItem) {
			
			int itemID = (Integer) item.get("itemId");
			 List<String> url = itemDao.getUrl(itemID);
			 //등록된 이미지가 없을 경우
			
			 if(url.isEmpty()) {
				
				 url.add("0");
			 }
			 
			 String[] ImgeUrl = url.toArray(new String[0]); // 리스트를 배열로 변환
		     
			 item.put("ImgeUrl", ImgeUrl); // 아이템에 이미지 URL 배열 추가
		}
		return myItem;
	}
	
	
	//Bis의 itmeList에 총 조회수 삽입하는 method입니다.
	@Override
	public List<HashMap<String,Object>> bisListput(List<HashMap<String,Object>> itemList, List<HashMap<String,Object>> viewList){
		for(HashMap<String,Object> item : itemList) {
			for(HashMap<String, Object> rate : viewList) {
				Set<String> keySet = rate.keySet();
				for(String key : keySet) {
					if(item.get("itemId").equals(rate.get("itemID"))) {
						if(key.equals("viewRating")) {
						 item.put(key, rate.get(key));
						 
						}
					}
				}
				
			}
		}
		return itemList;
	}

	//사용자에 대한 validation 검사를 진행하는 method입니다.
	private void vaildationUser(UserDTO userDTO) throws SignUpException{
		if(userDTO.getUserId() == null) {
			throw new SignUpException(SignUpExceptionEunm.SIGN_ID_NULL);
		}
		
		if(userDao.idCk(userDTO)) {
			/* ID 중복*/
			throw new SignUpException(SignUpExceptionEunm.Signup_DUPLCATION_ID);
		}
		if(userNameVaildation(userDTO)) {
			/*닉네임 특수문자 검사*/
			throw new SignUpException(SignUpExceptionEunm.SignUP_Bad_NiCKNAME);
		}
		if(userDao.findByUsername(userDTO.getUsername()) != null) {
			throw new SignUpException(SignUpExceptionEunm.Signup_DUPLCATION_NiCKNAME);
		}
		if(userDTO.getPassword() == null) {
			log.info("password null? => {}", userDTO.getPassword());
			throw new SignUpException(SignUpExceptionEunm.SIGN_PASSWORD_NULL);
		}
		
		if(userDTO.getPassword().length() < 8) {
			throw new SignUpException(SignUpExceptionEunm.Signup_BADPASSWORD);
		}
		if(userDao.nameCk(userDTO)) {
			/*닉네임 중복 검사*/
			throw new SignUpException(SignUpExceptionEunm.Signup_DUPLCATION_NiCKNAME);
		}
		if(userDTO.getMail() == null) {
			throw new SignUpException(SignUpExceptionEunm.SIGN_EMAIL_NULL);
		}
		if(userDao.mailCK(userDTO)) {
			/*메일 중복 검사*/
			throw new SignUpException(SignUpExceptionEunm.SignUP_DUPLICATION_EMAIL);
		}				
	}

		//nickName 에 특수문자 포함여부 
	private boolean userNameVaildation(UserDTO userdto) {
		String userName = userdto.getUsername();
		boolean vaild = Pattern.matches("^[a-zA-Z0-9가-힣]*$", userName);
		//vaild가 true면 특수문자가 없다는 겨 
		// 그니까 특수문자가 없을 땐 그냥 지나치도록, 반대값 리턴, == true = 특수문자 존재 
		return !vaild;
	}

	//지역 검색 기능
	@Override
	public List<ItemDTO> serchLocation(String location) {
		List<ItemDTO> result = itemDao.searchLocation(location);
		return result;
	}

	

}
