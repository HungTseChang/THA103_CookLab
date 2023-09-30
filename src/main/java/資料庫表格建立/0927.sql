insert into members (member_account, member_password, member_cellphone, member_mail, member_date, member_address, member_country, member_status, member_picture,member_nickname, member_gender, member_introduce)
value( "a123456", "awerty", "0920-123-456'" , "a123456@gmail.com", "2001-09-10" , "台北市北投區", "台灣", 0, null , "北投七頭郎", 0, "聽說我是自我介紹"),
     ( 'b123456', 'awerty', "0956-987-897" , "b123456@gmail.com","1976-09-08", "桃園龜山鄉","台灣",0,null,"nike",1,"聽說我是自我介紹之類的"),
     ( 'c123456', 'awerty', '0937-988-122' , 'c123456@gmail.com','1998-11-18' , '台中市政北七路','台灣',0,null,'大偉盧曼',0,"聽說我是自我介紹什麼的"),
     ( 'd123456', 'awerty', '0911-342-789' , 'd123456@gmail.com','2001-02-09' , '東京都台東區','日本',0,null,'MISAKI',1,"聽說我是自我介紹的芭芭拉"),
( 'e123456', 'awerty', '0921-931-493' , 'e123456@gmail.com','1972-12-11' , '雲林縣虎尾','台灣',0,null,'WhoPaWho',0,"聽說我是自我介紹");
     
INSERT INTO recipe (member_id,recipe_name,cover_image,introduction,additional_explanation,region,recipe_status,report_count,view_count,recipe_quantity) 
VALUES  ( 1,"食譜名稱", null, "簡介", "補充說明", "地區", 1, 1, 1, 1),
		( 2,"食譜名稱", null, "簡介", "補充說明", "地區", 1, 1, 1, 1),
		( 3,"食譜名稱", null, "簡介", "補充說明", "地區", 1, 1, 1, 1),
		( 4,"食譜名稱", null, "簡介", "補充說明", "地區", 1, 1, 1, 1),
        ( 5,"食譜名稱", null, "簡介", "補充說明", "地區", 1, 1, 1, 1);
       
	INSERT INTO recipe_reaction (recipe_no, member_id, recipe_reaction_status)
	VALUES(1, 1, 1),
		  (2, 2, 0),
		  (3, 3, 1);


INSERT INTO recipe_comments (recipe_no, member_id, comment_content, report_comments)
VALUES
  (1, 1,'Inappropriate content1', 1),
  (2, 2,  'Inappropriate content2', 1),
  (3, 3,  'Inappropriate content3', 1),
  (4, 4,  'Inappropriate content4', 1),
  (5, 5,  'Inappropriate content5', 1);
INSERT INTO recipe_comments_report (member_id, recipe_comments_no, reporting_comments_reason,  reporting_status)
VALUES
  (1, 1, 'Spam content', 1),
  (2, 2, 'Inappropriate language', 1),
  (3, 3, 'Offensive content', 0),
  (4, 4, 'Harassment',  2),
  (5, 5, 'Duplicate comment',  2);
      
INSERT INTO recipe_collection (recipe_no ,member_id) VALUES ( 1, 1);
INSERT INTO recipe_collection (recipe_no ,member_id) VALUES ( 2, 2);
INSERT INTO recipe_collection (recipe_no ,member_id ) VALUES ( 3, 3);
INSERT INTO recipe_collection (recipe_no ,member_id ) VALUES ( 4, 5);
INSERT INTO recipe_collection (recipe_no ,member_id ) VALUES ( 5, 5);

INSERT INTO recipe_step (recipe_no, step ,step_time ,step_img, step_content ) VALUES ( 1, 1, 100, null, "內文");
INSERT INTO recipe_step (recipe_no, step ,step_time ,step_img, step_content ) VALUES ( 2, 2, 100, null, "內文");
INSERT INTO recipe_step (recipe_no, step ,step_time ,step_img, step_content ) VALUES ( 3, 3, 100, null, "內文");
INSERT INTO recipe_step (recipe_no, step ,step_time ,step_img, step_content ) VALUES ( 4, 4, 100, null, "內文");
INSERT INTO recipe_step (recipe_no, step ,step_time ,step_img, step_content ) VALUES ( 5, 5, 100, null, "內文");

