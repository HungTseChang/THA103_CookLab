package com.cooklab.recipe_hashtag.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.recipe_comments_report.model.RecipeCommentsReportVO;
import com.cooklab.util.Util;


public class RecipeHashtagJDBCDAOIm implements RecipeHashtagDAO {

	private static final String INSERT_STMT = "INSERT INTO recipe_hashtag (hashtag_no,recipe_no) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT recipe_hashtag_no, hashtag_no, recipe_no,created_timestamp FROM recipe_hashtag order by recipe_hashtag_no";
	private static final String GET_ONE_STMT = "SELECT recipe_hashtag_no, hashtag_no, recipe_no,created_timestamp FROM recipe_hashtag where recipe_hashtag_no = ?";
	private static final String DELETE = "DELETE FROM recipe_hashtag where recipe_hashtag_no = ?";
	private static final String UPDATE = "UPDATE recipe_hashtag set hashtag_no=?, recipe_no=? where recipe_hashtag_no = ?";

	// 新增===========================================================================================================
	@Override
	public void insert(RecipeHashtagVO recipeHashtagVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, recipeHashtagVO.getHashTagNo());
			pstmt.setInt(2, recipeHashtagVO.getRecipeNO());

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

	// 修改================================================
	@Override
	public void update(RecipeHashtagVO recipeHashtagVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, recipeHashtagVO.getHashTagNo());
			pstmt.setInt(2, recipeHashtagVO.getRecipeNO());
			pstmt.setInt(3, recipeHashtagVO.getRepiceHashTagNo());

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

	// 刪除=====================================================================================
	@Override
	public void delete(Integer repiceHashTagNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, repiceHashTagNo);

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
	public RecipeHashtagVO findByPrimaryKey(Integer repiceHashTagNo) {

		RecipeHashtagVO rhVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, repiceHashTagNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				rhVO = new RecipeHashtagVO();

				rhVO.setRepiceHashTagNo(rs.getInt("recipe_hashtag_no"));
				rhVO.setHashTagNo(rs.getInt("hashtag_no"));
				rhVO.setRecipeNO(rs.getInt("recipe_no"));
				rhVO.setCreateTimestamp(rs.getDate("created_timestamp"));

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
		return rhVO;
	}

	// 查詢所有===========================================================================================================
	@Override
	public List<RecipeHashtagVO> getAll() {
		List<RecipeHashtagVO> list = new ArrayList<RecipeHashtagVO>();
		RecipeHashtagVO recipeHashtagVO = null;

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
				recipeHashtagVO = new RecipeHashtagVO();
				recipeHashtagVO.setRepiceHashTagNo(rs.getInt("recipe_hashtag_no"));
				recipeHashtagVO.setHashTagNo(rs.getInt("hashtag_no"));
				recipeHashtagVO.setRecipeNO(rs.getInt("recipe_no"));
				recipeHashtagVO.setCreateTimestamp(rs.getDate("created_timestamp"));

				list.add(recipeHashtagVO); // Store the row in the list
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
		RecipeHashtagJDBCDAOIm dao = new RecipeHashtagJDBCDAOIm();

		// 新增
//		RecipeHashtagVO htV01 = new RecipeHashtagVO();
//		htV01.setHashTagNo(1);
//		htV01.setRecipeNO(3);
//		dao.insert(htV01);
//		
		// 修改
//		RecipeHashtagVO htV02 = new RecipeHashtagVO();
//		htV02.setHashTagNo(5);
//		htV02.setRecipeNO(3);
//		htV02.setRepiceHashTagNo(2);
//		dao.update(htV02);

//		 刪除
//		dao.delete(2);

//		 查詢
//		RecipeHashtagVO htV03 =  dao.findByPrimaryKey(3);
//		System.out.println(htV03);

		// 查詢

		List<RecipeHashtagVO> list = dao.getAll();
		for (RecipeHashtagVO aRcr : list) {
			System.out.println(aRcr);
		}

	}

}
