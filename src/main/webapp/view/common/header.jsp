<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <header>
    <a class="logo" href="/board/list"><span class="material-symbols-outlined">clear_day</span></a>
    <nav>
        <ul class="nav-items">
            <li><a href="/board/createForm">게시글등록</a></li>
            <li><a href="/view/member/join.jsp">회원가입</a></li>
            <li><a href="/view/member/registration.jsp">회원정보수정</a></li>
            <li><a href="/view/member/login.jsp">로그인</a></li>
            <%--        무조건 name 설정을 준 속성들이 나갈수 있다. type, keyword       --%>
            <form class="d-flex" role="search" action="/board/list">
                <select name="date">
                    <option value="totalDate">전체기간</option>
                    <option value="1"${param.created_at == "1" ? "selected" : ""}>1일</option>
                    <option value="7"${param.created_at == "7" ? "selected" : ""}>7일</option>
                    <option value="30"${param.created_at == "30" ? "selected" : ""}>1달</option>
                    <option value="180"${param.created_at == "180" ? "selected" : ""}>6개월</option>
                    <option value="365"${param.created_at == "365" ? "selected" : ""}>1년</option>

                </select>
                <select name="type">
                    <option value="title"${param.type == "title" ? "selected" : ""}>제목</option>
                    <option value="writer"${param.type == "writer" ? "selected" : ""}>작성자</option>
                </select>
                &nbsp;
                <input name="keyword" class="form-control me-2" type="search" placeholder="Search" aria-label="Search"
                value="${param.keyword}">
                <button class="btn btn-outline-dark" type="submit">Search</button>
            </form>
        </ul>
    </nav>
</header>

</body>
</html>
