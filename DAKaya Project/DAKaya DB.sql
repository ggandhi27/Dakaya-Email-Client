/*
-- Query: SELECT * FROM dakaya.mails
LIMIT 0, 1000

-- Date: 2016-07-16 11:00
*/
INSERT INTO `mails` (`id`,`sender`,`receiver`,`subject`,`email`,`date`,`time`,`starred`,`spam`,`mread`) VALUES (1,'Gandhi',NULL,'Test mail','This is just a test mail to test the prototype.','2016.07.09','07:48:34',0,0,0);
INSERT INTO `mails` (`id`,`sender`,`receiver`,`subject`,`email`,`date`,`time`,`starred`,`spam`,`mread`) VALUES (3,'gandhi','gaurav','starred mail','Yeah I did it','2016.07.09','09:30:30',1,0,0);
INSERT INTO `mails` (`id`,`sender`,`receiver`,`subject`,`email`,`date`,`time`,`starred`,`spam`,`mread`) VALUES (4,'gandhi','gaurav','spam mail','This mail shoul appear in spam','2016.07.09','09.30.30',0,1,0);
INSERT INTO `mails` (`id`,`sender`,`receiver`,`subject`,`email`,`date`,`time`,`starred`,`spam`,`mread`) VALUES (5,'gandhi','gaurav','Inbox mail','Test Inbox mail','2016.08.10','10.03.30',0,0,1);
INSERT INTO `mails` (`id`,`sender`,`receiver`,`subject`,`email`,`date`,`time`,`starred`,`spam`,`mread`) VALUES (6,'gandhi','gaurav','starred mail2','Test starred box','vfsvfd','fvdv',1,0,0);
INSERT INTO `mails` (`id`,`sender`,`receiver`,`subject`,`email`,`date`,`time`,`starred`,`spam`,`mread`) VALUES (7,'Gaurav','chirag','This is a test mail','This mail should appear in sent box','2016.07.11','06:07:21',0,0,1);
INSERT INTO `mails` (`id`,`sender`,`receiver`,`subject`,`email`,`date`,`time`,`starred`,`spam`,`mread`) VALUES (9,'Gaurav','chirag','Test mail','This is test mail','2016.07.11','06:39:23',0,0,0);
INSERT INTO `mails` (`id`,`sender`,`receiver`,`subject`,`email`,`date`,`time`,`starred`,`spam`,`mread`) VALUES (12,'gaurav',NULL,'spam mail','This mail should appear in spam','2016.07.12','11:32:28',0,0,1);
INSERT INTO `mails` (`id`,`sender`,`receiver`,`subject`,`email`,`date`,`time`,`starred`,`spam`,`mread`) VALUES (13,'Gaurav','chirag','Sent Test mail','cndshcbdsihbcdskcds','2016.07.14','03:52:06',0,0,0);
INSERT INTO `mails` (`id`,`sender`,`receiver`,`subject`,`email`,`date`,`time`,`starred`,`spam`,`mread`) VALUES (14,'Gaurav',NULL,'Draft Test mail','Test Draft mail','2016.07.14','03:57:42',0,0,1);
INSERT INTO `mails` (`id`,`sender`,`receiver`,`subject`,`email`,`date`,`time`,`starred`,`spam`,`mread`) VALUES (15,'Gaurav','chirag','ksadf','kasdjfl
sadkfjlksaf','2016.07.14','04:03:15',0,0,0);
