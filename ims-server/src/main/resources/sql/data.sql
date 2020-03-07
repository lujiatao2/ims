INSERT INTO goods_category (`name`)
VALUES
	('手机'),
	('电脑'),
	('游戏机');

INSERT INTO goods (brand, model, `desc`, goods_category_id)
VALUES
	('Apple', 'iPhone 11', 'XR继任者', 1),
	('Apple', 'iPhone 11 Pro', 'XS继任者', 1),
	('Apple', 'iPhone 11 Pro Max', 'XS Max继任者', 1),
	('HUAWEI', 'Mate 30', '', 1),
	('HUAWEI', 'Mate 30 Pro', '', 1),
	('HUAWEI', 'Mate 30 5G', '5G版Mate 30', 1),
	('HUAWEI', 'Mate 30 Pro 5G', '5G版Mate 30 Pro', 1),
	('HUAWEI', 'Mate 30 RS', '保时捷版Mate 30', 1),
	('Apple', 'MacBook Air', '', 2),
	('Apple', 'MacBook Pro', '', 2),
	('Apple', 'iMac', '', 2),
	('Apple', 'iMac Pro', '', 2),
	('Lenovo', 'ThinkPad S2', '', 2),
	('Lenovo', 'ThinkPad E470', '', 2),
	('Lenovo', 'ThinkPad E480', '', 2),
	('Lenovo', 'ThinkPad E580', '', 2),
	('Lenovo', 'ThinkPad T490', '', 2),
	('Lenovo', 'ThinkPad T490S', '', 2),
	('Lenovo', 'ThinkPad T590', '', 2),
	('Lenovo', 'ThinkPad X390', '', 2),
	('Lenovo', 'ThinkPad X1 Carbon', '', 2);

INSERT INTO `user` (username, `password`, role)
VALUES
	('zhangsan', '$2a$10$yg5nJNx6w4be4k1lmRj6EeVjC/mms.Fl1oRDbdO87LRpyKei08dBW', 'ROLE_ADMIN'),
	('lisi', '$2a$10$Eq/SArXwAGjVkaZ25EK53.L//bSQ3as/bNvkDqc.WfQ52NCWg6692', 'ROLE_USER'),
	('wangwu', '$2a$10$M4MaWrW9STO4iCgB1Q/W1eMtGZYxewmo53/ZXWtdsK/Yk0HpRUGSy', 'ROLE_USER');