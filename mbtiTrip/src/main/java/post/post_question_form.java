package post;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import mbti.mbti;

@Getter
@Setter
public class post_question_form {

	@NotEmpty(message="제목은 필수입니다.")
	@Size(max=200)
	private String subject;
	
	@NotEmpty(message="mbti는 필수입니다.")
	private mbti mbti;
	
	@NotEmpty(message="내용은 필수입니다.")
	private String content;
	
	
	
}
