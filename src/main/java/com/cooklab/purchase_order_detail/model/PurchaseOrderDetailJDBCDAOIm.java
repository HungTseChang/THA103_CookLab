package com.cooklab.purchase_order_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;

public class PurchaseOrderDetailJDBCDAOIm implements PurchaseOrderDetailDAO {
	private static final String INSERT_STMT = "insert into purchase_order_detail(product_name, product_qty, expired_date, purchase_order_no, product_no, purchase_order_price) values(?,?,?,?,?,?)";
	private static final String UPDATE = "update purchase_order_detail set product_name = ?, product_qty = ?, expired_date= ?, purchase_order_no = ?, product_no = ?, purchase_order_price = ? where order_detail_no = ?";
	private static final String DELETE = "delete from purchase_order_detail where order_detail_no = ?";
	private static final String GET_ONE_STMT = "select order_detail_no,product_name, product_qty, expired_date, purchase_order_no, product_no, purchase_order_price,created_timestamp  from purchase_order_detail where order_detail_no = ?";
	private static final String GET_ALL_STMT = "select order_detail_no,product_name, product_qty, expired_date, purchase_order_no, product_no, purchase_order_price,created_timestamp  from purchase_order_detail order by order_detail_no";

	@Override
	public void insert(PurchaseOrderDetailVO purchaseOrderDetail) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, purchaseOrderDetail.getProductName());
			pstmt.setInt(2, purchaseOrderDetail.getProductQty());
			pstmt.setDate(3, purchaseOrderDetail.getExpiredDate());
			pstmt.setInt(4, purchaseOrderDetail.getPurchaseOrderNo());
			pstmt.setInt(5, purchaseOrderDetail.getProductNo());
			pstmt.setInt(6, purchaseOrderDetail.getPurchaseOrderPrice());

			pstmt.executeUpdate();

			System.out.println("新增資料成功");

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
	public void update(PurchaseOrderDetailVO purchaseOrderDetail) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, purchaseOrderDetail.getProductName());
			pstmt.setInt(2, purchaseOrderDetail.getProductQty());
			pstmt.setDate(3, purchaseOrderDetail.getExpiredDate());
			pstmt.setInt(4, purchaseOrderDetail.getPurchaseOrderNo());
			pstmt.setInt(5, purchaseOrderDetail.getProductNo());
			pstmt.setInt(6, purchaseOrderDetail.getPurchaseOrderPrice());
			pstmt.setInt(7, purchaseOrderDetail.getOrderDetailNo());

			pstmt.executeUpdate();

			System.out.println("更新資料成功");

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

			System.out.println("刪除資料成功");

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
	public PurchaseOrderDetailVO findByPrimaryKey(Integer orderDetailNo) {
		PurchaseOrderDetailVO purchaseOrderDetail = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, orderDetailNo);

			rs = pstmt.executeQuery();

			// 放入對應的MySQL表格欄位名稱
			while (rs.next()) {
				purchaseOrderDetail = new PurchaseOrderDetailVO();
				purchaseOrderDetail.setOrderDetailNo(rs.getInt("order_detail_no"));
				purchaseOrderDetail.setProductName(rs.getString("product_name"));
				purchaseOrderDetail.setProductQty(rs.getInt("product_qty"));
				purchaseOrderDetail.setExpiredDate(rs.getDate("expired_date"));
				purchaseOrderDetail.setPurchaseOrderNo(rs.getInt("purchase_order_no"));
				purchaseOrderDetail.setProductNo(rs.getInt("product_no"));
				purchaseOrderDetail.setPurchaseOrderPrice(rs.getInt("purchase_order_price"));
				purchaseOrderDetail.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
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
		return purchaseOrderDetail;
	}

	@Override
	public List<PurchaseOrderDetailVO> getAll() {
		List<PurchaseOrderDetailVO> list = new ArrayList<PurchaseOrderDetailVO>();
		PurchaseOrderDetailVO purchaseOrderDetail = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			// 放入對應的MySQL表格欄位名稱
			while (rs.next()) {
				purchaseOrderDetail = new PurchaseOrderDetailVO();
				purchaseOrderDetail.setOrderDetailNo(rs.getInt("order_detail_no"));
				purchaseOrderDetail.setProductName(rs.getString("product_name"));
				purchaseOrderDetail.setProductQty(rs.getInt("product_qty"));
				purchaseOrderDetail.setExpiredDate(rs.getDate("expired_date"));
				purchaseOrderDetail.setPurchaseOrderNo(rs.getInt("purchase_order_no"));
				purchaseOrderDetail.setProductNo(rs.getInt("product_no"));
				purchaseOrderDetail.setPurchaseOrderPrice(rs.getInt("purchase_order_price"));
				purchaseOrderDetail.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				list.add(purchaseOrderDetail); // Store the row in the list
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
