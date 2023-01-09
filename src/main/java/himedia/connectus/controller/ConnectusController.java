package himedia.connectus.controller;

//import java.time.LocalDateTime;
import java.util.List;

//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import himedia.connectus.domain.Post;
import himedia.connectus.repository.PostRepository;
//import himedia.connectus.service.PostService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ConnectusController {

	//private final PostService service;
	private final PostRepository repository;
	
	/**
	 * 게시판 1 화면
	 */
	@GetMapping("/")
	public String showPostsFeed(Model model) {
		List<Post> posts = repository.findAll();
		model.addAttribute("posts", posts);
		return "posts/posts-feed";
	}
	
	/**
	 *	게시물 상세보기 화면
	 */
	@GetMapping("/visit/{postId}")
	public String showPostViewDetails(@PathVariable Long postId, Model model) {
		Post post = repository.findById(postId).get();
		model.addAttribute("post", post);
		return "post/post-view-details";
	}
	
	/**
	 * 게시물 작성 화면
	 */
	@GetMapping("/visit/write")
	public String showPosting(Model model) {
		model.addAttribute("post", new Post());
		return "post/post-write";
	}
	
	/**
	 * 게시물 작성
	 */
	@PostMapping("/visit/write")
	public String posting(@ModelAttribute Post post
			, MultipartFile file
			, RedirectAttributes redirectAttributes
			//, @RequestParam("userDateStr") @DateTimeFormat(iso = ISO.DATE) LocalDateTime userDateStr
			) throws Exception {
		Post savedPost = repository.save(post, file);
		redirectAttributes.addAttribute("postId", savedPost.getPostId());
		return "redirect:/visit/{postId}";
		//return "redirect:/visit/{postId}";
	}
	
	/**
	 * 게시물 수정 화면
	 */
	@GetMapping("/visit/{postId}/edit")
	public String editForm(@PathVariable Long postId, Model model) {
		Post post = repository.findById(postId).get();
		model.addAttribute("post", post);
		return "post/post-edit";		
	}
	
	/**
	 * 게시물 수정
	 */
	@PostMapping("/visit/{postId}/edit")
	public String editSave(@PathVariable Long postId, @ModelAttribute Post post) {
		repository.update(postId, post);
		return "redirect:/visit/{postId}";
	}
}
