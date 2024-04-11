package com.example.test.User.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.example.test.User.DAO.UserDAO;
import com.example.test.User.DTO.UserDTO;

public interface UserService {
		
	public int createUser(UserDTO userdto) ; //생성 안되면 , 0 , 1


	public Map<String, Object> login(UserDTO userdto);

    int userUpdate(UserDTO userdto, Principal principal);

	public Map<String, Object> getInfo(Integer uID);

	public int createBis(UserDTO userdto);

	public int BisUpdate(UserDTO userdto);

	public Integer findByUID(String userName);

	public UserDTO getUser(String name);


	public Integer princeUID(Principal principar);


	public boolean passwordCK(Principal principal, String password);


	/*bis 관련 기능 */
	public List<HashMap<String, Object>> getMyItem(Principal principal);


	List<HashMap<String, Object>> bisListput(List<HashMap<String, Object>> itemList,
			List<HashMap<String, Object>> viewList);

}
