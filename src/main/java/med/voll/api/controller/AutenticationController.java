package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.infra.security.TokenService;
import med.voll.api.domain.user.DatasAutentication;
import med.voll.api.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping()
    public ResponseEntity login(@RequestBody @Valid DatasAutentication datas){
        var token = new UsernamePasswordAuthenticationToken(datas.login(), datas.password());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok(tokenService.generateToken((User) authentication.getPrincipal()));
    }
}
