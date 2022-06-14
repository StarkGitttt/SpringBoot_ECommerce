package poly.edu.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import poly.edu.models.Account;
import poly.edu.services.SessionService;

@Service
public class AuthInterceptor implements HandlerInterceptor {
    
    @Autowired
    SessionService session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
                String error = "";
                String uri = request.getRequestURI();
                Account user = session.get("user");
               if(user == null) {
                   error = "Please login";
               }

               if(user != null) {
                   if(!user.isAdmin() && uri.contains("/admin")) {
                       error = "You are not  an administrator";
                   }
               }
               if(error.length() > 0) {
                   session.set("security-uri", uri);
                   response.sendRedirect("/login?message="+error);
                   return false;
               }
		return true;
	}
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	}
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
	}
}
