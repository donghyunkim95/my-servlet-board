package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.Service.BoardService;
import com.kitri.myservletboard.data.Board;

import java.util.ArrayList;

// 인터페이스로 해야 수정이 쉽다.
public interface BoardDao {

    public ArrayList<Board> getAll ();

    public Board getById (Long id); // board를 하나만 가져오는 것

    public void save(Board board);

    public void update(Board board);

    public void delete(Board board);
}

