package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;



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

    @Override // 게시글 목록 확인
    public ArrayList<Board> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null; // 구현체가 똑같이 맞춰줘야 한다. (Arraylist~ 타입으로)

        ArrayList<Board> boards = new ArrayList<>();

        try {
            connection = connectDB(); // 반환하는 값 : connectDB() 가 connection 변수에 잘 들어와야 한다.
            String sql = "SELECT * FROM board";
            ps = connection.prepareStatement(sql);
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
}
