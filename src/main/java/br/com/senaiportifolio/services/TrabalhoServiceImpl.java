package br.com.senaiportifolio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import br.com.senaiportifolio.file_system.TrabalhoFileSystemRepo;
import br.com.senaiportifolio.models.Image;
import br.com.senaiportifolio.models.Trabalho;
import br.com.senaiportifolio.repository.TrabalhoRepository;
import br.com.senaiportifolio.services.image_service.ImageServiceImpl;

@Service
public class TrabalhoServiceImpl implements TrabalhoService {

    private final TrabalhoRepository trabalhoRepository;
    private final TrabalhoFileSystemRepo trabalhoFileSystem;
    private final ImageServiceImpl imageService;

    public TrabalhoServiceImpl(TrabalhoRepository trabalhoRepository, TrabalhoFileSystemRepo trabalhoFileSystem,
            ImageServiceImpl imageService) {
        this.trabalhoRepository = trabalhoRepository;
        this.trabalhoFileSystem = trabalhoFileSystem;
        this.imageService = imageService;
    }

    


    @Override
    public Trabalho createTrabalho(Trabalho newTrabalho) {
        try{
            System.out.println("Passou");
           return trabalhoRepository.save(newTrabalho);
            
        }
        catch(Exception e){
            throw new RuntimeException("Erro ao criar o trabalho", e);
        }
    }

    @Override
    public List<Trabalho> findAllTrabalhos() {
        return trabalhoRepository.findAll();
    }

    @Override
    public List<Trabalho> findAllTrabalhosBySubject(String subject) {
        return trabalhoRepository.findBySubjectLike(subject);
    }

    @Override
    public void deleteTrabalho(Long trabalho_id) {
        try{
            Optional<Trabalho> trabalho_data = trabalhoRepository.findById(trabalho_id);
            if (trabalho_data.isEmpty()){
                throw new RuntimeException("Este trabalho não existe");
            }
            trabalhoRepository.delete(trabalho_data.get());
        }
        catch(Exception e){
            throw new RuntimeException("Erro ao deletar o trabalho", e);
        }
        
    }

    @Override
    public Trabalho editTrabalho(Long trabalho_id, Trabalho editedTrabalho ) {
        try{
            Optional<Trabalho> trabalho_data = trabalhoRepository.findById(trabalho_id);
            if (trabalho_data.isEmpty()){
                throw new RuntimeException("Este trabalho não existe");
            }

            trabalho_data.map(trabalho -> {
                trabalho.setImage(editedTrabalho.getImage());
                trabalho.setTitle(editedTrabalho.getTitle());
                trabalho.setSubject(editedTrabalho.getSubject());
                trabalho.setLink(editedTrabalho.getLink());
                trabalho.setImage_url(editedTrabalho.getImage_url());
                return trabalho;
            });

            return trabalhoRepository.save(trabalho_data.get());
        }
        catch(Exception e){
            throw new RuntimeException("Erro ao editar o trabalho", e);
        }
    }

    @Deprecated
    @Override
    public Trabalho saveTrabalhoImage(byte[] imageBytes, String imageName, Long trabalho_id) {
        try{

            Optional<Trabalho> trabalho_data = trabalhoRepository.findById(trabalho_id);
            if(trabalho_data.isEmpty()){
                throw new RuntimeException("Este trabalho não existe");
            }

            String location = trabalhoFileSystem.save(imageBytes, imageName);

            Image newImage = new Image();

            newImage.setLocation(location);
            newImage.setName(imageName);

            trabalho_data.get().setImage(imageService.createImage(newImage));

            return editTrabalho(trabalho_id, trabalho_data.get());
        }
        catch(Exception e){
            throw new RuntimeException("Erro ao salvar a image no trabalho");
        }
    }

    @Deprecated
    @Override
    public FileSystemResource findTrabalhoImage(Long trabalho_id) {
        Optional<Trabalho> trabalho_data = trabalhoRepository.findById(trabalho_id);
        if(trabalho_data.isEmpty()){
            throw new RuntimeException("Este trabalho não existe");
        }
        
        Image trabalho_image = trabalho_data.get().getImage();

        return trabalhoFileSystem.findInTrabalhoImages(trabalho_image.getLocation());


    }
    
}
