package com.cooklab.support_form.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;

public class SupportFormJDBCDAOIm implements SupportFormDAO {

	//因表單建立時間可能為非上班時間，客服無法第一時間處理，故Insert指令不會對form_responder欄位插入資料
	private static final String INSERT_STMT = "INSERT INTO support_form (real_name,support_form_category_id,reply_email,form_context,form_title,form_status,form_source,form_submitter)VALUES(?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT form_no,real_name,support_form_category_id,reply_email,form_context,form_title,form_status,form_source,form_submitter,form_responder,created_timestamp from support_form";
	private static final String GET_ONE_STMT = "SELECT form_no,real_name,support_form_category_id,reply_email,form_context,form_title,form_status,form_source,form_submitter,form_responder,created_timestamp from support_form where form_no = ?";
	private static final String DELETE = "DELETE FROM support_form where form_no = ?";
	private static final String UPDATE = "UPDATE support_form set real_name=?, support_form_category_id=?, reply_email=?, form_context=?, form_title=?, form_status=?, form_source=?,form_submitter=?, form_responder=? where form_no = ?;";

	@Override
	public void insert(SupportFormVO supportFormVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, supportFormVO.getRealName());
			pstmt.setInt(2, supportFormVO.getSupportFormCategoryId());
			pstmt.setString(3, supportFormVO.getReplyEmail());
			pstmt.setString(4, supportFormVO.getFormContext());
			pstmt.setString(5, supportFormVO.getFormTitle());
			pstmt.setByte(6, supportFormVO.getFormStatus());
			pstmt.setString(7, supportFormVO.getFormSource());
			pstmt.setString(8, supportFormVO.getFormSubmitter());

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
	public void update(SupportFormVO supportFormVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, supportFormVO.getRealName());
			pstmt.setInt(2, supportFormVO.getSupportFormCategoryId());
			pstmt.setString(3, supportFormVO.getReplyEmail());
			pstmt.setString(4, supportFormVO.getFormContext());
			pstmt.setString(5, supportFormVO.getFormTitle());
			pstmt.setByte(6, supportFormVO.getFormStatus());
			pstmt.setString(7, supportFormVO.getFormSource());
			pstmt.setString(8, supportFormVO.getFormSubmitter());
			pstmt.setInt(9, supportFormVO.getFormResponder());
			pstmt.setInt(10, supportFormVO.getFormNo());

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
	public void delete(Integer formNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, formNo);

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

	@Override
	public SupportFormVO findByPrimaryKey(Integer formNo) {
		SupportFormVO supportFormVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, formNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				supportFormVO = new SupportFormVO();
				supportFormVO.setFormNo(rs.getInt("form_no"));
				supportFormVO.setRealName(rs.getString("real_name"));
				supportFormVO.setSupportFormCategoryId(rs.getInt("support_form_category_id"));
				supportFormVO.setReplyEmail(rs.getString("reply_email"));
				supportFormVO.setFormTitle(rs.getString("form_title"));
				supportFormVO.setFormContext(rs.getString("form_context"));
				supportFormVO.setFormStatus(rs.getByte("form_status"));
				supportFormVO.setFormSource(rs.getString("form_source"));
				supportFormVO.setFormSubmitter(rs.getString("form_submitter"));
				supportFormVO.setFormResponder(rs.getInt("form_responder"));
				supportFormVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
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
		return supportFormVO;
	}

	@Override
	public List<SupportFormVO> getAll() {
		List<SupportFormVO> list = new ArrayList<SupportFormVO>();
		SupportFormVO supportFormVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				supportFormVO = new SupportFormVO();
				supportFormVO.setFormNo(rs.getInt("form_no"));
				supportFormVO.setRealName(rs.getString("real_name"));
				supportFormVO.setSupportFormCategoryId(rs.getInt("support_form_category_id"));
				supportFormVO.setReplyEmail(rs.getString("reply_email"));
				supportFormVO.setFormTitle(rs.getString("form_title"));
				supportFormVO.setFormContext(rs.getString("form_context"));
				supportFormVO.setFormStatus(rs.getByte("form_status"));
				supportFormVO.setFormSource(rs.getString("form_source"));
				supportFormVO.setFormSubmitter(rs.getString("form_submitter"));
				supportFormVO.setFormResponder(rs.getInt("form_responder"));
				supportFormVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				list.add(supportFormVO); // Store the row in the list
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
