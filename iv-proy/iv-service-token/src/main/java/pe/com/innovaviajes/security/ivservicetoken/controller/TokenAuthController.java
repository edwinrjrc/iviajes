/**
 * 
 */
package pe.com.innovaviajes.security.ivservicetoken.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.innovaviajes.security.ivservicetoken.token.JwtProvider;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping("/auth")
public class TokenAuthController {

	@Autowired
	private JwtProvider jwtProvider; // Tu clase que genera los tokens

	@GetMapping("/anonymous")
	public ResponseEntity<Map<String, Object>> getAnonymousToken() {
		// Generamos un token para un usuario ficticio "anonymous_user"
		// con el rol de invitado/anónimo
		String token = jwtProvider.generateAnonymousToken("anonymous_user", List.of("ROLE_ANONYMOUS"));

		Map<String, Object> response = new HashMap<>();
		response.put("token", token);
		response.put("type", "Bearer");
		response.put("role", "ROLE_ANONYMOUS");

		return ResponseEntity.ok(response);
	}

}
