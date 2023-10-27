package com.cooklab.members.model;
import java.util.List;

import com.cooklab.member_order.model.MemberOrderVO;
import com.cooklab.members.MemberRecipeOverViewDTO;
import com.cooklab.recipe.model.RecipeVO;



public interface MembersDAO_interface {
	public int insert(MembersVO membersVO);
    public boolean update(MembersVO membersVO);
    public boolean delete(Integer memberId);
    public MembersVO findByPrimaryKey(Integer memberId);
    public MembersVO findByMembersAccout(String memberAccount);
    public boolean updateMemberStatus(MembersVO membersVO);
    public List<MembersVO> getAll();
	boolean updateMemberPassword(MembersVO membersVO);
	public List<RecipeVO> MemberRecipeRead(Integer memberId);
	//
	public List<RecipeVO> getByPage(Integer offset, Integer limit,Integer memberId);
	public List<MemberOrderVO> getOrder(Integer offset, Integer limit,Integer memberId);
}