insert into recipe_report( member_id, recipe_no, reporting_reason ,reporting_status)
 value( 1, 1, '挖某尬意',0 ),
 (2, 2, '是大麻!!他加了大麻',1),
 ( 3, 3 ,'測試功能不要害怕' ,0),
 ( 4, 4, '我說那個醬汁呢???',1),
 (5, 5, '全部摻在一起變撒尿牛丸了',1);
      
INSERT INTO hashtag (hashtag_name, search_count, use_count,official_tags)
VALUES
  ('#foodie', 100, 50,1),
  ('#cooking', 80, 45,1),
  ('#recipes', 120, 70,1),
  ('#delicious', 90, 55,1),
  ('#homemade', 70, 40,1);

INSERT INTO recipe_hashtag (hashtag_no, recipe_no)
VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 4),
  (5, 5);
  
-- ingredient_category食材種類測試用資料
insert into ingredient_category(category_name) values ("蘋果");
insert into ingredient_category(category_name) values ("豬肉");
insert into ingredient_category(category_name) values ("雞肉");
insert into ingredient_category(category_name) values ("紅蘿蔔");
insert into ingredient_category(category_name) values ("番茄");

-- kitchenware_category廚具種類測試用資料
insert into kitchenware_category(category_name) values ("鑄鐵");
insert into kitchenware_category(category_name) values ("琺瑯鍋");
insert into kitchenware_category(category_name) values ("不鏽鋼鍋");
insert into kitchenware_category(category_name) values ("鍋鏟");
insert into kitchenware_category(category_name) values ("撈麵網");

-- product商品測試用資料
INSERT INTO product (product_name, sale_qty, product_dec, product_introduction, product_price, offsale_time, shelf_time, storage_qty, ingredient_category_no, kitchenware_category_no, search_count)
VALUES
    ('番茄', 20, '番茄', '番茄', 200, NULL, NOW(), 40, 
    (select ingredient_category_no from ingredient_category where category_name = "番茄"), default, 0),
    ('鑄鐵鍋', 15, '鑄鐵鍋', '鑄鐵鍋', 300, NULL, NOW(), 30, default,
    (select kitchenware_category_no from kitchenware_category where category_name = "鑄鐵鍋"), 0),
    ('琺瑯鍋', 15, '琺瑯鍋', '琺瑯鍋', 300, NULL, NOW(), 30, default,
    (select kitchenware_category_no from kitchenware_category where category_name = "琺瑯鍋"), 0),
    ('不鏽鋼鍋', 15, '不鏽鋼鍋', '不鏽鋼鍋', 300, NULL, NOW(), 30, default,
    (select kitchenware_category_no from kitchenware_category where category_name = "不鏽鋼鍋"), 0),
    ('紅蘿蔔', 25, '紅蘿蔔', '紅蘿蔔', 50, NULL, NOW(), 60, 
    (select ingredient_category_no from ingredient_category where category_name = "紅蘿蔔"), default, 0);

INSERT INTO recipe_ingredient (recipe_no ,product_no ,text_label,ingredient_quantity ) VALUES ( 1, 1, "純文字標籤", "食材份量");
INSERT INTO recipe_ingredient (recipe_no ,product_no ,text_label,ingredient_quantity ) VALUES ( 2, 2, "純文字標籤", "食材份量");
INSERT INTO recipe_ingredient (recipe_no ,product_no ,text_label,ingredient_quantity ) VALUES ( 3, 3, "純文字標籤", "食材份量");
INSERT INTO recipe_ingredient (recipe_no ,product_no ,text_label,ingredient_quantity ) VALUES ( 4, 4, "純文字標籤", "食材份量");
INSERT INTO recipe_ingredient (recipe_no ,product_no ,text_label,ingredient_quantity ) VALUES ( 5, 5, "純文字標籤", "食材份量");
-- 食譜使用食材清單
INSERT INTO recipe_kitchenware (recipe_no ,product_no ,text_label ) VALUES ( 1, 1, "純文字標籤");
INSERT INTO recipe_kitchenware (recipe_no ,product_no ,text_label ) VALUES ( 2, 2, "純文字標籤");
INSERT INTO recipe_kitchenware (recipe_no ,product_no ,text_label ) VALUES ( 3, 3, "純文字標籤");
INSERT INTO recipe_kitchenware (recipe_no ,product_no ,text_label ) VALUES ( 4, 4, "純文字標籤");
INSERT INTO recipe_kitchenware (recipe_no ,product_no ,text_label ) VALUES ( 5, 5, "純文字標籤");
-- 食譜使用廚具清單

