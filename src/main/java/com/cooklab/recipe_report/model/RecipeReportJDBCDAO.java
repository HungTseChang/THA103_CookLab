package com.cooklab.recipe_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.Util;



public class RecipeReportJDBCDAO implements RecipeReoprtDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO recipe_report (member_id, recipe_no, reporting_reason, reporting_status)VALUES (?, ?, ?, ? )";
	private static final String GET_ALL_STMT = "SELECT recipe_report_no, member_id, recipe_no, reporting_reason, reporting_status, created_timestamp From recipe_report order by recipe_report_no";
	private static final String GET_ONE_STMT = "SELECT recipe_report_no,member_id,recipe_no,reporting_reason,reporting_status,created_timestamp From recipe_report where recipe_report_no=?";
	private static final String DELETE = "DELETE FROM recipe_report where recipe_report_no = ?";
	private static final String UPDATE = "UPDATE recipe_report set member_id=?, recipe_no=?, reporting_reason=?, reporting_status=?  where recipe_report_no=?";

	@Override
	public void insert(RecipeReportVO recipeReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, recipeReportVO.getMemberId());
			pstmt.setInt(2, recipeReportVO.getRecipeNo());
			pstmt.setString(3, recipeReportVO.getReportingReason());
			pstmt.setInt(4, recipeReportVO.getReportingStatus());

			pstmt.executeUpdate();

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

	@Override
	public void update(RecipeReportVO recipeReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, recipeReportVO.getMemberId());
			pstmt.setInt(2, recipeReportVO.getRecipeNo());
			pstmt.setString(3, recipeReportVO.getReportingReason());
			pstmt.setInt(4, recipeReportVO.getReportingStatus());
			pstmt.setInt(5, recipeReportVO.getRecipeReportNo());

			pstmt.executeUpdate();

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

	@Override
	public void delete(Integer recipeReportNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, recipeReportNo);

			pstmt.executeUpdate();

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

	@Override
	public RecipeReportVO findByPrimaryKey(Integer recipeReportNo) {
		RecipeReportVO recipeReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, recipeReportNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				recipeReportVO = new RecipeReportVO();
				recipeReportVO.setRecipeReportNo(rs.getInt("recipe_report_no"));
				recipeReportVO.setMemberId(rs.getInt("member_id"));
				recipeReportVO.setRecipeNo(rs.getInt("recipe_no"));
				recipeReportVO.setReportingReason(rs.getString("reporting_reason"));
				recipeReportVO.setReportingStatus(rs.getInt("reporting_status"));
				recipeReportVO.setCredcreatedTimestamp(rs.getTimestamp("created_timestamp"));
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
		return recipeReportVO;
	}

	@Override
	public List<RecipeReportVO> getAll() {
		List<RecipeReportVO> list = new ArrayList<RecipeReportVO>();
		RecipeReportVO recipeReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recipeReportVO = new RecipeReportVO();
				recipeReportVO.setRecipeReportNo(rs.getInt("recipe_report_no"));
				recipeReportVO.setMemberId(rs.getInt("member_id"));
				recipeReportVO.setRecipeNo(rs.getInt("recipe_no"));
				recipeReportVO.setReportingReason(rs.getString("reporting_reason"));
				recipeReportVO.setReportingStatus(rs.getInt("reporting_status"));
				recipeReportVO.setCredcreatedTimestamp(rs.getTimestamp("created_timestamp"));
				list.add(recipeReportVO); // Store the row in the list
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

		RecipeReportJDBCDAO dao = new RecipeReportJDBCDAO();
		// ���J
//		RecipeReportVO recipeReportVO = new RecipeReportVO(2, 4, "�����U�b�@�_�ܼ������Y�F",1);
//		dao.insert(recipeReportVO);
		// ��s
//		RecipeReportVO recipeReportVO = new RecipeReportVO(1, 1, "�����U�b�@�_���Aqoq",1);
//		recipeReportVO.setRecipeReportNo(1);
//		dao.update(recipeReportVO);
		// �R��
//		dao.delete(6);

		// �d�@��
		RecipeReportVO recipeReportVO = dao.findByPrimaryKey(1);
		System.out.println(recipeReportVO);

		// ������
		List<RecipeReportVO> list = dao.getAll();
		for (RecipeReportVO aRecipeReportVO : list) {
			System.out.println(aRecipeReportVO);

		}
	}
}
