package com.epam;

import static org.springframework.security.config.Customizer.withDefaults;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

import com.epam.config.RsaKeyProperties;
import com.epam.service.CustomUserDetailsService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;

@SpringBootApplication
@Configuration
@OpenAPIDefinition(info = @Info(title = "REST API", version = "1.0", contact = @Contact(name = "Srinivas", email = "Supriya_Divvela@epam.com")), security = {
		@SecurityRequirement(name = "basicAuth"), @SecurityRequirement(name = "bearerToken") })
@SecuritySchemes({ @SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic"),
		@SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT") })
@EnableConfigurationProperties(RsaKeyProperties.class)
public class QuizApplicationWithSpringSecurityOAuth {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private RsaKeyProperties jwtConfigProperties;
	@Autowired
	private UserDetailsService userDetailsService;
	public static final String[] PUBLIC_PATHS = { "/v3/api-docs.yaml", "/v3/api-docs/**", "/swagger-ui/**",
			"/swagger-ui.html", "/global" };

	public static void main(String[] args) {
		SpringApplication.run(QuizApplicationWithSpringSecurityOAuth.class, args);
	}

	@Bean
	@Order(1)
	public SecurityFilterChain basicAuthSecurityFilterChain(HttpSecurity http) throws Exception {
		// @formatter:off
	        http
	                .securityMatcher("/token")
	                .authorizeHttpRequests(authorize -> authorize
	                        .requestMatchers(PUBLIC_PATHS).permitAll()
	                        .anyRequest().authenticated()
	                )
	                .csrf().disable()
	                .httpBasic(withDefaults());
	        // @formatter:on
		return http.build();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// @formatter:off
	        http
	                .authorizeHttpRequests()
	                .requestMatchers(PUBLIC_PATHS).permitAll()
	                .anyRequest().authenticated().and()
	                .httpBasic().disable()
	                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
	                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .exceptionHandling((exceptions) -> exceptions
	                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
	                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
	                )
	                // XSS protection
	                .headers().xssProtection().and()
	                .contentSecurityPolicy("script-src 'self'");

	        // @formatter:on
		return http.build();
	}

	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(jwtConfigProperties.publicKey()).build();
	}

	@Bean
	JwtEncoder jwtEncoder() {
		JWK jwk = new RSAKey.Builder(jwtConfigProperties.publicKey()).privateKey(jwtConfigProperties.privateKey())
				.build();
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}

	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		grantedAuthoritiesConverter.setAuthorityPrefix("");

		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
		return jwtAuthenticationConverter;
	}

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(UserRepository users, PasswordEncoder encoder) {
//		return args -> {
//			users.save(new User("user@epam.com", encoder.encode("User@123"), "ROLE_USER"));
//			users.save(new User("admin@epam.com", encoder.encode("Admin@123"), "ROLE_USER,ROLE_ADMIN"));
//		};
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
