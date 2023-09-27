package com.cooklab.promo_code_used.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;




public class PromoCodeUsedJDBCDAOIm implements PromoCodeUsedDAO{

	private static final String INSERT_STMT = "INSERT INTO promo_code_used (member_id,promo_code_no) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT promo_code_used_no,member_id,promo_code_no,created_timestamp from promo_code_used order by promo_code_used_no ";
	private static final String GET_ONE_STMT = "SELECT promo_code_used_no,member_id,promo_code_no,created_timestamp from promo_code_used where promo_code_used_no = ?";
	private static final String DELETE = "DELETE FROM promo_code_used where promo_code_used_no = ?";
	private static final String UPDATE = "UPDATE promo_code_used set member_id=?, promo_code_no=? where promo_code_used_no = ?";
	
	public static void main(String[] args) {
		PromoCodeUsedJDBCDAOIm dao = new PromoCodeUsedJDBCDAOIm();

		// 
//		PromoCodeUsedVO promoCodeUsedVO1 = new PromoCodeUsedVO();
//		promoCodeUsedVO1.setMemberId(1);
//		promoCodeUsedVO1.setPromoCodeNo(2);
//		dao.insert(promoCodeUsedVO1);

		// 
		PromoCodeUsedVO promoCodeUsedVO2 = new PromoCodeUsedVO();
		promoCodeUsedVO2.setMemberId(3);
		promoCodeUsedVO2.setPromoCodeNo(1);
		promoCodeUsedVO2.setPromoCodeUsedNo(1);
		dao.update(promoCodeUsedVO2);

		// 
//		dao.delete(1);

		//
//		PromoCodeUsedVO promoCodeUsedVO3 = dao.findByPrimaryKey(2);
//		System.out.print(promoCodeUsedVO3);
//		System.out.println("---------------------");

		//
//		List<PromoCodeUsedVO> list = dao.getAll();
//		for (PromoCodeUsedVO aPromoCodeUsed : list) {
//			System.out.print(aPromoCodeUsed);
//			System.out.println();
//		}
	}

	@Override
	public void insert(PromoCodeUsedVO promoCodeUsedVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, promoCodeUsedVO.getMemberId());
			pstmt.setInt(2, promoCodeUsedVO.getPromoCodeNo());

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
	public void update(PromoCodeUsedVO promoCodeUsedVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, promoCodeUsedVO.getMemberId());
			pstmt.setInt(2, promoCodeUsedVO.getPromoCodeNo());
			pstmt.setInt(3, promoCodeUsedVO.getPromoCodeUsedNo());
			
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
	public void delete(Integer promoCodeUsedNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, promoCodeUsedNo);

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
	public PromoCodeUsedVO findByPrimaryKey(Integer promoCodeUsedNo) {
		PromoCodeUsedVO promoCodeUsedVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, promoCodeUsedNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				promoCodeUsedVO = new PromoCodeUsedVO();
				promoCodeUsedVO.setMemberId(rs.getInt("member_id"));
				promoCodeUsedVO.setPromoCodeNo(rs.getInt("promo_code_no"));
				promoCodeUsedVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				promoCodeUsedVO.setPromoCodeUsedNo(rs.getInt("promo_code_used_no"));
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
		return promoCodeUsedVO;
	}

	@Override
	public List<PromoCodeUsedVO> getAll() {
		List<PromoCodeUsedVO> list = new ArrayList<PromoCodeUsedVO>();
		PromoCodeUsedVO promoCodeUsedVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				promoCodeUsedVO = new PromoCodeUsedVO();
				promoCodeUsedVO.setMemberId(rs.getInt("member_id"));
				promoCodeUsedVO.setPromoCodeNo(rs.getInt("promo_code_no"));
				promoCodeUsedVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				promoCodeUsedVO.setPromoCodeUsedNo(rs.getInt("promo_code_used_no"));
				list.add(promoCodeUsedVO); // Store the row in the list
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
