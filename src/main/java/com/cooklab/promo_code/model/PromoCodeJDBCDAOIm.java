package com.cooklab.promo_code.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;


public class PromoCodeJDBCDAOIm implements PromoCodeDAO {


	private static final String INSERT_STMT = "INSERT INTO promo_code (promo_code_serial_number,start_time,end_time,percentage_discount_amount,fixed_discount_amount,usages_allowed,minimum_consumption) VALUES (?, ?, ?,?, ?, ?,?)";
	private static final String GET_ALL_STMT = "SELECT promo_code_no, promo_code_serial_number, start_time, end_time, percentage_discount_amount, fixed_discount_amount, usages_allowed, minimum_consumption,created_timestamp FROM promo_code order by promo_code_no";
	private static final String GET_ONE_STMT = "SELECT promo_code_no, promo_code_serial_number, start_time, end_time, percentage_discount_amount, fixed_discount_amount, usages_allowed, minimum_consumption,created_timestamp FROM promo_code WHERE promo_code_no = ?";
	private static final String DELETE = "DELETE FROM promo_code where promo_code_no = ?";
	private static final String UPDATE = "UPDATE promo_code set promo_code_serial_number=?, start_time=?, end_time=?, percentage_discount_amount=?, fixed_discount_amount=?, usages_allowed=?, minimum_consumption=? where promo_code_no = ?";

	public static void main(String[] args) {
		PromoCodeJDBCDAOIm dao = new PromoCodeJDBCDAOIm();

		// 
		PromoCodeVO promoCodeVO1 = new PromoCodeVO();
		promoCodeVO1.setPromoCodeSerialNumber("000001");
		promoCodeVO1.setStartTime(java.sql.Timestamp.valueOf("2023-09-09 12:34:56"));
		promoCodeVO1.setEndTime(java.sql.Timestamp.valueOf("2023-09-12 12:34:56"));
		promoCodeVO1.setPercentageDiscountAmount(0);
		promoCodeVO1.setFixedDiscountAmount(1000);
		promoCodeVO1.setUsagesAllowed(1000);
		promoCodeVO1.setMinimumConsumption(0);
		dao.insert(promoCodeVO1);

		// 
		PromoCodeVO  promoCodeVO2 = new PromoCodeVO();
		promoCodeVO2.setPromoCodeSerialNumber("000001");
		promoCodeVO2.setStartTime(java.sql.Timestamp.valueOf("2023-09-09 12:34:56"));
		promoCodeVO2.setEndTime(java.sql.Timestamp.valueOf("2023-09-20 12:34:56"));
		promoCodeVO2.setPercentageDiscountAmount(0);
		promoCodeVO2.setFixedDiscountAmount(1000);
		promoCodeVO2.setUsagesAllowed(1000);
		promoCodeVO2.setMinimumConsumption(0);
		promoCodeVO2.setPromoCodeNo(1);
		dao.update(promoCodeVO2);

		// 
//		dao.delete(1);

		// 
		PromoCodeVO promoCodeVO3 = dao.findByPrimaryKey(3);
		System.out.print(promoCodeVO3);
		System.out.println("---------------------");

		// 
		List<PromoCodeVO> list = dao.getAll();
		for (PromoCodeVO aPromoCode : list) {
			System.out.print(aPromoCode);
			System.out.println();
		}
	}

	@Override
	public void insert(PromoCodeVO promoCodeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, promoCodeVO.getPromoCodeSerialNumber());
			pstmt.setTimestamp(2, promoCodeVO.getStartTime());
			pstmt.setTimestamp(3, promoCodeVO.getEndTime());
			pstmt.setDouble(4, promoCodeVO.getPercentageDiscountAmount());
			pstmt.setDouble(5, promoCodeVO.getFixedDiscountAmount());
			pstmt.setInt(6, promoCodeVO.getUsagesAllowed());
			pstmt.setInt(7, promoCodeVO.getMinimumConsumption());

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
	public void update(PromoCodeVO promoCodeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, promoCodeVO.getPromoCodeSerialNumber());
			pstmt.setTimestamp(2, promoCodeVO.getStartTime());
			pstmt.setTimestamp(3, promoCodeVO.getEndTime());
			pstmt.setDouble(4, promoCodeVO.getPercentageDiscountAmount());
			pstmt.setDouble(5, promoCodeVO.getFixedDiscountAmount());
			pstmt.setInt(6, promoCodeVO.getUsagesAllowed());
			pstmt.setInt(7, promoCodeVO.getMinimumConsumption());
			pstmt.setInt(8, promoCodeVO.getPromoCodeNo());

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
	public void delete(Integer promoCodeNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, promoCodeNo);

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
	public PromoCodeVO findByPrimaryKey(Integer promoCodeNo) {
		PromoCodeVO promoCodeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, promoCodeNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				promoCodeVO = new PromoCodeVO();
				promoCodeVO.setPromoCodeSerialNumber(rs.getString("promo_code_serial_number"));
				promoCodeVO.setStartTime(rs.getTimestamp("start_time"));
				promoCodeVO.setEndTime(rs.getTimestamp("end_time"));
				promoCodeVO.setPercentageDiscountAmount(rs.getInt("percentage_discount_amount"));
				promoCodeVO.setFixedDiscountAmount(rs.getInt("fixed_discount_amount"));
				promoCodeVO.setUsagesAllowed(rs.getInt("usages_allowed"));
				promoCodeVO.setMinimumConsumption(rs.getInt("minimum_consumption"));
				promoCodeVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				promoCodeVO.setPromoCodeNo(rs.getInt("promo_code_no"));
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
		return promoCodeVO;
	}

	@Override
	public List<PromoCodeVO> getAll() {
		List<PromoCodeVO> list = new ArrayList<PromoCodeVO>();
		PromoCodeVO promoCodeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				promoCodeVO = new PromoCodeVO();
				promoCodeVO.setPromoCodeSerialNumber(rs.getString("promo_code_serial_number"));
				promoCodeVO.setStartTime(rs.getTimestamp("start_time"));
				promoCodeVO.setEndTime(rs.getTimestamp("end_time"));
				promoCodeVO.setPercentageDiscountAmount(rs.getInt("percentage_discount_amount"));
				promoCodeVO.setFixedDiscountAmount(rs.getInt("fixed_discount_amount"));
				promoCodeVO.setUsagesAllowed(rs.getInt("usages_allowed"));
				promoCodeVO.setMinimumConsumption(rs.getInt("minimum_consumption"));
				promoCodeVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				promoCodeVO.setPromoCodeNo(rs.getInt("promo_code_no"));
				list.add(promoCodeVO); // Store the row in the list
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
