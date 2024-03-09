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
	
	
	/*3월 8일 작업자 신성진, 사이트 이용자의 mbti 설정 메소드 설정 
	 * mbti 데이터는 미리 설정해두고, 땡겨와서 User에 넣는 방식으로 수정할 예정
	 */
	
	public void setUserMbti(SiteUser User, String mbti) {
		SiteUser user = new SiteUser();
		user.setMbti(mbtiRepo.findByMbti(mbti));
		this.userRepo.save(user);
	}
	//테스트용 주석

	//3월 8일 mbti 세팅용 메소드 작성자 : 신성진	
	public void createMbti(String mbti) {
		mbti mt = new mbti();
		mt.setMbti(mt);
		mbtiRepo.save(mt);
	}
}
