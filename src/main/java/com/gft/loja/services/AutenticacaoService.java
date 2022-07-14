package com.gft.loja.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gft.loja.dto.auth.AutenticacaoDTO;
import com.gft.loja.dto.auth.TokenDTO;
import com.gft.loja.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AutenticacaoService {

    /*@Autowired
    private AuthenticationManager authManager;*/

    @Value("${loja.jwt.expiration}")
    private String expiration;
    @Value("${loja.jwt.secret}")
    private String secret;
    @Value("${loja.jwt.issuer}")
    private String issuer;


    public TokenDTO autenticar(AutenticacaoDTO dto) throws AuthenticationException {

        /*Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha()));
        String token = gerarToken(authenticate);*/

        String token = gerarToken(null);

        return new TokenDTO(token);
    }

    private Algorithm criarAlgoritmo() {
        return Algorithm.HMAC256(secret);
    }

    private String gerarToken(Authentication authenticate) {
//        Usuario principal = (Usuario)authenticate.getPrincipal();

        Usuario principal = new Usuario();
        principal.setId(1L);
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return JWT.create()
                .withIssuer(issuer)
                .withExpiresAt(dataExpiracao)
                .withSubject(principal.getId().toString())
                .sign(this.criarAlgoritmo());
    }

}
