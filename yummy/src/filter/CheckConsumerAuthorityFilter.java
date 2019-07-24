package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({"/consumer/order/*",
	"/consumer/account/*",
	"/consumer/updateinfo",
	"/consumer/logout",
	"/consumer/logoff"})
public class CheckConsumerAuthorityFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("cid")==null) {
			System.err.println("Something wrong happened!!!!!!!");
			response.sendRedirect(request.getContextPath()+"/consumer/login");
		}
		//System.out.print("wwwww\n");
		chain.doFilter(request, response);
	}
	
}
