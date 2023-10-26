package com.cooklab.article_reaction.model;
import com.cooklab.article_reaction.model.ArticleReactionVO;
import com.cooklab.article_reaction.model.ArticleReactionService;

public class ArticleReactionTest {
	
	public static void main(String[] args) {
		
		ArticleReactionService arc = new ArticleReactionService();

        ArticleReactionVO result = arc.getOne(1);

        if (result != null) {
            // 打印结果或执行其他操作
            System.out.println("Found ArticleReactionVO: " + result);
        } else {
            System.out.println("ArticleReactionVO not found");
        }
		
	}	
}


