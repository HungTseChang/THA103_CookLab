package com.cooklab.question.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;

public class QuestionJDBCDDAOIm implements QuestionDAO {
	private static final String INSERT_STMT = "insert into question(question_group_no, question_title, question_content, question_good, question_bad) values (?, ?, ?, ?, ?)";
	private static final String UPDATE = "update question set question_group_no =?,question_title =?, question_content =?, question_good =? , question_bad =? where question_no = ?";
	private static final String DELETE = "delete from question where question_no = ?";
	private static final String GET_ONE_STMT = "select question_no, question_group_no, question_title, question_content, question_good, question_bad, created_timestamp FROM question where question_no = ?";
	private static final String GET_ALL_STMT = "select question_no, question_group_no, question_title, question_content, question_good, question_bad, created_timestamp FROM question order by question_no";

	public void insert(QuestionVO question) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, question.getQuestionGroupNo());
			pstmt.setString(2, question.getQuestionTitle());
			pstmt.setString(3, question.getQuestionContent());
			pstmt.setInt(4, question.getQuestionGood());
			pstmt.setInt(5, question.getQuestionBad());

			pstmt.executeUpdate();

			System.out.println("新增資料成功");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	public void update(QuestionVO question) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, question.getQuestionGroupNo());
			pstmt.setString(2, question.getQuestionTitle());
			pstmt.setString(3, question.getQuestionContent());
			pstmt.setInt(4, question.getQuestionGood());
			pstmt.setInt(5, question.getQuestionBad());
			pstmt.executeUpdate();

			System.out.println("更新資料成功");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	public void delete(Integer questionNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, questionNo);

			pstmt.executeUpdate();

			System.out.println("刪除資料成功");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	public QuestionVO findByPrimaryKey(Integer questionNo) {
		QuestionVO questionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, questionNo);

			rs = pstmt.executeQuery();

			// 放入對應的MySQL表格欄位名稱
			while (rs.next()) {
				questionVO = new QuestionVO();
				questionVO.setQuestionNo(rs.getInt("Question_no"));
				questionVO.setQuestionGroupNo(rs.getInt("Question_group_no"));
				questionVO.setQuestionTitle(rs.getString("Question_title"));
				questionVO.setQuestionContent(rs.getString("Question_content"));
				questionVO.setQuestionGood(rs.getInt("Question_good"));
				questionVO.setQuestionBad(rs.getInt("Question_bad"));
				questionVO.setCreatedTimestamp(rs.getDate("Created_timestamp"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return questionVO;
	}

	public List<QuestionVO> getAll() {
		List<QuestionVO> list = new ArrayList<QuestionVO>();
		QuestionVO qVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			// 放入對應的MySQL表格欄位名稱
			while (rs.next()) {
				qVO = new QuestionVO();
				qVO.setQuestionNo(rs.getInt("Question_no"));
				qVO.setQuestionGroupNo(rs.getInt("Question_group_no"));
				qVO.setQuestionTitle(rs.getString("Question_title"));
				qVO.setQuestionContent(rs.getString("Question_content"));
				qVO.setQuestionGood(rs.getInt("Question_good"));
				qVO.setQuestionBad(rs.getInt("Question_bad"));
				qVO.setCreatedTimestamp(rs.getDate("Created_timestamp"));
				list.add(qVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		QuestionJDBCDDAOIm QuDAOIm = new QuestionJDBCDDAOIm();

//		 新增
//		QuestionVO QuVO1 = new QuestionVO();
//		QuVO1.setQuestionGroupNo(Integer.valueOf(1));
//		QuVO1.setQuestionTitle(String.valueOf("我有問題"));
//		QuVO1.setQuestionContent(String.valueOf("問題在這"));
//		QuVO1.setQuestionGood(Integer.valueOf(3));
//		QuVO1.setQuestionBad(Integer.valueOf(4));
//		
//		
//		QuDAOIm.insert(QuVO1);

		// 修改
//		QuestionVO QuVO2 = new QuestionVO();
//		QuVO2.setQuestionGroupNo(Integer.valueOf(2));
//		QuVO2.setQuestionTitle(String.valueOf("我沒有問題"));
//		QuVO2.setQuestionContent(String.valueOf("問題不在這"));
//		QuVO2.setQuestionGood(Integer.valueOf(4));
//		QuVO2.setQuestionBad(Integer.valueOf(5));
//		QuDAOIm.insert(QuVO2);

		// 刪除
//		QuDAOIm.delete(4);

		// 查詢單一資料
//		QuestionVO QuVO3 = QuDAOIm.findByPrimaryKey(2);
//		System.out.print(QuVO3.getQuestionNo() + ",");
//		System.out.print(QuVO3.getQuestionGroupNo() + ",");
//		System.out.print(QuVO3.getQuestionTitle() + ",");
//		System.out.println(QuVO3.getQuestionContent() + ",");
//		System.out.println(QuVO3.getQuestionGood() + ",");
//		System.out.println(QuVO3.getQuestionBad() + ",");
//		System.out.println(QuVO3.getCreatedTimestamp() + ",");
//		
//		System.out.println("---------------------");

		// 查詢全部資料
		List<QuestionVO> list = QuDAOIm.getAll();
		for (QuestionVO aQuVO3 : list) {
			System.out.print(aQuVO3.getQuestionNo() + ",");
			System.out.print(aQuVO3.getQuestionGroupNo() + ",");
			System.out.print(aQuVO3.getQuestionTitle() + ",");
			System.out.print(aQuVO3.getQuestionContent() + ",");
			System.out.print(aQuVO3.getQuestionGood() + ",");
			System.out.print(aQuVO3.getQuestionBad() + ",");
			System.out.println(aQuVO3.getCreatedTimestamp() + ",");
		}
	}
}