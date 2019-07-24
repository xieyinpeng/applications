package filter;
 
import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * 
 * <p>
 * Title: HttpFilter
 * </p>
 * <p>
 * Description: http请求定制Filter
 * </p>
 */
public abstract class HttpFilter implements Filter {
 
	/**
	 * 用于保存init(FilterConfig filterConfig)的FilterConfig对象
	 */
	private FilterConfig filterConfig;
 
	/**
	 * 直接返回init(FilterConfig filterConfig)的FilterConfig对象
	 */
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}
 
	/**
	 * 不建议子类直接覆盖，将可能会导致filterConfig成员变量初始化失败
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
 
		this.filterConfig = filterConfig;
		init();
 
	}
 
	/**
	 * 供子类继承的初始化方法，可以通过getFilterConfig获取FilterConfig对象
	 */
	protected void init() {}
 
	/**
	 * 原生的doFilter方法，在方法内部把ServletRequest和ServletResponse
	 * 转为了HttpServletRequest和HttpServletResponse并调用了 doFilter(HttpServletRequest
	 * httpRequest, HttpServletResponse httpResponse, FilterChain chain)方法
	 * 
	 * 
	 * 若编写Filter的过滤方法不建议直接继承该方法，而应该继承doFilter(ServletRequest request,
	 * ServletResponse response, FilterChain chain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
 
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
 
		doFilter(httpRequest, httpResponse, chain);
 
	}
 
	/**
	 * 抽象方法，为http请求定制，必需实现的方法
	 * 
	 */
	public abstract void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException;
 
	/**
	 * 空的destroy方法
	 */
	@Override
	public void destroy() {}
 
}