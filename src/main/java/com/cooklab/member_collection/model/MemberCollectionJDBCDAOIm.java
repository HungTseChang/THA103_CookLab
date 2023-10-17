package com.cooklab.member_collection.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.Util;



public class MemberCollectionJDBCDAOIm implements MemberCollectionDAO{

	private static final String INSERT_STMT = "INSERT INTO member_collection (member_id_collectioned,member_id) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT member_collection_no,member_id_collectioned,member_id,created_timestamp FROM member_collection order by member_collection_no";
	private static final String GET_ONE_STMT = "SELECT member_collection_no,member_id_collectioned,member_id,created_timestamp  FROM member_collection WHERE member_collection_no = ?";
	private static final String DELETE = "DELETE FROM memeber_collection where member_collection_no = ?";
	private static final String UPDATE = "UPDATE member_collection set member_id_collectioned=?,member_id=? where member_collection_no = ?";



	public static void main(String[] args) {
		MemberCollectionJDBCDAOIm dao = new MemberCollectionJDBCDAOIm();

		// 
		MemberCollectionVO memberCollectionVO = new MemberCollectionVO();
		memberCollectionVO.setMemberIdCollectioned(2);
//		memberCollectionVO.setMemberId(1);
		dao.insert(memberCollectionVO);

		// 
		MemberCollectionVO memberCollectionVO2 = new MemberCollectionVO();
		memberCollectionVO2.setMemberIdCollectioned(2);
//		memberCollectionVO2.setMemberId(1);
		memberCollectionVO2.setMemberCollectionNo(1);
		dao.insert(memberCollectionVO2);

		//
//		dao.delete(1);

		// 
		MemberCollectionVO memberCollectionVO3 = dao.findByPrimaryKey(1);
		System.out.print(memberCollectionVO3);
		System.out.println("---------------------");

		//
		List<MemberCollectionVO> list = dao.getAll();
		for (MemberCollectionVO aMemberCollection : list) {
			System.out.print(aMemberCollection);
			System.out.println();
		}

	}



	@Override
	public void insert(MemberCollectionVO memberCollectionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memberCollectionVO.getMemberIdCollectioned());
//			pstmt.setInt(2, memberCollectionVO.getMemberId());

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
	public void update(MemberCollectionVO memberCollectionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, memberCollectionVO.getMemberIdCollectioned());
//			pstmt.setInt(2, memberCollectionVO.getMemberId());
			pstmt.setInt(3, memberCollectionVO.getMemberCollectionNo());


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
	public void delete(Integer memberCollectionNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memberCollectionNo);

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
	public MemberCollectionVO findByPrimaryKey(Integer memberCollectionNo) {
		MemberCollectionVO memberCollectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memberCollectionNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				memberCollectionVO = new MemberCollectionVO();
				memberCollectionVO.setMemberCollectionNo(rs.getInt("member_collection_no"));
				memberCollectionVO.setMemberIdCollectioned(rs.getInt("member_id_collectioned"));
//				memberCollectionVO.setMemberId(rs.getInt("member_id"));
				memberCollectionVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
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
		return memberCollectionVO;
	}



	@Override
	public List<MemberCollectionVO> getAll() {

		List<MemberCollectionVO> list = new ArrayList<MemberCollectionVO>();
		MemberCollectionVO memberCollectionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberCollectionVO = new MemberCollectionVO();
				memberCollectionVO.setMemberCollectionNo(rs.getInt("member_collection_no"));
				memberCollectionVO.setMemberIdCollectioned(rs.getInt("member_id_collectioned"));
//				memberCollectionVO.setMemberId(rs.getInt("member_id"));
				memberCollectionVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				list.add(memberCollectionVO); // Store the row in the list
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
