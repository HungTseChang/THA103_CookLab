package com.cooklab.article_sub.model;

import java.util.*;

import com.cooklab.util.Util;

import java.sql.*;


public class ArticleSubJDBCDAO implements ArticleSubDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO article_sub (article_no, member_id , article_sub_status, article_sub_content, article_sub_count) values(?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT article_sub_no, article_no, member_id, created_timestamp, article_sub_status, article_sub_content, article_sub_count , last_edit_timestamp  From article_sub order by article_sub_no";
	private static final String GET_ONE_STMT = "SELECT article_sub_no, article_no, member_id, created_timestamp, article_sub_status, article_sub_content, article_sub_count , last_edit_timestamp From article_sub where article_sub_no=?";
	private static final String DELETE = "DELETE FROM article_sub where article_sub_no = ?";
	private static final String UPDATE = "UPDATE article_sub set article_no=?, member_id=?, article_sub_status=?, article_sub_content=? ,article_sub_count=?,last_edit_timestamp=now()  where article_sub_no=?";

	@Override
	public void insert(ArticleSubVO articleSubVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articleSubVO.getArticleNo());
			pstmt.setInt(2, articleSubVO.getMemberId());
			pstmt.setInt(3, articleSubVO.getArticleSubStatus());
			pstmt.setString(4, articleSubVO.getArticleSubContent());
			pstmt.setInt(5, articleSubVO.getArticleSubCount());

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
	public void update(ArticleSubVO articleSubVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, articleSubVO.getArticleNo());
			pstmt.setInt(2, articleSubVO.getMemberId());
			pstmt.setInt(3, articleSubVO.getArticleSubStatus());
			pstmt.setString(4, articleSubVO.getArticleSubContent());
			pstmt.setInt(5, articleSubVO.getArticleSubCount());
			pstmt.setInt(6, articleSubVO.getArticleSubNo());

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
	public void delete(Integer articleSubNO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, articleSubNO);

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
	public ArticleSubVO findByPrimaryKey(Integer articleSubNo) {
		ArticleSubVO articleSubVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, articleSubNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleSubVO = new ArticleSubVO();
				articleSubVO.setArticleSubNo(rs.getInt("article_sub_no"));
				articleSubVO.setArticleNo(rs.getInt("article_no"));
				articleSubVO.setMemberId(rs.getInt("member_id"));
				articleSubVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				articleSubVO.setArticleSubStatus(rs.getByte("article_sub_status"));
				articleSubVO.setArticleSubContent(rs.getString("article_sub_content"));
				articleSubVO.setArticleSubCount(rs.getInt("article_sub_count"));
				articleSubVO.setLastEditTimeStampstame(rs.getTimestamp("last_edit_timestamp"));
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
		return articleSubVO;
	}

	@Override
	public List<ArticleSubVO> getAll() {
		List<ArticleSubVO> list = new ArrayList<ArticleSubVO>();
		ArticleSubVO articleSubVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleSubVO = new ArticleSubVO();
				articleSubVO.setArticleSubNo(rs.getInt("article_sub_no"));
				articleSubVO.setArticleNo(rs.getInt("article_no"));
				articleSubVO.setMemberId(rs.getInt("member_id"));
				articleSubVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				articleSubVO.setArticleSubStatus(rs.getByte("article_sub_status"));
				articleSubVO.setArticleSubContent(rs.getString("article_sub_content"));
				articleSubVO.setArticleSubCount(rs.getInt("article_sub_count"));
				articleSubVO.setLastEditTimeStampstame(rs.getTimestamp("last_edit_timestamp"));
				list.add(articleSubVO); // Store the row in the list
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
		ArticleSubJDBCDAO dao = new ArticleSubJDBCDAO();
//		//���J
//		ArticleSubVO articleSubVO = new ArticleSubVO (4 , 3,2, "�o�O�s�����^��" , 20 );
// 		dao.insert(articleSubVO);
		// ��s
//		ArticleSubVO articleSubVO = new ArticleSubVO (2,5,0,"�H�K���������e",5);
//		articleSubVO.setArticleSubNo(5);
//		dao.update(articleSubVO);


//		dao.delete(6);

		// �d�@��
//		ArticleSubVO articleSubVO =dao.findByPrimaryKey(2);
//		System.out.println(articleSubVO);
		// �d����
		List<ArticleSubVO> list = dao.getAll();
		for (ArticleSubVO aArticleSub : list) {
			System.out.println(aArticleSub);
		}

	}
}