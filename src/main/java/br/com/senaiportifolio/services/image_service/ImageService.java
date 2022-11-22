package br.com.senaiportifolio.services.image_service;

import java.util.List;

import br.com.senaiportifolio.models.Image;

@Deprecated
public interface ImageService {

    public Image createImage(Image newImage);

    public List<Image> findAllImages();

    public Image findImageById(Long image_id);

}
