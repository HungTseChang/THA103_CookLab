package com.cooklab.admins.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;

import com.cooklab.util.HibernateUtil;
import org.hibernate.Session;
public class DashboardOpenSessionInViewFilter  implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		SessionFactory factory = HibernateUtil.getSessionFactory();  //直接創建工廠 綁定Thread 創建交易
		System.out.println("Filter 取出工廠");

		try {

			factory.getCurrentSession().beginTransaction();
			chain.doFilter(req, res);
			System.out.println("結束");

			factory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
			System.out.println(e);
			factory.getCurrentSession().getTransaction().rollback();
			e.printStackTrace();
			chain.doFilter(req, res);
		}
	}

}
