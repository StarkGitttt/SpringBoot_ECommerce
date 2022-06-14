package poly.edu.services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CookieService {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	public void add(String name, String value, int hours) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(hours * 60 * 60);
		cookie.setPath("/");
		response.addCookie(cookie);
		
	}
	public Cookie getCookie(String name) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equalsIgnoreCase(name)) return cookie;
			}
		}
		return null;
	}
	public String getValue(String name) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equalsIgnoreCase(name)) return cookie.getValue();
			}
		}
		return null;
	}
	public void removeCookie(String name) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equalsIgnoreCase(name)) {
					cookie.setValue("");
		            cookie.setPath("/");
		            cookie.setMaxAge(0);
					return;					
				}
			}
		}
	}
}