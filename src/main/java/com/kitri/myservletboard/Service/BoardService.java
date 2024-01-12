package com.kitri.myservletboard.Service;

import com.kitri.myservletboard.dao.BoardDao;
import com.kitri.myservletboard.dao.BoardJdbcDao;
import com.kitri.myservletboard.data.Pagination;
import com.kitri.myservletboard.data.Board;

import java.util.ArrayList;

public class BoardService {

    // 게시판 리스트를 가져오는 로직

    //싱글톤으로 만들어보자
//    BoardDao boardDao = BoardMemoryDao.getInstance();
    BoardDao boardDao = BoardJdbcDao.getInstance();

    private BoardService() {};
    private static final BoardService instance = new BoardService();

    public static BoardService getInstance() {
        return instance;
    }

    public Board getBoard(Long id) {
        return boardDao.getById(id); // 실제 데이터는 DAO 에서 불러오니까 DAO 호출해야한다.
    }


    public ArrayList<Board> getBoards (Pagination pagination) {
        pagination.setTotalRecords(((BoardJdbcDao)boardDao).count()); // totalRecords의 값 계산
        // 얘가 되면 이제 계산을 할 수 있게 된다.
        pagination.calcPagination();

        return boardDao.getAll(pagination);
    }

    public ArrayList<Board> getBoards (String type, String keyword, Pagination pagination) {
        pagination.setTotalRecords(((BoardJdbcDao)boardDao).searchCount(type, keyword)); // totalRecords의 값 계산
        // 얘가 되면 이제 계산을 할 수 있게 된다.
        pagination.calcPagination();
        return boardDao.getAll(type, keyword, pagination);
    }

    public ArrayList<Board> getBoards (String period, String type, String keyword, Pagination pagination) {
        pagination.setTotalRecords(((BoardJdbcDao)boardDao).searchCount(type, keyword)); // totalRecords의 값 계산
        // 얘가 되면 이제 계산을 할 수 있게 된다.
        pagination.calcPagination();
        return boardDao.getAll(period, type, keyword, pagination);
    }

    public void addBoard(Board board) {
    //Board board가 필요한 이유 : 데이터를 넣기 위함
        boardDao.save(board);
    }

    public void updateBoard(Board board) {
        boardDao.update(board);
    }

    public void deleteBoard(Board board) {
        boardDao.delete(board);
    }
}
