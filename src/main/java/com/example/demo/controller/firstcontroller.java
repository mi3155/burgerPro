package com.example.demo.controller;

import com.example.demo.Entity.Burger;
import com.example.demo.Entity.Notice;
import com.example.demo.dto.PageDTO;
import com.example.demo.dto.SignupDTO;
import com.example.demo.dto.noticeDTO;
import com.example.demo.service.BurgerServiceImp;
import com.example.demo.service.NoticeServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Log4j2
public class firstcontroller {

    @Autowired
    final private BurgerServiceImp service;
    final private NoticeServiceImp NoticeService;



    @GetMapping("/burger") // 메인페이지 ( 로그인 전)
    public String burger(){

        return "01burger.html";
    }

    @GetMapping("/main")
    public String main(){
        return "main.html";
    }

//    로그인 홈페이지 시작
    @GetMapping("/loginmain")
    public String loginmain(Authentication authentication,Model model){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //System.out.println("안녕하세요 " + userDetails.getUsername());

        model.addAttribute("login", userDetails);

        return "login/loginmain.html";
    }

    @GetMapping("/loginburger") // 메인페이지( 로그인 후 )
    public String hello2(Authentication authentication,Model model){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //System.out.println("안녕하세요 " + userDetails.getUsername());

        model.addAttribute("login", userDetails);

        return "login/loginburger.html";
    }

    @GetMapping("/loginDelivery") // 메인페이지( 로그인 후 )
    public String hello44(Authentication authentication,Model model){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //System.out.println("안녕하세요 " + userDetails.getUsername());

        model.addAttribute("login", userDetails);

        return "login/loginDelivery.html";
    }

    @GetMapping("/loginPromotion") // 메인페이지( 로그인 후 )
    public String hello55(Authentication authentication,Model model){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //System.out.println("안녕하세요 " + userDetails.getUsername());

        model.addAttribute("login", userDetails);

        return "login/loginPromotion.html";
    }
//    로그인 홈페이지 끝

    @GetMapping("/promotion")
    public String Promotion(){
        return "promotion.html";
    }

    @GetMapping("/macDelivery")
    public String Delivery(){
        return "macDelivery.html";
    }



    @GetMapping("/login") // 로그인 창
    public String hello3(){

        return "login.html";
    }

    @GetMapping("/Signup") // 회원가입 창
    public String hello5(){

        return "Signup.html";
    }

    @PostMapping("/SignupProc")
    public String hello7(SignupDTO dto, Model model){
        System.out.println(dto.toString());

        Burger burger = service.save(dto);
        model.addAttribute("Burger",burger);

        System.out.println("id : " + dto.getId() + "님 가입을 환영합니다.");

        return "login";
    }

    ///////////////////////////////////////////
    //notice

    @GetMapping("/post.do")
    public String PostPage(Authentication authentication,Model model){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //System.out.println("안녕하세요 " + userDetails.getUsername());

        model.addAttribute("login", userDetails);

        return "notice/post.html";}


    @PostMapping("/postproc.do")  // 인자값에 Model model이 있었는데 필요없는거같아서 뺌
    public String PostProcPage(noticeDTO dto,Authentication authentication){

        UserDetails userDetails = (UserDetails)  authentication.getPrincipal();
        dto.setId(userDetails.getUsername());

        NoticeService.postfunc(dto);
       // model.addAttribute("dto", dto);
        return "redirect:/list.do";
    }

    @GetMapping("/list.do")
    public String ListPage(PageDTO dto, Model model,Authentication authentication){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //System.out.println("안녕하세요 " + userDetails.getUsername());

        model.addAttribute("login", userDetails);

        int nowPage=1; //기본 페이지

        log.info("현재페이지 : " + dto.getNowPage());
        if(dto.getNowPage()!=0)
            nowPage=dto.getNowPage();

        //게시물가져오기
        Page<Notice> list =  NoticeService.getBoardList(nowPage-1,10); //페이지번호 0부터 시작


        //블럭 계산
        int pagePerBlock=15;                        //한페이지당 표시할 블럭수
        Long totalRecord=list.getTotalElements();   //전체 레코드 수
        int totalPage = list.getTotalPages();       //전체 페이지 수
        int nowBlock = (int)Math.ceil((double)nowPage/pagePerBlock);//현재블럭 번호
        int totalBlock=(int)Math.ceil((double)totalPage/pagePerBlock);//전체블럭 개수

        //블럭에 표시할 StartNum,EndNum 계산
        int pageStart=(nowBlock -1)*pagePerBlock+1;
        int pageEnd=((pageStart+pagePerBlock)<=totalPage)?(pageStart+pagePerBlock):totalPage+1;

        //Model에 연결
        model.addAttribute("list",list);
        model.addAttribute("PS",pageStart);
        model.addAttribute("PE",pageEnd);
        model.addAttribute("pagePerBlock",pagePerBlock);
        model.addAttribute("nowBlock",nowBlock);
        model.addAttribute("totalBlock",totalBlock);
        model.addAttribute("nowPage",nowPage);

        return "notice/list.html";
    }


