package com.cooklab.hashtag.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.product.model.ProductVO;

public class HashtagHDAOIm implements HashtagDAO {
	private SessionFactory factory;

	public HashtagHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(HashtagVO hashtagVO) {
		return (Integer) getSession().save(hashtagVO);
	}

	@Override
	public boolean update(HashtagVO hashtagVO) {
		try {
			getSession().update(hashtagVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Integer hashtagNO) {
		HashtagVO vo = getSession().get(HashtagVO.class, hashtagNO);
		if (vo != null) {
			getSession().delete(vo);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public HashtagVO findByPrimaryKey(Integer hashtagNo) {
		return getSession().get(HashtagVO.class, hashtagNo);
	}

	@Override
	public List<HashtagVO> getAll() {
		return getSession().createQuery("from HashtagVO", HashtagVO.class).list();
	}

	@Override
	public List<HashtagVO> getOfficalHashtag() {
		return getSession().createQuery("from HashtagVO where officialTags is not null", HashtagVO.class).list();
	}

	@Override
	public List<HashtagVO> getPopularHashtag(int n) {
		return getSession().createQuery("from HashtagVO order by useCount DESC", HashtagVO.class).setMaxResults(n)
				.list();
	}

	@Override
	public HashtagVO findByHashtagName(String hashtagName) {
		return getSession().createQuery("from HashtagVO where hashtagName = :hashtagName", HashtagVO.class)
				.setParameter("hashtagName", hashtagName).uniqueResult();
	}

}
