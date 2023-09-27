package com.cooklab.recipe_kitchenware.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cooklab.util.*;

public class RecipeKitchenwareJDBCDAOlm implements RecipeKitchenwareDAO {

	private static final String INSERT_STMT = "INSERT INTO recipe_kitchenware (recipe_no ,product_no ,text_label ) VALUES ( ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM recipe_kitchenware ORDER BY recipe_kitchenware_no";
	private static final String GET_ONE_STMT = "SELECT * FROM recipe_kitchenware where recipe_kitchenware_no   = ?";
	private static final String DELETE = "DELETE FROM recipe_kitchenware where recipe_kitchenware_no = ?";
	private static final String UPDATE = "UPDATE recipe_kitchenware SET recipe_no =?, product_no  =?, text_label = ? WHERE recipe_kitchenware_no = ?";

	@Override
	public void insert(RecipeKitchenwareVO recipeKitchenwareVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, recipeKitchenwareVO.getRecipeNo());
			pstmt.setInt(2, recipeKitchenwareVO.getProductNo());
			pstmt.setString(3, recipeKitchenwareVO.getTextLabel());

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
	public void update(RecipeKitchenwareVO recipeKitchenwareVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, recipeKitchenwareVO.getRecipeNo());
			pstmt.setInt(2, recipeKitchenwareVO.getProductNo());
			pstmt.setString(3, recipeKitchenwareVO.getTextLabel());
			pstmt.setInt(4, recipeKitchenwareVO.getRecipeKitchenwareNo());

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
	public void delete(Integer recipeKitchenwareNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, recipeKitchenwareNo);

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
	public RecipeKitchenwareVO findByPrimaryKey(Integer recipeKitchenwareNo) {
		RecipeKitchenwareVO recipeKitchenwareVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, recipeKitchenwareNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				recipeKitchenwareVO = new RecipeKitchenwareVO();
				recipeKitchenwareVO.setRecipeKitchenwareNo(rs.getInt("recipe_kitchenware_no"));
				recipeKitchenwareVO.setRecipeNo(rs.getInt("recipe_no"));
				recipeKitchenwareVO.setProductNo(rs.getInt("product_no"));
				recipeKitchenwareVO.setTextLabel(rs.getString("text_label"));
				recipeKitchenwareVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
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
		return recipeKitchenwareVO;
	}

	@Override
	public List<RecipeKitchenwareVO> getAll() {
		List<RecipeKitchenwareVO> list = new ArrayList<RecipeKitchenwareVO>();
		RecipeKitchenwareVO recipeKitchenwareVO = null;

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
				recipeKitchenwareVO = new RecipeKitchenwareVO();
				recipeKitchenwareVO.setRecipeKitchenwareNo(rs.getInt("recipe_kitchenware_no"));
				recipeKitchenwareVO.setRecipeNo(rs.getInt("recipe_no"));
				recipeKitchenwareVO.setProductNo(rs.getInt("product_no"));
				recipeKitchenwareVO.setTextLabel(rs.getString("text_label"));
				recipeKitchenwareVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				list.add(recipeKitchenwareVO); // Store the row in the list
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
