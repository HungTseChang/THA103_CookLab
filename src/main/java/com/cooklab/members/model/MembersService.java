package com.cooklab.members.model;

import java.util.List;

public class MembersService {


	private MembersDAO_interface dao;

	public MembersService() {
		dao = new MembersJDBCDAO();
	}

	public MembersVO addMembers(String memberAccount, String memberPassword,String memberIntroduce,
			String memberCellphone,String memberMail, java.sql.Date memberDate,String memberAddress,String memberCountry
			,byte memberStatus,byte[] memberPicture,String memberNickname,byte memberGender
			) {

		MembersVO membersVO = new MembersVO();

//		empVO.setEname(ename);
//		empVO.setJob(job);
//		empVO.setHiredate(hiredate);
//		empVO.setSal(sal);
//		empVO.setComm(comm);
//		empVO.setDeptno(deptno);
//		dao.insert(empVO);

		return membersVO;
	}

	public MembersVO updateEmp(String memberAccount, String memberPassword,String memberIntroduce,
			String memberCellphone,String memberMail, java.sql.Date memberDate,String memberAddress,String memberCountry
			,byte memberStatus,byte[] memberPicture,String memberNickname,byte memberGender
			) {

		MembersVO membersVO = new MembersVO();
//
//		empVO.setEmpno(empno);
//		empVO.setEname(ename);
//		empVO.setJob(job);
//		empVO.setHiredate(hiredate);
//		empVO.setSal(sal);
//		empVO.setComm(comm);
//		empVO.setDeptno(deptno);
//		dao.update(empVO);

		return membersVO;
	}

	public void deleteEmp(Integer empno) {
		dao.delete(empno);
	}

	public MembersVO getOneEmp(Integer memberId) {
		return dao.findByPrimaryKey(memberId);
	}

	public List<MembersVO> getAll() {
		return dao.getAll();
	}
}
