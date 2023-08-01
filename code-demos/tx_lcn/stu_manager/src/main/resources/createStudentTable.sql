/*
 * 不要建立外键约束。因为在实现数据新增的过程。无法确定，数据新增的顺序。
 * 无法保证表格在同一个库，无法保证新增当前数据的时候，被引用的数据一定已存在。
 */
create table lcn_student(
    id bigint not null,
    name varchar(64) not null,
    age int(3),
    c_id bigint not null,
    primary key (id)
);