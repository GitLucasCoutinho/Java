package org.example.dao;

import org.example.domain.GenericDomain;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Classe abstrata que representa um DAO genérico.
 * DAO (Data Access Object) é um padrão de projeto usado para
 * separar a lógica de acesso a dados da lógica de negócio.
 *
 * @param <T> Tipo genérico que representa a entidade que será manipulada.
 */
public abstract class GenericDAO<T> {


    private final List<T> db = new ArrayList<>();

    /**
     * Método responsável por salvar uma entidade no "banco de dados".
     *
     * @param domain objeto da entidade que será salvo.
     * @return o próprio objeto salvo, permitindo encadeamento ou confirmação.
     */
    public T update(T domain) {

        var stored = db.stream().filter(d -> d.equals(domain))
                .findFirst().orElseThrow();

        db.remove(stored);

        return save(domain);
    }

    public T save(T domain) {
        // Adiciona o objeto recebido à lista (simulando persistência).
        db.add(domain);
        return domain;
    }

    public boolean delete(T domain) {
        // Remove o objeto da lista (simulando exclusão).
        return db.remove(domain);
    }

    public Optional<T> findById(Predicate<T> filterCallback) {
        // Busca o objeto na lista usando o filtro fornecido.
        return db.stream().filter(filterCallback).findFirst();

    }

    public List<T> findAll() {
        // Retorna uma cópia da lista de objetos (simulando consulta a todos os registros).
        return db;
    }

    public int count() {
        // Retorna o número de objetos na lista (simulando contagem de registros).
        return db.size();
    }

}