package com.kitri.myservletboard.controller;

import com.kitri.myservletboard.Service.BoardService;
import com.kitri.myservletboard.data.Board;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {

    BoardService boardService = BoardService.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>요청을 잘 응답받았습니다</h1>");

        // URL을 파싱(잘게분석)해서 어떤 요청인지 파악
        out.println(request.getRequestURL());

        String requestURI = request.getRequestURI(); // /board/list
        String contextPath = request.getContextPath(); // /
        String command = requestURI.substring(contextPath.length()); // /board/list

        // 포워드 기능을 위해 view 라는 변수를 선언한다.
        String view = "/view/board/";

        out.println("command = " + command);

        if (command.equals("/board/list")) {
            // 요청 : 게시글 리스트 좀 보여줘
            // 응답 : 게시글 리스트 페이지로 응답
            // ㄴ 리다이렉트로 응답해보기
//            response.sendRedirect("list.jsp");
//            response.addHeader("Refresh", "2; url= " + "/view/board/list.jsp");
            ArrayList<Board> boards = boardService.getBoards(); // 게시판 리스트
            // 서비스를 통해 게시판 리스트를 가져왔다.
            // jsp에게 넘겨줘야 게시판을 동적으로 만든다.
            // request 에 attribute 를 이용하자
            request.setAttribute("boards", boards);
            view += "list.jsp";

        } else if (command.equals("/board/createForm")) {
//            response.sendRedirect("/view/board/createForm.jsp");
            // 요청 : 게시글 등록하게 등록폼 좀 줘
            // 응답 : 등록폼으로 응답
            view += "createForm.jsp";

        } else if (command.equals("/board/create")) {
            // 요청 : 게시판 이렇게 만들어줘
            // 응답 : 등록으로 응답
        } else if (command.equals("/board/updateForm")) {
//            response.sendRedirect("/view/board/updateForm.jsp");
            // 요청 : 게시판 이렇게 수정해줘
            // 응답 : 생성으로 응답
            view += "updateForm.jsp";

        } else if (command.equals("/board/update")) {
            // 요청 : 이 번호의 게시판 삭제 해줘
            // 응답 : 수정으로 응답
        } else if (command.equals("/board/delete")) {
            // 요청 : 이 번호의 게시판 삭제 해줘
            // 응답 : 삭제로 응답
        } else if (command.contains("/board/detail")) {
            // id에 해당하는 게시판 하나를 가져오면 된다.
            // /board/detail?id=3 이런식으로 들어온다.

            //요청이오면 HttpServletRequest 안에 담겨서 온다.
            // ★ 개발할 때 tip : 잘 안 된다면 디버그를 잘 이용하자.
//            String queryString = request.getQueryString();
            Long id = Long.parseLong(request.getParameter("id"));
            Board board = boardService.getBoard(id);
//            System.out.println();

            request.setAttribute("board", board);
            view += "detail.jsp";
        }
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);
            // 뷰(페이지)를 응답하는 방법
            // 리다이렉트

            // 포워드


    }
}