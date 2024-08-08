package ifpb.edu.br.pj.ifpbichos.business.service.converter;

import ifpb.edu.br.pj.ifpbichos.model.entity.Notificacao;
import ifpb.edu.br.pj.ifpbichos.model.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {
    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public void enviarNotificacao(String titulo, String mensagem) {
        Notificacao notificacao = new Notificacao();
        notificacao.setTitulo(titulo);
        notificacao.setMensagem(mensagem);
        notificacao.setLida(false);

        notificacaoRepository.save(notificacao);
    }
}
