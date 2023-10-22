CREATE DATABASE IF NOT EXISTS cook_lab;

USE cook_lab;

-- 會員
CREATE TABLE members (
	member_id			INT AUTO_INCREMENT  NOT NULL PRIMARY KEY,
	member_account 		VARCHAR(50) NOT  NULL,
	member_password 	VARCHAR(50) NOT NULL,
	member_introduce 	VARCHAR(500),
	member_cellphone 	VARCHAR(20) DEFAULT NULL,
	member_mail 		VARCHAR(100) NOT NULL,
	member_date 		DATE ,
	member_address 		VARCHAR(200) DEFAULT NULL,
	member_country 		VARCHAR(20) DEFAULT NULL,
	member_status 		TINYINT DEFAULT NULL,
	member_picture 		LONGBLOB DEFAULT NULL,
	member_nickname  	VARCHAR(20) DEFAULT NULL,
	member_gender 		TINYINT DEFAULT NULL,
	created_timestamp 	DATETIME DEFAULT now(),
	last_edit_timestamp	DATETIME DEFAULT now()
); 

-- 食譜
CREATE TABLE recipe (
	recipe_no    			INT AUTO_INCREMENT PRIMARY KEY,
    member_id 				INT, 
    recipe_name 			VARCHAR(100) NOT NULL,
    cover_image    			LONGBLOB,
    introduction    		VARCHAR(400),
    additional_explanation	VARCHAR(400),
    region    				VARCHAR(40),
    recipe_status    		TINYINT NOT NULL,
    report_count    		INT UNSIGNED DEFAULT 0,
    view_count    			INT UNSIGNED DEFAULT 0,
    recipe_quantity 		TINYINT NOT NULL,
    last_edit_timestamp		DATETIME DEFAULT  now(),
    created_timestamp 		DATETIME DEFAULT  now(),
    CONSTRAINT recipe_fk FOREIGN KEY(member_id) REFERENCES members(member_id)
);

-- 食譜反應
CREATE TABLE recipe_reaction (
	recipe_no 				INT,
	member_id 				INT,
	recipe_reaction_status	TINYINT,
	created_timestamp 		DATETIME DEFAULT now(),
	PRIMARY KEY (recipe_no,member_id),
	CONSTRAINT recipe_reaction_fk1 FOREIGN KEY (recipe_no) REFERENCES recipe (recipe_no),
	CONSTRAINT recipe_reaction_fk2 FOREIGN KEY (member_id) REFERENCES members (member_id)
);

-- 食譜留言區
CREATE TABLE recipe_comments (
	recipe_comments_no 	INT AUTO_INCREMENT PRIMARY KEY,
	recipe_no 			INT NOT NULL,
	member_id 			INT NOT NULL,
	comment_content 	VARCHAR(400),
	report_comments 	INT UNSIGNED DEFAULT 0,
	last_edit_timestamp	DATETIME DEFAULT now(),
	created_timestamp 	DATETIME DEFAULT now(),
	CONSTRAINT recipe_comments_fk1 FOREIGN KEY (recipe_no) REFERENCES recipe (recipe_no),
	CONSTRAINT recipe_comments_fk2 FOREIGN KEY (member_id) REFERENCES members (member_id)
);

-- 食譜留言檢舉
CREATE TABLE recipe_comments_report (
	recipe_report_no 			INT AUTO_INCREMENT PRIMARY KEY,
	member_id 				INT NOT NULL,
	recipe_comments_no 		INT NOT NULL,
	reporting_comments_reason VARCHAR(500),
	reporting_status 			TINYINT,
	reporting_answer   VARCHAR(500),
	created_timestamp			DATETIME DEFAULT now(),
	CONSTRAINT recipe_comments_report_fk1 FOREIGN KEY (member_id) REFERENCES members (member_id),
	CONSTRAINT recipe_comments_report_fk2 FOREIGN KEY (recipe_comments_no) REFERENCES recipe_comments (recipe_comments_no)
);

