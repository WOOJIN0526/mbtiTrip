package user;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mbti.mbti;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	
	public SiteUser create(String username, String email, String password, mbti mbti) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setMbti(mbti);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);
		return user;
	}

	public SiteUser getUser(String name) {
		Optional<SiteUser> siteUser = this.userRepository.findByUsername(name);
		return siteUser.get();
	}
}
