package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;

@Repository
public class GuestBookDao {

	@Autowired
	private DataSource dataSource;
	
	// 0. import java.sql.*;
		private Connection conn = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;

		

		private void getConnection() {
			try {
				

				// 2. Connection 얻어오기
				conn = dataSource.getConnection();
				// System.out.println("접속성공");

			}  catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		public void close() {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	
		
		//등록리스트 가져오기---------------------------------------- 늘 안되는 곳을 
		public List<GuestBookVo> getGuestList() {
			
			//리스트 생성하기
			List<GuestBookVo> guestList = new ArrayList<GuestBookVo>();
			
			this.getConnection();
			
			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = "";
				query += " select guestbook_id, ";
				query += "        name, ";
				query += "        password, ";
				query += "        content, ";
				query += "        reg_date ";
				query += " from guestbook ";
				query += " order by guestbook_id asc ";

				
				pstmt = conn.prepareStatement(query);

				rs = pstmt.executeQuery();

				// 4.결과처리
				while (rs.next()) {
					int no = rs.getInt("guestbook_id");
					String name = rs.getString("name");
					String password = rs.getString("password");
					String content = rs.getString("content");
					String regdate = rs.getString("reg_date");

					GuestBookVo guestBookVo = new GuestBookVo(no, name, password, content, regdate);

					guestList.add(guestBookVo);
				}

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

			this.close();
			
			return guestList;
			
		}
		
		
		//방명록 등록하기-----------------------------------------------------
		public int guestInsert(GuestBookVo guestBookVo) {
			
			int count = 0;
			
			this.getConnection();
			
			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = "";
				query += " INSERT INTO guestbook ";
				query += " VALUES(seq_guestbook_id.nextval, ?, ?, ?, sysdate) ";

				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, guestBookVo.getName());
				pstmt.setString(2, guestBookVo.getPassword());
				pstmt.setString(3, guestBookVo.getContent());
				

				count = pstmt.executeUpdate();

				// 4.결과처리
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			this.close();
			
			return count;
		}
		
		
		// 방명록 삭제하기*********************************************************
		public int guestDelete(int no) {

			int count = -1;

			getConnection();

			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = "";
				query += " DELETE FROM guestbook ";
				query += " WHERE guestbook_id = ? ";

				pstmt = conn.prepareStatement(query);

				pstmt.setInt(1, no);

				count = pstmt.executeUpdate();

				// 4.결과처리

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			close();

			return count;

		}
		
		
		//Delete 메소드 좋은방법
		public int goodDelete(int no, String password) {

			int count = -1;

			getConnection();

			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = "";
				query += " DELETE FROM guestbook ";
				query += " WHERE guestbook_id = ? ";
				query += " and password = ? ";

				pstmt = conn.prepareStatement(query);

				pstmt.setInt(1, no);
				pstmt.setString(2, password);
				
				
				count = pstmt.executeUpdate();

				// 4.결과처리

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			close();

			return count;

		}
		
}
