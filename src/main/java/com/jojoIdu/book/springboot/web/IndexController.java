package com.jojoIdu.book.springboot.web;

import com.jojoIdu.book.springboot.config.auth.dto.SessionUser;
import com.jojoIdu.book.springboot.service.posts.PostsService;
import com.jojoIdu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postService;
    private final HttpSession httpSession;

    // 인덱스 페이지, 전체 조회
    @GetMapping("/")
    public String index(Model model) {
        /* 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할때
        앞의 경로와 뒤의 파일 확장자는 자동으로 지정됨.
        앞의 경로는 src/main/resources/templates 이고
        뒤의 파일 확장자는 .mastache가 붙는다.
        여기선 src/main/resources/templates/index.mustache파일로 View Resolver가 처리함!
         */
        model.addAttribute("posts",postService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) {
            model.addAttribute("userName",user.getName());
        }
        return "index";
    }
    
    // 게시글 저장
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    // 게시글 수정
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }


}
