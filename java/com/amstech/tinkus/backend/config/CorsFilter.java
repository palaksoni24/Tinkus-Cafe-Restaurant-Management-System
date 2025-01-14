package com.amstech.tinkus.backend.config;

    import java.io.IOException;

	import jakarta.servlet.Filter;
	import jakarta.servlet.FilterChain;
	import jakarta.servlet.FilterConfig;
	import jakarta.servlet.ServletException;
	import jakarta.servlet.ServletRequest;
	import jakarta.servlet.ServletResponse;
	import jakarta.servlet.annotation.WebFilter;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;

	@WebFilter("/*")
	public class CorsFilter implements Filter {

		@Override
		public void init(FilterConfig filterConfig) throws ServletException {
			System.out.println("CorsFilter: Init");
		}

		@Override
		public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
				throws IOException, ServletException {
			System.out.println("CorsFilter: doFilter");

			HttpServletResponse response = (HttpServletResponse) servletResponse;
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Credentials", "true");
		    response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, HEAD, OPTIONS");
			response.setHeader("Access-Control-Allow-Headers",
					"Origin, Accept, X-Requested-With, Content-Type, Access-Control-Allow-Origin, Access-Control-Request-Method, Access-Control-Request-Headers, Access-Control-Max-Age");

			HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
			if ("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())) {
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				filterChain.doFilter(servletRequest, response);
			}

		}

		@Override
		public void destroy() {
			System.out.println("CorsFilter: Destroy");
		}
	}



