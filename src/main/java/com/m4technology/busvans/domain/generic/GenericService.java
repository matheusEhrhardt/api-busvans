package com.m4technology.busvans.domain.generic;


import com.m4technology.busvans.domain.exception.EntidadeEmUsoExeption;
import com.m4technology.busvans.domain.exception.EntidadeNaoEncontradaException;
import com.m4technology.busvans.domain.exception.NegocioException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class GenericService<T extends JpaRepository, E>{

    @Autowired
    protected T repository;

    @PersistenceContext
    private EntityManager entityManager;

    private E model;

    public GenericService(E model) {
        this.model = model;
    }

    public List<E> buscarPorParametros(HashMap<String,Object> parametros){
        String jpql = montarJpqlBuscarPorParametros(parametros);
        TypedQuery<E> query = (TypedQuery<E>) entityManager.createQuery(jpql, model.getClass());
        return query.getResultList();
    }

    public List<E> buscarTodos(){
        return repository.findAll();
    }
    public E buscarPorId(Long id) {
        Optional<E> model = repository.findById(id);
        if (model.isEmpty()){
            throw new EntidadeNaoEncontradaException();
        }
        return model.get();
    }

    public E salvar(E model){
        try {
            return (E) repository.save(model);
        } catch (NegocioException e){
            throw new NegocioException();
        }
    }

    public List<E> salvar(List<E> models){
        try {
            return repository.saveAll(models);
        } catch (NegocioException e){
            throw new NegocioException();
        }
    }

    public E salvar(E model, Long id){
        E modelExistente = buscarPorId(id);
        BeanUtils.copyProperties(model,modelExistente, "id");
        try {
            return (E) repository.save(model);
        } catch (NegocioException e){
            throw new NegocioException();
        }
    }

    public void excluir(Long id){
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException();
        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoExeption();
        }
    }

    private String montarJpqlBuscarPorParametros(HashMap<String,Object> parametros){;
        List<Field> atributos = Arrays.stream(model.getClass().getDeclaredFields())
                .collect(Collectors.toList());

        String sql = "SELECT a FROM ".concat(model.getClass().getSimpleName()).concat(" a");

        int numAtributos = 0;

        for (Field atributo : atributos) {
            for (String chave : parametros.keySet()) {
                if (!atributo.getName().toUpperCase().equals(chave.toUpperCase())) continue;
                sql = numAtributos == 0 ? sql.concat(" WHERE ") : sql.concat(" AND ");
                sql = sql.concat(atributo.getName()).concat(" = ");

                if (atributo.getType().getSimpleName().equals("String")){
                    sql = sql.concat("'").concat(String.valueOf(parametros.get(chave))).concat("'");
                }
                else if (atributo.getType().getSimpleName().contains("Date")) {
                    sql = sql.concat("TO_DATE('")
                            .concat(String.valueOf(parametros.get(chave)))
                            .concat("','yyyy-MM-dd')");
                }
                else {
                    sql = sql.concat(String.valueOf(parametros.get(chave)));
                }
                numAtributos++;
                break;
            }
        }

        return sql;
    }

}
