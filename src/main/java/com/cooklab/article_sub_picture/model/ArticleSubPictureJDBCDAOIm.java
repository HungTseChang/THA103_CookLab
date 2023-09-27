package com.cooklab.article_sub_picture.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;

import java.io.IOException;
import java.io.File;

public class ArticleSubPictureJDBCDAOIm implements ArticleSubPictureDAO {
	private static final String INSERT_STMT = "INSERT INTO article_sub_picture (article_sub_no,picture,created_timestamp) VALUES (?, ?,now())";
	private static final String GET_ALL_STMT = "SELECT article_sub_picture_no,article_sub_no,picture,created_timestamp FROM article_sub_picture order by article_sub_picture_no";
	private static final String GET_ONE_STMT = "SELECT article_sub_picture_no,article_sub_no,picture,created_timestamp FROM article_sub_picture where article_sub_picture_no = ?";
	private static final String DELETE = "DELETE FROM article_sub_picture where article_sub_picture_no, = ?";
	private static final String UPDATE = "UPDATE article_sub_picture set article_sub_no=?, picture=? ,created_timestamp=now()  where article_sub_picture_no = ?";

	@Override
	public void insert(ArticleSubPictureVO ArticleSubPictureVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, ArticleSubPictureVO.getArticleSubNo());
			pstmt.setBytes(2, ArticleSubPictureVO.getPicture());

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

	// ============================insert完結=======================================
	@Override
	public void update(ArticleSubPictureVO ArticleSubPictureVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ArticleSubPictureVO.getArticleSubNo());
			pstmt.setBytes(2, ArticleSubPictureVO.getPicture());
			pstmt.setInt(3, ArticleSubPictureVO.getArticleSubPictureNo());

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

	// =================================update結束========================================
	@Override
	public void delete(Integer ArticleSubPictureNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ArticleSubPictureNo);

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

	// ===============================delete結束======================================
	@Override
	public ArticleSubPictureVO findByPrimaryKey(Integer ArticleSubPictureNo) {

		ArticleSubPictureVO ArticleSubPictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ArticleSubPictureNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				ArticleSubPictureVO = new ArticleSubPictureVO();
				ArticleSubPictureVO.setArticleSubPictureNo(rs.getInt("article_sub_picture_no"));
				ArticleSubPictureVO.setArticleSubNo(rs.getInt("article_sub_no"));
				ArticleSubPictureVO.setPicture(rs.getBytes("picture"));
				ArticleSubPictureVO.setCreatedTimestamp(rs.getDate("created_timestamp"));

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
		return ArticleSubPictureVO;
	}

	// ===========================findByPrimaryKey結束==============================================
	@Override
	public List<ArticleSubPictureVO> getAll() {
		List<ArticleSubPictureVO> list = new ArrayList<ArticleSubPictureVO>();
		ArticleSubPictureVO ArticleSubPictureVO = null;

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
				ArticleSubPictureVO = new ArticleSubPictureVO();
				ArticleSubPictureVO.setArticleSubPictureNo(rs.getInt("article_sub_picture_no"));
				ArticleSubPictureVO.setArticleSubNo(rs.getInt("article_sub_no"));
				ArticleSubPictureVO.setPicture(rs.getBytes("picture"));
				ArticleSubPictureVO.setCreatedTimestamp(rs.getDate("created_timestamp"));

				list.add(ArticleSubPictureVO); // Store the row in the list
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

	// ===================================getAll結束========================================
	public static void main(String[] args) {

		ArticleSubPictureJDBCDAOIm dao = new ArticleSubPictureJDBCDAOIm();

//				// 新增
//				ArticleSubPictureVO ArticleSubPictureVO1 = new ArticleSubPictureVO();
//				ArticleSubPictureVO1.setArticleSubNo(1);
//				File imageFile = new File("img/12.png");
//	            FileInputStream inputStream;
//				try {
//					inputStream = new FileInputStream(imageFile);
//		            byte[] imageBytes = new byte[(int) imageFile.length()];
//		            inputStream.read(imageBytes);
//		            inputStream.close();
////		            
//	//
//					ArticleSubPictureVO1.setPicture(imageBytes);
//					dao.insert(ArticleSubPictureVO1);
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

////				// 修改
		ArticleSubPictureVO ArticleSubPictureVO2 = new ArticleSubPictureVO();
		ArticleSubPictureVO2.setArticleSubPictureNo(Integer.valueOf(1));
		ArticleSubPictureVO2.setArticleSubNo(Integer.valueOf(1));
		File imageFile1 = new File("img/blue.png");
		FileInputStream inputStream1;
		try {
			inputStream1 = new FileInputStream(imageFile1);
			byte[] imageBytes1 = new byte[(int) imageFile1.length()];
			inputStream1.read(imageBytes1);
			inputStream1.close();

			ArticleSubPictureVO2.setPicture(imageBytes1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.update(ArticleSubPictureVO2);
//	
//////				// 刪除
//				dao.delete(1);
//	////
//////				// 查詢
//				ArticleSubPictureVO ArticleSubPictureVO3 = dao.findByPrimaryKey(3);
//				System.out.print(ArticleSubPictureVO3.getArticleSubPictureNo() + ",");
//				System.out.print(ArticleSubPictureVO3.getArticleSubNo() + ",");
//				System.out.print(ArticleSubPictureVO3.getPicture() + ",");
//				System.out.println(ArticleSubPictureVO3.getCreatedTimestamp() + ",");
//				System.out.println("---------------------");
//	//
//////				// 查詢
		List<ArticleSubPictureVO> list = dao.getAll();
		for (ArticleSubPictureVO aArticleSubPictureVO3 : list) {
			System.out.print(aArticleSubPictureVO3.getArticleSubPictureNo() + ",");
			System.out.print(aArticleSubPictureVO3.getArticleSubNo() + ",");
			System.out.print(aArticleSubPictureVO3.getPicture() + ",");
			System.out.println(aArticleSubPictureVO3.getCreatedTimestamp() + ",");
			System.out.println("---------------------");
		}
	}

}
