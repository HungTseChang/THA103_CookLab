package com.cooklab.members.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/MembersImgServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class MembersImgServlet extends HttpServlet {
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String id = req.getParameter("memberId").trim();	//page 60 & page 105
			ResultSet rs = stmt.executeQuery(
				"SELECT member_picture from members where member_id = "+ id);
			System.out.println(rs);
			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("member_picture"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				InputStream in = getServletContext().getResourceAsStream("/frontstage/members/images/no_pic.png");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
				
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);	//404 page 134 & page 140
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("CCC");
			InputStream in = getServletContext().getResourceAsStream("/frontstage/members/images/no_pic.png");
			byte[] b = in.readAllBytes();
//			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
		
	}
	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CookLab"); // page 182 // Servers/context.xml
			con = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
