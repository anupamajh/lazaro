package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Album;
import com.carmel.common.dbservice.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public List<Album> findAllByClientIdAndTitle(String clientId, String title) {
        return albumRepository.findAllByClientIdAndTitle(clientId, title);
    }

    @Override
    public Album save(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public List<Album> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id) {
        return albumRepository.findAllByClientIdAndTitleAndIdIsNot(clientId, title, id);
    }

    @Override
    public Optional<Album> findById(String id) {
        return albumRepository.findById(id);
    }

    @Override
    public List<Album> findAllByIsDeletedAndClientId(int isDeleted, String clientId) {
        return albumRepository.findAllByIsDeletedAndClientId(isDeleted, clientId);
    }

    @Override
    public Page<Album> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return albumRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public Page<Album> findAll(Specification<Album> textInAllColumns, Pageable pageable) {
        return albumRepository.findAll(textInAllColumns, pageable);
    }
}
