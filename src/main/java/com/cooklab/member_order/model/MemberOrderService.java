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
		int message = dao.insert(memberOrderVO);
		if(message==1) {
			return "success";
		}else {
			return "false";
		}
		
	}
	
	public List<MemberOrderVO> getAll() {
		return dao.getAll();
	}
	
	public MemberOrderVO findByPrimaryKey(Integer orderNo) {
		return dao.findByPrimaryKey(orderNo);
	}
	
	public Integer update(MemberOrderVO memberOrderVO) {
		return dao.update(memberOrderVO);
	}
}
