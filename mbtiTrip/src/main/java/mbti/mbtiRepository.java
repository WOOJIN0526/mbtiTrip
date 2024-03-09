package mbti;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
/*3월 8일 작업자 신성진 */

public interface mbtiRepository extends JpaRepository<mbti, Integer>{
	//3월 9일 type 변경
	mbti findByMbti(String mbti);	
}
