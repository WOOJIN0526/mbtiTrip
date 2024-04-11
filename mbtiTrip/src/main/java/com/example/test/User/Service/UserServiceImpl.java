package com.example.test.User.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.GCSService.GCSService;
import com.example.test.User.DAO.UserCartDAO;
import com.example.test.User.DAO.UserDAO;
import com.example.test.User.DTO.UserDTO;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Service 
public class UserServiceImpl implements UserService{

	@Autowired
	private GCSService gcsService;
	
	private BCryptPasswordEncoder bcrypasswordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserDAO userDao;
	
	@Override
	public int createUser(UserDTO userDTO) {
		String userPassword = userDTO.getPassword();
		log.info("userPassword : {}", userPassword);
		String encodePassword = bcrypasswordEncoder.encode(userPassword);
		userDTO.setPassword(encodePassword);
		int result = this.userDao.insert(userDTO);
			return result;
	
	}
	
	@Override
	public int createBis(UserDTO userdto) {
		String userPassword = userdto.getPassword();
		String encodePassword = bcrypasswordEncoder.encode(userPassword);
		userdto.setPassword(encodePassword);
		int result = this.userDao.insertBis(userdto);
		return result;
	}
	
	@Override
	public  Map<String, Object> login(UserDTO userdto) {
		return userDao.login(userdto);
	}

	@Override
	public int userUpdate(UserDTO userdto, Principal principal) {
		Integer UID = princeUID(principal);
		userdto.setUID(UID);
		userdto.setPassword(bcrypasswordEncoder.encode(userdto.getPassword()));
		
		int result =userDao.userUpdate(userdto);
		return result;
	}
	
	@Override
	public int BisUpdate(UserDTO userdto) {
		int result =userDao.BisUpdate(userdto);
		return result;
	}

	@Override
	public Map<String, Object> getInfo(Integer uID) {
		return userDao.getInfo(uID);
	}
	
	@Override
	public Integer findByUID(String userName) {
		log.info("User Service {}", userName);
		Integer UID = userDao.getUID(userName);
		log.info("User Service after {}", userName);
		return UID;
	}

	@Override
	public UserDTO getUser(String name) {
		UserDTO siteUser = this.userDao.findByUsername(name);
		return siteUser;
	}

	public Integer princeUID(Principal principal) {
		String userName = principal.getName();
		Integer UID = findByUID(userName);
		return UID;
	}

	public boolean passwordCK(Principal principal, String inputPw) {
		String target = getUser(principal.getName()).getPassword();
		boolean ck = bcrypasswordEncoder.matches(inputPw, target);
		return ck;
	}

	
	
	
	/*이 아래 bis 관련 기능 */
	@Override
	public List<HashMap<String, Object>> getMyItem(Principal principal) {
		String userName = principal.getName();
		userName ="testUser4";
		List<HashMap<String, Object>> myItem = userDao.getMyItem(userName);
		return myItem;
	}
	
	

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

	

}
