package com.cooklab.members.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.cooklab.member_order.model.MemberOrderVO;
import com.cooklab.members.MemberNotifyOverViewDTO;
import com.cooklab.members.MemberOrderOverViewDTO;
import com.cooklab.members.model.MembersService;
import com.cooklab.members.model.MembersVO;
import com.cooklab.notify_center.model.NotifyCenterVO;
import com.cooklab.util.HibernateUtil;
import com.google.gson.Gson;

@WebServlet("/MemberNotifyServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class MembersNotifyOverViewServlet  extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String notifyNoStr = req.getParameter("notifyNo");
		Integer notifyNo = 0;  // 默认值或者适当的初始值
		if (notifyNoStr != null && !notifyNoStr.isEmpty())
			notifyNo =  Integer.valueOf(notifyNoStr);
		else
			notifyNo = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		NotifyCenterVO ncVO = session.get(NotifyCenterVO.class, notifyNo);
		ncVO.setNotifyRead((byte)1);
		session.merge(ncVO);
		res.getWriter().write("OK");	
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();

		HttpSession session = req.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		List<NotifyCenterVO> memberNotifyVO = new MembersService().getNotify(Integer.valueOf(0)*10, 90,userId);
		List<MemberNotifyOverViewDTO> listNotify = new ArrayList<>();
		for(NotifyCenterVO memberOrderList :memberNotifyVO) {
			listNotify.add(new MemberNotifyOverViewDTO(memberOrderList));
		}
//		}
//		System.out.println(memberNotifyVO.toString());
		String jsonString = gson.toJson(listNotify);
		res.getWriter().write(jsonString);	
	}
}
