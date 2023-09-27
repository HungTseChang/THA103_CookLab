package com.cooklab.permission.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;

public class PermissionJDBCDDAOIm implements PermissionDAO {
	private static final String INSERT_STMT = "insert into permission(permission_tittle, super_admin, cancel_all_permission, membership_management, advertising_management, reporting_management, article_management, recipe_management) values (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "update permission set permission_no =?,permission_tittle =?, super_admin =?, cancel_all_permission =? , membership_management =? , advertising_management =? , reporting_management = ? , article_management =? , recipe_management =? where question_no = ?";
	private static final String DELETE = "delete from permission where permission_no = ?";
	private static final String GET_ONE_STMT = "select permission_no, permission_tittle, super_admin, cancel_all_permission, membership_management, advertising_management, reporting_management, article_management, recipe_management, created_timestamp FROM permission where permission_no = ?";
	private static final String GET_ALL_STMT = "select permission_no, permission_tittle, super_admin, cancel_all_permission, membership_management, advertising_management, reporting_management, article_management, recipe_management, created_timestamp FROM permission order by permission_no";

	public void insert(PermissionVO permission) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, "permission.getPermissionTitle()");
			pstmt.setInt(2, permission.getSuperAdmin());
			pstmt.setInt(3, permission.getCancelAllPermission());
			pstmt.setInt(4, permission.getMembershipManagement());
			pstmt.setInt(5, permission.getAdvertisingManagement());
			pstmt.setInt(6, permission.getReportingManagement());
			pstmt.setInt(7, permission.getArticleManagement());
			pstmt.setInt(8, permission.getRecipeManagement());

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

	public void update(PermissionVO permission) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, permission.getPermissionTitle());
			pstmt.setInt(2, permission.getSuperAdmin());
			pstmt.setInt(3, permission.getCancelAllPermission());
			pstmt.setInt(4, permission.getMembershipManagement());
			pstmt.setInt(5, permission.getAdvertisingManagement());
			pstmt.setInt(6, permission.getReportingManagement());
			pstmt.setInt(7, permission.getArticleManagement());
			pstmt.setInt(8, permission.getRecipeManagement());
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

	public void delete(Integer permissionNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, permissionNo);

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

	public PermissionVO findByPrimaryKey(Integer permissionNo) {
		PermissionVO permissionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, permissionNo);

			rs = pstmt.executeQuery();

			// 放入對應的MySQL表格欄位名稱
			while (rs.next()) {
				permissionVO = new PermissionVO();
				permissionVO.setPermissionTitle(rs.getString("permission_tittle"));
				permissionVO.setSuperAdmin(rs.getInt("super_admin"));
				permissionVO.setCancelAllPermission(rs.getInt("cancel_all_permission"));
				permissionVO.setMembershipManagement(rs.getInt("membership_management"));
				permissionVO.setAdvertisingManagement(rs.getInt("advertising_management"));
				permissionVO.setReportingManagement(rs.getInt("reporting_management"));
				permissionVO.setArticleManagement(rs.getInt("article_management"));
				permissionVO.setRecipeManagement(rs.getInt("recipe_management"));
				permissionVO.setCreatedTimestamp(rs.getDate("created_timestamp"));
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
		return permissionVO;
	}

	public List<PermissionVO> getAll() {
		List<PermissionVO> list = new ArrayList<PermissionVO>();
		PermissionVO PVO = null;

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
				PVO = new PermissionVO();
				PVO.setPermissionNo(rs.getInt("permission_no"));
				PVO.setPermissionTitle(rs.getString("permission_tittle"));
				PVO.setSuperAdmin(rs.getInt("super_admin"));
				PVO.setCancelAllPermission(rs.getInt("cancel_all_permission"));
				PVO.setMembershipManagement(rs.getInt("membership_management"));
				PVO.setAdvertisingManagement(rs.getInt("advertising_management"));
				PVO.setReportingManagement(rs.getInt("reporting_management"));
				PVO.setArticleManagement(rs.getInt("article_management"));
				PVO.setRecipeManagement(rs.getInt("recipe_management"));
				PVO.setCreatedTimestamp(rs.getDate("created_timestamp"));
				list.add(PVO);
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

		PermissionJDBCDDAOIm PeDAOIm = new PermissionJDBCDDAOIm();

//		 新增
//		PermissionVO PeVO1 = new PermissionVO();
//		PeVO1.setPermissionTittle(String.valueOf("董事長秘書"));
//		PeVO1.setSuperAdmin(Integer.valueOf(0));
//		PeVO1.setCancelAllPermission(Integer.valueOf(0));
//		PeVO1.setMembershipManagement(Integer.valueOf(1));
//		PeVO1.setAdvertisingManagement(Integer.valueOf(0));
//		PeVO1.setReportingManagement(Integer.valueOf(1));
//		PeVO1.setArticleManagement(Integer.valueOf(0));
//		PeVO1.setRecipeManagement(Integer.valueOf(1));
//
//		PeDAOIm.insert(PeVO1);

		// 修改
//		PermissionVO PeVO2 = new PermissionVO();
//		PeVO2.setPermissionTittle(String.valueOf("董事長"));
//		PeVO2.setSuperAdmin(Integer.valueOf(0));
//		PeVO2.setCancelAllPermission(Integer.valueOf(0));
//		PeVO2.setMembershipManagement(Integer.valueOf(1));
//		PeVO2.setAdvertisingManagement(Integer.valueOf(0));
//		PeVO2.setReportingManagement(Integer.valueOf(1));
//		PeVO2.setArticleManagement(Integer.valueOf(0));
//		PeVO2.setRecipeManagement(Integer.valueOf(1));
//		PeDAOIm.insert(PeVO2);

		// 刪除
//		PeDAOIm.delete(4);

		// 查詢單一資料
//		PermissionVO PeVO3 = PeDAOIm.findByPrimaryKey(2);
//		System.out.print(PeVO3.getPermissionNo() + ",");
//		System.out.print(PeVO3.getPermissionTittle() + ",");
//		System.out.print(PeVO3.getSuperAdmin() + ",");
//		System.out.println(PeVO3.getCancelAllPermission() + ",");
//		System.out.println(PeVO3.getMembershipManagement() + ",");
//		System.out.println(PeVO3.getAdvertisingManagement() + ",");
//		System.out.println(PeVO3.getReportingManagement() + ",");
//		System.out.println(PeVO3.getArticleManagement() + ",");
//		System.out.println(PeVO3.getRecipeManagement() + ",");
//		System.out.println(PeVO3.getCreatedTimestamp()+ ",");
//		
//		System.out.println("---------------------");

		// 查詢全部資料
		List<PermissionVO> list = PeDAOIm.getAll();
		for (PermissionVO PeVO3 : list) {
			System.out.print(PeVO3.getPermissionNo() + ",");
			System.out.print(PeVO3.getPermissionTitle() + ",");
			System.out.print(PeVO3.getSuperAdmin() + ",");
			System.out.println(PeVO3.getCancelAllPermission() + ",");
			System.out.println(PeVO3.getMembershipManagement() + ",");
			System.out.println(PeVO3.getAdvertisingManagement() + ",");
			System.out.println(PeVO3.getReportingManagement() + ",");
			System.out.println(PeVO3.getArticleManagement() + ",");
			System.out.println(PeVO3.getRecipeManagement() + ",");
			System.out.println(PeVO3.getCreatedTimestamp() + ",");
		}
	}
}