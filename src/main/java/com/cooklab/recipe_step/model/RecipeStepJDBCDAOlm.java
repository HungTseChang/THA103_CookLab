package com.cooklab.recipe_step.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cooklab.util.*;

public class RecipeStepJDBCDAOlm implements RecipeStepDAO {

	private static final String INSERT_STMT = "INSERT INTO recipe_step (recipe_no, step ,step_time ,step_img, step_content ) VALUES ( ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM recipe_step ORDER BY recipe_no";
	private static final String GET_ONE_STMT = "SELECT * FROM recipe_step where recipe_no   = ?";
	private static final String DELETE = "DELETE FROM recipe_step where recipe_no = ?";
	private static final String UPDATE = "UPDATE recipe_step SET step  =?, step_time = ?, step_img =?, step_content=? WHERE recipe_no = ?";

	@Override
	public void insert(RecipeStepVO recipeStepVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, recipeStepVO.getRecipeNo());
			pstmt.setInt(2, recipeStepVO.getStep());
			pstmt.setInt(3, recipeStepVO.getStepTime());
			pstmt.setBytes(4, recipeStepVO.getStepImg());
			pstmt.setString(5, recipeStepVO.getStepContent());

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
	public void update(RecipeStepVO recipeStepVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, recipeStepVO.getStep());
			pstmt.setInt(2, recipeStepVO.getStepTime());
			pstmt.setBytes(3, recipeStepVO.getStepImg());
			pstmt.setString(4, recipeStepVO.getStepContent());
//			pstmt.setInt(5, recipeStepVO.getRecipeNo());

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
	public void delete(Integer recipeNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, recipeNo);

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
	public RecipeStepVO findByPrimaryKey(Integer recipeNo) {
		RecipeStepVO recipeStepVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, recipeNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				recipeStepVO = new RecipeStepVO();
//				recipeStepVO.setRecipeNo(rs.getInt("recipe_no"));
				recipeStepVO.setStep(rs.getInt("step"));
				recipeStepVO.setStepTime(rs.getInt("step_time"));
				recipeStepVO.setStepImg(rs.getBytes("step_img"));
				recipeStepVO.setStepContent(rs.getString("step_content"));
				recipeStepVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));

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
		return recipeStepVO;
	}

	@Override
	public List<RecipeStepVO> getAll() {
		List<RecipeStepVO> list = new ArrayList<RecipeStepVO>();
		RecipeStepVO recipeStepVO = null;

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
				recipeStepVO = new RecipeStepVO();
//				recipeStepVO.setRecipeNo(rs.getInt("recipe_no"));
				recipeStepVO.setStep(rs.getInt("step"));
				recipeStepVO.setStepTime(rs.getInt("step_time"));
				recipeStepVO.setStepImg(rs.getBytes("step_img"));
				recipeStepVO.setStepContent(rs.getString("step_content"));
				recipeStepVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				list.add(recipeStepVO); // Store the row in the list
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
