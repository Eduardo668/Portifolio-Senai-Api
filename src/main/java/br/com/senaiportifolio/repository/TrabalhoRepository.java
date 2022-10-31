package br.com.senaiportifolio.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senaiportifolio.models.Trabalho;

@Transactional
@Repository
public interface TrabalhoRepository extends JpaRepository<Trabalho, Long> {
    
    public List<Trabalho> findBySubjectLike(String subject);

}
