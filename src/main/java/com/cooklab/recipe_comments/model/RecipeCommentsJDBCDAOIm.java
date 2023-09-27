package com.cooklab.recipe_comments.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.Util;



public class RecipeCommentsJDBCDAOIm implements RecipeCommentsDAO {
	private static final String INSERT_STMT = "INSERT INTO recipe_comments (recipe_no,member_id,comment_content,report_comments) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT recipe_comments_no, recipe_no, member_id, comment_content, report_comments,last_edit_timestamp,created_timestamp FROM recipe_comments order by recipe_comments_no";
	private static final String GET_ONE_STMT = "SELECT recipe_comments_no, recipe_no, member_id, comment_content, report_comments,last_edit_timestamp,created_timestamp FROM recipe_comments where recipe_comments_no = ?";
	private static final String DELETE = "DELETE FROM recipe_comments where recipe_comments_no = ?";
	private static final String UPDATE = "UPDATE recipe_comments set recipe_no=?, member_id=?, comment_content=?, report_comments=? ,last_edit_timestamp=now() where recipe_comments_no = ?";

	// 新增===========================================================================================================
	@Override
	public void insert(RecipeCommentsVO recipeCommentsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, recipeCommentsVO.getRecipeNo());
			pstmt.setInt(2, recipeCommentsVO.getMemberId());
//			pstmt.setDate(3, recipeCommentsVO.getCommentTimestamp());
			pstmt.setString(3, recipeCommentsVO.getCommentContent());
			pstmt.setInt(4, recipeCommentsVO.getReportComments());

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

	// 修改===========================================================================================================
	@Override
	public void update(RecipeCommentsVO recipeCommentsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, recipeCommentsVO.getRecipeNo());
			pstmt.setInt(2, recipeCommentsVO.getMemberId());
//			pstmt.setDate(3, recipeCommentsVO.getCommentTimestamp());
			pstmt.setString(3, recipeCommentsVO.getCommentContent());
			pstmt.setInt(4, recipeCommentsVO.getReportComments());
			pstmt.setInt(5, recipeCommentsVO.getRecipeCommentsNo());

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

	// 刪除===========================================================================================================
	@Override
	public void delete(Integer recipeCommentsNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, recipeCommentsNo);

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

	// 用主鍵查詢===========================================================================================================
	@Override
	public RecipeCommentsVO findByPrimaryKey(Integer recipeCommentsNo) {

		RecipeCommentsVO rccVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, recipeCommentsNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				rccVO = new RecipeCommentsVO();
				rccVO.setRecipeCommentsNo(rs.getInt("recipe_comments_no"));
				rccVO.setRecipeNo(rs.getInt("recipe_no"));
				rccVO.setMemberId(rs.getInt("member_id"));
				rccVO.setCommentContent(rs.getString("comment_content"));
				rccVO.setReportComments(rs.getInt("report_comments"));
				rccVO.setLastEditTimestamp(rs.getDate("last_edit_timestamp"));
				rccVO.setcreatedTimestamp(rs.getDate("created_timestamp"));

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
		return rccVO;
	}

	// 查詢所有===========================================================================================================
	@Override
	public List<RecipeCommentsVO> getAll() {
		List<RecipeCommentsVO> list = new ArrayList<RecipeCommentsVO>();
		RecipeCommentsVO recipeCommentsVO = null;

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
				recipeCommentsVO = new RecipeCommentsVO();
				recipeCommentsVO.setRecipeCommentsNo(rs.getInt("recipe_comments_no"));
				recipeCommentsVO.setRecipeNo(rs.getInt("recipe_no"));
				recipeCommentsVO.setMemberId(rs.getInt("member_id"));
				recipeCommentsVO.setCommentContent(rs.getString("comment_content"));
				recipeCommentsVO.setReportComments(rs.getInt("report_comments"));
				recipeCommentsVO.setLastEditTimestamp(rs.getDate("last_edit_timestamp"));
				recipeCommentsVO.setcreatedTimestamp(rs.getDate("created_timestamp"));
				list.add(recipeCommentsVO); // Store the row in the list
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
		RecipeCommentsJDBCDAOIm dao = new RecipeCommentsJDBCDAOIm();

		// 新增
//		RecipeCommentsVO rccV01 = new RecipeCommentsVO();
//		rccV01.setRecipeNo(3);
//		rccV01.setMemberId(2);
//		rccV01.setCommentContent("食譜留言區測試99");
//		rccV01.setReportComments(3);
//		dao.insert(rccV01);

		// 修改
//		RecipeCommentsVO rccV02 = new RecipeCommentsVO();
//		rccV02.setRecipeNo(3);
//		rccV02.setMemberId(2);
//		rccV02.setCommentContent("檢舉留言修改測試2");
//		rccV02.setReportComments(2);
//		rccV02.setRecipeCommentsNo(6);
//		dao.update(rccV02);

		// 刪除
//		dao.delete(3);

		// 查詢
//		RecipeCommentsVO rccV03 =  dao.findByPrimaryKey(6);
//		System.out.println(rccV03);

		// 查詢

		List<RecipeCommentsVO> list = dao.getAll();
		for (RecipeCommentsVO aRcc : list) {
			System.out.println(aRcc);
		}

	}
}
