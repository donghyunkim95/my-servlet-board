<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kitri.myservletboard.data.Board" %>
<%@ page import="com.kitri.myservletboard.data.Pagination" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<%
  Pagination pagination = (Pagination) request.getAttribute("pagination");
  String type = (String) request.getAttribute("type");
  String keyword = (String) request.getAttribute("keyword");
  String period = (String) request.getAttribute("period");

  String params = "";
  if (keyword != null) {
    params += "&type=" + type + "&keyword=" + keyword + "&date=" + period;
  } else {
    keyword ="";
  }
%>

<jsp:include page="/view/common/head.jsp">
  <jsp:param name="title" value="게시판 목록"/>
</jsp:include>
<body>

<jsp:include page="/view/common/header.jsp">
  <jsp:param name="type" value="<%=type%>"/>
  <jsp:param name="keyword" value="<%=keyword%>"/>
  <jsp:param name="created_at" value="<%=period%>"/>
</jsp:include>

<div class="d-flex pt-5 mt-5">
  <div class="flex-fill w-25"></div>
  <h2 class="flex-fill w-50" style="text-align: center;"><b>게시판 목록</b></h2>
  <form class="flex-fill w-25 pr-5 mr-5">
    <select name="orderBy" onchange="this.form.submit()">
      <option value="latest">최신순</option>
      <option value="oldest">과거순</option>
      <option value="views">조회순</option>
    </select>
    <select name="maxRecordsPerPage" onchange="this.form.submit()">
      <option value="5">5개씩 보기</option>
      <option value="10">10개씩 보기</option>
      <option value="15">15개씩 보기</option>
      <option value="20">20개씩 보기</option>
      <option value="30">30개씩 보기</option>
      <option value="40">40개씩 보기</option>
      <option value="50">50개씩 보기</option>
    </select>
  </form>
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

      <% ArrayList<Board> boards = (ArrayList<Board>) request.getAttribute("boards");
        for (int i = 0; i < boards.size(); i++) { %>

      <%--  id가 의미하는건 boards.get(i).getId() 이다.
            동적인 데이터, 값을 넣고 싶을 때에는 <%%> 로 넣어준다. --%>
      <tr>
        <th scope="row"><%= boards.get(i).getId() %></th>
        <td><a href="/board/detail?id=<%= boards.get(i).getId()%>"><%= boards.get(i).getTitle() %></a></td>
        <td><%= boards.get(i).getWriter() %></td>
        <td><%= boards.get(i).getCreatedAt().format(DateTimeFormatter.ofPattern("YYYY-MM-DD")) %></td>
        <td><%= boards.get(i).getViewCount() %></td>
        <td><%= boards.get(i).getCommentCount() %></td>
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
          <%--            previous와 next 를 동적인 값으로 바꿔보자          --%>
          <%--리턴값이 오브젝트여서 (Pagination) 으로 형변환--%>
          <%-- if (isHasNext) 가 트루 면 활성화다. disabled가 아니다.--%>
          <%
            if (pagination.isHasPrev()) {
          %>
          <li class="page-item">
            <a class="page-link" href="/board/list?page=<%=pagination.getStartPageOnScreen()-1%><%=params%>" tabindex="-1" aria-disabled="true">Previous</a>
          </li>
          <%} else {%>
          <li class="page-item disabled">
            <a class="page-link" href="/board/list?page=<%=pagination.getStartPageOnScreen()-1%><%=params%>" tabindex="-1" aria-disabled="true">Previous</a>
          </li>
          <%}%>

          <%
            for(int i = pagination.getStartPageOnScreen(); i <= pagination.getEndPageOnScreen(); i++) {
              if(pagination.getPage() == i ) {
          %>
          <li class="page-item"><a class="page-link active" href="/board/list?page=<%=i%><%=params%>"><%=i%></a></li>
          <%} else {%>
          <li class="page-item"><a class="page-link" href="/board/list?page=<%=i%><%=params%>"><%=i%></a></li>
          <%}}%>

          <%
            if (pagination.isHasNext()) {
          %>
          <li class="page-item">
            <a class="page-link" href="/board/list?page=<%=pagination.getEndPageOnScreen()+1%><%=params%>">Next</a>
          </li>
          <%} else {%>
          <li class="page-item disabled">
            <a class="page-link" href="/board/list?page=<%=pagination.getEndPageOnScreen()+1%><%=params%>">Next</a>
          </li>
          <%}%>

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