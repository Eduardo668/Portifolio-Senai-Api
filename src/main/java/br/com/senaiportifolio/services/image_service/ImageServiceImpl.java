package br.com.senaiportifolio.services.image_service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.senaiportifolio.models.Image;
import br.com.senaiportifolio.repository.ImageRepository;

@Deprecated
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image createImage(Image newImage) {
        try {
            return imageRepository.save(newImage);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar a imagem");
        }
    }

    @Override
    public List<Image> findAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public Image findImageById(Long image_id) {
            Optional<Image> image_data = imageRepository.findById(image_id);
            if (image_data.isEmpty()){
                throw new RuntimeException("Esta imagem não existe");
            }
            return image_data.get();
    }
}