-- 食譜收藏
CREATE TABLE recipe_collection (
	collection_no   	INT AUTO_INCREMENT PRIMARY KEY,
    recipe_no    		INT NOT NULL, 
    member_id     		INT NOT NULL,
    created_timestamp	DATETIME DEFAULT now(),
    CONSTRAINT recipe_collection_fk1 FOREIGN KEY (recipe_no) REFERENCES recipe (recipe_no),
    CONSTRAINT recipe_collection_fk2 FOREIGN KEY (member_id) REFERENCES members (member_id)
);

  -- 步驟
CREATE TABLE recipe_step (
	recipe_no 			INT NOT NULL,
    step  				INT NOT NULL,
    step_time    		INT NOT NULL,
    step_img 			LONGBLOB,
    step_content 		VARCHAR(400),
    created_timestamp	DATETIME DEFAULT now(),
    PRIMARY KEY (recipe_no, step),
    CONSTRAINT recipe_step_fk FOREIGN KEY(recipe_no ) REFERENCES recipe(recipe_no )
);

-- 食譜檢舉
CREATE TABLE recipe_report (
	recipe_report_no 	INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	member_id 			INT NOT  NULL,
	recipe_no 			INT NOT NULL,
	reporting_reason 	VARCHAR(500) DEFAULT NULL,
	reporting_status 	TINYINT,
     reporting_answer   VARCHAR(500),
	created_timestamp	DATETIME DEFAULT now(),
	CONSTRAINT recipe_report_fk1 FOREIGN KEY (member_id) REFERENCES members(member_id),
	CONSTRAINT recipe_report_fk2 FOREIGN KEY (recipe_no) REFERENCES recipe(recipe_no)
); 

-- 標籤
CREATE TABLE hashtag (
	hashtag_no 			INT AUTO_INCREMENT PRIMARY KEY,
	hashtag_name		VARCHAR (100) NOT NULL,
    category_tags 		VARCHAR (50),
	search_count  		INT DEFAULT 0,
	use_count 			INT DEFAULT 0 ,
    official_tags 	 	TINYINT NOT NULL,
	created_timestamp	DATETIME DEFAULT now()
); 

-- 食譜使用標籤
CREATE TABLE recipe_hashtag (
	recipe_hashtag_no 	INT AUTO_INCREMENT PRIMARY KEY,
	hashtag_no  		INT NOT NULL,
	recipe_no 			INT NOT NULL,
	created_timestamp	DATETIME DEFAULT now(),
	CONSTRAINT recipe_hashtag_fk1 FOREIGN KEY (hashtag_no) REFERENCES hashtag(hashtag_no),
	CONSTRAINT recipe_hashtag_fk2 FOREIGN KEY (recipe_no) REFERENCES recipe(recipe_no)
); 

-- 食材種類
CREATE TABLE ingredient_category (
	ingredient_category_no	INT AUTO_INCREMENT PRIMARY KEY,
	category_name 			VARCHAR(50) NOT NULL,
	created_timestamp 		DATETIME DEFAULT now()
);

-- 廚具種類
CREATE TABLE kitchenware_category (
	kitchenware_category_no	INT AUTO_INCREMENT PRIMARY KEY,
	category_name 			VARCHAR(50) NOT NULL,
	created_timestamp 		DATETIME DEFAULT now()
);

-- 商品
CREATE TABLE product (
	product_no 				INT auto_increment PRIMARY KEY,
	product_name 			VARCHAR(20) NOT NULL,
	sale_qty 				INT UNSIGNED NOT NULL,
	product_dec 			LONGTEXT,
	product_introduction	LONGTEXT,
	product_price 			INT UNSIGNED NOT NULL,
	offsale_time 			DATETIME,
	shelf_time 				DATETIME,
	storage_qty    			INT NOT NULL,
	ingredient_category_no 	INT,
	kitchenware_category_no	INT,
	search_count 			INT,
	product_picture         LONGBLOB,
	created_timestamp 		DATETIME DEFAULT now(),
	CONSTRAINT product_fk1 FOREIGN KEY (ingredient_category_no) REFERENCES ingredient_category(ingredient_category_no),
	CONSTRAINT product_fk2 FOREIGN KEY (kitchenware_category_no) REFERENCES kitchenware_category(kitchenware_category_no)
);

