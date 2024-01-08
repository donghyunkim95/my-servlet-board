<%@ page import="com.kitri.myservletboard.data.Board" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>게시판목록</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<style>
  * {
    font-family: Arial, Helvetica, sans-serif;
  }

  footer {
    bottom: 60px;
    font-size: 12px;
  }

  #wrap {
    width: 100%;
    /* margin-top = header height */
    margin-top: 60px;
  }

  /* Navigation bar */
  header {
    /* for sticky header */
    position: fixed;
    top: 0;

    width: 100%;
    height: 60px;
    z-index: 2000;
    background-color: #fff;
    box-shadow: 0 2px 2px rgba(0, 0, 0, 0.05), 0 1px 0 rgba(0, 0, 0, 0.05);
  }

  .logo {
    display: inline-block;
    height: 36px;
    margin: 12px 0 12px 25px;
  }

  .logo>img {
    height: 36px;
  }

  nav {
    float: right;

  }

  .nav-items {
    margin-right: 20px;
    text-decoration: none;
  }

  .nav-items>li {
    display: inline-block;
    /* 가로정렬 */
  }

  .nav-items>li>a {
    /* for Vertical Centering */
    line-height: 60px;
    padding: 0 30px;
    color: rgba(0, 0, 0, 0.4);
  }

  .nav-items>li>a:hover {
    color: rgba(0, 0, 0, 0.8);
  }
</style>

<body>
  <header>
    <a class="logo" href="/board/list"><img src="https://poiemaweb.com/img/logo.png"></a>
    <nav>
      <ul class="nav-items">
        <li><a href="/board/list">게시글목록</a></li>
        <li><a href="/board/createForm">게시글등록</a></li>
        <li><a href="/board/updateForm">게시글수정</a></li>
        <li><a href="/view/member/join.jsp">회원가입</a></li>
        <li><a href="/view/member/registration.jsp">회원정보수정</a></li>
        <li><a href="/view/member/login.jsp">로그인</a></li>
      </ul>
    </nav>
  </header>

  <div>
    <h2 style="text-align: center; margin-top: 100px;"><b>게시판 목록</b></h2>
  </div>
  <div class="container class=d-flex justify-content-center">
    <div class="p-2 border-primary mb-3">
      <table class="table align-middle table-hover">
        <thead class="table-dark">
          <tr>
            <th scope="col">번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">날짜</th>
            <th scope="col">조회수</th>
            <th scope="col">댓글수</th>
          </tr>
        </thead>
        <tbody class="table-group-divider">
        <%
          ArrayList<Board> boards = (ArrayList<Board>) request.getAttribute("boards");
          for (int i = 0; i <boards.size(); i++) {
        %>
          <tr>
            <th scope="row"><%=boards.get(i).getId()%> </th>
            <td><a href ="board/detail?id=<%=boards.get(i).getId()%>">
                <%= boards.get(i).getTitle()%>
              <%--  id가 의미하는건 boards.get(i).getId() 이다.
                    동적인 데이터, 값을 넣고 싶을 때에는 <%%> 로 넣어준다. --%>
            <td><%= boards.get(i).getWriter()%>
            <td><%= boards.get(i).getCreatedAt()%>
            <td><%= boards.get(i).getViewCount()%>
            <td><%= boards.get(i).getCommentCount()%> </td>
          </tr>
          <%
            }
          %>
        </tbody>
      </table>
      <div>
        <a href="/board/createForm" role="button" class="btn btn-outline-dark">글쓰기</a>
      </div>
      <div class="d-flex justify-content-center">
      <nav aria-label="Page navigation example">
        <ul class="pagination pagination-sm">
          <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
          </li>
          <li class="page-item"><a class="page-link" href="#">1</a></li>
          <li class="page-item"><a class="page-link" href="#">2</a></li>
          <li class="page-item"><a class="page-link" href="#">3</a></li>
          <li class="page-item">
            <a class="page-link" href="#">Next</a>
          </li>
        </ul>
      </nav>
    </div>
    </div>
  </div>
  </div>
  <div class="p-2">
    <div class="container d-flex justify-content-center">
      <footer>
        <span class="text-muted">&copy; Company's Bootstrap-board</span>
      </footer>
    </div>
  </div>


</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</html>