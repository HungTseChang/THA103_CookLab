package com.cooklab.question_group.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;

public class QuestionGroupJDBCDDAOIm implements QuestionGroupDAO {
	private static final String INSERT_STMT = "insert into question_group(question_name) values (?)";
	private static final String UPDATE = "update question_group set question_name =?";
	private static final String DELETE = "delete from question_group where question_group_no = ?";
	private static final String GET_ONE_STMT = "select question_group_no,  question_name, created_timestamp FROM question_group where question_group_no = ?";
	private static final String GET_ALL_STMT = "select question_group_no, question_name, created_timestamp FROM question_group order by question_group_no";

	public void insert(QuestionGroupVO questionGroup) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, questionGroup.getQuestionName());
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

	public void update(QuestionGroupVO questionGroup) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, questionGroup.getQuestionName());
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

	public void delete(Integer questionGroupNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, questionGroupNo);

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

	public QuestionGroupVO findByPrimaryKey(Integer questionGroupNo) {
		QuestionGroupVO questionGroupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, questionGroupNo);

			rs = pstmt.executeQuery();

			// 放入對應的MySQL表格欄位名稱
			while (rs.next()) {
				questionGroupVO = new QuestionGroupVO();
				questionGroupVO.setQuestionGroupNo(rs.getInt("Question_group_no"));
				questionGroupVO.setQuestionName(rs.getString("Question_name"));
				questionGroupVO.setCreatedTimestamp(rs.getDate("Created_timestamp"));

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
		return questionGroupVO;
	}

	public List<QuestionGroupVO> getAll() {
		List<QuestionGroupVO> list = new ArrayList<QuestionGroupVO>();
		QuestionGroupVO qVO = null;

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
				qVO = new QuestionGroupVO();
				qVO.setQuestionGroupNo(rs.getInt("Question_group_no"));
				qVO.setQuestionName(rs.getString("Question_name"));
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

		QuestionGroupJDBCDDAOIm QuGDAOIm = new QuestionGroupJDBCDDAOIm();

//	 新增
//		QuestionGroupVO QuGVO1 = new QuestionGroupVO();
//
//		QuGVO1.setQuestionName(String.valueOf("我有問題"));
//
//		QuGDAOIm.insert(QuGVO1);

		// 修改
//		QuestionGroupVO QuGVO2 = new QuestionGroupVO();
//
//		QuGVO2.setQuestionName(String.valueOf("我沒問題"));
//
//		QuGDAOIm.insert(QuGVO2);

		// 刪除
//		QuGDAOIm.delete(4);

		// 查詢單一資料
//		QuestionGroupVO QuGVO3 = QuGDAOIm.findByPrimaryKey(1);
//		System.out.print(QuGVO3.getQuestionGroupNo() + ",");
//		System.out.print(QuGVO3.getQuestionName() + ",");
//		System.out.print(QuGVO3.getCreatedTimestamp() + ",");
//
//		System.out.println("---------------------");

		// 查詢全部資料

		List<QuestionGroupVO> list = QuGDAOIm.getAll();
		for (QuestionGroupVO aQuGVO3 : list) {
			System.out.print(aQuGVO3.getQuestionGroupNo() + ",");
			System.out.print(aQuGVO3.getQuestionName() + ",");
			System.out.print(aQuGVO3.getCreatedTimestamp() + ",");

			System.out.println();
		}
	}

}
