package agjs.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(
		urlPatterns={"/admin/index.html","/admin/journeyManagement.html","/admin/announcement.html",
				"/admin/back-messagename.jsp","/admin/back-chat.jsp","/admin/mailListall.jsp",
				"/admin/orderManagement.html","/admin/roomManagement.html"
				},
		initParams = {@WebInitParam(name="loginPath", value="/AGJS/admin/login.html")
				}
)
public class AdministratorLoginFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	private String loginPath;
	
	@Override
	public void init() throws ServletException {
		loginPath=getInitParameter("loginPath");
	}


    @Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		//取得session
		HttpSession session = req.getSession();
		//從session判斷使用者是否登入過
		Object verifySession = session.getAttribute("administratorLogin");
		System.out.println("過濾器："+verifySession);
		if(verifySession ==null) {
			System.out.println("請登入");
			res.sendRedirect(loginPath);
		}else {
			System.out.println("過濾器："+verifySession);
			chain.doFilter(request, response);
		}
		
	}

}
