package services;

public interface UsuarioService {
    void Cadastrar(String email, String senha);

    void Login(String email, String senha);

}
