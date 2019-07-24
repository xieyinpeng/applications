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


@WebFilter({"/manager/consumer/*",
	"/manager/account/*"
	,"/manager/provider/*"
	,"/manager/logout"})
public class CheckManagerAuthorityFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("mid")==null) {
			System.err.println("Something wrong happened!!!!!!!");
			response.sendRedirect(request.getContextPath()+"/manager/login");
		}
		chain.doFilter(request, response);
	}

}
