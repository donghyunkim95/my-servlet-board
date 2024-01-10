package com.kitri.myservletboard.data;

public class Pagination {

    private int page; // 페이지를 페이지네이션에 담는다.

    private int maxRecordsPerPage = 10; // 게시글수 (10개)

    private int maxPagesOnScreen = 5; // 페이지수 (5개)

    private int startIndex = 0;
    private int totalRecords = 0; // ★ 전체 갯수

    private boolean hasNext = false;
    private boolean hasPrev = false;


    private int startPageOnScreen =1; // 화면에서 시작페이지는 1번째

    private int endPageOnScreen = this.maxPagesOnScreen; // 마지막 페이지 화면은, 페이지수
    public void calcPagination() {
        // 위의 페이지네이션 정보 계산 하는 메서드
        // 데이터가 많을 때 주위 상황 데이터에 따라 바뀔 것이다.
        // ★ 전체 갯수가 필요하다. 전채 갯수가 있어야 다음 페이지로 넘어갈 지도 정할 수 있고 ... 할 수 있기 때문이다.


        int totalPages = (int)(Math.ceil((double)this.totalRecords / this.maxPagesOnScreen)); // 전체 페이지수 = 전체 게시글 / 10
        // Math.ceil -> double 형식 : 반올림해주는 것
        this.startPageOnScreen =
                ((int)Math.ceil((double) this.page / this.maxPagesOnScreen) -1) * this.maxPagesOnScreen + 1; //화면시작페이지 (1,6,11...)= (현재페이지 (2) / 화면에서 맥스페이지 (5) -1) * 맥스페이지 (5) +1
                                                                                                             // ex) ((2/5)-1) *5 +1  => (2/5) 나온 값 : 0.4 반올림하면 1.
        this.endPageOnScreen =
                this.startPageOnScreen + this.maxPagesOnScreen -1;      // 화면 마지막 페이지 (5) (10).. = 화면 시작페이지 (1, 6, 11...) + 5 -1

        if (this.endPageOnScreen > totalPages) {
            this.endPageOnScreen = totalPages;   // 화면 마지막 페이지 (5) (10)... > 전체페이지수 (9) 면 화면마지막페이지 = 전체페이지수 이다.
        }
        if (this.endPageOnScreen < totalPages) { // 화면마지막페이지 < 전체페이지수 라면 next 버튼 활성화
            this.hasNext = true;
        }
        if (this.startPageOnScreen > this.maxPagesOnScreen) {  // 화면시작페이지 > 화면 마지막페이지 라면 prev 버튼 활성화
            this.hasPrev = true;
        }
    }


    public Pagination() {}

    public Pagination(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public int getMaxRecordsPerPage() {
        return maxRecordsPerPage;
    }

    public void setMaxRecordsPerPage(int maxRecordsPerPage) {
        this.maxRecordsPerPage = maxRecordsPerPage;
    }

    public int getMaxPagesOnScreen() {
        return maxPagesOnScreen;
    }

    public void setMaxPagesOnScreen(int maxPagesOnScreen) {
        this.maxPagesOnScreen = maxPagesOnScreen;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getStartPageOnScreen() {
        return startPageOnScreen;
    }

    public void setStartPageOnScreen(int startPageOnScreen) {
        this.startPageOnScreen = startPageOnScreen;
    }

    public int getEndPageOnScreen() {
        return endPageOnScreen;
    }

    public void setEndPageOnScreen(int endPageOnScreen) {
        this.endPageOnScreen = endPageOnScreen;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrev() {
        return hasPrev;
    }

    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }
}
