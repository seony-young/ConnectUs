package himedia.connectus.repository;

import java.io.File;
//import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Repository;

import himedia.connectus.domain.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PostRepositoryJPA implements PostRepository {
	private final EntityManager em;

	@Override
	public Post save(Post post, MultipartFile file
			//, LocalDateTime userDateStr
			) throws Exception {
		if(file.isEmpty()) {
			post.setFileName(null);
			post.setFilePath(null);
		}
		else {
			String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + "_" + file.getOriginalFilename();
			File saveFile = new File(projectPath, fileName);
			file.transferTo(saveFile);
			post.setFileName(fileName);
			post.setFilePath("/files/" + fileName);			
		}
		
		//post.setUserDateStr(userDateStr);
		
		em.persist(post);
		return post;
	}

	@Override
	public Optional<Post> findById(Long id) {
		Post post = em.find(Post.class, id);
		return Optional.ofNullable(post);
	}

	//지역명 선택(제출)시 검색 결과 응답
	@Override
	public List<Post> findByPlace(String place) {
		List<Post> result =  em.createQuery("select p from Post p where p.place = :place", Post.class)
				.setParameter("place", place)
				.getResultList();
		return result;
	}

	@Override
	public List<Post> findAll() {
		return em.createQuery("select p from Post p", Post.class)
				.getResultList();
	}

	@Override
	public void update(Long postId, Post updatedPost) {
		String sql = "update Post p "
				+ "set content=:content"
				+ ", place=:place"
				+ ", filePath=:filePath "
				+ "where postId=:postId";
		//날짜(제목) 변경 가능하도록
		
		int result = em.createQuery(sql)
						.setParameter("content", updatedPost.getContent())
						.setParameter("place", updatedPost.getPlace())
						.setParameter("filePath", updatedPost.getFilePath())
						.setParameter("fileName", updatedPost.getFileName())
						.setParameter("postId", postId)
						.executeUpdate();
		log.info("[JPARepository] update method: END - result >> {}", result);
		em.clear();
	}

}
