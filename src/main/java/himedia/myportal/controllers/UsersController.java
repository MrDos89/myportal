package himedia.myportal.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import himedia.myportal.repositories.vo.UserVo;
import himedia.myportal.services.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UsersController {
	@Autowired
	UserService userServiceImpl;
	
	
	@GetMapping({"","/","/join"})
	public String joinForm() {
		return "users/joinform";
	}
	
	@GetMapping("/joinsuccess")
	public String joinSuccess() {
		return "users/joinsuccess";
	}
	
	@PostMapping("/join")
	public String joinAction(@ModelAttribute UserVo userVo) {
		boolean success = userServiceImpl.join(userVo);
		
		if (success == false) {
			System.err.println("회원가입 실패");
			return "redirect:/users/join";
		} else {
			System.out.println("회원가입 성공");
			return "redirect:/users/joinsuccess";
		}
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "users/loginform";
	}
	
	@PostMapping("/login")
	public String loginAction(@RequestParam(value="email", required=false) String email, @RequestParam(value="password", required=false) String password, HttpSession session){
		if (email.length() == 0 || password.length() == 0) {
			System.err.println("이메일 혹은 패스워드가 전송되지 않음.");
			
			return "redirect:/users/login";
		}
		
		UserVo authUser = userServiceImpl.getUser(email, password);
		
		if (authUser != null) {
			// 로그인 확인
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		} else {
			return "redirect:/users/login";
		}
	}
	
//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//		session.removeAttribute("authUser");
//		session.invalidate();
//		
//		return "redirect:/";
//	}
	
	@ResponseBody
	@GetMapping("/checkEmail")
	// JSON API
	public Object exists(@RequestParam(value="email", required=false, defaultValue="") String email) {
		// { "Result": "success", "data" : true }
		UserVo vo = userServiceImpl.getUser(email);
		boolean exists = vo != null ? true : false;
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", "success");
		map.put("exists", exists);
		
		return map;
	}
	
}
