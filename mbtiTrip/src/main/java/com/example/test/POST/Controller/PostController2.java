package com.example.test.POST.Controller;

import java.io.File;

import java.io.IOException;

import java.security.Principal;
import java.util.ArrayList;

import java.util.List;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.POST.PostAttach;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.AnswerDTO2;
import com.example.test.POST.DTO.PostDTO2;
import com.example.test.POST.Service.PostService2;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;
import com.example.test.paging.Page;


@Controller
@RequestMapping("/post/")
public class PostController2 {
	
	//@Value("${upload.path}")
	private String uploadPath;

	@Autowired
	private PostService2 postService;

	@Autowired
	private UserService userService;

	//게시글 목록 화면
	@RequestMapping(value = "/noticeBoard", method = RequestMethod.GET)
	public void list(Model model, Page page) throws Exception{

		Integer totalCount = null;
		Integer rowPerPage = null;
		Integer pageCount = null;
		Integer pageNum = page.getPageNum();
		String keyword = page.getKeyword();

		// 조회된 전체 게시글 수
		if( page.getTotalCount() == 0 ) {
			totalCount =postService.totalCount();
		}
		 else { 
		        	totalCount = page.getTotalCount();
		 }
		//페이지 당 노출 게시글 수
		if( page.getRowsPerPage() == 0 )
		{	rowPerPage = 10;
			}
		else {
			rowPerPage = page.getRowsPerPage();
		}
		//노출 페이지수
		if(page.getPageCount() == 0)
		{
			pageCount = 10;
		}
		else {
			pageCount = page.getPageCount();
		}
		if(page.getPageNum() == 0){
			page = new Page(1, rowPerPage, totalCount, pageCount); 
		} else{
			page = new Page(pageNum, rowPerPage, totalCount, pageCount);
		}
		
		if(keyword == null || keyword == ""){
			page.setKeyword("");
			model.addAttribute("list", postService.list(page));
		} else {
			page.setKeyword(keyword);
			model.addAttribute("list", postService.search(page));
		}

		model.addAttribute("page", page);

	}

	//게시글 쓰기 화면
	//@PreAuthorize()
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerForm(Model model, PostDTO2 post, Principal user) throws Exception{
		String userName = "";
		if(user !=null){
			userName = user.getName();
			model.addAttribute("userName", userName);
		}
	} 

	//게시글 쓰기 처리
	@RequestMapping(value ="/register", method = RequestMethod.POST)
	public String register(Model model, PostDTO2 post) throws Exception{

		//[파일 업로드]
		MultipartFile[] file = post.getFile();
		String[ ] filePath = post.getFilePath();

		//파일 업로드 처리
		ArrayList<PostAttach> attachList = uploadFiles(file);

		//게시글 등록
		postService.register(post);

		//첨부파일 등록
		for(PostAttach attach : attachList){
			postService.uploadFile(attach);
		}
		
		model.addAttribute("msg", "등록이 완료되었습니다");
		return "post/noticeBoard/success"; 

	}

	//게시글 읽기 화면
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String read(Model model, Integer postId, Principal user) throws Exception{

		PostDTO2 post = postService.read(postId);
		if(post == null){
		 model.addAttribute("msg", "조회할수 없는 게시글 입니다");
		return " post_empty";
		}
		
		String userName = "";
		if( user !=null ){
			userName = user.getName();
			UserDTO user1 = userService.getUser(userName);
			
			model.addAttribute("userName", userName);
		}

		String writer = post.getWriter();
		if( writer.equals(userName)){
			model.addAttribute("set", true); // 작성자일 경우만 수정, 삭제 노출
		}

		//조회수 증가
		postService.views(postId);

		model.addAttribute("post", post);
		// 파일 목록 조회
		model.addAttribute("files", postService.readFileList(postId) );
		// 댓글 목록 조회
		model.addAttribute("replyList", postService.replyList(postId));

		return "post_detail";
	}

	//게시글 수정화면
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyForm(Model model, Integer postId, Principal user) throws Exception{

		String userName = "";
		if( user != null) {
			userName = user.getName();
			model.addAttribute("userName", userName);
		}
	 	PostDTO2 post = postService.read(postId);
		String writer = post.getWriter();
		if( !writer.equals(userName)){
			return "rediect:/error/noAuth";
		}

		model.addAttribute("post", post);
		model.addAttribute("files", postService.readFileList(postId));
		
		return "post/noticeBoard/modify";
	}

