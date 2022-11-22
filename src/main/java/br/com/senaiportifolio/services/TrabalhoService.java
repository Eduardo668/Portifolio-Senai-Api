package br.com.senaiportifolio.services;

import java.util.List;

import org.springframework.core.io.FileSystemResource;

import br.com.senaiportifolio.models.Trabalho;

public interface TrabalhoService {
    
    public Trabalho createTrabalho(Trabalho newTrabalho);

    public List<Trabalho> findAllTrabalhos();

    public List<Trabalho> findAllTrabalhosBySubject(String subject);

    public void deleteTrabalho(Long trabalho_id);

    public Trabalho editTrabalho(Long trabalho_id, Trabalho editedTrabalho);

    @Deprecated
    public Trabalho saveTrabalhoImage(byte[] imageBytes, String imageName, Long trabalho_id);

    @Deprecated
    public FileSystemResource findTrabalhoImage(Long trabalho_id);

}
