package mbti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.SiteUser;
import user.UserRepository;

@Service
public class mbtiService {

	
	@Autowired
	private mbtiRepository mbtiRepo;
	@Autowired
	private UserRepository userRepo;
	
	
	/*3월 8일 작업자 신성진, 사이트 이용자의 mbti 설정 메소드 설정 */
	public void setUserMbti(SiteUser User, String mbti ) {
		SiteUser user = new SiteUser();
		user.setMbti(mbti);
		this.userRepo.save(user);
	}
}