-- 食譜使用食材
CREATE TABLE recipe_ingredient (
    recipe_ingredient_no	INT AUTO_INCREMENT PRIMARY KEY,
    recipe_no				INT NOT NULL, 
    product_no				INT ,
    text_label				VARCHAR(20),
    ingredient_quantity		VARCHAR(20) NOT NULL,
    created_timestamp 		DATETIME DEFAULT now(),
	CONSTRAINT recipe_ingredient_fk1 FOREIGN KEY(recipe_no) REFERENCES recipe(recipe_no),
    CONSTRAINT recipe_ingredient_fk2 FOREIGN KEY(product_no) REFERENCES product(product_no)
);

-- 食譜使用廚具
CREATE TABLE recipe_kitchenware (
    recipe_kitchenware_no	INT AUTO_INCREMENT PRIMARY KEY,
    recipe_no				INT NOT NULL, 
    product_no				INT,
    text_label				VARCHAR(20),
    created_timestamp 		DATETIME DEFAULT now(),
	CONSTRAINT recipe_kitchenware_fk1 FOREIGN KEY(recipe_no) REFERENCES recipe(recipe_no),
    CONSTRAINT recipe_kitchenware_fk2 FOREIGN KEY(product_no) REFERENCES product(product_no)
);

-- 進貨表單
CREATE TABLE purchase_order (
	purchase_order_no 		INT AUTO_INCREMENT PRIMARY KEY,
	purchase_order_date 	DATE NOT NULL,
	purchase_order_supplier	VARCHAR(20),
	purchase_order_total 	INT UNSIGNED NOT NULL,
	created_timestamp 		DATETIME DEFAULT now()
);

-- 進貨商品表單明細
CREATE TABLE purchase_order_detail (
	order_detail_no 		INT AUTO_INCREMENT PRIMARY KEY,
	product_name 			VARCHAR(50),
	product_qty				INT UNSIGNED NOT NULL,    
	expired_date 			DATE,
	purchase_order_no		INT NOT NULL,
	product_no 				INT NOT NULL,
	purchase_order_price	INT,
	created_timestamp 		DATETIME DEFAULT now(),
	CONSTRAINT purchase_order_detail_fk1 FOREIGN KEY (purchase_order_no) REFERENCES purchase_order(purchase_order_no),
	CONSTRAINT purchase_order_detail_fk2 FOREIGN KEY (product_no) REFERENCES product(product_no)
);

-- 購物車明細
CREATE TABLE shopping_cart (
	shopping_cart_no  	INT AUTO_INCREMENT PRIMARY KEY,
	member_id   		INT  NOT NULL,
	product_no   		INT NOT NULL,
	product_qty  		INT  NOT NULL,
    created_timestamp 	DATETIME DEFAULT now(),
	CONSTRAINT shopping_cart_fk1 FOREIGN KEY (member_id) REFERENCES members (member_id),
    CONSTRAINT shopping_cart_fk2 FOREIGN KEY (product_no) REFERENCES product (product_no)
);

-- 優惠碼
CREATE TABLE promo_code (
	promo_code_no				INT AUTO_INCREMENT PRIMARY KEY,
    promo_code_serial_number 	VARCHAR(15) NOT NULL,
	start_time	  				DATETIME NOT NULL,
	end_time		  			DATETIME NOT NULL,
    percentage_discount_amount 	DECIMAL(4,3),
    fixed_discount_amount 		DECIMAL(8,2),
    usages_allowed 				INT,
    minimum_consumption 		INT,
    created_timestamp 			DATETIME DEFAULT now()
);

