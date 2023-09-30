package com.cooklab.recipe_comments_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cooklab.Util.*;



public class RecipeCommentsReportJDBCDAOIm implements RecipeCommentsReportDAO {

	private static final String INSERT_STMT = 
		"INSERT INTO recipe_comments_report (member_id,recipe_comments_no,reporting_comments_reason,reporting_status) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT recipe_report_no, member_id, recipe_comments_no, reporting_comments_reason, reporting_status,created_timestamp FROM recipe_comments_report order by recipe_report_no";
	private static final String GET_ONE_STMT = 
		"SELECT recipe_report_no, member_id, recipe_comments_no, reporting_comments_reason, reporting_status,created_timestamp FROM recipe_comments_report where recipe_report_no = ?";
	private static final String DELETE = 
		"DELETE FROM recipe_comments_report where recipe_report_no = ?";
	private static final String UPDATE = 
		"UPDATE recipe_comments_report set member_id=?, recipe_comments_no=?, reporting_comments_reason=?,  reporting_status=? where recipe_report_no = ?";

	//新增===========================================================================================================
	@Override
	public void insert(RecipeCommentsReportVO recipeCommentsReportVO) {


		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, recipeCommentsReportVO.getMemberId());
			pstmt.setInt(2, recipeCommentsReportVO.getRecipeCommentsNo());
			pstmt.setString(3, recipeCommentsReportVO.getReportingCommentsReason());
//			pstmt.setDate(4, recipeCommentsReportVO.getReportingCommentsTimestamp());
			pstmt.setInt(4, recipeCommentsReportVO.getReportingStatus());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	//修改=================================================================
	@Override
	public void update(RecipeCommentsReportVO recipeCommentsReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, recipeCommentsReportVO.getMemberId());
			pstmt.setInt(2, recipeCommentsReportVO.getRecipeCommentsNo());
			pstmt.setString(3, recipeCommentsReportVO.getReportingCommentsReason());
//			pstmt.setDate(4, recipeCommentsReportVO.getReportingCommentsTimestamp());
			pstmt.setInt(4, recipeCommentsReportVO.getReportingStatus());
			pstmt.setInt(5, recipeCommentsReportVO.getRecipeReportNo());


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	//刪除=====================================================================================
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
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	//用主鍵查詢===========================================================================================================
	@Override
	public RecipeCommentsReportVO findByPrimaryKey(Integer recipeReportNo) {
		
		RecipeCommentsReportVO rcrVO = null;
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
				// empVo 也稱為 Domain objects
				rcrVO = new RecipeCommentsReportVO();
				
				rcrVO.setRecipeReportNo(rs.getInt("recipe_report_no"));
				rcrVO.setMemberId(rs.getInt("member_id"));
				rcrVO.setRecipeCommentsNo(rs.getInt("recipe_comments_no"));
				rcrVO.setReportingCommentsReason(rs.getString("reporting_comments_reason"));
				rcrVO.setReportingStatus(rs.getByte("reporting_status"));
				rcrVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return rcrVO;
	}

	//查詢所有===========================================================================================================
	@Override
	public List<RecipeCommentsReportVO> getAll() {
		List<RecipeCommentsReportVO> list = new ArrayList<RecipeCommentsReportVO>();
		RecipeCommentsReportVO recipeCommentsReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				recipeCommentsReportVO = new RecipeCommentsReportVO();
				recipeCommentsReportVO.setRecipeReportNo(rs.getInt("recipe_report_no"));
				recipeCommentsReportVO.setMemberId(rs.getInt("member_id"));
				recipeCommentsReportVO.setRecipeCommentsNo(rs.getInt("recipe_comments_no"));
				recipeCommentsReportVO.setReportingCommentsReason(rs.getString("reporting_comments_reason"));
				recipeCommentsReportVO.setReportingStatus(rs.getByte("reporting_status"));
				recipeCommentsReportVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				list.add(recipeCommentsReportVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public static void main(String[] args)
	{
		RecipeCommentsReportJDBCDAOIm dao = new RecipeCommentsReportJDBCDAOIm();
		
		// 新增
//		RecipeCommentsReportVO rcrV01 = new RecipeCommentsReportVO();
//		rcrV01.setMemberId(2);
//		rcrV01.setRecipeCommentsNo(2);
//		rcrV01.setReportingCommentsReason("食譜留言檢舉測試99");
//		rcrV01.setReportingStatus((byte) 3);
//		dao.insert(rcrV01);
//		
		//修改
//		RecipeCommentsReportVO rcrV02 = new RecipeCommentsReportVO();
//		rcrV02.setMemberId(2);
//		rcrV02.setRecipeCommentsNo(2);
//		rcrV02.setReportingCommentsReason("食譜留言檢舉理由TEST");
//		rcrV02.setReportingStatus((byte)0);
//		rcrV02.setRecipeReportNo(7);
//		dao.update(rcrV02);
		
		
		// 刪除
//		dao.delete(3);
		
//		 查詢
//		RecipeCommentsReportVO rcrV03 =  dao.findByPrimaryKey(7);
//		System.out.println(rcrV03);

		// 查詢
		
		List<RecipeCommentsReportVO> list = dao.getAll();
		for (RecipeCommentsReportVO aRcr : list) {
			System.out.println(aRcr);
		}
		
	}
}
