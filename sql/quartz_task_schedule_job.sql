--
-- Table structure for table `task_schedule_job`
--

DROP TABLE IF EXISTS `task_schedule_job`;

CREATE TABLE `task_schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `job_name` varchar(255) DEFAULT NULL,
  `job_group` varchar(255) DEFAULT NULL,
  `job_status` varchar(255) DEFAULT NULL,
  `cron_expression` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `bean_class` varchar(255) DEFAULT NULL,
  `is_concurrent` varchar(255) DEFAULT NULL COMMENT '1',
  `spring_id` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) NOT NULL,
  PRIMARY KEY (`job_id`),
  UNIQUE KEY `name_group` (`job_name`,`job_group`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `task_schedule_job`
--

LOCK TABLES `task_schedule_job` WRITE;
INSERT INTO `task_schedule_job` VALUES (1,NULL,'2017-07-26 14:17:34','test','test','1','0/1 * * * * ?','test','com.fightermap.quartz.TaskTest','0',NULL,'run');


