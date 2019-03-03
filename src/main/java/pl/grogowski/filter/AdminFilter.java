package pl.grogowski.filter;

import pl.grogowski.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        if (session.getAttribute("user") == null) {
            ((HttpServletResponse) resp).sendRedirect("/");
        } else {
            User user = (User) session.getAttribute("user");
            if (!user.getAdmin()) {
                ((HttpServletResponse) resp).sendRedirect("/user/main");
            }
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
