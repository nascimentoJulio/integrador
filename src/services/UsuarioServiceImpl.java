package services;

import models.Usuario;
import repository.Cache;
import repository.dao.UsuarioDao;

public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioDao usuarioDao;

    public UsuarioServiceImpl(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }


    @Override
    public void Cadastrar(String email, String senha) {
        try {
            Usuario usuario = usuarioDao.findByEmail(email);
            if (usuario != null){
                System.out.println("usuario ja cadastrado");
            }else {
                Usuario novoUsuario = new Usuario(email, senha);
                usuarioDao.insert(novoUsuario);
                System.out.println("Usuario cadastrado com sucesso");
            }
        } catch (Exception e) {
            System.out.println("Não foi possivel cadastrar usuario.");
        }

    }

    @Override
    public void Login(String email, String senha) {
        try {
            Usuario usuario = usuarioDao.findByEmail(email);
            if (usuario == null){
                System.out.println("usuario não cadastrado");
            }
            else if (!usuario.getSenha().equals(senha)){
                System.out.println("Senha incorreta!");
            }
            else {
                Cache.salvarTokem(usuario.getEmail());
                System.out.println("Login realizado com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Não foi possivel cadastrar usuario.");
        }
    }
}
