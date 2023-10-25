package com.cooklab.hashtag.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.hashtag.model.HashtagService;
import com.cooklab.hashtag.model.HashtagVO;
import com.google.gson.Gson;

@WebServlet("/HashtagServlet")
public class HashtagServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		Gson gson = new Gson();

		if ("getToRecipe".equals(action)) {

			Map<String, List<String>> mapCategory = new HashMap<>();
			List<String> popular = new ArrayList<>();
			List<String> cook = new ArrayList<>();
			List<String> meal = new ArrayList<>();
			List<String> particular = new ArrayList<>();
			List<String> festival = new ArrayList<>();

			List<HashtagVO> listPopularHashtagVO = new HashtagService().getPopularHashtag(3);
			List<HashtagVO> listHashtagVO = new HashtagService().getOfficalHashtag();

			for (HashtagVO hashtagVO : listPopularHashtagVO) {
				popular.add(hashtagVO.getHashtagName());
			}
			for (HashtagVO hashtagVO : listHashtagVO) {
				if (hashtagVO.getCategoryTags() != null)
					switch (hashtagVO.getCategoryTags()) {
					case "烹飪方式": {
						cook.add(hashtagVO.getHashtagName());
						break;
					}
					case "餐點類型": {
						meal.add(hashtagVO.getHashtagName());
						break;
					}
					case "特殊飲食需求": {
						particular.add(hashtagVO.getHashtagName());
					}
						break;
					case "節日": {
						festival.add(hashtagVO.getHashtagName());
						break;
					}
					}
			}
			mapCategory.put("popular", popular);
			mapCategory.put("cook", cook);
			mapCategory.put("meal", meal);
			mapCategory.put("particular", particular);
			mapCategory.put("festival", festival);

			String jsonString = gson.toJson(mapCategory);
			res.getWriter().write(jsonString);
			return;
		}

	}
}
