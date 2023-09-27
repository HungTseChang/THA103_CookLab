package com.cooklab.recipe_ingredient.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cooklab.util.*;

public class RecipeIngredientJDBCDAOlm implements RecipeIngredientDAO {
	private static final String INSERT_STMT = "INSERT INTO recipe_ingredient (recipe_no ,product_no ,text_label,ingredient_quantity ) VALUES ( ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM recipe_ingredient ORDER BY recipe_ingredient_no";
	private static final String GET_ONE_STMT = "SELECT * FROM recipe_ingredient where recipe_ingredient_no   = ?";
	private static final String DELETE = "DELETE FROM recipe_ingredient where recipe_ingredient_no = ?";
	private static final String UPDATE = "UPDATE recipe_ingredient SET recipe_no =?, product_no  =?, text_label = ?,ingredient_quantity = ? WHERE recipe_ingredient_no = ?";

	@Override
	public void insert(RecipeIngredientVO recipeIngredientVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, recipeIngredientVO.getRecipeNo());
			pstmt.setInt(2, recipeIngredientVO.getProductNo());
			pstmt.setString(3, recipeIngredientVO.getTextLabel());
			pstmt.setString(4, recipeIngredientVO.getIngredientQuantity());

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
	public void update(RecipeIngredientVO recipeIngredientVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, recipeIngredientVO.getRecipeNo());
			pstmt.setInt(2, recipeIngredientVO.getProductNo());
			pstmt.setString(3, recipeIngredientVO.getTextLabel());
			pstmt.setString(4, recipeIngredientVO.getIngredientQuantity());
			pstmt.setInt(5, recipeIngredientVO.getRecipeIngredientNo());

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
	public void delete(Integer recipeIngredientNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, recipeIngredientNo);

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
	public RecipeIngredientVO findByPrimaryKey(Integer recipeIngredientNo) {
		RecipeIngredientVO recipeIngredientVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, recipeIngredientNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				recipeIngredientVO = new RecipeIngredientVO();
				recipeIngredientVO.setRecipeIngredientNo(rs.getInt("recipe_ingredient_no"));
				recipeIngredientVO.setRecipeNo(rs.getInt("recipe_no"));
				recipeIngredientVO.setProductNo(rs.getInt("product_no"));
				recipeIngredientVO.setTextLabel(rs.getString("text_label"));
				recipeIngredientVO.setIngredientQuantity(rs.getString("ingredient_quantity"));
				recipeIngredientVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
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
		return recipeIngredientVO;
	}

	@Override
	public List<RecipeIngredientVO> getAll() {
		List<RecipeIngredientVO> list = new ArrayList<RecipeIngredientVO>();
		RecipeIngredientVO recipeIngredientVO = null;

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
				recipeIngredientVO = new RecipeIngredientVO();
				recipeIngredientVO.setRecipeIngredientNo(rs.getInt("recipe_ingredient_no"));
				recipeIngredientVO.setRecipeNo(rs.getInt("recipe_no"));
				recipeIngredientVO.setProductNo(rs.getInt("product_no"));
				recipeIngredientVO.setTextLabel(rs.getString("text_label"));
				recipeIngredientVO.setIngredientQuantity(rs.getString("ingredient_quantity"));
				recipeIngredientVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				list.add(recipeIngredientVO); // Store the row in the list
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
