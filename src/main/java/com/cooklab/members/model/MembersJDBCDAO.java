package com.cooklab.members.model;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.cooklab.util.Util;

public class MembersJDBCDAO implements MembersDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO members (member_account, member_password, member_introduce, member_cellphone, member_mail, member_date, member_address, member_country, member_status, member_picture, member_nickname, member_gender) "
			+ "VALUES (?, ?, ?, ?, ?, ? , ? , ? , ? , ? , ? ,? )";
	private static final String GET_ALL_STMT = "SELECT member_id,member_account,member_password,member_introduce,member_cellphone,member_mail,member_date,member_address,member_country,member_status,member_picture,member_nickname,member_gender ,created_timestamp ,last_edit_timestamp From members order by member_id";
	private static final String GET_ONE_STMT = "SELECT member_id,member_account,member_password,member_introduce,member_cellphone,member_mail,member_date,member_address,member_country,member_status,member_picture,member_nickname,member_gender ,created_timestamp ,last_edit_timestamp From members where member_id=?";
	private static final String GET_ONE_STMT_MEM_ACCOUNT = "SELECT member_id,member_account,member_password,member_introduce,member_cellphone,member_mail,member_date,member_address,member_country,member_status,member_picture,member_nickname,member_gender ,created_timestamp ,last_edit_timestamp From members where member_account=?";
	private static final String DELETE = "DELETE FROM members where member_id = ?";
	private static final String UPDATE = "UPDATE members set member_account=?,member_introduce=?,member_cellphone=?,member_mail=?,"
			+ "member_date=?,member_address=?,member_picture=?,"
			+ "member_nickname=?,member_gender=? ,last_edit_timestamp=now()  where member_id=?";

	@Override
	public void insert(MembersVO membersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, membersVO.getMemberAccount());
			pstmt.setString(2, membersVO.getMemberPassword());
			pstmt.setString(3, membersVO.getMemberIntroduce());
			pstmt.setString(4, membersVO.getMemberCellphone());
			pstmt.setString(5, membersVO.getMemberMail());
			pstmt.setDate(6, (java.sql.Date) membersVO.getMemberDate());
			pstmt.setString(7, membersVO.getMemberAddress());
			pstmt.setString(8, membersVO.getMemberCountry());
			pstmt.setInt(9, membersVO.getMemberStatus());
			pstmt.setBytes(10, membersVO.getMemberPicture());
			pstmt.setString(11, membersVO.getMemberNickname());
			pstmt.setInt(12, membersVO.getMemberGender());

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
	public void update(MembersVO membersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, membersVO.getMemberAccount());
//			pstmt.setString(2, membersVO.getMemberPassword());
			pstmt.setString(2, membersVO.getMemberIntroduce());
			pstmt.setString(3, membersVO.getMemberCellphone());
			pstmt.setString(4, membersVO.getMemberMail());
			pstmt.setDate(5, (java.sql.Date) membersVO.getMemberDate());
			pstmt.setString(6, membersVO.getMemberAddress());
