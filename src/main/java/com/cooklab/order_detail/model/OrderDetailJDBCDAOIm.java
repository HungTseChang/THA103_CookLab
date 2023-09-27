package com.cooklab.order_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;




public class OrderDetailJDBCDAOIm implements OrderDetailDAO {

	private static final String INSERT_STMT = "INSERT INTO order_detail (order_no,product_no,order_qty) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT order_detail_no,order_no,product_no,order_qty,created_timestamp from order_detail order by order_detail_no";
	private static final String GET_ONE_STMT = "SELECT order_detail_no,order_no,product_no,order_qty,created_timestamp from order_detail where order_detail_no = ?";
	private static final String DELETE = "DELETE FROM order_detail where order_detail_no = ?";
	private static final String UPDATE = "UPDATE order_detail set order_no=?, product_no=?, order_qty=? where order_detail_no = ?";

	public static void main(String[] args) {
		OrderDetailJDBCDAOIm dao = new OrderDetailJDBCDAOIm();

		
		OrderDetailVO orderDetailVO1 = new OrderDetailVO();
		orderDetailVO1.setOrderNo(1);
		orderDetailVO1.setProductNo(1);
		orderDetailVO1.setOrderQty(200);
		dao.insert(orderDetailVO1);

		
		OrderDetailVO orderDetailVO2 = new OrderDetailVO();
		orderDetailVO2.setOrderNo(1);
		orderDetailVO2.setProductNo(1);
		orderDetailVO2.setOrderQty(2);
		orderDetailVO2.setOrderDetailNo(3);
		dao.update(orderDetailVO2);

		
//		dao.delete(3);

		
		OrderDetailVO orderDetailVO3 = dao.findByPrimaryKey(1);
		System.out.print(orderDetailVO3);
		System.out.println("---------------------");

		
		List<OrderDetailVO> list = dao.getAll();
		for (OrderDetailVO aorderDetail : list) {
			System.out.print(aorderDetail);
			System.out.println();
		}
	}

	@Override
	public void insert(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, orderDetailVO.getOrderNo());
			pstmt.setInt(2, orderDetailVO.getProductNo());
			pstmt.setInt(3, orderDetailVO.getOrderQty());

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
	public void update(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, orderDetailVO.getOrderNo());
			pstmt.setInt(2, orderDetailVO.getProductNo());
			pstmt.setInt(3, orderDetailVO.getOrderQty());
			pstmt.setInt(4, orderDetailVO.getOrderDetailNo());
			
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
	public void delete(Integer orderDetailNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, orderDetailNo);

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
	public OrderDetailVO findByPrimaryKey(Integer orderDetailNo) {
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, orderDetailNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderNo(rs.getInt("order_no"));
				orderDetailVO.setProductNo(rs.getInt("product_no"));
				orderDetailVO.setOrderQty(rs.getInt("order_qty"));
				orderDetailVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				orderDetailVO.setOrderDetailNo(rs.getInt("order_detail_no"));
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
		return orderDetailVO;
	}

	@Override
	public List<OrderDetailVO> getAll() {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderNo(rs.getInt("order_no"));
				orderDetailVO.setProductNo(rs.getInt("product_no"));
				orderDetailVO.setOrderQty(rs.getInt("order_qty"));
				orderDetailVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				orderDetailVO.setOrderDetailNo(rs.getInt("order_detail_no"));
				list.add(orderDetailVO); // Store the row in the list
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
