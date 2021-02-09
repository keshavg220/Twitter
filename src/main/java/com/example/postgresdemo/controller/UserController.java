package com.example.postgresdemo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.config.JwtTokenUtil;
import com.example.postgresdemo.model.JwtRequest;
import com.example.postgresdemo.model.JwtResponse;
import com.example.postgresdemo.model.Question;
import com.example.postgresdemo.model.UserDao;
import com.example.postgresdemo.repository.QuestionRepository;
import com.example.postgresdemo.repository.UserRepository;
import com.example.postgresdemo.service.JwtUserDetailsService;
import org.springframework.http.MediaType;


@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
    private UserRepository userRepository;
	
    @Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@PostMapping("/logins")
    public UserDao createLogin(@Valid @RequestBody UserDao login) {
		UserDao newUser = new UserDao();
		newUser.setUsername(login.getUsername());
		newUser.setPassword(bcryptEncoder.encode(login.getPassword()));
        return userRepository.save(newUser);
    }
	
	 @GetMapping("/loginCheck/{username}")
	 public UserDao getLoginUserInfo(@PathVariable String username) {
	    return userRepository.findByUsername(username);
	 }

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		System.out.println("token2 "+token);
		String token2 = "Bearer " +token;
		HttpHeaders headers = getHeaders();
		headers.set("Authorization", token2);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		return headers;
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
}
