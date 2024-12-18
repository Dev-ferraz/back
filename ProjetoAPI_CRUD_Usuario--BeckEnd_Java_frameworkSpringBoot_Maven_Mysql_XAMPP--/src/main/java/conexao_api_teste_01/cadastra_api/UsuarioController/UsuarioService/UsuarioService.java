package conexao_api_teste_01.cadastra_api.UsuarioController.UsuarioService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import conexao_api_teste_01.cadastra_api.UsuarioController.UsuarioEntity.UsuarioEntity;
import conexao_api_teste_01.cadastra_api.UsuarioController.UsuarioRepository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    
    public List<UsuarioEntity> listaTodos() {
        return (List<UsuarioEntity>) usuarioRepository.findAll(); 
    }

    
    public Optional<UsuarioEntity> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    
    public UsuarioEntity inserir(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    
    public UsuarioEntity alterar(Long id, UsuarioEntity usuarioAtualizado) {
        return usuarioRepository.findById(id)
            .map(usuario -> {
                usuario.setNome(usuarioAtualizado.getNome());
                usuario.setLogin(usuarioAtualizado.getLogin());
                usuario.setSenha(usuarioAtualizado.getSenha());
                usuario.setEmail(usuarioAtualizado.getEmail());
                return usuarioRepository.save(usuario);
            })
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o ID: " + id));
    }

    
    public void excluir(Long id) {
        usuarioRepository.deleteById(id);
    }
}