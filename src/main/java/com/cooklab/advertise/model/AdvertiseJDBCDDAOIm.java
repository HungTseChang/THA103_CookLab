package com.cooklab.advertise.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;

public class AdvertiseJDBCDDAOIm implements AdvertiseDAO {

	private static final String INSERT_STMT = "insert into advertise(advertise_name, advertise_shelf_time, advertise_offsale_time, advertise_img,  advertise_url) values (?, ?, ?, ?, ?)";
	private static final String UPDATE = "update advertise set advertise_name =?,advertise_shelf_time =?, advertise_offsale_time =?,  advertise_img =?, advertise_url =? where question_no = ?";
	private static final String DELETE = "delete from advertise where advertise_no = ?";
	private static final String GET_ONE_STMT = "select advertise_no, advertise_name, advertise_shelf_time, advertise_offsale_time, advertise_img, advertise_url, created_timestamp FROM advertise where advertise_no = ?";
	private static final String GET_ALL_STMT = "select advertise_no, advertise_name, advertise_shelf_time, advertise_offsale_time, advertise_img, advertise_url, created_timestamp FROM advertise order by advertise_no";

	public void insert(AdvertiseVO advertise) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, advertise.getAdvertiseName());
			pstmt.setDate(2, advertise.getAdvertiseShelfTime());
			pstmt.setDate(3, advertise.getAdvertiseOffsaleTime());
			pstmt.setString(4, advertise.getAdvertiseImg());
			pstmt.setString(5, advertise.getAdvertiseUrl());

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

	public void update(AdvertiseVO advertise) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, advertise.getAdvertiseName());
			pstmt.setDate(2, advertise.getAdvertiseShelfTime());
			pstmt.setDate(3, advertise.getAdvertiseOffsaleTime());
			pstmt.setString(4, advertise.getAdvertiseImg());
			pstmt.setString(5, advertise.getAdvertiseUrl());
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

	public void delete(Integer advertiseNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, advertiseNo);

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

	public AdvertiseVO findByPrimaryKey(Integer advertiseNo) {
		AdvertiseVO advertiseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, advertiseNo);

			rs = pstmt.executeQuery();

			// 放入對應的MySQL表格欄位名稱
			while (rs.next()) {
				advertiseVO = new AdvertiseVO();
				advertiseVO.setAdvertiseNo(rs.getInt("advertise_no"));
				advertiseVO.setAdvertiseName(rs.getString("advertise_name"));
				advertiseVO.setAdvertiseShelfTime(rs.getDate("advertise_shelf_time"));
				advertiseVO.setAdvertiseOffsaleTime(rs.getDate("advertise_offsale_time"));
				advertiseVO.setAdvertiseImg(rs.getString("advertise_img"));
				advertiseVO.setAdvertiseUrl(rs.getString("advertise_url"));
				advertiseVO.setCreatedTimestamp(rs.getDate("Created_timestamp"));
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
		return advertiseVO;
	}

	public List<AdvertiseVO> getAll() {
		List<AdvertiseVO> list = new ArrayList<AdvertiseVO>();
		AdvertiseVO aVO = null;

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
				aVO = new AdvertiseVO();
				aVO.setAdvertiseNo(rs.getInt("advertise_no"));
				aVO.setAdvertiseName(rs.getString("advertise_name"));
				aVO.setAdvertiseShelfTime(rs.getDate("advertise_shelf_time"));
				aVO.setAdvertiseOffsaleTime(rs.getDate("advertise_offsale_time"));
				aVO.setAdvertiseImg(rs.getString("advertise_img"));
				aVO.setAdvertiseUrl(rs.getString("advertise_url"));
				aVO.setCreatedTimestamp(rs.getDate("Created_timestamp"));
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

		AdvertiseJDBCDDAOIm AdDAOIm = new AdvertiseJDBCDDAOIm();

//		 新增
//		AdvertiseVO AdVO1 = new AdvertiseVO();
//		AdVO1.setAdvertiseName(String.valueOf("促銷廣告"));
//		AdVO1.setAdvertiseShelfTime(java.sql.Date.valueOf("2023-05-05"));
//		AdVO1.setAdvertiseOffsaleTime(java.sql.Date.valueOf("2023-02-02"));
//		AdVO1.setAdvertiseImg(String.valueOf("images.google.com"));
//		AdVO1.setAdvertiseUrl(String.valueOf("https://www.youtube.com/"));
//		
//		
//		AdDAOIm.insert(AdVO1);

		// 修改
//		AdvertiseVO AdVO2 = new AdvertiseVO();
//		AdVO2.setAdvertiseName(String.valueOf("折扣廣告"));
//		AdVO2.setAdvertiseShelfTime(java.sql.Date.valueOf("2023-06-06"));
//		AdVO2.setAdvertiseOffsaleTime(java.sql.Date.valueOf("2023-04-04"));
//		AdVO2.setAdvertiseImg(String.valueOf("images.google.com"));
//		AdVO2.setAdvertiseUrl(String.valueOf("https://www.google.com/"));
//		AdDAOIm.insert(AdVO2);

		// 刪除
//		AdDAOIm.delete(4);

		// 查詢單一資料
//		AdvertiseVO AdVO3 = AdDAOIm.findByPrimaryKey(1);
//		System.out.print(AdVO3.getAdvertiseNo() + ",");
//		System.out.print(AdVO3.getAdvertiseName() + ",");
//		System.out.print(AdVO3.getAdvertiseShelfTime() + ",");
//		System.out.println(AdVO3.getAdvertiseOffsaleTime() + ",");
//		System.out.println(AdVO3.getAdvertiseImg() + ",");
//		System.out.println(AdVO3.getAdvertiseUrl() + ",");
//		System.out.println(AdVO3.getCreatedTimestamp() + ",");
//		
//		System.out.println("---------------------");

		// 查詢全部資料
		List<AdvertiseVO> list = AdDAOIm.getAll();
		for (AdvertiseVO aAdVO3 : list) {
			System.out.print(aAdVO3.getAdvertiseNo() + ",");
			System.out.print(aAdVO3.getAdvertiseName() + ",");
			System.out.print(aAdVO3.getAdvertiseShelfTime() + ",");
			System.out.println(aAdVO3.getAdvertiseOffsaleTime() + ",");
			System.out.println(aAdVO3.getAdvertiseImg() + ",");
			System.out.println(aAdVO3.getAdvertiseUrl() + ",");
			System.out.println(aAdVO3.getCreatedTimestamp() + ",");
			System.out.println();
		}
	}

}
