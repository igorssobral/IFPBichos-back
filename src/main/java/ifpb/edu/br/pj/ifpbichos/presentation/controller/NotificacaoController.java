package ifpb.edu.br.pj.ifpbichos.presentation.controller;

import ifpb.edu.br.pj.ifpbichos.business.service.converter.NotificacaoService;
import ifpb.edu.br.pj.ifpbichos.model.entity.Notificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notificacao")
public class NotificacaoController {
    @Autowired
    private NotificacaoService notificacaoService;

    @PostMapping("/notificacoes")
    public ResponseEntity<Notificacao> enviarNotificacao(@RequestBody Notificacao notificacao) {
        notificacaoService.enviarNotificacao(notificacao.getTitulo(), notificacao.getMensagem());
        return ResponseEntity.ok(notificacao);
    }
}
