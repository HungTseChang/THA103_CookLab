package com.cooklab.product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import com.cooklab.util.*;

public class ProductJDBCDAOIm implements ProductDAO {
	private static final String INSERT_STMT = "insert into product(product_name, sale_qty, product_dec, product_introduction, product_price, offsale_time, shelf_time, storage_qty, ingredient_category_no, kitchenware_category_no,product_picture) values (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "update product set product_name = ?, sale_qty = ?, product_dec = ?, product_introduction = ?, product_price = ?, offsale_time = ?, shelf_time = ?, storage_qty = ?, ingredient_category_no =?, kitchenware_category_no = ?, search_count = ? ,product_picture=? where product_no = ?";
	private static final String DELETE = "delete from product where product_no = ?";
	private static final String GET_ONE_STMT = "select product_no, product_name, sale_qty, product_dec, product_introduction, product_price, offsale_time, shelf_time, storage_qty, ingredient_category_no, kitchenware_category_no, search_count ,created_timestamp from product where product_no = ?";
	private static final String GET_ALL_STMT = "select product_no, product_name, sale_qty, product_dec, product_introduction, product_price, offsale_time, shelf_time, storage_qty, ingredient_category_no, kitchenware_category_no, search_count ,created_timestamp from product order by product_no";

	@Override
	public void insert(ProductVO product) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, product.getProductName());
			pstmt.setInt(2, product.getSaleQty());
			pstmt.setString(3, product.getProductDec());
			pstmt.setString(4, product.getProductIntroduction());
			pstmt.setInt(5, product.getProductPrice());
			pstmt.setTimestamp(6, product.getOffsaleTime());
			pstmt.setTimestamp(7, product.getShelfTime());
			pstmt.setInt(8, product.getStorageQty());
			if (product.getIngredientCategoryNo() != null) {
				pstmt.setInt(9, product.getIngredientCategoryNo());
			} else {
				pstmt.setNull(9, Types.INTEGER);// 使用setNull方法讓JDBC傳送空值
			}
			if (product.getKitchenwareCategoryNo() != null) {
				pstmt.setInt(10, product.getKitchenwareCategoryNo());
			} else {
				pstmt.setNull(10, Types.INTEGER);// 使用setNull方法讓JDBC傳送空值
			}

			
	        if (product.getProductPicture() != null) {
	            pstmt.setBytes(11, product.getProductPicture());
	        } else {
	            pstmt.setNull(11, Types.BLOB);// 使用setNull方法让JDBC传送空值
	        }
			
	           
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

	@Override
	public void update(ProductVO product) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, product.getProductName());
			pstmt.setInt(2, product.getSaleQty());
			pstmt.setString(3, product.getProductDec());
			pstmt.setString(4, product.getProductIntroduction());
			pstmt.setInt(5, product.getProductPrice());
			pstmt.setTimestamp(6, product.getOffsaleTime());
			pstmt.setTimestamp(7, product.getShelfTime());
			pstmt.setInt(8, product.getStorageQty());
			if (product.getIngredientCategoryNo() != null) {
				pstmt.setInt(9, product.getIngredientCategoryNo());
			} else {
				pstmt.setNull(9, Types.INTEGER);// 使用setNull方法讓JDBC傳送空值
			}
			if (product.getKitchenwareCategoryNo() != null) {
				pstmt.setInt(10, product.getKitchenwareCategoryNo());
			} else {
				pstmt.setNull(10, Types.INTEGER);// 使用setNull方法讓JDBC傳送空值
			}
			if (product.getSearchCount() != null) {
				pstmt.setInt(11, product.getSearchCount());
			} else {
				pstmt.setNull(11, Types.INTEGER);// 使用setNull方法讓JDBC傳送空值
			}
			pstmt.setBytes(12, product.getProductPicture());
			pstmt.setInt(13, product.getProductNo());

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

	@Override
	public void delete(Integer productNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, productNo);

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
	public ProductVO findByPrimaryKey(Integer productNo) {
		ProductVO product = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, productNo);

			rs = pstmt.executeQuery();

			// 放入對應的MySQL表格欄位名稱
			while (rs.next()) {
				product = new ProductVO();
				product.setProductNo(rs.getInt("product_no"));
				product.setProductName(rs.getString("product_name"));
				product.setSaleQty(rs.getInt("sale_qty"));
				product.setProductDec(rs.getString("product_dec"));
				product.setProductIntroduction(rs.getString("product_introduction"));
				product.setProductPrice(rs.getInt("product_price"));
				product.setOffsaleTime(rs.getTimestamp("offsale_time"));
				product.setShelfTime(rs.getTimestamp("shelf_time"));
				product.setStorageQty(rs.getInt("storage_qty"));
				product.setIngredientCategoryNo(rs.getInt("ingredient_category_no"));
				product.setKitchenwareCategoryNo(rs.getInt("kitchenware_category_no"));
				product.setSearchCount(rs.getInt("search_count"));
				product.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
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
		return product;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO product = null;

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
				product = new ProductVO();
				product.setProductNo(rs.getInt("product_no"));
				product.setProductName(rs.getString("product_name"));
				product.setSaleQty(rs.getInt("sale_qty"));
				product.setProductDec(rs.getString("product_dec"));
				product.setProductIntroduction(rs.getString("product_introduction"));
				product.setProductPrice(rs.getInt("product_price"));
				product.setOffsaleTime(rs.getTimestamp("offsale_time"));
				product.setShelfTime(rs.getTimestamp("shelf_time"));
				product.setStorageQty(rs.getInt("storage_qty"));
				product.setIngredientCategoryNo(rs.getInt("ingredient_category_no"));
				product.setKitchenwareCategoryNo(rs.getInt("kitchenware_category_no"));
				product.setSearchCount(rs.getInt("search_count"));
				product.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				list.add(product); // Store the row in the list
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
