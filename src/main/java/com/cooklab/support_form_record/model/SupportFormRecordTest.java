package com.cooklab.support_form_record.model;

import java.util.List;

public class SupportFormRecordTest {
	public static void main(String[] args) {
		SupportFormRecordJDBCDAOIm sFRDAOIm = new SupportFormRecordJDBCDAOIm();

//	 新增
//	SupportFormRecordVO sFRvo1 = new SupportFormRecordVO();
//	sFRvo1.setFormNo(1);
//	sFRvo1.setRecordContext("測試");
//	sFRvo1.setAdminNo(1);
//	sFRDAOIm.insert(sFRvo1);

		// 修改
//	SupportFormRecordVO sFRvo2 = new SupportFormRecordVO();
//	sFRvo2.setFormNo(8);
//	sFRvo2.setRecordContext("測試333");
//	sFRvo2.setAdminNo(1);
//	sFRvo2.setRecordNo(2);
//	sFRDAOIm.update(sFRvo2);

		// 刪除
//	sFRDAOIm.delete(4);

		// 查詢單一資料
//	SupportFormRecordVO sFRvo3 = sFRDAOIm.findByPrimaryKey(3);
//	System.out.print(sFRvo3);
//	System.out.println("---------------------");

		// 查詢全部資料
		List<SupportFormRecordVO> list = sFRDAOIm.getAll();
		for (SupportFormRecordVO asFRvo3 : list) {
			System.out.print(asFRvo3);
			System.out.println();

		}
	}
}
