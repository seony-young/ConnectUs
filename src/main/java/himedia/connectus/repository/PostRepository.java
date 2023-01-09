package himedia.connectus.repository;

//import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import himedia.connectus.domain.Post;

public interface PostRepository {
	Post save(Post post, MultipartFile file
			//, LocalDateTime userDateStr
			) throws Exception;
	Optional<Post> findById(Long id);
	//사용자 직접 검색이 아닌, 지역명 선택(제출)시 검색 결과 응답
	List<Post> findByPlace(String place);
	List<Post> findAll();
	void update(Long postId, Post updatedPost);
}
