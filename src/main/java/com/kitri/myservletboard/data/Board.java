package com.kitri.myservletboard.data;

import java.time.LocalDateTime;

public class Board {
    // 게시판 글들 : 객체로 만들면 된다.
    // 게시판 : id (식별자) 제목 내용 작성자 작성일자 조회수 댓글수

        private Long id;
        private String title;
        private String content;
        private String writer;
        private LocalDateTime createdAt;
        private int viewCount;
        private int commentCount;

        // 생성자 만들기
        public Board(Long id, String title, String content, String writer, LocalDateTime createdAt, int viewCount, int commentCount) {
                this.id = id;
                this.title = title;
                this.content = content;
                this.writer = writer;
                this.createdAt = createdAt;
                this.viewCount = viewCount;
                this.commentCount = commentCount;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public String getWriter() {
                return writer;
        }

        public void setWriter(String writer) {
                this.writer = writer;
        }

        public LocalDateTime getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
                this.createdAt = createdAt;
        }

        public int getViewCount() {
                return viewCount;
        }

        public void setViewCount(int viewCount) {
                this.viewCount = viewCount;
        }

        public int getCommentCount() {
                return commentCount;
        }

        public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
        }
}
