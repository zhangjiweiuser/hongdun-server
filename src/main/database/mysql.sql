drop table if exists feed_origin;

/*==============================================================*/
/* Table: feed_origin                                           */
/*==============================================================*/
create table feed_origin
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
   editor               int comment '采集人员id',
   level                int comment '重要程度',
   status               int comment '状态(0:待提交,1:待审核,2:审核通过,3:审核不通过)',
   note               text,
   primary key (id)
);

alter table feed_origin comment 'feed流原始采集信息';



drop table if exists user;

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int not null comment '主键',
   username             varchar(25) not null comment '姓名',
   password             varchar(32) not null comment '密码',
   real_name            varchar(30) comment '真实姓名',
   type                 int not null default 0 comment '类型，0：普通用户，1：审核人员，2：管理人员',
   status               int not null default 0 comment '状态，0：正常，1：暂停，2：删除',
   created_at           datetime comment '创建时间',
   updated_at           datetime comment '更新时间',
   primary key (id)
)
type = InnoDB
auto_increment = 1
charset = UTF8;

alter table user comment '用户表';
