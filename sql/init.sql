-- API请求日志表
create table if not exists api_log
(
    id          bigint unsigned auto_increment comment 'id' primary key,
    batch_no    varchar(128)                          not null comment '批次号',
    api_content text                                  not null comment '请求log',
    status      varchar(64) default 'UNCOMMITTED'     not null comment '日志表:UNCOMMITTED-未处理, FINISHED-已处理, ERROR-错误',
    remark      varchar(256) comment '备注信息',
    type        varchar(64)                           not null comment '业务类型:GOOD,ORDER,STOCK',
    created_at  datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at  datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) ENGINE = InnoDB comment 'API请求日志表'
  charset = utf8mb4;


-- API待处理的task表
create table if not exists task
(
    id            bigint unsigned auto_increment comment 'id' primary key,
    batch_no      varchar(128)                          not null comment '批次号',
    out_batch_no  varchar(128) comment '对接业态的批次号',
    vendor_id     bigint comment '供应商编码',
    bu_id         bigint comment '业态编码',
    supplier_code varchar(128) comment '待推送供应商代码',
    store_code    varchar(128) comment '门店编码',
    status        varchar(64) default 'NEW'             not null comment 'task表状态:NEW-新建，HANDLING-处理中, HANDLED-已处理, RETRY-待重试',
    remark        varchar(256) comment '备注信息',
    type          varchar(64)                           not null comment '业务类型:GOOD,ORDER,STOCK',
    created_at    datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at    datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint t_task_batchNo unique (batch_no)
) ENGINE = InnoDB comment '待处理task表'
  charset = utf8mb4;

-- API待处理task表商品信息明细表
create table if not exists goods
(
    id                    bigint unsigned auto_increment comment 'Id' primary key,
    batch_no              varchar(128)                       not null comment '批次号',
    serial_no             varchar(128)                       not null comment '商品序列号',
    goods_category        varchar(64)                        not null comment '商品类别',
    goods_brand           varchar(64)                        not null comment '商品品牌',
    goods_name            varchar(128)                       not null comment '商品名称',
    goods_short_name      varchar(64)                        not null comment '商品简称',
    sale_unit             varchar(64)                        not null comment '销售单位',
    retail_price          decimal(12, 2) unsigned            not null comment '核定零售价',
    supplier_code         varchar(128) comment '供应商管理码',
    bar_code              varchar(256)                       not null comment '商品条码',
    init_stock            DECIMAL(13, 3) comment '初始库存',
    goods_code            varchar(64)                        not null comment '货号/型号',
    place_origin          varchar(256) comment '产地',
    texture               varchar(256) comment '材质',
    color                 varchar(64) comment '颜色',
    norm1                 varchar(256)                       not null comment '大规格',
    norm2                 varchar(256) comment '小规格/尺码',
    norm3                 varchar(256) comment '补充规格',
    barPrint1             varchar(256) comment '牌价打印内容1',
    posted_price_content2 varchar(256) comment '牌价打印内容2',
    posted_price_content3 varchar(256) comment '牌价打印内容3',
    good_desc             varchar(256) comment '商品描述',
    gold_price            decimal(12, 2) unsigned comment '当日金价',
    fineness              decimal(16, 6) unsigned comment '成色',
    suttle                decimal(16, 6) unsigned comment '净重/克',
    weight                decimal(16, 6) unsigned comment '重量/克',
    merc_id               varchar(128) comment '商品代码',
    status                varchar(64)                        not null comment '同步状态',
    remark                varchar(256) comment '备注信息',
    goods_ext_data        text comment '商品信息扩展字段',
    created_at            datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at            datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint t_task_batchNo unique (batch_no),
    constraint t_task_batchNo_serialNo unique (batch_no, serial_no)
) ENGINE = InnoDB comment '商品信息表'
  charset = utf8mb4;

-- API平台对接供应商信息表
create table if not exists vendor
(
    id          bigint unsigned auto_increment comment '供应商id' primary key,
    vendor_code varchar(128)                          not null comment '供应商编码',
    vendor_name varchar(128)                          not null comment '供应商全称',
    description varchar(64)                           null comment '描述',
    status      varchar(64) default 'ACTIVE'          not null comment '供应商状态值:ACTIVE-激活，FROZEN-冻结',
    created_at  datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at  datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uq_t_vendor_code unique (vendor_code)
) ENGINE = InnoDB comment 'API平台对接供应商信息表'
  charset = utf8mb4;

