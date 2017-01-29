/**
 * 权限部分逻辑代码，搭配使用的还有
 * <ul>
 *     <li>mapper下的permission</li>
 *     <li>properties中的db.properties对应的permission部分</li>
 *     <li>applicationContext-mysql-permission.xml</li>
 * </ul>
 *
 * 这部分代码分两部分，业务代码（dao、entity）及权限控制模块（support），
 * 其中应该还有第三个模块，系统本身其他代码模块，整合业务与控制模块。
 */
package zxy.permission;