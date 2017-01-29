package zxy.permission.support;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 自定义jsp标签，用于权限控制
 */
public class PermissionJspTag extends BodyTagSupport {
    private PermissionContext permissionContext;
    private HttpSession session;
    /**
     * 权限编码
     */
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public void setPageContext(PageContext pageContext) {
        super.setPageContext(pageContext);
        session = pageContext.getSession();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.findWebApplicationContext(pageContext.getServletContext());
        permissionContext = webApplicationContext.getBean(PermissionContext.class);
    }

    @Override
    public int doStartTag() throws JspException {
        if (permissionContext.pass(session, getCode())) {
            return EVAL_BODY_INCLUDE;
        }
        return SKIP_BODY;
    }
}
