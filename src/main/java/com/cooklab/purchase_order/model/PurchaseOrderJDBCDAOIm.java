package com.cooklab.purchase_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;

public class PurchaseOrderJDBCDAOIm implements PurchaseOrderDAO {
	private static final String INSERT_STMT = "insert into purchase_order(purchase_order_date, purchase_order_supplier, purchase_order_total) values (?, ?, ?)";
	private static final String UPDATE = "update purchase_order set purchase_order_date=?,purchase_order_supplier=?, purchase_order_total=? where purchase_order_no = ?";
	private static final String DELETE = "delete from purchase_order where purchase_order_no = ?";
	private static final String GET_ONE_STMT = "select purchase_order_no, purchase_order_date, purchase_order_supplier, purchase_order_total FROM purchase_order where purchase_order_no = ?";
	private static final String GET_ALL_STMT = "select purchase_order_no, purchase_order_date, purchase_order_supplier, purchase_order_total FROM purchase_order order by purchase_order_no";

	@Override
	public void insert(PurchaseOrderVO purchaseOrder) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setDate(1, purchaseOrder.getPurchaseOrderDate());
			pstmt.setString(2, purchaseOrder.getPurchaseOrderSupplier());
			pstmt.setInt(3, purchaseOrder.getPurchaseOrderTotal());

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
	public void update(PurchaseOrderVO purchaseOrder) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDate(1, purchaseOrder.getPurchaseOrderDate());
			pstmt.setString(2, purchaseOrder.getPurchaseOrderSupplier());
			pstmt.setInt(3, purchaseOrder.getPurchaseOrderTotal());
			pstmt.setInt(4, purchaseOrder.getPurchaseOrderNo());

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
	public void delete(Integer purchaseOrderNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, purchaseOrderNo);

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
	public PurchaseOrderVO findByPrimaryKey(Integer purchaseOrderNo) {
		PurchaseOrderVO purchaseOrder = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, purchaseOrderNo);

			rs = pstmt.executeQuery();

			// 放入對應的MySQL表格欄位名稱
			while (rs.next()) {
				purchaseOrder = new PurchaseOrderVO();
				purchaseOrder.setPurchaseOrderNo(rs.getInt("purchase_order_no"));
				purchaseOrder.setPurchaseOrderDate(rs.getDate("Purchase_order_date"));
				purchaseOrder.setPurchaseOrderSupplier(rs.getString("Purchase_order_supplier"));
				purchaseOrder.setPurchaseOrderTotal(rs.getInt("Purchase_order_total"));
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
		return purchaseOrder;
	}

	@Override
	public List<PurchaseOrderVO> getAll() {
		List<PurchaseOrderVO> list = new ArrayList<PurchaseOrderVO>();
		PurchaseOrderVO purchaseOrder = null;

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
				purchaseOrder = new PurchaseOrderVO();
				purchaseOrder.setPurchaseOrderNo(rs.getInt("purchase_order_no"));
				purchaseOrder.setPurchaseOrderDate(rs.getDate("purchase_order_date"));
				purchaseOrder.setPurchaseOrderSupplier(rs.getString("purchase_order_supplier"));
				purchaseOrder.setPurchaseOrderTotal(rs.getInt("purchase_order_total"));
				list.add(purchaseOrder); // Store the row in the list
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