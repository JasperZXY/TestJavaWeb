# 权限部分
## Resource表数据约定
menu、button类的用4位整数表示，前2位表示模块或分组，后2位表示操作（01访问，02新增，03修改，04删除）。
nav类用1~3位数字表示。

## role_resource_relation
改用新的设计方式，所有对应的resource_id用“,”放在一条记录里面，所以认最新一条记录，要让某个角色没权限，
可以让其对应的resource_ids为空即可。



