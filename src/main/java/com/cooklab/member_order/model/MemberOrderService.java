package com.cooklab.member_order.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


import com.cooklab.util.HibernateUtil;

public class MemberOrderService {

	private MemberOrderDAO dao;
	
	public MemberOrderService() {
		dao = new MemberOrderHDAOIm(HibernateUtil.getSessionFactory());
	}
	
	public String insert(MemberOrderVO memberOrderVO) {
		dao.insert(memberOrderVO);
		return "success";
		
	}
}