-- API平台对接业态信息表
create table if not exists bu
(
    id          bigint unsigned auto_increment comment '业态Id' primary key,
    bu_code     varchar(128)                          not null comment '业态编码',
    bu_name     varchar(128)                          not null comment '业态全称',
    description varchar(64)                           null comment '描述',
    status      varchar(64) default 'ACTIVE'          not null comment '业态状态值:ACTIVE-激活，FROZEN-冻结',
    created_at  datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at  datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uq_t_bu_code unique (bu_code)
) ENGINE = InnoDB comment 'API平台对接业态信息表'
  charset = utf8mb4;

-- 供应商业态关联表
create table if not exists vendor_bu
(
    id         bigint unsigned auto_increment comment 'Id' primary key,
    vendor_id  bigint                                not null comment '供应商id',
    bu_id      bigint                                not null comment '业态id',
    status     varchar(64) default 'ACTIVE'          not null comment '状态关系:ACTIVE-激活，FROZEN-冻结',
    created_at datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) ENGINE = InnoDB comment '供应商业态关系表'
  charset = utf8mb4;

-- 扩展字段与业态接口match表
create table if not exists ext_match
(
    id             bigint unsigned auto_increment comment 'Id' primary key,
    vendor_bu_id   bigint                                not null comment '扩展字段维护表id',
    vendor_ext_key varchar(128)                          not null comment '供应商扩展字段key',
    bu_ext_key     varchar(128)                          not null comment '业态接口属性',
    status         varchar(64) default 'ACTIVE'          not null comment '状态关系:ACTIVE-激活，FROZEN-冻结',
    type           varchar(64)                           not null comment '业务类型:GOOD,ORDER,STOCK',
    created_at     datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at     datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint t_vendor_bu_math unique (vendor_bu_id, vendor_ext_key, bu_ext_key)
) ENGINE = InnoDB comment '扩展字段与业态字段对应表'
  charset = utf8mb4;

-- 业态对接供应商信息表(静态信息数据--业态提供)
create table if not exists bu_out_vendor
(
    id             bigint unsigned auto_increment comment 'id' primary key,
    bu_id          bigint                                null comment '业态id',
    bu_vendor_code varchar(128)                          null comment '业态对应供应商编码',
    description    varchar(64)                           null comment '描述',
    status         varchar(64) default 'ACTIVE'          not null comment '状态值:ACTIVE-激活，FROZEN-冻结',
    created_at     datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at     datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uq_t_bu_id_vendor_code unique (bu_id, bu_vendor_code)
) ENGINE = InnoDB comment '业态对接供应商信息表'
  charset = utf8mb4;

-- 业态店铺表(一个业态多个门店)(静态信息数据--业态提供)
create table if not exists bu_out_store
(
    id           bigint unsigned auto_increment comment 'id' primary key,
    vendor_bu_id varchar(128)                          null comment '业态id',
    store_code   varchar(128)                          not null comment '门店编码',
    description  varchar(64)                           null comment '描述',
    status       varchar(64) default 'ACTIVE'          not null comment '状态值:ACTIVE-激活，FROZEN-冻结',
    created_at   datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at   datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uq_t_bu_id_shop_code unique (vendor_bu_id, store_code)
) ENGINE = InnoDB comment '业态店铺表'
  charset = utf8mb4;

-- 商品信息归档表
create table if not exists goods_archive
(
    id                    bigint unsigned auto_increment comment 'Id' primary key,
    vendor_id             bigint                             not null comment '供应商id',
    bu_id                 bigint                             not null comment '业态id',
    store_id              bigint                             null comment '门店id',
    goods_category        varchar(64)                        not null comment '商品类别',
    goods_brand           varchar(64)                        not null comment '商品品牌',
    goods_name            varchar(128)                       not null comment '商品名称',
    goods_short_name      varchar(64)                        not null comment '商品简称',
    sale_unit             varchar(64)                        not null comment '销售单位',
    retail_price          decimal(12, 2) unsigned            not null comment '核定零售价',
    supplier_code         varchar(128) comment '供应商管理码',
    bar_code              varchar(256)                       not null comment '商品条码',
    init_stock            DECIMAL(13, 3) comment '初始库存',
    goods_code            varchar(64)                        not null comment '货号/型号',
    place_origin          varchar(256) comment '产地',
    texture               varchar(256) comment '材质',
    color                 varchar(64) comment '颜色',
    norm1                 varchar(256)                       not null comment '大规格',
    norm2                 varchar(256) comment '小规格/尺码',
    norm3                 varchar(256) comment '补充规格',
    barPrint1             varchar(256) comment '牌价打印内容1',
    posted_price_content2 varchar(256) comment '牌价打印内容2',
    posted_price_content3 varchar(256) comment '牌价打印内容3',
    good_desc             varchar(256) comment '商品描述',
    created_at            datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at            datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) ENGINE = InnoDB comment '商品信息表归档表'