package poly.edu.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
	
	@Autowired
	HttpSession session;
	
	@SuppressWarnings("unchecked")
	public<T> T get(String name) {
		T getValue = (T) session.getAttribute(name);
		return getValue==null ? null :  (T)getValue;
	}
	
	public void set(String name, Object obj) {
		session.setAttribute(name, obj);
	}
	
	public void remove(String name) {
		session.removeAttribute(name);
	}
}
