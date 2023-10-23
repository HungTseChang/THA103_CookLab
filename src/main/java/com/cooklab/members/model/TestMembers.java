package com.cooklab.members.model;

public class TestMembers {
	public static void main(String[] args)
	{
//		MembersJDBCDAO jdc = new MembersJDBCDAO();
		MembersService memSvc = new MembersService();
		memSvc.updateMemberStatus(35,(byte)0);
	}
}
