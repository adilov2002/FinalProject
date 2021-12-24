package com.example.FinalProject.security;

import com.example.FinalProject.entities.Users;
import com.example.FinalProject.repositories.UsersRepostiory;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import net.bytebuddy.description.annotation.AnnotationDescription;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

@Provider
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    private UsersRepostiory usersRepostiory = new UsersRepostiory();

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        Method method = resourceInfo.getResourceMethod();
        //Access allowed for all
        if (!method.isAnnotationPresent(PermitAll.class)) {
            //Access denied for all
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                        .entity("Access blocked for all users !!").build());
                return;
            }

            //Get request headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();

            //Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

            //If no authorization information present; block access
            if (!Optional.ofNullable(authorization).isPresent() || authorization.isEmpty()) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("You cannot access this resource").build());
                return;
            }

            //Get encoded username and password
            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
            //Decode username and password
            String usernameAndPassword = new String(Base64.decode(encodedUserPassword));

            //Split username and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            //Verify user access
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Path path = method.getAnnotation(Path.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

                //Is user valid?
                if (!isUserAllowed(username, password, rolesSet, method.getAnnotation(Path.class).value())) {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                            .entity("You cannot access this resource").build());
                    return;
                }
            }
        }
    }

    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet, final String path) {
        boolean isAllowed = false;

        //Step 1. Fetch password from database and match with password in argument
        //If both match then get the defined role for user from database and continue; else return isAllowed [false]
        //Access the database and do this part yourself
        //String userRole = userMgr.getUserRole(username);

        ArrayList<Users> users = usersRepostiory.getAllUsers();

        for (Users u : users){
            if (u.getPassword().equals(password) && u.getUsername().equals(username)){
                if (rolesSet.contains(u.getRole().getName())){
                    if (path.contains("/places")){
                        isAllowed = true;
                    } else if (path.equals("/universities") && u.getGroups().stream().anyMatch(groups -> groups.getName().equals("student"))) {
                        isAllowed = true;
                    } else if (path.equals("/hostels") && u.getGroups().stream().anyMatch(groups -> groups.getName().equals("tourist"))){
                        isAllowed = true;
                    } else if (path.equals("/vacancies") && u.getGroups().stream().anyMatch(groups -> groups.getName().equals("job-seeker"))){
                        isAllowed = true;
                    }
                }
            }
        }

        return isAllowed;
    }

}

