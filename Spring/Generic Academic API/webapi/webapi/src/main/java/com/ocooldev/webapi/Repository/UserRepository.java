package com.ocooldev.webapi.Repository;

import com.ocooldev.webapi.model.Usuario;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private static  List<Usuario> usuarios = new ArrayList<>();

    public void save(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println(usuario);
        System.out.println(usuarios.toString());

    }

    public void deleteById(Integer id) {
        System.out.println(String.format("DELETE/id - Recebendo o id: %d para", id));
        System.out.println(id);
    }

    public List<Usuario> findAll() {
        System.out.println("LIST - Listando os usuários do sistema");
       // usuarios.add(new Usuario("gleyson", "login","gleyson"));
        return usuarios;
    }
    public Usuario findByUsername(String username) {
        System.out.println(String.format(
                "FIND/username - Recebendo o username: %s para localizar um usuário",
                username
        ));
        return new Usuario("gleyson", "login","gleyson");

    }
}