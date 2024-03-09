package mbti;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
/*3월 8일 작업자 신성진 */

public interface mbtiRepository extends JpaRepository<mbti, Integer>{

	mbti findByMbti(String mbti);
}
