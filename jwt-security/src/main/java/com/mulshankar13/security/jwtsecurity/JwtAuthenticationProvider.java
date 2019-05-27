package com.mulshankar13.security.jwtsecurity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mulshankar13.security.model.JwtAuthenticationToken;
import com.mulshankar13.security.model.JwtUser;
import com.mulshankar13.security.model.JwtUserDetails;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

	@Autowired
	private JwtValidator jwtValidator;

	@Override
	public boolean supports(Class<?> aClass) {
		
		return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
			throws AuthenticationException {
		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
		String token =jwtAuthenticationToken.getToken();
		JwtUser jwtUser = jwtValidator.validate(token);
		if(null==jwtUser) {
			throw new RuntimeException("JWT token is incorrect..");
		}
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(jwtUser.getRole());   
		return new JwtUserDetails (
				jwtUser.getUserName(),
				jwtUser.getId(),
				token,
				grantedAuthorities);
	}

}
