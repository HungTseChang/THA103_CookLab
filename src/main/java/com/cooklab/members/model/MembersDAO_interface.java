package com.cooklab.members.model;
import java.util.List;



public interface MembersDAO_interface {
	public void insert(MembersVO membersVO);
    public void update(MembersVO membersVO);
    public void delete(Integer memberId);
    public MembersVO findByPrimaryKey(Integer memberId);
    public MembersVO findByMembersAccout(String memberAccount);
    public List<MembersVO> getAll();

//  public List<MembersVO> getAll(Map<String, String[]> map); 
}


