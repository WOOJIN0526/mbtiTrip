package post;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class postDto {

	private Integer id;
	
	private String password;
	
	private String username;
	
	private String subject;
	
	private String content;
	
	private String date;
	
	//검색 타입
	private String type;
	//검색 내용
	private String keyword;
	
	
	
}
// 검색 카테고리에 쓰려고 만든건데 아직 잘 모르겠음 0312 김현석