<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
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
<body class="sb-nav-fixed">
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

<main class="mt-5 pt-5">
    <div class="container-fluid px-4 ">

        <div class="card mb-4 w-50 mx-auto">
            <div>
                <h2 class="mt-3" style="text-align: center;"><b>게시판 상세</b></h2>
                <hr class="mb-0">
            </div>
            <div class="d-flex flex-column" style="height: 500px;">
                <div class="p-2 border-bottom border-dark-subtle d-flex flex-row-reverse">
                    <div class="pd opacity-75 bg-info-subtle border border-dark rounded-pill"><small>조회수 : ${board.getViewCount()}</small></div>
                    &nbsp
                    <div class="pd opacity-75 bg-success-subtle border border-dark rounded-pill"><small>댓글수 : ${board.getCommentCount()}</small></div>
                    <div class="d-flex flex-row flex-fill">
                        <div class="pd opacity-75 border border-dark rounded-pill">#${board.getId()}</div>
                    </div>
                </div>
                <div class="p-2 border-bottom">
                    <b>${board.getTitle()}</b>
                </div>
                <div class="p-2 border-bottom">
                    <b>저자 :</b> ${board.getWriter()}
                </div>
                <div class="p-2 border-bottom">
                    <b>등록일시 :</b> ${board.getCreatedAt()}
                </div>
                <div class="m-3 h-75">
                    <textarea class="h-100 form-control bg-white" id="content" name="content"
                              disabled>${board.getContent()}</textarea>
                </div>
                <div class="d-flex flex-row-reverse mb-3 mr-3">
                    &nbsp
                    &nbsp
                    <a href="/board/delete?id=${board.getId()}" class="btn btn-secondary btn-sm" onclick="return confirm('삭제하시겠습니까?')"><small>삭제하기</small></a>
                    &nbsp
                    <a href="/board/updateForm?id=${board.getId()}" class="btn btn-secondary btn-sm"><small>수정하기</small></a>
                    &nbsp
                    <a href="/board/list" class="btn btn-secondary btn-sm"><small>목록으로</small></a>
                    &nbsp
                </div>
            </div>
        </div>
    </div>
</main>
</body>
<style>
    .pd {
        padding-left: 5px;
        padding-right: 5px;
    }
</style>
</html>