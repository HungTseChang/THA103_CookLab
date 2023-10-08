package com.cooklab.hashtag.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;



public class HashtagJDBCDAOIm implements HashtagDAO {

	private static final String INSERT_STMT = "INSERT INTO hashtag (hashtag_name,search_count,use_count) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT hashtag_no, hashtag_name, search_count, use_count,created_timestamp FROM hashtag order by hashtag_no";
	private static final String GET_ONE_STMT = "SELECT hashtag_no, hashtag_name, search_count, use_count,created_timestamp FROM hashtag where hashtag_no = ?";
	private static final String DELETE = "DELETE FROM hashtag where hashtag_no = ?";
	private static final String UPDATE = "UPDATE hashtag set hashtag_name=?, search_count=?, use_count=? where hashtag_no = ?";

	// 新增===========================================================================================================
	@Override
	public void insert(HashtagVO hashtagVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, hashtagVO.getHashtangName());
			pstmt.setInt(2, hashtagVO.getSearchCount());
			pstmt.setInt(3, hashtagVO.getUseCount());

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

	// 修改=================================================================
	@Override
	public void update(HashtagVO hashtagVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, hashtagVO.getHashtangName());
			pstmt.setInt(2, hashtagVO.getSearchCount());
			pstmt.setInt(3, hashtagVO.getUseCount());
			pstmt.setInt(4, hashtagVO.getHashtagNO());

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

	// 刪除===========================================================
	@Override
	public void delete(Integer hashtagNO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, hashtagNO);

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

	// 用主鍵查詢===========================================================================================================
	@Override
	public HashtagVO findByPrimaryKey(Integer hashtagNO) {

		HashtagVO hVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, hashtagNO);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				hVO = new HashtagVO();

				hVO.setHashtagNO(rs.getInt("hashtag_no"));
				hVO.setHashtangName(rs.getString("hashtag_name"));
				hVO.setSearchCount(rs.getInt("search_count"));
				hVO.setUseCount(rs.getInt("use_count"));
				hVO.setCreateStamp(rs.getTimestamp("created_timestamp"));

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
		return hVO;
	}

	// 查詢所有===========================================================================================================
	@Override
	public List<HashtagVO> getAll() {
		List<HashtagVO> list = new ArrayList<HashtagVO>();
		HashtagVO hashtagVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				hashtagVO = new HashtagVO();
				hashtagVO.setHashtagNO(rs.getInt("hashtag_no"));
				hashtagVO.setHashtangName(rs.getString("hashtag_name"));
				hashtagVO.setSearchCount(rs.getInt("search_count"));
				hashtagVO.setUseCount(rs.getInt("use_count"));
				hashtagVO.setCreateStamp(rs.getTimestamp("created_timestamp"));

				list.add(hashtagVO); // Store the row in the list
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
		HashtagJDBCDAOIm dao = new HashtagJDBCDAOIm();

		// 新增
//		HashtagVO hV01 = new HashtagVO();
//		hV01.setHashtangName("標籤6");
//		hV01.setSearchCount(5);
//		hV01.setUseCount(6);
//		dao.insert(hV01);
//		
		// 修改
//		HashtagVO hV02 = new HashtagVO();
//		hV02.setHashtangName("標籤修改TEST1");
//		hV02.setSearchCount(8);
//		hV02.setUseCount(8);
//		hV02.setHashtagNO(2);
//		dao.update(hV02);

		// 刪除
//		dao.delete(2);

//		 查詢
//		HashtagVO hV03 =  dao.findByPrimaryKey(4);
//		System.out.println(hV03);

		// 查詢

		List<HashtagVO> list = dao.getAll();
		for (HashtagVO aRcr : list) {
			System.out.println(aRcr);
		}

	}
}