-- 優惠碼使用情況
CREATE TABLE promo_code_used  (
	promo_code_used_no	INT AUTO_INCREMENT PRIMARY KEY,
	member_id 			INT,
	promo_code_no		INT, 
    created_timestamp 	DATETIME DEFAULT now(),
    CONSTRAINT promo_code_used_fk1 FOREIGN KEY (member_id) REFERENCES members (member_id),
    CONSTRAINT promo_code_used_fk2 FOREIGN KEY (promo_code_no) REFERENCES promo_code (promo_code_no)
);

-- 會員訂單
CREATE TABLE member_order  (
	order_no      					INT AUTO_INCREMENT PRIMARY KEY,
	member_id						INT,
	order_status					TINYINT, 
	total_order_amount 				INT UNSIGNED DEFAULT 0,
    checkout_amount 				INT UNSIGNED DEFAULT 0,
	promo_code_no  					INT,
	shipping_address 				VARCHAR(200),
    created_timestamp 				DATETIME DEFAULT now(),
    CONSTRAINT member_order_fk1	FOREIGN KEY (member_id) REFERENCES members (member_id),
    CONSTRAINT member_order_fk2 FOREIGN KEY (promo_code_no) REFERENCES promo_code (promo_code_no)
);

-- 訂單明細
CREATE TABLE order_detail(
	order_detail_no 	INT AUTO_INCREMENT PRIMARY KEY ,
    order_no 			INT NOT NULL,
    product_no  		INT NOT NULL,
    order_qty  			INT NOT NULL,
    created_timestamp	DATETIME DEFAULT now(),
	CONSTRAINT order_detail_fk1 FOREIGN KEY (order_no) REFERENCES member_order (order_no),
    CONSTRAINT order_detail_fk2 FOREIGN KEY (product_no) REFERENCES product (product_no)
);

-- 討論區文章分類
CREATE TABLE article_category(
    article_category_no    INT AUTO_INCREMENT PRIMARY KEY,
    article_category    VARCHAR(30),
    category_status 	tinyint default 0 ,
    created_timestamp 	DATETIME DEFAULT now()
);

-- 討論區文章
CREATE TABLE article(
    article_no    		INT AUTO_INCREMENT PRIMARY KEY,
    article_category    INT NOT NULL,
    article_title 		VARCHAR(100) NOT NULL,
    member_id     		INT NOT NULL,
    article_status  	TINYINT   NOT NULL,
    article_content 	LONGTEXT  NOT NULL,
    article_count  		INT,
	view_count  		INT,
	last_edit_timestamp	DATETIME DEFAULT now() ,
    created_timestamp 	DATETIME DEFAULT now(),
    CONSTRAINT article_member_id_fk FOREIGN KEY (member_id) REFERENCES members (member_id),
	CONSTRAINT article_category_fk FOREIGN KEY (article_category) REFERENCES article_category(article_category_no)
);

-- 討論區文章檢舉
CREATE TABLE article_report(
	article_report_no 	INT AUTO_INCREMENT PRIMARY KEY,
	article_no 			INT NOT NULL,
	reporter_id 		INT NOT NULL,
	reporting_reason 	VARCHAR(500) NOT NULL,
	reporting_status 	TINYINT NOT NULL,
    reporting_answer   VARCHAR(500),
	created_timestamp	DATETIME DEFAULT now(),
	CONSTRAINT article_report_fk1 FOREIGN KEY (article_no) REFERENCES article (article_no),
	CONSTRAINT article_report_fk2 FOREIGN KEY (reporter_id) REFERENCES  members (member_id)
);

-- 討論區文章反應 
CREATE TABLE article_reaction(
	article_reaction_no	INT AUTO_INCREMENT PRIMARY KEY,
	member_id			INT ,
	article_no 			INT,
	statuts 			TINYINT NOT NULL,
	created_timestamp 	DATETIME DEFAULT now(),
	CONSTRAINT article_reaction_fk1 FOREIGN KEY (member_id) REFERENCES members (member_id),
	CONSTRAINT article_reaction_fk2 FOREIGN KEY (article_no) REFERENCES article (article_no)
);

