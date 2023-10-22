package com.cooklab.support_form_record.controller;

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
import javax.servlet.http.HttpSession;

import com.cooklab.support_form.model.SupportFormHService;
import com.cooklab.support_form.model.SupportFormVO;
import com.cooklab.support_form_record.model.SupportFormRecordHService;
import com.cooklab.support_form_record.model.SupportFormRecordVO;
import com.google.gson.Gson;

@WebServlet("/SupportFormRecordAjax")
public class SupportFormRecordAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 為了取得管理員登入資訊，需從Session取值
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 創建Gson物件以便將資料打包為json格式回傳給前端
		Gson gson = new Gson();

		if ("getAll".equals(action)) {
			Integer formNo = Integer.valueOf(req.getParameter("formNo"));
			SupportFormHService sfSvc = new SupportFormHService();

			// 呼叫SupportFormService的方法，透過Hibernate聯合映射取得相關的表單紀錄物件List，
			List<SupportFormRecordVO> list = sfSvc.getRecord(formNo);

			// 宣告MAP物件的LIST集合，以便將VO取出的資料轉為MAP形式
			List<Map<String, Object>> data = new ArrayList<>();

			// 使用FOR EACH一一取值並放置於MAP物件
			for (SupportFormRecordVO supportFormRecord : list) {
				Map<String, Object> supportFormRecordMap = new HashMap<>();
//				supportFormRecordMap.put("recordNo", supportFormRecord.getRecordNo());
				supportFormRecordMap.put("recordContext", supportFormRecord.getRecordContext());
				supportFormRecordMap.put("createdTimestamp", supportFormRecord.getCreatedTimestamp());
				supportFormRecordMap.put("recorderNo", supportFormRecord.getAdmins().getAdminNo());
				supportFormRecordMap.put("recorderName", supportFormRecord.getAdmins().getAdminNickname());
				data.add(supportFormRecordMap);
			}

			// 使用Gson轉換為Json格式
			String Data = gson.toJson(data);

			// 回傳資料給Ajax進行進一步的處理
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(Data);
		}

		if ("insert".equals(action)) {
			Integer formNo = Integer.valueOf(req.getParameter("formNo"));

			// 屆時會改從session取得adminNo，因需測試方便故改用固定值
//			Integer adminNo = (Integer) session.getAttribute("AdminNo");
			Integer adminNo = 1;

			String recordContext = req.getParameter("recordContext");

			SupportFormRecordHService sfrSvc = new SupportFormRecordHService();

			SupportFormRecordVO sfrVO = new SupportFormRecordVO();

			sfrVO = sfrSvc.addSupportFormRecord(recordContext, adminNo, formNo);

			if (sfrVO != null) {
//				String url = "/THA103_CookLab/dashboard/supportform/support-tickets-table.html" + "?" + "formNo="
//						+ formNo;

				// 創建Map物件放入成功訊息
				Map<String, String> successMsg = new HashMap<String, String>();
				successMsg.put("success", "data transfer success");
//				successMsg.put("url", url);

				String successjson = gson.toJson(successMsg);

				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(successjson);
				System.out.println("已送出回應");
			}
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
