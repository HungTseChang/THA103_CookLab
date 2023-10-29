package com.cooklab.advertise.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet("/AdvertisetImgServlet")
public class AdvertiseImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String advertiseNo = req.getParameter("advertiseNo").trim();
			ResultSet rs = stmt.executeQuery("SELECT advertise_img FROM product WHERE advertise_no = " + advertiseNo);
//			"SELECT IMAGE FROM PICTURES WHERE PID = " + req.getParameter("PID")

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("advertise_img"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				// res.sendError(HttpServletResponse.SC_NOT_FOUND); //404 page 134 140
//				InputStream in = getServletContext().getResourceAsStream("/NoData/none2.jpg");
//				byte[] b = new byte[in.available()];
//				in.read(b);
//				out.write(b);
//				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
//			byte [] b = new byte[in.available()];
//			in.read(b);
			byte[] b = in.readAllBytes(); // Java9
			out.write(b);
			in.close();

		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2"); // p.182 -->Server/context.xml-->line21
																					// -->jdbc/TestDB2
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
