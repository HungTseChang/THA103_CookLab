package com.cooklab.article_sub_report.model;
import java.util.List;



public interface ArticleSubReportDAO_interface {
	public void insert(ArticleSubReportVO articleSubReportVO);
    public void update(ArticleSubReportVO articleSubReportVO);
    public void delete(Integer articleSubReportNo);
    public ArticleSubReportVO findByPrimaryKey(Integer articleSubReportNo);
    public List<ArticleSubReportVO> getAll();

}