-- 討論區圖片
CREATE TABLE article_picture(
	article_picture_no	INT AUTO_INCREMENT PRIMARY KEY,
	article_no 			INT,
	picture 			LONGBLOB,
	created_timestamp	DATETIME DEFAULT now(),
	CONSTRAINT article_picture_fk FOREIGN KEY (article_no) REFERENCES article (article_no)
);

-- 討論區回文
CREATE TABLE article_sub (
    article_sub_no    	INT AUTO_INCREMENT PRIMARY KEY,
    article_no    		INT NOT NULL,
    member_id     		INT NOT NULL,
    article_sub_status  TINYINT   NOT NULL,
    article_sub_content LONGTEXT  NOT NULL,
    article_sub_count  	INT,
	last_edit_timestamp	DATETIME DEFAULT now(),
    created_timestamp 	DATETIME DEFAULT now(),
    CONSTRAINT article_sub_fk1 FOREIGN KEY (member_id) REFERENCES members (member_id),
    CONSTRAINT article_sub_fk2 FOREIGN KEY (article_no) REFERENCES article (article_no)
);

-- 討論區回文的文章反應
CREATE TABLE article_sub_reaction(
	article_sub_reaction_no	INT AUTO_INCREMENT PRIMARY KEY,
	member_id				INT ,
	article_sub_no 			INT,
	statuts 				TINYINT NOT NULL,
	created_timestamp 		DATETIME DEFAULT now(),
	CONSTRAINT article_sub_reaction_fk1 FOREIGN KEY (member_id) REFERENCES members (member_id),
	CONSTRAINT article_sub_reaction_fk2 FOREIGN KEY (article_sub_no) REFERENCES article_sub (article_sub_no)
);

-- 討論區回文檢舉
CREATE TABLE article_sub_report(
	article_sub_report_no	INT AUTO_INCREMENT PRIMARY KEY,
	article_sub_no 			INT NOT NULL,
	reporter_id 			INT NOT NULL,
	reporting_reason  		VARCHAR(500) NOT NULL,
	reporting_status 		TINYINT NOT NULL,
	reporting_answer         VARCHAR(500),
	created_timestamp		DATETIME DEFAULT now(),
	CONSTRAINT article_sub_report_fk1 FOREIGN KEY (article_sub_no) REFERENCES article_sub (article_sub_no),
	CONSTRAINT article_sub_report_fk2 FOREIGN KEY (reporter_id) REFERENCES  members (member_id)
);

-- 討論區回文圖片
CREATE TABLE article_sub_picture(
	article_sub_picture_no	INT AUTO_INCREMENT PRIMARY KEY,
	article_sub_no 			INT NOT NULL,
	picture 				LONGBLOB,
	created_timestamp 		DATETIME DEFAULT now(),
	CONSTRAINT article_sub_picture_fk FOREIGN KEY (article_sub_no) REFERENCES article_sub (article_sub_no)
);

-- 問題群組
CREATE TABLE question_group (
	question_group_no	INT PRIMARY KEY,
	question_name 		VARCHAR(100),
	created_timestamp	DATETIME DEFAULT now()
);

-- 常見問題
CREATE TABLE question (
	question_no 		INT AUTO_INCREMENT PRIMARY KEY,
	question_group_no	INT NOT NULL,
	question_title 		VARCHAR(100) NOT NULL,
	question_content 	VARCHAR(500) NOT NULL,
	question_good 		INT DEFAULT 0,
	question_bad 		INT DEFAULT 0,
	created_timestamp 	DATETIME DEFAULT now(),
	CONSTRAINT question_fk FOREIGN KEY(question_group_no) REFERENCES question_group(question_group_no)
);

-- 權限類別
CREATE TABLE permission(
	permission_no INT AUTO_INCREMENT PRIMARY KEY,
	permission_title 		VARCHAR(20),
	super_admin 			TINYINT NOT NULL,
	cancel_all_permission 	TINYINT NOT NULL,
	membership_management 	TINYINT NOT NULL,
	advertising_management	TINYINT NOT NULL,
	reporting_management 	TINYINT NOT NULL,
	article_management	 	TINYINT NOT NULL,
	recipe_management 		TINYINT NOT NULL,
	created_timestamp 		DATETIME DEFAULT now()
);

