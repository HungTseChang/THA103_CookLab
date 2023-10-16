package com.cooklab.article_report.model.model;

import java.util.List;

public interface ArticleReportDAO {
    public void insert(ArticleReportVO ArticleReportVO);
    public void update(ArticleReportVO ArticleReportVO);
    public void delete(Integer articleReportNo);
    public ArticleReportVO findByPrimaryKey(Integer articleReportNo);
    public List<ArticleReportVO> getAll();
}
