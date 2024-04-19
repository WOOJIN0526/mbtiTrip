package com.example.test.User.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
/**@author Shin-Sungjin
 * @deprecated 
 * replace와 adventure Entity 통합으로 인해 사용되지 않습니다.
 * 
 * */
@Getter
@Setter
public class UserCartReplaceDTO {

	private Integer UserCartReplaceID; //pk


	private Integer replaceID; //fk
	

	private Integer userCartID; //fk
	
	
	private LocalDateTime updateDate;
}
