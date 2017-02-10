/**
 * 权限部分逻辑代码，搭配使用的还有
 * <ul>
 * <li>mapper下的permission</li>
 * <li>properties中的db.properties对应的permission部分</li>
 * <li>applicationContext-mysql-permission.xml</li>
 * <li>springmvc.xml部分配置</li>
 * </ul>
 * <p>
 * 这部分代码分两部分，业务代码（dao、entity、permission根目录下的类）及权限控制模块（support）。
 * 其中的dao、entity为推荐的建表结构。
 */
package zxy.permission;