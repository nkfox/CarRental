package filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * This class changes encoding.
 * Created by Nataliia Kozoriz on 28.01.2016.
 */
public class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void destroy() {
    }

}