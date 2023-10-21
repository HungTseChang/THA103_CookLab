package com.cooklab.support_form.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.SessionFactory;

import com.cooklab.util.HibernateUtil;

@WebFilter("/SupportFormAjax")
public class TestFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			factory.getCurrentSession().beginTransaction();
			chain.doFilter(req, res);
			factory.getCurrentSession().getTransaction().commit();
			System.out.println("commit");
		} catch (Exception e) {
			System.out.println("rollback");
			factory.getCurrentSession().getTransaction().rollback();
			e.printStackTrace();
			chain.doFilter(req, res);
		}
	}

}
