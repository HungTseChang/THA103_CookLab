package com.cooklab.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.SessionFactory;

import com.cooklab.util.HibernateUtil;
@WebFilter(urlPatterns = { 
		/*Martin*/
		"/RecipeCreateServlet",
		"/RecipeUpdateServlet",
		"/RecipeOverviewImgServlet",
		"/RecipeOverviewServlet",
		"/RecipeCollectionServlet",
		"/RecipeCollectionStatusServlet",
		"/RecipeIngredientServlet",
		"/RecipeKitchenwareServlet",
		"/RecipeCommentsServlet",
		"/HashtagServlet",
		/*Martin*/
		"/MemberCenter",
		"/EditPassword",
		"/MembersImgServlet",
		"/MemberRecipeServlet",
		"/Register",
		"/MembersServlet",
		"/Sign",
		"/Verification",
		"/MemberOrderForServlet",
		"/FollowServlet",
		"/MemberNotifyServlet",
		"/MembersArticleServlet",
		"/CheckLogin",
		"/ArticleServlet",
		"/ArticleSubServlet",
		"/ArticleCategoryServlet",
		"/ArticleReactionServlet",
		"/ArticleSubReactionServlet",
		"/frontstage/article/*",
		"/CheckLogin",	
		"/AdminsServlet",
		"/PermissionServlet",
		"/ArticleReportServlet",
		"/ArticleSubReportServlet",
		"/MemberdashboardServlet",
		"/LoginServlet",
		"/ReVerification",
		"/ForgetPassowrd",
		"/SupportFormServlet", 
		"/SupportFormRecordServlet", 
		"/NotifyCenterServlet",
		"/QuestionServlet",
		"/SFSFront",
		"/dashboard/article/*",
		"/ProductServlet", //商品
		"/MemberOrderServlet", //會員訂單
		"/IngredientServlet",
		"/KitchenwaretServlet",
		"/CartServlet", //購物車
		"/DashboardRecipeServlet",
		"/QSFront",
		"/RecipeReportServlet",
		"/DisboardHastagServlet",
		"/AdvertiseServlet2",
		//廣告
		})
public class OpenSessionInViewFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			factory.getCurrentSession().beginTransaction();
			chain.doFilter(req, res);
			factory.getCurrentSession().getTransaction().commit();
			System.out.println("commit");	//測試
		} catch (Exception e) {
			factory.getCurrentSession().getTransaction().rollback();
			System.out.println("rollback"); //測試
			e.printStackTrace();
			chain.doFilter(req, res);
		}
	}

}
