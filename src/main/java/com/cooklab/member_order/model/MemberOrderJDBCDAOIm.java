package com.cooklab.member_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;

public class MemberOrderJDBCDAOIm implements MemberOrderDAO {
	private static final String INSERT_STMT = "INSERT INTO member_order (member_id,order_status,total_order_amount,checkout_amount,promo_code_no,shipping_address) VALUES (?, ?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = "SELECT order_no,member_id,order_status,total_order_amount,checkout_amount,promo_code_no,shipping_address,created_timestamp FROM member_order order by order_no";
	private static final String GET_ONE_STMT = "SELECT order_no,member_id,order_status,total_order_amount,checkout_amount,promo_code_no,shipping_address,created_timestamp FROM member_order where order_no = ?";
	private static final String DELETE = "DELETE FROM member_order where order_no = ?";
	private static final String UPDATE = "UPDATE member_order set member_id=?, order_status=?, total_order_amount=?, checkout_amount=?,promo_code_no=?, shipping_address=? where order_no = ?";

	public static void main(String[] args) {

		MemberOrderJDBCDAOIm dao = new MemberOrderJDBCDAOIm();

//		 
		MemberOrderVO memberOrderVO1 = new MemberOrderVO();
		memberOrderVO1.setMemberId(1);
		memberOrderVO1.setOrderNo(1);
		memberOrderVO1.setTotalOrderAmount(3000);
		memberOrderVO1.setCheckoutAmount(1000);
		memberOrderVO1.setPromoCodeNo(1);
		memberOrderVO1.setShippingAddress("aaaaa");
		dao.insert(memberOrderVO1);

//		
		MemberOrderVO memberOrderVO2 = new MemberOrderVO();
		memberOrderVO2.setMemberId(1);
		memberOrderVO2.setOrderNo(2);
		memberOrderVO2.setTotalOrderAmount(2000);
		memberOrderVO2.setCheckoutAmount( 1000);
		memberOrderVO2.setPromoCodeNo(2);
		memberOrderVO2.setShippingAddress("aaaaa");
		memberOrderVO2.setOrderNo(3);
		dao.update(memberOrderVO2);

		
//		dao.delete(3);

//		 
//		MemberOrderVO memberOrderVO3 = dao.findByPrimaryKey(4);
//		System.out.print(memberOrderVO3);
//		System.out.println("---------------------");

		// 
		List<MemberOrderVO> list = dao.getAll();
		for (MemberOrderVO amemberOrder : list) {
			System.out.print(amemberOrder);
			System.out.println();
		}
	}

	@Override
	public void insert(MemberOrderVO memberOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memberOrderVO.getMemberId());
			pstmt.setInt(2, memberOrderVO.getOrderNo());
			pstmt.setDouble(3, memberOrderVO.getTotalOrderAmount());
			pstmt.setDouble(4, memberOrderVO.getCheckoutAmount());
			pstmt.setInt(5, memberOrderVO.getPromoCodeNo());
			pstmt.setString(6, memberOrderVO.getShippingAddress());

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
	public void update(MemberOrderVO memberOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, memberOrderVO.getMemberId());
			pstmt.setInt(2, memberOrderVO.getOrderNo());
			pstmt.setDouble(3, memberOrderVO.getTotalOrderAmount());
			pstmt.setDouble(4, memberOrderVO.getCheckoutAmount());
			pstmt.setInt(5, memberOrderVO.getPromoCodeNo());
			pstmt.setString(6, memberOrderVO.getShippingAddress());
			pstmt.setInt(7, memberOrderVO.getOrderNo());

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
	public void delete(Integer orderNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, orderNo);

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
	public MemberOrderVO findByPrimaryKey(Integer orderNo) {
		MemberOrderVO memberOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, orderNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memberOrderVO = new MemberOrderVO();
				memberOrderVO.setMemberId(rs.getInt("member_id"));
				memberOrderVO.setOrderNo(rs.getInt("order_status"));
				memberOrderVO.setTotalOrderAmount(rs.getInt("total_order_amount"));
				memberOrderVO.setCheckoutAmount(rs.getInt("checkout_amount"));
				memberOrderVO.setPromoCodeNo(rs.getInt("promo_code_no"));
				memberOrderVO.setShippingAddress(rs.getString("shipping_address"));
				memberOrderVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				memberOrderVO.setOrderNo(rs.getInt("order_no"));
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
		return memberOrderVO;
	}

	@Override
	public List<MemberOrderVO> getAll() {
		List<MemberOrderVO> list = new ArrayList<MemberOrderVO>();
		MemberOrderVO memberOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				memberOrderVO = new MemberOrderVO();
				memberOrderVO.setMemberId(rs.getInt("member_id"));
				memberOrderVO.setOrderNo(rs.getInt("order_status"));
				memberOrderVO.setTotalOrderAmount(rs.getInt("total_order_amount"));
				memberOrderVO.setCheckoutAmount(rs.getInt("checkout_amount"));
				memberOrderVO.setPromoCodeNo(rs.getInt("promo_code_no"));
				memberOrderVO.setShippingAddress(rs.getString("shipping_address"));
				memberOrderVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				memberOrderVO.setOrderNo(rs.getInt("order_no"));
				list.add(memberOrderVO); // Store the row in the list
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
