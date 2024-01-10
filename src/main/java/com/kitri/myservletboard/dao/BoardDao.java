package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.Service.BoardService;
import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;

import java.util.ArrayList;

// 인터페이스로 해야 수정이 쉽다.
public interface BoardDao {

//    public ArrayList<Board> getAll ();

    // 게시글 목록 확인
//    ArrayList<Board> getAll(Pagination pagination);

    // 게시글 목록 확인
    ArrayList<Board> getAll();
    ArrayList<Board> getAll(Pagination pagination);

    public Board getById (Long id); // board를 하나만 가져오는 것

    public void save(Board board);

    public void update(Board board);

    public void delete(Board board);
}