-- purchase_order測試用資料
insert into purchase_order(purchase_order_date, purchase_order_supplier, purchase_order_total) values ('2023-09-11' , "tibame", 400);
insert into purchase_order(purchase_order_date, purchase_order_supplier, purchase_order_total) values ('2023-09-11', "pxxme", 500);
insert into purchase_order(purchase_order_date, purchase_order_supplier, purchase_order_total) values ('2023-09-11', "hoho", 600);
insert into purchase_order(purchase_order_date, purchase_order_supplier, purchase_order_total) values ('2023-09-11', "小農天堂", 600);
insert into purchase_order(purchase_order_date, purchase_order_supplier, purchase_order_total) values ('2023-09-11', "台灣莊園", 600);

-- purchase_order_detail進貨商品表單明細測試用資料
INSERT INTO purchase_order_detail (product_name, product_qty, expired_date, purchase_order_no, product_no, purchase_order_price)
VALUES
    ('番茄', 10, '2023-09-30', 
    (select purchase_order_no from purchase_order where purchase_order_supplier = "tibame" and purchase_order_date="2023-09-11"), 
    (select product_no from product where product_name = "番茄"), 150),
    ('紅蘿蔔', 5, '2023-10-15', 
    (select purchase_order_no from purchase_order where purchase_order_supplier = "小農天堂" and purchase_order_date="2023-09-11"), 
    (select product_no from product where product_name = "紅蘿蔔"), 250),
    ('鑄鐵鍋', 8, '2023-09-25',
    (select purchase_order_no from purchase_order where purchase_order_supplier = "hoho" and purchase_order_date="2023-09-11"), 
    (select product_no from product where product_name = "鑄鐵鍋"),40),
    ('琺瑯鍋', 15, '2023-10-05', 
    (select purchase_order_no from purchase_order where purchase_order_supplier = "pxxme" and purchase_order_date="2023-09-11"), 
    (select product_no from product where product_name = "琺瑯鍋"), 100),
    ('不鏽鋼鍋', 3, '2023-10-20',
    (select purchase_order_no from purchase_order where purchase_order_supplier = "台灣莊園" and purchase_order_date="2023-09-11"), 
    (select product_no from product where product_name = "不鏽鋼鍋"), 300);
    
INSERT INTO shopping_cart (member_id,product_no,product_qty) 
			VALUES (1, 1, 200);

-- 優惠碼
INSERT INTO promo_code (promo_code_serial_number,start_time,end_time,percentage_discount_amount,fixed_discount_amount,usages_allowed,minimum_consumption) 
			VALUES ("0A0X0C", "2023-09-09 12:34:56", "2025-09-09 12:34:56",0, 1000, 5000,0);

-- 優惠碼使用狀態
INSERT INTO promo_code_used (member_id,promo_code_no) 
			VALUES (1, 1);
            
-- 會員訂單
INSERT INTO member_order (member_id,order_status,total_order_amount,checkout_amount,promo_code_no,shipping_address) 
			VALUES (1, 1, 2000, 1000,  1 ,"台北市南京復興");

-- 訂單明細
INSERT INTO order_detail (order_no,product_no,order_qty) 
			VALUES (1, 1, 200);
            
            

 -- 新增 問題群組

INSERT INTO question_group(question_group_no,question_name) VALUE(1,"帳號安全問題");
INSERT INTO question_group(question_group_no,question_name) VALUE(2,"帳號安全問題");

-- 新增 常見問題

INSERT INTO question (question_group_no, question_title, question_content, question_good, question_bad)
VALUE(1,"忘記密碼","我忘了密碼該怎麼半",99,3),
	 (2,"忘記帳號","我忘了帳號該怎麼半",100,2);

-- 新增 權限類別

INSERT INTO permission(permission_title,super_admin,cancel_all_permission,membership_management,advertising_management,reporting_management,article_management,recipe_management) 
VALUE("董事長",1,1,1,1,1,1,1),
     ("清潔工",2,2,2,2,2,2,2);

-- 新增管理員

INSERT INTO admins(admin_nickname,permission_no,admin_account,admin_password) 
VALUE("前台管理",1,"AAA123","1234"),
     ("廁所管理",2,"BBB123","1234");

-- 新增廣告

INSERT INTO advertise(advertise_name,advertise_shelf_time,advertise_offsale_time,advertise_img,advertise_url) VALUE("優惠券推廣","2023-02-02",NULL,"2023-03-03","https://www.youtube.com/"); 

