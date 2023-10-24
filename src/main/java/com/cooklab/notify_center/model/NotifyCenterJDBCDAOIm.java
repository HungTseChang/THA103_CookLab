package com.cooklab.notify_center.model;

import java.util.*;
import java.sql.*;

import com.cooklab.util.Util;

public class NotifyCenterJDBCDAOIm implements NotifyCenterDAO {
	private static final String INSERT_STMT = "INSERT INTO notify_center (member_id, notify_type, notify_read,notify_content) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT notify_no ,member_id, notify_type, notify_read, notify_content, created_timestamp From notify_center order by notify_no";
	private static final String GET_ONE_STMT = "SELECT notify_no ,member_id,notify_type,notify_read, notify_content, created_timestamp From notify_center where notify_no=?";
	private static final String DELETE = "DELETE FROM notify_center where notify_no = ?";
	private static final String UPDATE = "UPDATE notify_center set member_id=?,notify_type=?,notify_read=?,notify_content=?  where notify_no=?";

	@Override
	public Integer insert(NotifyCenterVO notifyCenterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, notifyCenterVO.getMemberId());
			pstmt.setInt(2, notifyCenterVO.getNotifyType());
			pstmt.setInt(3, notifyCenterVO.getNotifyRead());
			pstmt.setString(4, notifyCenterVO.getNotifyContent());

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
		return null;

	}

	@Override
	public Integer update(NotifyCenterVO notifyCenterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, notifyCenterVO.getMemberId());
			pstmt.setInt(2, notifyCenterVO.getNotifyType());
			pstmt.setInt(3, notifyCenterVO.getNotifyRead());
			pstmt.setString(4, notifyCenterVO.getNotifyContent());
			pstmt.setInt(5, notifyCenterVO.getNotifyNo());

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
		return null;

	}

	@Override
	public Integer delete(Integer notifyNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, notifyNo);

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
		return notifyNo;

	}

	@Override
	public NotifyCenterVO findByPrimaryKey(Integer notifyNo) {
		NotifyCenterVO notifyCenterVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, notifyNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				notifyCenterVO = new NotifyCenterVO();
				notifyCenterVO.setNotifyNo(rs.getInt("notify_no"));
				notifyCenterVO.setMemberId(rs.getInt("member_id"));
				notifyCenterVO.setNotifyType(rs.getByte("notify_type"));
				notifyCenterVO.setNotifyRead(rs.getByte("notify_read"));
				notifyCenterVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				notifyCenterVO.setNotifyContent(rs.getString("notify_content"));
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
		return notifyCenterVO;
	}

	@Override
	public List<NotifyCenterVO> getAll() {
		List<NotifyCenterVO> list = new ArrayList<NotifyCenterVO>();
		NotifyCenterVO notifyCenterVO = null;

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
				notifyCenterVO = new NotifyCenterVO();
				notifyCenterVO.setNotifyNo(rs.getInt("notify_no"));
				notifyCenterVO.setMemberId(rs.getInt("member_id"));
				notifyCenterVO.setNotifyType(rs.getByte("notify_type"));
				notifyCenterVO.setNotifyRead(rs.getByte("notify_read"));
				notifyCenterVO.setNotifyContent(rs.getString("notify_content"));
				notifyCenterVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				list.add(notifyCenterVO); // Store the row in the list
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

	public static void main(String[] args) {

		NotifyCenterJDBCDAOIm dao = new NotifyCenterJDBCDAOIm();

//		NotifyCenterVO notifyCenterVO= new NotifyCenterVO( 3, 1, 1 ,"�H�K�������n�b�N��6");
//		dao.insert(notifyCenterVO);

//		NotifyCenterVO notifyCenterVO= new NotifyCenterVO( 4, 1, 1 ,"�H�K�������n�b�N��6");
//		notifyCenterVO.setNotifyNo(1);
//		dao.update(notifyCenterVO);

//		dao.delete(6);

//		NotifyCenterVO notifyCenterVO = dao.findByPrimaryKey(3);
//		System.out.println(notifyCenterVO);

		List<NotifyCenterVO> list = dao.getAll();
		for (NotifyCenterVO aNotifyCenterVO : list) {
			System.out.println(aNotifyCenterVO);
		}

	}

}
