package com.cooklab.admins.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;

public class AdminsJDBCDAOIm implements AdminsDAO {
	private static final String INSERT_STMT = "insert into admins(admin_nickname, permission_no, admin_account, admin_password) values (?, ?, ?, ?)";
	private static final String UPDATE = "update admins set admin_nickname =?, permission_no =?, admin_account =? , admin_password =?  where admin_no = ?";
	private static final String DELETE = "delete from admins where admin_no = ?";
	private static final String GET_ONE_STMT = "select admin_no, admin_nickname,  permission_no, admin_account, admin_password, created_timestamp FROM admins where admin_no = ?";
	private static final String GET_ALL_STMT = "select admin_no, admin_nickname,  permission_no, admin_account, admin_password, created_timestamp FROM admins order by admin_no";

	public void insert(AdminsVO admins) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, admins.getAdminNickname());
			pstmt.setInt(2, admins.getPermissionNo());
			pstmt.setString(3, admins.getAdminAccount());
			pstmt.setString(4, admins.getAdminPassword());

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

	public void update(AdminsVO admins) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, admins.getAdminNickname());
			pstmt.setInt(2, admins.getPermissionNo());
			pstmt.setString(3, admins.getAdminAccount());
			pstmt.setString(4, admins.getAdminPassword());
			pstmt.setInt(5, admins.getAdminNo());

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

	public void delete(Integer adminNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, adminNo);

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

	public AdminsVO findByPrimaryKey(Integer AdminNo) {
		AdminsVO adminsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, AdminNo);

			rs = pstmt.executeQuery();

			// 放入對應的MySQL表格欄位名稱
			while (rs.next()) {
				adminsVO = new AdminsVO();
				adminsVO.setAdminNo(rs.getInt("Admin_no"));
				adminsVO.setAdminNickname(rs.getString("Admin_nickname"));
				adminsVO.setPermissionNo(rs.getInt("Permission_no"));
				adminsVO.setAdminAccount(rs.getString("Admin_account"));
				adminsVO.setAdminPassword(rs.getString("Admin_password"));
				adminsVO.setCreatedTimestamp(rs.getTimestamp("Created_timestamp"));
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
		return adminsVO;
	}

	public List<AdminsVO> getAll() {
		List<AdminsVO> list = new ArrayList<AdminsVO>();
		AdminsVO aVO = null;

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
				aVO = new AdminsVO();
				aVO.setAdminNo(rs.getInt("Admin_no"));
				aVO.setAdminNickname(rs.getString("Admin_nickname"));
				aVO.setPermissionNo(rs.getInt("Permission_no"));
				aVO.setAdminAccount(rs.getString("Admin_account"));
				aVO.setAdminPassword(rs.getString("Admin_password"));
				aVO.setCreatedTimestamp(rs.getTimestamp("Created_timestamp"));
				list.add(aVO);
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

		AdminsJDBCDAOIm AdDAOIm = new AdminsJDBCDAOIm();

//		 新增
//		AdminsVO AdVO1 = new AdminsVO();
//		AdVO1.setAdminNickname(String.valueOf("王曉明"));
//		AdVO1.setPermissionNo(Integer.valueOf(2));
//		AdVO1.setAdminAccount(String.valueOf("ABCD"));
//		AdVO1.setAdminPassword(String.valueOf("DDDD"));
//		
//		
//		AdDAOIm.insert(AdVO1);

//		// 修改
//		AdminsVO AdVO2 = new AdminsVO();

//		AdVO1.setAdminNickname(String.valueOf("王大明"));
//		AdVO1.setPermissionNo(Integer.valueOf(3));
//		AdVO1.setAdminAccount(String.valueOf("DCBAABCD"));
//		AdVO1.setAdminPassword(String.valueOf("CCCC"));
//		
//		AdDAOIm.insert(AdVO2);
//
//		// 刪除
//		AdDAOIm.delete(4);
//
//		// 查詢單一資料
		AdminsVO AdVO3 = AdDAOIm.findByPrimaryKey(1);
		System.out.print(AdVO3.getAdminNo() + ",");
		System.out.print(AdVO3.getAdminNickname() + ",");
		System.out.println(AdVO3.getPermissionNo() + ",");
		System.out.println(AdVO3.getAdminAccount() + ",");
		System.out.println(AdVO3.getAdminPassword() + ",");
		System.out.println(AdVO3.getCreatedTimestamp() + ",");

		System.out.println("---------------------");
//
//		// 查詢全部資料
//		List<AdminsVO> list = AdDAOIm.getAll();
//		for (AdminsVO aAdVO3 : list) {
//			System.out.print(aAdVO3.getAdminNo() + ",");
//			System.out.print(aAdVO3.getAdminNickname() + ",");
//			System.out.print(aAdVO3.getPermissionNo() + ",");
//			System.out.print(aAdVO3.getAdminAccount() + ",");
//			System.out.print(aAdVO3.getAdminPassword() + ",");
//			System.out.println(aAdVO3.getCreatedTimestamp() + ",");
//
//			System.out.println(list.size());
//		}
//	}
	}
}
