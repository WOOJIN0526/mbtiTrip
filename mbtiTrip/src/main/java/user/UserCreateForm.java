package user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm { //회원가입 폼 작성

	@Size(min = 5, max= 20)
	@NotEmpty(message = "사용자 ID를 반드시 입력해주세요")
	private String username;
	
	@NotEmpty(message = "비밀번호는 필수입니다")
	private String password1;
	
	@NotEmpty(message = "비밀번호 확인은 필수입니다")
	private String password2;
	
	@NotEmpty(message = "이메일은 필수입니다")
	@Email
	private String email;
	
	@NotEmpty(message = "mbti는 필수입니다")
	private String mbti;
}