    @GetMapping("/read.do")
    public String ReadPage(HttpServletRequest req, Model model, Authentication authentication){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //System.out.println("안녕하세요 " + userDetails.getUsername());

        model.addAttribute("login", userDetails);

        System.out.println("NUM : " + req.getParameter("num"));

        //현재페이지 값 저장
        int nowPage = Integer.parseInt(req.getParameter("nowPage"));

        //세션추출
        HttpSession session = req.getSession();

        //현재 읽고있는 게시물 정보 받기
        Long num =  Long.parseLong(req.getParameter("num"));
        //카운트 증가
        NoticeService.Upcount(num);
        //게시물 받아오기
        Notice notice = NoticeService.getBoard(num);



        //세션에 읽고있는 게시물 넣기
        session.setAttribute("notice", notice);
        session.setAttribute("nowPage",nowPage);

        //모델에 추가(페이지로 전달)
        model.addAttribute("notice", notice);
        model.addAttribute("nowPage",nowPage);

        return "notice/read.html";
    }

    @PostMapping("/isupdate.do")
    public String isupdate(noticeDTO dto,Model model, Authentication authentication){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //System.out.println("안녕하세요 " + userDetails.getUsername());

        model.addAttribute("login", userDetails);

        model.addAttribute("dto",dto);
        return "notice/isupdate.html";
    }
    @PostMapping("/update.do")
    public String UpdatePage(noticeDTO dto,HttpServletRequest req){

        log.info("Update 예정인 정보" + dto.toString());

        HttpSession session = req.getSession();
        Notice board = (Notice)session.getAttribute("notice");
        int nowPage = (Integer)session.getAttribute("nowPage");
        log.info("READ중인 정보 : " + board.toString());
        //패스워드 일치 여부
        if(dto.getPassword().equals(board.getPassword())){
            //dto에 값 추가
            dto.setId(board.getId());
            dto.setNum(board.getNum());
            //서비스 수정함수 사용
            NoticeService.Updateboard(dto);;
            //list.do 로 이동 (읽고 있는 페이지로 이동)
            return "redirect:/list.do?nowPage="+nowPage;
        } else{
            //read.do로 이동(읽고 있는 게시물로)
            return "redirect:/read.do?num=" + board.getNum()+"&nowPage="+nowPage;

        }
    }

    @GetMapping("/isdelete.do")
    public String isdeletepage(Authentication authentication,Model model){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //System.out.println("안녕하세요 " + userDetails.getUsername());

        model.addAttribute("login", userDetails);

        return "notice/isdelete.html";
    }
    @PostMapping("/delete.do")
    public String deletepage(noticeDTO dto, HttpServletRequest req){
        log.info("Delete 예정인 정보 : " + dto.toString());

        HttpSession session = req.getSession();
        Notice notice = (Notice)session.getAttribute("notice");
        int nowPage = (Integer)session.getAttribute("nowPage");
        log.info("READ중인 정보 : " + notice.toString());
        //패스워드 일치 여부
        if(dto.getPassword().equals(notice.getPassword())){

            //서비스 삭제함수 사용
            NoticeService.deleteboard(notice.getNum());

            //list.do 로 이동 (읽고 있는 페이지로 이동)
            return "redirect:/list.do?nowPage="+nowPage;
        } else{
            //read.do로 이동(읽고 있는 게시물로)
            return "redirect:/read.do?num=" + notice.getNum()+"&nowPage="+nowPage;

        }
    }

    //로그아웃
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response,SecurityContextHolder.getContext().getAuthentication());
            return "redirect:/login.html";
    }

    @GetMapping("/accessfailed")
    public String accessfailed(){
        return "login/accessfailed.html";
    }
}
