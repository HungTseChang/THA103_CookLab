package com.cooklab.article_picture.model;

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

public class ArticlePictureJDBCDAOIm implements ArticlePictureDAO {
	private static final String INSERT_STMT = "INSERT INTO article_picture (article_no,picture,created_timestamp) VALUES (?, ?,now())";
	private static final String GET_ALL_STMT = "SELECT article_picture_no,article_no,picture,created_timestamp FROM article_picture order by article_picture_no";
	private static final String GET_ONE_STMT = "SELECT article_picture_no,article_no,picture,created_timestamp FROM article_picture where article_picture_no = ?";
	private static final String DELETE = "DELETE FROM article_picture where article_picture_no = ?";
	private static final String UPDATE = "UPDATE article_picture set article_no=?, picture=? ,created_timestamp=now() where article_picture_no = ? ";

	@Override
	public void insert(ArticlePictureVO ArticlePictureVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, ArticlePictureVO.getArticleNo());
			pstmt.setBytes(2, ArticlePictureVO.getPicture());

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
	public void update(ArticlePictureVO ArticlePictureVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ArticlePictureVO.getArticleNo());
			pstmt.setBytes(2, ArticlePictureVO.getPicture());
			pstmt.setInt(3, ArticlePictureVO.getArticlePictureNo());

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
	public void delete(Integer articlePictureNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, articlePictureNo);

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
	public ArticlePictureVO findByPrimaryKey(Integer articlePictureNo) {

		ArticlePictureVO ArticlePictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, articlePictureNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				ArticlePictureVO = new ArticlePictureVO();
				ArticlePictureVO.setArticlePictureNo(rs.getInt("article_picture_no"));
				ArticlePictureVO.setArticleNo(rs.getInt("article_no"));
				ArticlePictureVO.setPicture(rs.getBytes("picture"));
				ArticlePictureVO.setCreatedTimestamp(rs.getDate("created_timestamp"));

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
		return ArticlePictureVO;
	}

	// ===========================findByPrimaryKey結束==============================================
	@Override
	public List<ArticlePictureVO> getAll() {
		List<ArticlePictureVO> list = new ArrayList<ArticlePictureVO>();
		ArticlePictureVO ArticlePictureVO = null;

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
				ArticlePictureVO = new ArticlePictureVO();
				ArticlePictureVO.setArticlePictureNo(rs.getInt("article_picture_no"));
				ArticlePictureVO.setArticleNo(rs.getInt("article_no"));
				ArticlePictureVO.setPicture(rs.getBytes("picture"));
				ArticlePictureVO.setCreatedTimestamp(rs.getDate("created_timestamp"));
				list.add(ArticlePictureVO); // Store the row in the list
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

		ArticlePictureJDBCDAOIm dao = new ArticlePictureJDBCDAOIm();

		// 新增
//				ArticlePictureVO ArticlePictureVO1 = new ArticlePictureVO();
//				ArticlePictureVO1.setArticleNo(1);
//				File imageFile = new File("img/12.png");
//	            FileInputStream inputStream;
//				try {
//					inputStream = new FileInputStream(imageFile);  /// inputStream為imageFile的資料流類型
//		            byte[] imageBytes = new byte[(int) imageFile.length()];///imageBytes為byte[] 其長度(位元數)等同於imageFile的大小
//		            inputStream.read(imageBytes);  ///用inputStream的方法read()將其檔案輸入致imageBytes
//		            inputStream.close();
////		            
//	//
//					ArticlePictureVO1.setPicture(imageBytes);
//					dao.insert(ArticlePictureVO1);
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

////				// 修改
		ArticlePictureVO ArticlePictureVO2 = new ArticlePictureVO();
		ArticlePictureVO2.setArticlePictureNo(Integer.valueOf(3));
		ArticlePictureVO2.setArticleNo(Integer.valueOf(1));
		File imageFile1 = new File("img/blue.png");
		FileInputStream inputStream1;
		try {
			inputStream1 = new FileInputStream(imageFile1);
			byte[] imageBytes1 = new byte[(int) imageFile1.length()];
			inputStream1.read(imageBytes1);
			inputStream1.close();

			ArticlePictureVO2.setPicture(imageBytes1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.update(ArticlePictureVO2);

////				// 刪除
//				dao.delete(2);
		////
////				// 查詢
//				ArticlePictureVO ArticlePictureVO3 = dao.findByPrimaryKey(3);
//				System.out.print(ArticlePictureVO3.getArticlePictureNo() + ",");
//				System.out.print(ArticlePictureVO3.getArticleNo() + ",");
//				System.out.print(ArticlePictureVO3.getPicture() + ",");
//				System.out.print(ArticlePictureVO3.getCreatedTimestamp() + ",");
//
//				System.out.println("---------------------");
		//
////				// 查詢
		List<ArticlePictureVO> list = dao.getAll();
		for (ArticlePictureVO aArticlePictureVO3 : list) {
			System.out.print(aArticlePictureVO3.getArticlePictureNo() + ",");
			System.out.print(aArticlePictureVO3.getArticleNo() + ",");
			System.out.print(aArticlePictureVO3.getPicture() + ",");
			System.out.print(aArticlePictureVO3.getCreatedTimestamp() + ",");
			System.out.println("---------------------");
		}
	}

}
