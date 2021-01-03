package com.students;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebSecurity
//public class AppConfig extends WebSecurityConfigurerAdapter{
public class AppConfig {	
	/*
	 * @Autowired private UserDetailsService userDetailsService;
	 * 
	 * @Bean public AuthenticationProvider authProvider() {
	 * 
	 * DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	 * provider.setUserDetailsService(userDetailsService);
	 * provider.setPasswordEncoder(new BCryptPasswordEncoder());
	 * 
	 * return provider;
	 * 
	 * }
	 */

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http .csrf().disable() .authorizeRequests().antMatchers("/login").permitAll()
	 * .anyRequest().authenticated() .and() .logout().invalidateHttpSession(true)
	 * .clearAuthentication(true) .logoutRequestMatcher(new
	 * AntPathRequestMatcher("/logout"))
	 * .logoutSuccessUrl("/logout-success").permitAll();
	 * 
	 * }
	 */
	
	

	/*
	 * @Bean
	 * 
	 * @Override protected UserDetailsService userDetailsService() {
	 * 
	 * List<UserDetails> users = new ArrayList<>();
	 * users.add(User.withDefaultPasswordEncoder().username("admin").password(
	 * "admin").roles("USER").build());
	 * 
	 * return new InMemoryUserDetailsManager(users);
	 * 
	 * 
	 * }
	 */

	
}
