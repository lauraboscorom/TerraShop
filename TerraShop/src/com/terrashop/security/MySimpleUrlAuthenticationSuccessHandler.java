package com.terrashop.security;


import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.terrashop.entity.Usuario;
import com.terrashop.service.UsuarioService;

public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


	@Autowired
	private UsuarioService usuarioService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication)
      throws IOException {

        HttpSession session = request.getSession();

        UserDetails userDetails= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        Usuario authUser = usuarioService.buscarUsuarioPorUsuario(userDetails.getUsername()) ; 
        session.setAttribute("usuario", authUser.getUsuario());
        session.setAttribute("nombre", authUser.getNombre());
        session.setAttribute("idUsuario", authUser.getIdUsuario());

        boolean isUser = false;
        boolean isAdmin = false;

        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROL_USUARIO")) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROL_ADMIN")) {
                isAdmin = true;
                break;
            }
        }

        if (isUser) {
        	session.setAttribute("rol","usuario" );
        }else {
        	session.setAttribute("rol","admin" );
        }
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);

    }

    protected void handle(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        final String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(final Authentication authentication) {
        boolean isUsuario = false;
        boolean isAdmin = false;
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROL_USUARIO")) {
            	isUsuario = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROL_ADMIN")) {
                isAdmin = true;
                break;
            }
        }

        if (isUsuario) {
            return "/usuario/myprofile";
        } else if (isAdmin) {
            return "/index";
        } else {
            throw new IllegalStateException();
        }
    }

    protected final void clearAuthenticationAttributes(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }


}
