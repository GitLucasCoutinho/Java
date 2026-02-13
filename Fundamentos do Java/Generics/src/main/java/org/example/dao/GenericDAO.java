package org.example.dao;

import org.example.domain.GenericDomain;

import java.util.ArrayList;
import java.util.Arrays;
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
public class GenericDAO<ID, T extends GenericDomain<ID>> {

    public GenericDAO() {
    }

    private final List<T> db = new ArrayList<>();

    /**
     * Método responsável por salvar uma entidade no "banco de dados".
     *
     * @param domain objeto da entidade que será salvo.
     * @return o próprio objeto salvo, permitindo encadeamento ou confirmação.
     */
    public T update(ID id, T domain) {

        var stored = find(d -> d.getId().equals(id)).orElseThrow();
        db.remove(stored);

        return save(domain);
    }

    /* //Aqui o update faz a bussca direto no metodo, isso nao e uma boa pratica. o melhor seria utilizar o metodo find assim o codigo fica mais limpo e reutilizavel, alem de evitar a repeticao de codigo. como no feito acima nao comentado
        public T update(ID id, T domain) {

        var stored = db.stream().filter(d -> d.getId().equals(id))
                .findFirst().orElseThrow();

        db.remove(stored);

        return save(domain);
    }
    * */

    public T save(T domain) {
        // Adiciona o objeto recebido à lista (simulando persistência).
        db.add(domain);
        return domain;
    }


    public boolean save(int  batch, T... domains) {
        System.out.println("Salvanso em lotes (%s) \n "+ batch + " registros salvos com sucesso!");
        return db.addAll(Arrays.stream(domains).toList());
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

    public Optional<T> find(Predicate<T> filterCallback) {
        return db.stream().filter(filterCallback).findFirst();
    }
}