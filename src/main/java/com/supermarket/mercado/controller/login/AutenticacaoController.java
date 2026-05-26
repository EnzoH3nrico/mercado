package com.supermarket.mercado.controller.login;

import com.supermarket.mercado.model.user.Usuario;
import com.supermarket.mercado.dto.autenticacao.DadosAutenticacao;
import com.supermarket.mercado.dto.token.DadosTokenJWT;
import com.supermarket.mercado.exceptions_handling.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){



        var tokenAutenticador = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var autenticacao = manager.authenticate(tokenAutenticador);

        var tokenJWT = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
