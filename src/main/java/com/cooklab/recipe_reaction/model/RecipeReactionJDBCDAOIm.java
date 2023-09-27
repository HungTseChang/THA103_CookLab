package com.cooklab.recipe_reaction.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.Util;



public class RecipeReactionJDBCDAOIm implements RecipeReactionDAO {
	private static final String INSERT_STMT = "INSERT INTO recipe_reaction (recipe_no,member_id,recipe_reaction_status) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT recipe_no, member_id, recipe_reaction_status,created_timestamp FROM recipe_reaction order by recipe_no AND member_id";
	private static final String GET_ONE_STMT = "SELECT recipe_no, member_id, recipe_reaction_status,created_timestamp FROM recipe_reaction where recipe_no = ? AND member_id = ?";
	private static final String DELETE = "DELETE FROM recipe_reaction where recipe_no = ? AND member_id = ?";
	private static final String UPDATE = "UPDATE recipe_reaction set recipe_no=?, member_id=?, recipe_reaction_status=? where recipe_no = ? AND member_id = ? ";

	// 新增===========================================================================================================
	@Override
	public void insert(RecipeReactionVO recipeReactionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, recipeReactionVO.getRecipeNo());
			pstmt.setInt(2, recipeReactionVO.getMemberId());
			pstmt.setInt(3, recipeReactionVO.getRecipeReactionStatus());

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

	// 修改=================================================================
	@Override
	public void update(RecipeReactionVO recipeReactionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, recipeReactionVO.getRecipeNo());
			pstmt.setInt(2, recipeReactionVO.getMemberId());
			pstmt.setInt(3, recipeReactionVO.getRecipeReactionStatus());
			pstmt.setInt(4, recipeReactionVO.getRecipeNo());
			pstmt.setInt(5, recipeReactionVO.getMemberId());

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
	public void delete(Integer recipeNo, Integer memberId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, recipeNo);
			pstmt.setInt(2, memberId);

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
	public RecipeReactionVO findByPrimaryKey(Integer recipeNo, Integer memberId) {
		RecipeReactionVO rrVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, recipeNo);
			pstmt.setInt(2, memberId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				rrVO = new RecipeReactionVO();

				rrVO.setRecipeNo(rs.getInt("recipe_no"));
				rrVO.setMemberId(rs.getInt("member_id"));
				rrVO.setRecipeReactionStatus(rs.getByte("recipe_reaction_status"));
				rrVO.setCreateTimestamp(rs.getDate("created_timestamp"));

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
		return rrVO;
	}

	// 查詢所有===========================================================================================================
	@Override
	public List<RecipeReactionVO> getAll() {
		List<RecipeReactionVO> list = new ArrayList<RecipeReactionVO>();
		RecipeReactionVO recipeReactionVO = null;

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
				recipeReactionVO = new RecipeReactionVO();
				recipeReactionVO.setRecipeNo(rs.getInt("recipe_no"));
				recipeReactionVO.setMemberId(rs.getInt("member_id"));
				recipeReactionVO.setRecipeReactionStatus(rs.getByte("recipe_reaction_status"));
				recipeReactionVO.setCreateTimestamp(rs.getDate("created_timestamp"));
				list.add(recipeReactionVO); // Store the row in the list
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
		RecipeReactionJDBCDAOIm dao = new RecipeReactionJDBCDAOIm();

		// 新增
//		RecipeReactionVO rrV01 = new RecipeReactionVO();
//		rrV01.setRecipeNo(4);
//		rrV01.setMemberId(2);
//		rrV01.setRecipeReactionStatus((byte) 7);
//		dao.insert(rrV01);
//		
		// 修改
//		RecipeReactionVO rrV02 = new RecipeReactionVO();
//		rrV02.setRecipeNo(4);
//		rrV02.setMemberId(2);
//		rrV02.setRecipeReactionStatus((byte)2);
//		dao.update(rrV02);

		// 刪除
//		dao.delete(4,2);

//		 查詢
//		RecipeReactionVO rrV03 =  dao.findByPrimaryKey(3,3);
//		System.out.println(rrV03);

		// 查詢
//		
//		List<RecipeReactionVO> list = dao.getAll();
//		for (RecipeReactionVO aRcr : list) {
//			System.out.println(aRcr);
//		}

	}
}
