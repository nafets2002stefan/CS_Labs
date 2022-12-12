#Laboratory work nr. 5

<br/>
<br/>
<br/>

---

<br/>

### Course: Cryptography & Security
### Author: Berestean Stefan

---

<br/>

## Theory:
What is the difference between authentication and authorization? Simply put, authentication is the process of verifying who someone is, whereas authorization is the process of verifying what specific applications, files, and data a user has access to. The situation is like that of an airline that needs to determine which people can come on board. 
<br/>
The first step is to confirm the identity of a passenger to make sure they are who they say they are. Once a passenger’s identity has been determined, the second step is verifying any special services the passenger has access to, whether it’s flying first-class or visiting the VIP lounge.



## Objectives:
1. Take what you have at the moment from previous laboratory works and put it in a web service / serveral web services.
2. Your services should have implemented basic authentication and MFA (the authentication factors of your choice).
3. Your web app needs to simulate user authorization and the way you authorise user is also a choice that needs to be done by you.
4. As services that your application could provide, you could use the classical ciphers. Basically the user would like to get access and use the classical ciphers, but they need to authenticate and be authorized.
---
<br/>

### Authorization and Authentification

#### Implementation:

* We'll create an API in Spring Boot with PostgreSQL Database.For authorization 
will use token(JWT). In config we have JWTFilter which will take every request and pass
through it.Checks if Authorization starts with "Bearer " then checks if it already in database.

```
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && !authHeader.isEmpty() && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);

            if(jwt.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
            }
            else {
                try {
                    String username = jwtUtil.validateTokenAndRetrieveClaim(jwt);
                    UserDetails userDetails = personDetailsService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    userDetails.getPassword(),
                                    userDetails.getAuthorities());

                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
                catch (JWTVerificationException exception) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                            "Invalid JWT Token");
                }
            }
        }
```
* Also in config, we have SecurityConfig where we have all the logic of authorization.
In config method, declare which actions can a user do.Also put some 
restrictions for some type of users and disable csrf(for Postman will not work with csrf enabled).

```
protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()   //disable latter (but POSTMAN NOT WORKING WITH enabled)
                .authorizeRequests()
                .antMatchers("/people").hasRole("ADMIN")
                .antMatchers("/auth/registration", "/auth/login", "/error").permitAll()
                .anyRequest().hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/people", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout()
                .logoutSuccessUrl("/auth/login")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }  
```
* For encryption, use Bcrypt and also when we save password in database(hash password).

```
@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

```

* In AuthController, we have endpoints for login and registration .In registration create a token,but in
login check with existing one.
```
@PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid PersonDTO personDTO,
                                                   BindingResult bindingResult) {
        Person person = convertToPerson(personDTO);

        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error: errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }

            throw new PersonNotCreatedException(errorMsg.toString());
        }

        registrationService.register(person);

        String token = jwtUtil.generateToken(person.getUsername());
        return Collections.singletonMap("jwt-token", token);
    }
```
  

<br/>

---


## Conclusion

In this laboratory work we learned what is difference between authentication and authorization.
Learned how to implement them in code, work with roles.