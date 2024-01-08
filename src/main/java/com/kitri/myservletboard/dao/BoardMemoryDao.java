package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.Service.BoardService;
import com.kitri.myservletboard.data.Board;

import java.time.LocalDateTime;
import java.util.ArrayList;

//생명주기 담당하는 memory
// memory 관련 : map
public class BoardMemoryDao implements BoardDao {

    private static final BoardMemoryDao instance = new BoardMemoryDao();

    public static BoardMemoryDao getInstance() {
        return instance;
    }

    ArrayList<Board> memoryBoardDB = new ArrayList<>();


    private BoardMemoryDao() {
        memoryBoardDB.add(new Board (1L, "첫번째 글입니다.", "반갑습니다.", "손흥민", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board (2L, "두번째 글입니다.", "안녕하세요.", "이강인", LocalDateTime.now(), 10, 3));
        memoryBoardDB.add(new Board (3L, "세번째 글입니다.", "잘가세요.", "박현오", LocalDateTime.now(), 10, 5));
        memoryBoardDB.add(new Board (4L, "네번째 글입니다.", "또 뵙네요.", "박세한", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board (5L, "다섯번째 글입니다.", "수고하세요.", "오시현", LocalDateTime.now(), 10, 2));
        memoryBoardDB.add(new Board (6L, "여섯번째 글입니다.", "감사합니다.", "김성실", LocalDateTime.now(), 10, 4));
        memoryBoardDB.add(new Board (7L, "일곱번째 글입니다.", "고맙습니다.", "김미성", LocalDateTime.now(), 10, 2));
        memoryBoardDB.add(new Board (8L, "여덟번째 글입니다.", "재밌습니다.", "박준혁", LocalDateTime.now(), 10, 6));
        memoryBoardDB.add(new Board (9L, "아홉번째 글입니다.", "즐겁습니다.", "주나영", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board (10L, "열번째 글입니다.", "안녕히가세요.", "박민선", LocalDateTime.now(), 10, 1));
    }

    @Override
    public ArrayList<Board> getAll() {
        return memoryBoardDB;
    }

    @Override
    public Board getById(Long id) {
        return memoryBoardDB.stream().filter(board -> {
            return board.getId() == id;
        }).findFirst().get();
    }


    @Override
    public void save(Board board) {

        // id 자동생성 로직을 구현해보자. (단, id가 기존의 id와 중복되지 않도록)
        // arraylist에 없는 값을 하나만 생성하면 된다.

        // if (memoryBoardDB 에 등록되지 않았다면) {
        // id 번호를 추가로 부여한다. }

        Long id= 0L;
        boolean flag = false;
        while (!flag) {
            flag = true;
            id++; // 1씩 증가 2, 3, ... 10, 11
            for (Board board_ : memoryBoardDB) {
                if (id == board_.getId()) {
                    // 중복
                    flag = false;
                    break;
                }
            }
        }
        board.setId(id);
        memoryBoardDB.add(board);
    }


    @Override
    public void update(Board board) {
        Board board_ = getById(board.getId());
        memoryBoardDB.remove(board_);
        memoryBoardDB.add(board);
    }

    @Override
    public void delete(Board board) {
        memoryBoardDB.remove(board);
    }
}
