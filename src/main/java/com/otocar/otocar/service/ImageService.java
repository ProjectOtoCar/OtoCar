package com.otocar.otocar.service;

import com.otocar.otocar.model.Image;
import com.otocar.otocar.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ImageService implements CrudServce<Long, Image> {

    private ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image findById(Long aLong) {
        return imageRepository.findById(aLong).orElse(null);
    }

    @Override
    public Optional<Void> deleteById(Long aLong) {
        imageRepository.deleteById(aLong);
        return Optional.empty();
    }

    @Override
    public Image save(Image obj) {
        Optional<Image> optionalImage = Optional.of(imageRepository.save(obj));
        return optionalImage.orElse(null);
    }

    @Override
    public Image change(Long aLong, Image obj) {
        if(imageRepository.findById(aLong).isEmpty()){
            return imageRepository.save(obj);
        }
        obj.setId(aLong);
        return imageRepository.save(obj);
    }

    @Override
    public Optional<Void> patch(Long aLong, Map<String, Object> fields) {
        boolean isEdit = false;
        Optional<Image> optionalImage = imageRepository.findById(aLong);
        if(optionalImage.isEmpty()) {
            return Optional.empty();
        }
        if(fields.size() == 0){
            return Optional.empty();
        }
        if(fields.get("isMainImage") != null) {
            optionalImage.get().setMainImage(Boolean.getBoolean((String) fields.get("isMainImage")));
            isEdit = true;
        }
        if(fields.get("photo") != null) {
            optionalImage.get().setPhoto(((String)fields.get("photo")));
            isEdit = true;
        }
        if(isEdit){
            imageRepository.save(optionalImage.get());
        }
        return Optional.empty();
    }
}