	//게시글 수정 처리
	@PostMapping("/modify")
	public String modify(Model model, PostDTO2 post, Integer[] deleteNo) throws Exception{

		//선택한 파일 DB에서 삭제
		ArrayList<PostAttach> deleteFileList = new ArrayList<PostAttach>();
		//삭제번호를 클릭한 경우에만 반복
		if(deleteNo !=null){
			for( Integer no : deleteNo){
				deleteFileList.add(postService.readFile(no));
			}
		}

		//파일 삭제
		deleteFiles(deleteFileList);

		//파일 업로드
		MultipartFile[] file = post.getFile();
		String[] filePath = post.getFilePath();

		//파일 업로드 처리
		ArrayList<PostAttach> attachList = uploadFiles(file);

		postService.modify(post);

		//첨부파일 등록
		for(PostAttach attach : attachList) {
			attach.setPostId(post.getPostID());
			postService.uploadModifyFile(attach);
		}
		
		model.addAttribute("msg", "수정이 완료되었습니다");
		return "post/success";

	}

	//게시글 삭제 처리
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(Model model, Integer postId) throws Exception{
		List<PostAttach> attachList = postService.readFileList(postId);

		//게시글에 첨부한 파일 삭제
		deleteFiles(attachList);

		postService.remove(postId);	

		model.addAttribute("msg", "삭제가 완료되었습니다");
		return "post/success";
	}

	//게시글 검색처리
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Model model, Page page) throws Exception {

		String keyword = page.getKeyword();
		Integer totalCount = postService.totalCount(keyword);
		Integer rowsPerPage = null;
		Integer pageCount = null;

		//페이지 당 노출 게시글 수
		if( page.getRowsPerPage() == 0 )
			rowsPerPage = 10;
		else
		  	rowsPerPage = page.getRowsPerPage();

		//노출 페이지 수
		if(page.getPageCount() ==0)
			pageCount = 10;
		else
			pageCount = page.getPageCount();
		page = new Page(1, rowsPerPage, totalCount, pageCount);
		page.setKeyword(keyword);

		model.addAttribute("list", postService.search(page));
		model.addAttribute("page", page);
		
		return "post_list";

	}

	//파일 업로드
	private ArrayList<PostAttach> uploadFiles(MultipartFile[ ] files) throws IOException{

		ArrayList<PostAttach> attachList = new ArrayList<PostAttach>();

		//업로드 경로에 파일 복사
		for(MultipartFile file : files){
		
			if(file.isEmpty()){
				continue;
			}

		//파일명 중복 방지를 위한 고유 ID 생성
		UUID uid = UUID.randomUUID();

		//실제 원본 파일 이름
		String originalFileName = file.getOriginalFilename();

		String uploadFileName = uid.toString() + "_" + originalFileName;

		//업로드 폴더에 업로드할 파일 복사
		byte[] fileData = file.getBytes();
		File target = new File(uploadPath, uploadFileName);
		FileCopyUtils.copy(fileData, target);

		PostAttach attach = new PostAttach();
		attach.setFullName(uploadPath + "/" + uploadFileName); //업로드 파일 전체경로
		attach.setFileName(originalFileName); // 파일명
		attachList.add(attach);	
		}

		return attachList;
	}

	// 파일 삭제
	public void deleteFiles(List<PostAttach> deleteFileList) throws Exception{

		//해당 게시글의 첨부파일 전체 삭제
		for(PostAttach deleteFile : deleteFileList) {
			String fullName = deleteFile.getFullName();
			Integer fileNo = deleteFile.getFileNo();

			File file = new File(fullName);
			//실제로 파일이 존재하는지 확인
			if(file.exists()){
				//파일 삭제
				if(file.delete()){
					postService.deleteFile(fileNo);
				} 
					
				
			}
		}

	}

	//댓글 등록
	@RequestMapping(value = "/replyRegister", method = RequestMethod.GET)
	public String replyRegister(Model model, AnswerDTO2 reply, Principal user) throws Exception{

		String userName = "";
		if( user !=null){
			userName = user.getName();
			model.addAttribute("userName", userName);
		} 

		//댓글 등록
		postService.replyRegister(reply);

		Integer postId = reply.getPostId();

		//댓글 목록 조회
		model.addAttribute("replyList", postService.replyList(postId));
		
		return "post_list";
	}

	//댓글 수정
	@RequestMapping(value = "replyModify", method = RequestMethod.GET)
	public String replyModify(Model model, AnswerDTO2 reply, Principal user) throws Exception{

		String userName = "";
		if( user !=null) {
			userName = user.getName();
			model.addAttribute("userName", userName);	
		}

		//댓글 수정
		postService.replyModify(reply);

		Integer postId = reply.getPostId();
		
		//댓글 목록 조회
		model.addAttribute("replyList", postService.replyList(postId));

		return "post_list";
	}

	//댓글 삭제
	@RequestMapping(value = "replyRemove", method = RequestMethod.GET)
	public String replyRemove(Model model, AnswerDTO2 reply, Principal user) throws Exception{

		String userName = "";
		if( user != null) {
			userName = user.getName();
			model.addAttribute("userName", userName);
		}

		//댓글 삭제
		postService.replyRemove(reply.getAnswerID());

		Integer postId = reply.getPostId();

		//댓글 목록 조회
		model.addAttribute("replyList", postService.replyList(postId));

		return "post_list";
	}
}
