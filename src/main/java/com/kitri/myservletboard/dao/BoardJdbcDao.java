package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;
import com.kitri.myservletboard.data.SearchData;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class BoardJdbcDao implements BoardDao {

    private static final BoardJdbcDao instance = new BoardJdbcDao();

    public static BoardJdbcDao getInstance() {
        return instance;
    }


    public Connection connectDB() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/my-servlet-board";
            String user = "root";
            String pwd = "zaqxsw123";

            conn = DriverManager.getConnection(url, user, pwd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public ArrayList<Board> getAll() {
        return null;
    }

    @Override // 게시글 목록 확인
    public ArrayList<Board> getAll(Pagination pagination) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null; // 구현체가 똑같이 맞춰줘야 한다. (Arraylist~ 타입으로)

        ArrayList<Board> boards = new ArrayList<>();

        try {
            connection = connectDB(); // 반환하는 값 : connectDB() 가 connection 변수에 잘 들어와야 한다.
            String sql = "SELECT * FROM board LIMIT ?,?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (pagination.getPage() -1) * pagination.getMaxRecordsPerPage()); // startIndex
            ps.setInt(2, (pagination.getMaxRecordsPerPage()));
            // ★ -1 하는 이유 : 페이지 정보를 받잖아요? 받은 페이지정보를 통해 ? 인 동적 값을 치환해준다.
            // 1페이지가 왓을때에는 0.10 / 2페이지가 왓을 때에는 10.10 로 만들어줘야한다.
            // 1페이지가 왓을때 -1 안해주면 1*10 이 되서 2페이지가 불러온다.
            // 1페이지가 왓을 때 0을 만들어주기 위해 -1 을 해준것이다.

            rs = ps.executeQuery();

            while (rs.next()) { // 반복문
                // 데이터를 컬럼 단위로 읽는다.
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");

                boards.add(new Board(id, title, content, writer, createdAt, viewCount, commentCount));
            }
//            if (connection == null) {
//                System.out.println("failed");
//            }
//            System.out.println("success");
        } catch (Exception e) {

        } finally {
            // catch 가 되던 정상작동이 되던 무조건 실행하는 것 : finally
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return boards; // arraylist를 return 해준다.
    }

    @Override
    public ArrayList<Board> getAll(String type, String keyword, Pagination pagination) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null; // 구현체가 똑같이 맞춰줘야 한다. (Arraylist~ 타입으로)

        ArrayList<Board> boards = new ArrayList<>();

        if (type == null) {
            type = "title";
        }
        if (keyword == null) {
            keyword = "";
        }


        try {
            connection = connectDB(); // 반환하는 값 : connectDB() 가 connection 변수에 잘 들어와야 한다.
            String sql = "SELECT * FROM board WHERE " + type + " LIKE '%" + keyword + "%' LIMIT ?,?;";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (pagination.getPage() -1) * pagination.getMaxRecordsPerPage()); // startIndex
            ps.setInt(2, (pagination.getMaxRecordsPerPage()));
            rs = ps.executeQuery();

            while (rs.next()) { // 반복문
                // 데이터를 컬럼 단위로 읽는다.
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");

                boards.add(new Board(id, title, content, writer, createdAt, viewCount, commentCount));
            }

        } catch (Exception e) {

        } finally {
            // catch 가 되던 정상작동이 되던 무조건 실행하는 것 : finally
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return boards; // arraylist를 return 해준다.
    }

    @Override
    public ArrayList<Board> getAll(String period, String type, String keyword, Pagination pagination) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null; // 구현체가 똑같이 맞춰줘야 한다. (Arraylist~ 타입으로)

        ArrayList<Board> boards = new ArrayList<>();
        SearchData searchData = new SearchData(); // ★ 메서드 호출

        try {
            connection = connectDB(); // 반환하는 값 : connectDB() 가 connection 변수에 잘 들어와야 한다.

            String sql = sql = "SELECT * FROM board WHERE " + type + " LIKE '%" + keyword + "%' AND created_at BETWEEN DATE_ADD(NOW(), INTERVAL -"
                          + searchData.getDate(period) + " DAY ) AND NOW() LIMIT ?,?;";

            ps = connection.prepareStatement(sql);
            ps.setInt(1, (pagination.getPage() -1) * pagination.getMaxRecordsPerPage()); // startIndex
            ps.setInt(2, (pagination.getMaxRecordsPerPage()));
            rs = ps.executeQuery();

            while (rs.next()) { // 반복문
                // 데이터를 컬럼 단위로 읽는다.
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");

                boards.add(new Board(id, title, content, writer, createdAt, viewCount, commentCount));
            }

        } catch (Exception e) {

        } finally {
            // catch 가 되던 정상작동이 되던 무조건 실행하는 것 : finally
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return boards; // arraylist를 return 해준다.
    }

    @Override // 상세페이지 확인
    public Board getById(Long id) {

        // connection
        // ps -> executeQuery();
        // rs
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null; // 구현체가 똑같이 맞춰줘야 한다. (Arraylist~ 타입으로)

        Board board = null;

        try {
            connection = connectDB(); // 반환하는 값 : connectDB() 가 connection 변수에 잘 들어와야 한다.
            String sql = "SELECT * FROM board where id=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            while (rs.next()) { // 반복문
                // 데이터를 컬럼 단위로 읽는다.
                Long id_ = rs.getLong("id");
                String title_ = rs.getString("title");
                String content_ = rs.getString("content");
                String writer_ = rs.getString("writer");
                LocalDateTime createdAt_ = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount_ = rs.getInt("view_count");
                int commentCount_ = rs.getInt("comment_count");

                board = new Board(id_, title_, content_, writer_, createdAt_, viewCount_, commentCount_);
            }

        } catch (Exception e) {

        } finally {
            // catch 가 되던 정상작동이 되던 무조건 실행하는 것 : finally
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return board;
    }

    @Override // 게시글 등록
    public void save(Board board) {

        // mySQL에서 ㅁAI 체크했었음 -> DB 한테 자동으로 채우라고 위임해주는 것
        // ★ 따라서 id 를 따로 줄 필요가 없다.
        // 제목 작성자 내용 만 넣어주면 된다.
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = connectDB();
            String sql = "INSERT INTO board (title, content, writer) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setString(3, board.getWriter());
            ps.executeUpdate();

        } catch (Exception e) {

        } finally {
            // catch 가 되던 정상작동이 되던 무조건 실행하는 것 : finally
            try {
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Board board) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = connectDB();
            String sql = "UPDATE board SET title=?, content=? WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setLong(3, board.getId());
            ps.executeUpdate();

        } catch (Exception e) {

        } finally {
            // catch 가 되던 정상작동이 되던 무조건 실행하는 것 : finally
            try {
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Board board) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = connectDB();
            String sql = "DELETE FROM board WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, board.getId());
            ps.executeUpdate();

        } catch (Exception e) {

        } finally {
            // catch 가 되던 정상작동이 되던 무조건 실행하는 것 : finally
            try {
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int count () {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null; // 구현체가 똑같이 맞춰줘야 한다. (Arraylist~ 타입으로)

        int count = 0; // int : 갯수로 받는다, int count =0 초기값을 정해줌

        try {
            connection = connectDB(); // 반환하는 값 : connectDB() 가 connection 변수에 잘 들어와야 한다.
            String sql = "SELECT count(*) FROM board";

            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            // count 결과를 보고싶다면?
            rs.next(); // count 를 읽음 (66)
            // key 를 읽을 것이니까.
            count = rs.getInt("count(*)");   // 문자열을 int로 바꾸고 count로 넣어줌


        } catch (Exception e) {

        } finally {
            // catch 가 되던 정상작동이 되던 무조건 실행하는 것 : finally
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    public int searchCount (String type, String keyword) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null; // 구현체가 똑같이 맞춰줘야 한다. (Arraylist~ 타입으로)

        int count = 0; // int : 갯수로 받는다, int count =0 초기값을 정해줌

        // type은 안 넣어줘도 된다.


        try {
            String sql = null;
            connection = connectDB(); // 반환하는 값 : connectDB() 가 connection 변수에 잘 들어와야 한다.
            if (keyword == null) {
                sql = "SELECT COUNT(*) FROM board";
            } else {
                sql = "SELECT COUNT(*) FROM board WHERE " + type + " LIKE '%" + keyword + "%'";
            }

            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            // count 결과를 보고싶다면?
            rs.next(); // count 를 읽음 (66) // 자바에서 DB 결과를 한줄 읽음
            // key 를 읽을 것이니까.
            count = rs.getInt("count(*)");   // 문자열을 int로 바꾸고 count로 넣어줌


        } catch (Exception e) {

        } finally {
            // catch 가 되던 정상작동이 되던 무조건 실행하는 것 : finally
            try {
                rs.close();
                    ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return count;
    }
}



