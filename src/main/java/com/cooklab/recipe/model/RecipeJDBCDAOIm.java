package com.cooklab.recipe.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;


public class RecipeJDBCDAOIm implements RecipeDAO {
	private static final String INSERT_STMT = "INSERT INTO recipe (member_id,recipe_name ,cover_image, introduction, additional_explanation , region, recipe_status, report_count, view_count, recipe_quantity) VALUES ( ?, ?,?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM recipe ORDER BY recipe_no";
	private static final String GET_ONE_STMT = "SELECT * FROM recipe where recipe_no = ?";
	private static final String DELETE = "DELETE FROM recipe where recipe_no = ?";
	private static final String UPDATE = "UPDATE recipe SET member_id =?,recipeName=?, cover_image =?, introduction =?, additional_explanation =?, region =?, recipe_status =?, report_count=?,view_count =?, recipe_quantity =?, last_edit_timestamp = now() WHERE recipe_no = ?";

	@Override
	public void insert(RecipeVO recipeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, recipeVO.getMemberId());
			pstmt.setString(2, recipeVO.getRecipeName());
			pstmt.setBytes(3, recipeVO.getCoverImage());
			pstmt.setString(4, recipeVO.getIntroduction());
			pstmt.setString(5, recipeVO.getAdditionalExplanation());
			pstmt.setString(6, recipeVO.getRegion());
			pstmt.setByte(7, recipeVO.getRecipeStatus());
			pstmt.setInt(8, recipeVO.getReportCount());
			pstmt.setInt(9, recipeVO.getViewCount());
			pstmt.setByte(10, recipeVO.getRecipeQuantity());

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
	public void update(RecipeVO recipeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, recipeVO.getMemberId());
			pstmt.setString(2, recipeVO.getRecipeName());
			pstmt.setBytes(3, recipeVO.getCoverImage());
			pstmt.setString(4, recipeVO.getIntroduction());
			pstmt.setString(5, recipeVO.getAdditionalExplanation());
			pstmt.setString(6, recipeVO.getRegion());
			pstmt.setByte(7, recipeVO.getRecipeStatus());
			pstmt.setInt(8, recipeVO.getReportCount());
			pstmt.setInt(9, recipeVO.getViewCount());
			pstmt.setByte(10, recipeVO.getRecipeQuantity());
			pstmt.setInt(11, recipeVO.getRecipeNo());

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
	public void delete(Integer recipe_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, recipe_no);

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
	public RecipeVO findByPrimaryKey(Integer recipe_no) {
		RecipeVO recipeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, recipe_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				recipeVO = new RecipeVO();
				recipeVO.setRecipeNo(rs.getInt("recipe_no"));
				recipeVO.setMemberId(rs.getInt("member_id"));
				recipeVO.setCoverImage(rs.getBytes("cover_image"));
				recipeVO.setIntroduction(rs.getString("introduction"));
				recipeVO.setAdditionalExplanation(rs.getString("additional_explanation"));
				recipeVO.setRegion(rs.getString("region"));
				recipeVO.setRecipeStatus(rs.getByte("recipe_status"));
				recipeVO.setReportCount(rs.getInt("report_count"));
				recipeVO.setViewCount(rs.getInt("view_count"));
				recipeVO.setRecipeQuantity(rs.getByte("recipe_quantity"));
				recipeVO.setLastEditTimestamp(rs.getTimestamp("last_edit_timestamp"));
				recipeVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));

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
		return recipeVO;
	}

	@Override
	public List<RecipeVO> getAll() {
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		RecipeVO recipeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				recipeVO = new RecipeVO();
				recipeVO.setRecipeNo(rs.getInt("recipe_no"));
				recipeVO.setMemberId(rs.getInt("member_id"));
				recipeVO.setCoverImage(rs.getBytes("cover_image"));
				recipeVO.setIntroduction(rs.getString("introduction"));
				recipeVO.setAdditionalExplanation(rs.getString("additional_explanation"));
				recipeVO.setRegion(rs.getString("region"));
				recipeVO.setRecipeStatus(rs.getByte("recipe_status"));
				recipeVO.setReportCount(rs.getInt("report_count"));
				recipeVO.setViewCount(rs.getInt("view_count"));
				recipeVO.setRecipeQuantity(rs.getByte("recipe_quantity"));
				recipeVO.setLastEditTimestamp(rs.getTimestamp("last_edit_timestamp"));
				recipeVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				list.add(recipeVO); // Store the row in the list
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

}