-- 管理員
CREATE TABLE admins(
	admin_no 			INT AUTO_INCREMENT PRIMARY KEY,
	admin_nickname 		VARCHAR(30) NOT NULL,
	permission_no 		INT NOT NULL,
	admin_account 		VARCHAR(20) NOT NULL,
	admin_password 		VARCHAR(20) NOT NULL,
	created_timestamp 	DATETIME DEFAULT now(),
	CONSTRAINT admins_fk FOREIGN KEY(permission_no) REFERENCES permission(permission_no)
);

-- 廣告
CREATE TABLE advertise (
	advertise_no 			INT AUTO_INCREMENT PRIMARY KEY,
	advertise_name 			VARCHAR(100) NOT NULL,
	advertise_shelf_time	DATETIME,  
	advertise_offsale_time	DATETIME,
	advertise_img 			LONGBLOB,
	advertise_url 			TEXT NOT NULL,
	created_timestamp 		DATETIME DEFAULT now()
);

-- 通知中心
CREATE TABLE notify_center (
	notify_no 			INT AUTO_INCREMENT  PRIMARY KEY,
	member_id  			INT NOT NULL,
	notify_type 		INT,
	notify_read 		TINYINT,
	notify_content 		VARCHAR(500),
	created_timestamp	DATETIME DEFAULT now(),
    CONSTRAINT notify_center_fk FOREIGN KEY(member_id) REFERENCES members(member_id) 
); 

-- 會員關注
CREATE TABLE member_collection (
	member_collection_no 	INT AUTO_INCREMENT PRIMARY KEY,
	member_id_collectioned	INT NOT NULL,
	member_id 				INT NOT NULL,
	created_timestamp 		DATETIME DEFAULT now(),
    CONSTRAINT member_collection_fk1 FOREIGN KEY(member_id_collectioned) REFERENCES members(member_id),
    CONSTRAINT member_collection_fk2 FOREIGN KEY(member_id) REFERENCES members(member_id) 
); 

-- 討論區文章收藏
CREATE TABLE article_collection (
	article_collection_no	INT AUTO_INCREMENT PRIMARY KEY,
	article_no 			INT NOT NULL,
	member_id 			INT NOT NULL,
	created_timestamp 	DATETIME DEFAULT now(),
	CONSTRAINT article_collection_fk1 FOREIGN KEY (article_no) REFERENCES article(article_no),
	CONSTRAINT article_collection_fk2 FOREIGN KEY (member_id) REFERENCES members(member_id)
); 

-- 客服表單
CREATE TABLE support_form (
	form_no 					INT AUTO_INCREMENT PRIMARY KEY,
	real_name 					VARCHAR(20) NOT NULL,
	support_form_category_id	INT NOT NULL,
	reply_email  				VARCHAR(100) NOT NULL,
    form_context 				LONGTEXT NOT NULL,
    form_title					VARCHAR(100) NOT NULL,
    form_status					TINYINT NOT NULL DEFAULT 0,
    form_source					TINYINT NOT NULL,
	form_submitter				VARCHAR(30) NOT NULL,
    form_responder				INT,
    created_timestamp 			DATETIME DEFAULT now(),
	CONSTRAINT support_form_fk1 FOREIGN KEY (form_responder) REFERENCES admins(admin_no)
); 

-- 表單處理紀錄
CREATE TABLE support_form_record (
	record_no 					INT AUTO_INCREMENT PRIMARY KEY,
	form_no 					INT NOT NULL,
    record_context				LONGTEXT NOT NULL,
	admin_no					INT NOT NULL,
    created_timestamp 			DATETIME DEFAULT now(),
	CONSTRAINT support_form_record_fk1 FOREIGN KEY (form_no) REFERENCES support_form(form_no),
	CONSTRAINT support_form_record_fk2 FOREIGN KEY (admin_no) REFERENCES admins(admin_no)
); 