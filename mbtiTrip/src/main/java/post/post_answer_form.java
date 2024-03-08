package post;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class post_answer_form {

	@NotEmpty(message = "내용은 필수항목입니다.")
	private String content;
}
// 답변폼입니다