insert into notify_center(member_id, notify_type, notify_read, notify_content)
 value(1, 1, 0 , "隨便打打不要在意喔1"),
      (2, 0, 0 ,  "隨便打打不要在意喔2"),
      ( 3, 1, 0 ,  "隨便打打不要在意喔3"),
	   ( 4, 1, 0 , "隨便打打不要在意喔4"),
	   ( 5, 1, 0 , '隨便打打不要在意喔5');
    

-- 增新討論區資料--

INSERT INTO article (article_category,article_title,member_id,article_status,article_content,article_count,view_count) 
                     VALUES ( "閒聊版", "昨天大家吃甚麼?", 1, 1, "今年好霉氣全無財錦進門養豬各各大老鼠各各瘟做酒缸缸好做醋滴滴酸", 1, 7),
                            ( "閒聊版", "今天大家吃甚麼?", 2, 0, "今年好霉氣全無財錦進門養豬各各大老鼠各各瘟做酒缸缸好做醋滴滴酸", 2, 7),
							( "閒聊版", "明天大家吃甚麼?", 3, 1, "今年好霉氣全無財錦進門養豬各各大老鼠各各瘟做酒缸缸好做醋滴滴酸", 3, 7),
                            ( "閒聊版", "後天大家吃甚麼?", 2, 0, "今年好霉氣全無財錦進門養豬各各大老鼠各各瘟做酒缸缸好做醋滴滴酸", 4, 7),
                            ( "閒聊版", "大後天大家吃甚麼?", 1, 1, "今年好霉氣全無財錦進門養豬各各大老鼠各各瘟做酒缸缸好做醋滴滴酸", 5, 7);
 
 -- 討論區反應 資料
INSERT INTO article_reaction(member_id,article_no,statuts) 
					VALUES (1, 3, 1),
						   (2, 2, 0),
						   (3, 1, 1),
						   (2, 3, 0),
						   (1, 1, 1);
                           
-- 討論區文章檢舉
INSERT INTO article_report (article_no,reporter_id,reporting_reason,reporting_status) 
					VALUES (1, 1, "排版好累", 1),
						   (2, 2, "排版真的好累", 1),
						   (3, 3, "排版真得確實好累", 0),
						   (2, 1, "排版確實好累", 1),
						   (1, 2, "排版好累", 1);

-- 討論區圖片
INSERT INTO article_picture (article_no,picture) 
							VALUES (1, 1),
								   (2, 1),
								   (3, 1),
                                   (4, 1),
                                   (5, 1);


                                   
-- 討論區回文文章 article_sub --
Insert into article_sub (article_no, member_id, article_sub_status, article_sub_content , article_sub_count, last_edit_timestamp)
value( 1 ,2 , 3, "這是回文" , 20 ,now()),
( 2 ,3 , 3, "這是回文" , 20 ,now()),
( 3 ,4 , 3, "這是回文" , 20 ,now()),
( 4 ,1 , 2, "這是回文" , 20 ,now()),
( 5 ,4 , 3, "這是回文" , 20 ,now());

-- 討論區回文圖片 article_sub_picture
INSERT INTO article_sub_picture (article_sub_no,picture)
							VALUES (1, 1),
								   (2, 1),
								   (3, 1),
                                   (4, 1),
                                   (5, 1);


-- 討論區回文檢舉 article_sub_report --      
Insert into article_sub_report (article_sub_no, reporter_id, reporting_reason, reporting_status)
value( 1 ,2 ,  "我就是想檢舉" , 1),
( 2 ,3 ,  "我就是想檢舉!" , 0),
( 3 ,4 ,  "我就是想檢舉" , 1),
( 4 ,5 ,  "我就是想檢舉" , 0),
( 5 ,1 ,  "我就是想檢舉" , 1);                          
                                   
                                   
--  關注會員 member_collection --
INSERT INTO  member_collection( member_id_collectioned, member_id )
VALUE(1,2),(2,3),(3,4),(4,5);

-- 討論區文章收藏--
INSERT INTO  article_collection(article_no, member_id)
VALUE(1,2),(2,3),(3,4),(4,5);



INSERT INTO member_collection (member_id_collectioned,member_id) 
			VALUES  (1,2),(1,3),(2,4);
            
INSERT INTO support_form (real_name,support_form_category_id,reply_email,form_context,
form_status,form_title,form_source,form_submitter)
VALUE("客戶",1,"AAA@cooklab.com","問題很多",1,"就是問題","官方表單","客服A");

INSERT INTO support_form_record (form_no,record_context,admin_no)
VALUE(1,"我是處理紀錄",1);