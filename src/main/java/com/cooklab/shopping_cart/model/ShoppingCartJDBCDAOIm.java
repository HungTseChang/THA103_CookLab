package com.cooklab.shopping_cart.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;


public class ShoppingCartJDBCDAOIm implements ShoppingCartDAO{
	


	private static final String INSERT_STMT = "INSERT INTO shopping_cart (member_id,product_no,product_qty) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT shopping_cart_no,member_id,product_no,product_qty,created_timestamp from shopping_cart order by shopping_cart_no";
	private static final String GET_ONE_STMT = "SELECT shopping_cart_no,member_id,product_no,product_qty,created_timestamp from shopping_cart where shopping_cart_no = ?";
	private static final String DELETE = "DELETE FROM shopping_cart where shopping_cart_no = ?";
	private static final String UPDATE = "UPDATE shopping_cart set member_id=?, product_no=?, product_qty=? where shopping_cart_no = ?";

	public static void main(String[] args) {
		ShoppingCartJDBCDAOIm dao = new ShoppingCartJDBCDAOIm();

		// 
//		ShoppingCartVO shoppingCartVO1 = new ShoppingCartVO();
//		shoppingCartVO1.setMemberId(1);
//		shoppingCartVO1.setProductNo(1);
//		shoppingCartVO1.setProductQty(20);
//		dao.insert(shoppingCartVO1);
//
//		// 
//		ShoppingCartVO shoppingCartVO2 = new ShoppingCartVO();
//		shoppingCartVO2.setMemberId(1);
//		shoppingCartVO2.setProductNo(1);
//		shoppingCartVO2.setProductQty(50);
//		shoppingCartVO2.setShoppingCartNo(1);
//		dao.update(shoppingCartVO2);
//
//		//
////		dao.delete(1);
//
//		// 
//		ShoppingCartVO shoppingCartVO3 = dao.findByPrimaryKey(5);
//		System.out.print(shoppingCartVO3);
//		System.out.println("---------------------");

		//
		List<ShoppingCartVO> list = dao.getAll();
		for (ShoppingCartVO aShoppingCart : list) {
			System.out.print(aShoppingCart);
			System.out.println();
		}
	}

	@Override
	public void insert(ShoppingCartVO shoppingCartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, shoppingCartVO.getMemberId());
			pstmt.setInt(2, shoppingCartVO.getProductNo());
			pstmt.setInt(3, shoppingCartVO.getProductQty());

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

	@Override
	public void update(ShoppingCartVO shoppingCartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, shoppingCartVO.getMemberId());
			pstmt.setInt(2, shoppingCartVO.getProductNo());
			pstmt.setInt(3, shoppingCartVO.getProductQty());
			pstmt.setInt(4, shoppingCartVO.getShoppingCartNo());

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

	@Override
	public void delete(Integer shoppingCartNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, shoppingCartNo);

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

	@Override
	public ShoppingCartVO findByPrimaryKey(Integer shoppingCartNo) {
		ShoppingCartVO shoppingCartVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, shoppingCartNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				shoppingCartVO = new ShoppingCartVO();
				shoppingCartVO.setMemberId(rs.getInt("member_id"));
				shoppingCartVO.setProductNo(rs.getInt("product_no"));
				shoppingCartVO.setProductQty(rs.getInt("product_qty"));
				shoppingCartVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				shoppingCartVO.setShoppingCartNo(rs.getInt("shopping_cart_no"));
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
		return shoppingCartVO;
	}

	@Override
	public List<ShoppingCartVO> getAll() {
		List<ShoppingCartVO> list = new ArrayList<ShoppingCartVO>();
		ShoppingCartVO shoppingCartVO = null;

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
				shoppingCartVO = new ShoppingCartVO();
				shoppingCartVO.setMemberId(rs.getInt("member_id"));
				shoppingCartVO.setProductNo(rs.getInt("product_no"));
				shoppingCartVO.setProductQty(rs.getInt("product_qty"));
				shoppingCartVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				shoppingCartVO.setShoppingCartNo(rs.getInt("shopping_cart_no"));
				list.add(shoppingCartVO); // Store the row in the list
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

}
