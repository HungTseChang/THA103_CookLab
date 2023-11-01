use cook_lab;
ALTER TABLE permission 
ADD mall_management  TINYINT NOT NULL AFTER recipe_management;
ALTER TABLE permission CHANGE super_admin admin_management TINYINT NOT NULL;
ALTER TABLE permission CHANGE cancel_all_permission service_management TINYINT NOT NULL;
ALTER TABLE admins
ADD admin_email 		VARCHAR(100) NOT NULL after admin_account;