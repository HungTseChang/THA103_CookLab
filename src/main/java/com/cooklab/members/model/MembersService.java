package com.cooklab.members.model;

import java.sql.Timestamp;
import java.util.List;

public class MembersService {

	

	private MembersDAO_interface dao;

	public MembersService() {
		dao = new MembersJDBCDAO();
	}

	public MembersVO addMembers(String memberAccount, String memberPassword,String memberIntroduce,
			String memberCellphone,String memberMail, java.sql.Date memberDate,String memberAddress,String memberCountry
			,Byte memberStatus, String memberNickname,Byte memberGender
			) {

		MembersVO membersVO = new MembersVO();

		membersVO.setMemberAccount(memberAccount);
		membersVO.setMemberPassword(memberPassword);
		membersVO.setMemberIntroduce(memberIntroduce);
		membersVO.setMemberCellphone(memberCellphone);
		membersVO.setMemberMail(memberMail);
		membersVO.setMemberDate(memberDate);
		membersVO.setMemberAddress(memberAddress);
		membersVO.setMemberCountry(memberCountry);
		membersVO.setMemberStatus(memberStatus);
		membersVO.setMemberNickname(memberNickname);
		membersVO.setMemberGender(memberGender);
//		membersVO.setMemberPicture(memberPicture);
		
		dao.insert(membersVO);

		return membersVO;
	}

	public MembersVO updateMember(Integer memberId,String memberAccount,String memberIntroduce,
			String memberCellphone,String memberMail, java.sql.Date memberDate,String memberAddress
			, byte[] memberPicture,String memberNickname,Byte memberGender 
			) {

		MembersVO membersVO = new MembersVO();
//
		membersVO.setMemberId(memberId);
		membersVO.setMemberAccount(memberAccount);
//		membersVO.setMemberPassword(memberPassword);
		membersVO.setMemberIntroduce(memberIntroduce);
		membersVO.setMemberCellphone(memberCellphone);
		membersVO.setMemberMail(memberMail);
		membersVO.setMemberDate(memberDate);
		membersVO.setMemberAddress(memberAddress);
//		membersVO.setMemberCountry(memberCountry);
//		membersVO.setMemberStatus(memberStatus);
		membersVO.setMemberNickname(memberNickname);
		membersVO.setMemberGender(memberGender);
		membersVO.setMemberPicture(memberPicture);
		dao.update(membersVO);

		return membersVO;
	}

	public void deleteMember(Integer empno) {
		dao.delete(empno);
	}

	public MembersVO getOneMember(Integer memberId) {
		return dao.findByPrimaryKey(memberId);
	}
	

	public MembersVO getOneMemberAccount(String memberAccount) {
		return dao.findByMembersAccout(memberAccount);
	}
	//修改使用者狀態
	public MembersVO updateMemberStatus(Integer memberId,byte memberStatus)
	{
		MembersVO membersVO = new MembersVO();
		dao.updateMemberStatus(memberId, memberStatus);
		return membersVO;
	}
	public List<MembersVO> getAll() {
		return dao.getAll();
	}
}
