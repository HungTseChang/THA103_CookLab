package com.cooklab.members.model;
import java.util.List;



public interface MembersDAO_interface {
	public int insert(MembersVO membersVO);
    public boolean update(MembersVO membersVO);
    public boolean delete(Integer memberId);
    public MembersVO findByPrimaryKey(Integer memberId);
    public MembersVO findByMembersAccout(String memberAccount);
    public boolean updateMemberStatus(MembersVO membersVO);
    public List<MembersVO> getAll();
	boolean updateMemberPassword(MembersVO membersVO);

//  public List<MembersVO> getAll(Map<String, String[]> map); 
}


