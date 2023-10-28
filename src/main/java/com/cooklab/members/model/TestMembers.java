package com.cooklab.members.model;

public class TestMembers {
	public static void main(String[] args)
	{
//		MembersJDBCDAO jdc = new MembersJDBCDAO();
		MembersVO memVO = new MembersVO();
		MembersService memSvc = new MembersService();
//		memSvc.updateMemberStatus(6,(byte)0);
		
//		byte[] byteArray = new byte[10];
//		memVO = memSvc.updateMember((Integer)2,"pppp12ww34","pppp1234",
//		"098055065","tommy76190@gmail.com", java.sql.Date.valueOf("2010-02-28"),""
//		,byteArray,"ppppp",Byte.valueOf((byte) 2));
		
//		memSvc.getOneMemberAccount("123");
//		memSvc.update();
		
		memVO = memSvc.getOneMemberAccount("'ppp456'");
	}
}
