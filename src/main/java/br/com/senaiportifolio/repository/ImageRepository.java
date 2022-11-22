package br.com.senaiportifolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senaiportifolio.models.Image;

@Deprecated
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    
}
