/*
 * Modified by: obregoj Last updated: Jun 13, 2018 3:01:09 PM
 */
package com.caista.birapps.etis.main.security;


import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.filter.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.*;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.*;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class KeycloakSecurityConfigurer extends KeycloakWebSecurityConfigurerAdapter {

  @Bean
  public GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
    SimpleAuthorityMapper mapper = new SimpleAuthorityMapper();
    mapper.setConvertToUpperCase(true);
    return mapper;
  }

  @Override
  protected KeycloakAuthenticationProvider keycloakAuthenticationProvider() {
    final KeycloakAuthenticationProvider provider = new KeycloakAuthenticationProvider();
    provider.setGrantedAuthoritiesMapper(grantedAuthoritiesMapper());
    return provider;
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(keycloakAuthenticationProvider());
  }

  @Override
  protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
    return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    super.configure(http);
    http.csrf().disable();
    http.authorizeRequests()
        .antMatchers("/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs/**")
        .permitAll().anyRequest().authenticated();
  }

  @Bean
  KeycloakConfigResolver keycloakConfigResolver() {
    return new KeycloakSpringBootConfigResolver();
  }

  @Bean
  public FilterRegistrationBean keycloakAuthenticationProcessingFilterRegistrationBean(
      final KeycloakAuthenticationProcessingFilter filter) {
    final FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
    registrationBean.setEnabled(false);
    return registrationBean;
  }

  @Bean
  public FilterRegistrationBean keycloakPreAuthActionsFilterRegistrationBean(
      final KeycloakPreAuthActionsFilter filter) {
    final FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
    registrationBean.setEnabled(false);
    return registrationBean;
  }
}
