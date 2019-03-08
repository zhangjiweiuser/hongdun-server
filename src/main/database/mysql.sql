drop table if exists backend_feed_origin;

/*==============================================================*/
/* Table: feed_origin                                           */
/*==============================================================*/
create table backend_feed_origin
(
   id                   int not null comment '主键',
   code                 varchar(32) comment '序列号',
   title                varchar(50) comment '主题',
   description          text comment '描述',
   back_resource        int(11) comment '背景图',
   source_pub_time      datetime DEFAULT NULL comment '来源网站发布的时间',
   source               varchar(150) comment '流来源',
   source_url           varchar(150) comment '流来源网址',
   content              text comment '流内容',
   created_at           datetime comment '创建时间',
   updated_at           datetime comment '更新时间',
   editor               varchar(50) comment '采集人员',
   level                int(11) comment '重要程度',
   status               int(11) comment '状态(1:待审核,2:审核通过,3:审核不通过)' DEFAULT 1,
   note               text,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment 'feed流原始采集信息';


drop table if exists backend_user;

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
CREATE TABLE `backend_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `note` varchar(50) DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT '2' COMMENT '1超级管理员 2:普通人员，3:运营人员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户表';

