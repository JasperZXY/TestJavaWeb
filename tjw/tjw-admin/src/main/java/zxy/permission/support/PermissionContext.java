package zxy.permission.support;

/**
 * 暂时用全局的变量代替，方便，后续修改
 */
public class PermissionContext {
    private static PermissionPass permissionPass = null;
    private static NoPermissionListener noPermissionListener = null;

    public static PermissionPass getPermissionPass() {
        if (permissionPass == null) {
            permissionPass = new DefaultPermissionPassDelegate<>();
        }
        return permissionPass;
    }

    /**
     * 设置PermissionPass，若没有设置，默认为
     * {@link zxy.permission.support.DefaultPermissionPassDelegate}
     *
     * @param permissionPass
     */
    public static void setPermissionPass(PermissionPass permissionPass) {
        PermissionContext.permissionPass = permissionPass;
    }

    public static NoPermissionListener getNoPermissionListener() {
        if (noPermissionListener == null) {
            noPermissionListener = new DefaultNoPermissionListener<>();
        }
        return noPermissionListener;
    }

    public static void setNoPermissionListener(NoPermissionListener noPermissionListener) {
        PermissionContext.noPermissionListener = noPermissionListener;
    }
}