//			pstmt.setString(7, membersVO.getMemberCountry());
//			pstmt.setInt(8, membersVO.getMemberStatus());
			pstmt.setBytes(7, membersVO.getMemberPicture());
			pstmt.setString(8, membersVO.getMemberNickname());
			pstmt.setInt(9, membersVO.getMemberGender());
			pstmt.setInt(10, membersVO.getMemberId());

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
	public void delete(Integer memberId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memberId);

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
	public MembersVO findByPrimaryKey(Integer memberId) {

		MembersVO membersVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memberId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				membersVO = new MembersVO();
				membersVO.setMemberId(rs.getInt("member_id"));
				membersVO.setMemberAccount(rs.getString("member_account"));
				membersVO.setMemberPassword(rs.getString("member_password"));
				membersVO.setMemberIntroduce(rs.getString("member_introduce"));
				membersVO.setMemberCellphone(rs.getString("member_cellphone"));
				membersVO.setMemberMail(rs.getString("member_mail"));
				membersVO.setMemberDate(rs.getDate("member_date"));
				membersVO.setMemberAddress(rs.getString("member_address"));
				membersVO.setMemberCountry(rs.getString("member_country"));
				membersVO.setMemberStatus(rs.getByte("member_status"));
				membersVO.setMemberPicture(rs.getBytes("member_picture"));
				membersVO.setMemberNickname(rs.getString("member_nickname"));
				membersVO.setMemberGender(rs.getByte("member_gender"));
				membersVO.setCredcreatedTimestamp(rs.getTimestamp("created_timestamp"));
				membersVO.setLastEditTimestamp(rs.getTimestamp("last_edit_timestamp"));
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
		return membersVO;
	}
	@Override
	public MembersVO findByMembersAccout(String memberAccount) {

		MembersVO membersVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT_MEM_ACCOUNT);

			pstmt.setString(1, memberAccount);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				membersVO = new MembersVO();
				membersVO.setMemberId(rs.getInt("member_id"));
				membersVO.setMemberAccount(rs.getString("member_account"));
				membersVO.setMemberPassword(rs.getString("member_password"));
				membersVO.setMemberIntroduce(rs.getString("member_introduce"));
				membersVO.setMemberCellphone(rs.getString("member_cellphone"));
				membersVO.setMemberMail(rs.getString("member_mail"));
				membersVO.setMemberDate(rs.getDate("member_date"));
				membersVO.setMemberAddress(rs.getString("member_address"));
				membersVO.setMemberCountry(rs.getString("member_country"));
				membersVO.setMemberStatus(rs.getByte("member_status"));
				membersVO.setMemberPicture(rs.getBytes("member_picture"));
				membersVO.setMemberNickname(rs.getString("member_nickname"));
				membersVO.setMemberGender(rs.getByte("member_gender"));
				membersVO.setCredcreatedTimestamp(rs.getTimestamp("created_timestamp"));
				membersVO.setLastEditTimestamp(rs.getTimestamp("last_edit_timestamp"));
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
		return membersVO;
	}
	@Override
	public List<MembersVO> getAll() {
		List<MembersVO> list = new ArrayList<MembersVO>();
		MembersVO membersVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				membersVO = new MembersVO();
				membersVO.setMemberId(rs.getInt("member_id"));
				membersVO.setMemberAccount(rs.getString("member_account"));
				membersVO.setMemberPassword(rs.getString("member_password"));
				membersVO.setMemberIntroduce(rs.getString("member_introduce"));
				membersVO.setMemberCellphone(rs.getString("member_cellphone"));
				membersVO.setMemberMail(rs.getString("member_mail"));
				membersVO.setMemberDate(rs.getDate("member_date"));
				membersVO.setMemberAddress(rs.getString("member_address"));
				membersVO.setMemberCountry(rs.getString("member_country"));
				membersVO.setMemberStatus(rs.getByte("member_status"));
				membersVO.setMemberPicture(rs.getBytes("member_picture"));
				membersVO.setMemberNickname(rs.getString("member_nickname"));
				membersVO.setMemberGender(rs.getByte("member_gender"));
				membersVO.setCredcreatedTimestamp(rs.getTimestamp("created_timestamp"));
				membersVO.setLastEditTimestamp(rs.getTimestamp("last_edit_timestamp"));
				list.add(membersVO); // Store the row in the list
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

		MembersJDBCDAO dao = new MembersJDBCDAO();

//		 ���J
//		MembersVO membersVO = new MembersVO( "bb123456", "awerty","�Ӧ۪F�_���t�W�@�ǯT" ,"0920-123-456" , "a123456@gmail.com",null , "�x�_���_���","�x�W",0, null ,"�_��C�Y��",0);
//		dao.insert(membersVO);

//		��s
//		MembersVO membersVO = new MembersVO( "XXXXX", "awerty","�Ӧۥx�W���t�W��ǯT" ,"0920-123-456" , "a123456@gmail.com",null , "�x�_���_���","�x�W",0, null ,"�n��C�Y��",0);

//		membersVO.setMemberId(5);
//		dao.update(membersVO);
//�R��
//		dao.delete(6);

		// �d�@��

		MembersVO membersVO = dao.findByPrimaryKey(5);
		System.out.println(membersVO);
		// �d����
//		List<MembersVO> list = dao.getAll();
//		for (MembersVO aMember : list) {
//			System.out.println(aMember);
//			
//		}

	}
}