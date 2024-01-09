package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

public class BoardJdbcDao implements BoardDao{

    private static final BoardJdbcDao instance = new BoardJdbcDao();

    public static BoardJdbcDao getInstance() {
        return instance;
    }


    public Connection connectDB () {
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
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null; // 구현체가 똑같이 맞춰줘야 한다. (Arraylist~ 타입으로)

        ArrayList<Board> boards = new ArrayList<>();

        try {
            connection = connectDB(); // 반환하는 값 : connectDB() 가 connection 변수에 잘 들어와야 한다.
            String sql = "SELECT * FROM board";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) { // 반복문
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
    public Board getById(Long id) {
        return null;
    }

    @Override
    public void save(Board board) {

    }

    @Override
    public void update(Board board) {

    }

    @Override
    public void delete(Board board) {

    }
}
