package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Album;
import com.carmel.common.dbservice.model.Photo;
import com.carmel.common.dbservice.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    @Override
    public Photo save(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public List<Photo> findAllByClientIdAndAlbumIsNotAndName(String clientId, Album album, String name) {
        return photoRepository.findAllByClientIdAndAlbumIsNotAndNameAndIsDeleted(clientId, album, name, 0);
    }

    @Override
    public List<Photo> findAllByClientIdAndAlbumIsNotAndNameAndIdIsNot(String clientId, Album album, String name, String id) {
        return photoRepository.findAllByClientIdAndAlbumIsNotAndNameAndIdIsNotAndIsDeleted(clientId, album, name, id, 0);
    }

    @Override
    public Optional<Photo> findById(String id) {
        return photoRepository.findById(id);
    }

    @Override
    public List<Photo> findAllByIsDeletedAndClientIdAndAlbum(int isDeleted, String clientId, Album album) {
        return photoRepository.findAllByIsDeletedAndClientIdAndAlbum(isDeleted, clientId, album);
    }

    @Override
    public Page<Photo> findAllByIsDeletedAndClientIdAndAlbum(int isDeleted, String clientId, Album album, Pageable pageable) {
        return photoRepository.findAllByIsDeletedAndClientIdAndAlbum(isDeleted, clientId, album, pageable);
    }

    @Override
    public Page<Photo> findAll(Specification<Photo> textInAllColumns, Pageable pageable) {
        return photoRepository.findAll(textInAllColumns, pageable);
    }
}
