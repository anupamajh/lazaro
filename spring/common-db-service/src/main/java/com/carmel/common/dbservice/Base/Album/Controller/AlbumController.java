package com.carmel.common.dbservice.Base.Album.Controller;

import com.carmel.common.dbservice.common.Search.SearchBuilder;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.Base.Album.Model.Album;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.Base.Album.Responce.AlbumResponse;
import com.carmel.common.dbservice.Base.Album.Servise.AlbumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.carmel.common.dbservice.Base.Album.Specification.AlbumSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/album")
public class AlbumController {

    Logger logger = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    AlbumService albumService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AlbumResponse save(@Valid @RequestBody Album album) {
     logger.trace("Entering");
     AlbumResponse albumResponse = new AlbumResponse();
     try{
         albumResponse = albumService.saveAlbum(album);
     }catch (Exception ex){
         albumResponse.setSuccess(true);
         albumResponse.setError(ex.getMessage());
     }
     return albumResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public AlbumResponse moveToTrash(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try{
            albumResponse = albumService.moveToTrash(formData);
        }catch (Exception ex){
            albumResponse.setSuccess(true);
            albumResponse.setError(ex.getMessage());
        }
        return albumResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public AlbumResponse get(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try{
            albumResponse = albumService.get(formData);
        }catch (Exception ex){
            albumResponse.setSuccess(true);
            albumResponse.setError(ex.getMessage());
        }
        return albumResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public AlbumResponse getDeleted() {
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try{
            albumResponse = albumService.getDeleted();
        }catch (Exception ex){
            albumResponse.setSuccess(true);
            albumResponse.setError(ex.getMessage());
        }
        return albumResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public AlbumResponse getAll() {
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try{
            albumResponse = albumService.getAll();
        }catch (Exception ex){
            albumResponse.setSuccess(true);
            albumResponse.setError(ex.getMessage());
        }
        return albumResponse;
    }

    @RequestMapping(value = "/get-albums", method = RequestMethod.POST)
    public AlbumResponse getPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try{
            albumResponse = albumService.getPaginated(formData);
        }catch (Exception ex){
            albumResponse.setSuccess(true);
            albumResponse.setError(ex.getMessage());
        }
        return albumResponse;
    }

    @RequestMapping(value = "/search-albums", method = RequestMethod.POST)
    public AlbumResponse searchPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try{
            albumResponse = albumService.searchPaginated(formData);
        }catch (Exception ex){
            albumResponse.setSuccess(true);
            albumResponse.setError(ex.getMessage());
        }
        return albumResponse;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public AlbumResponse search(@RequestBody SearchRequest searchRequest) {
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try{
            albumResponse = albumService.search(searchRequest);
        }catch (Exception ex){
            albumResponse.setSuccess(true);
            albumResponse.setError(ex.getMessage());
        }
        return albumResponse;
    }




}
