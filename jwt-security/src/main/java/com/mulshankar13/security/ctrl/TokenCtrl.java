package com.mulshankar13.security.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mulshankar13.security.model.JwtUser;
import com.mulshankar13.security.security.JwtGenerator;

@RestController
@RequestMapping("/token")
public class TokenCtrl {
	
	@Autowired
	private JwtGenerator jwtGenerator;
	
	@PostMapping
	public String generate (@RequestBody final JwtUser jwtUser) {
		return jwtGenerator.generate(jwtUser);		
	}
	
}
