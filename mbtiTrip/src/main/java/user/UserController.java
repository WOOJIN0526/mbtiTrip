package user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.validation.Valid;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/signup") 
	public String signup(UserCreateForm userCreateForm) {
		return "signup";
	}
	
	@PostMapping("/signup") 
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "signup";
		}
		
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			return "signup";
		}
		
		
		this.userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1(), userCreateForm.getMbti(), userCreateForm.getPNumber());
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
}
