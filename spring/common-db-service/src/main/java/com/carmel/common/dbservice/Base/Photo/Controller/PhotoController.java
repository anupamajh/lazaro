package com.carmel.common.dbservice.Base.Photo.Controller;

import com.carmel.common.dbservice.Base.Album.Controller.AlbumController;
import com.carmel.common.dbservice.common.Search.SearchBuilder;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.Base.Album.Model.Album;
import com.carmel.common.dbservice.Base.Photo.Model.Photo;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.Base.Photo.Responce.PhotoResponse;
import com.carmel.common.dbservice.Base.Album.Servise.AlbumService;
import com.carmel.common.dbservice.Base.Photo.Service.PhotoService;
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

import static com.carmel.common.dbservice.Base.Photo.Specification.PhotoSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/photos")
public class PhotoController {
    Logger logger = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    PhotoService photoService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PhotoResponse save(@Valid @RequestBody Photo photo) {
        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try{
            photoResponse = photoService.savePhoto(photo);
        }catch (Exception ex){
            photoResponse.setSuccess(true);
            photoResponse.setError(ex.getMessage());
        }
        return photoResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public PhotoResponse moveToTrash(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try{
            photoResponse = photoService.moveToTrash(formData);
        }catch (Exception ex){
            photoResponse.setSuccess(true);
            photoResponse.setError(ex.getMessage());
        }
        return photoResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public PhotoResponse get(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try{
            photoResponse = photoService.get(formData);
        }catch (Exception ex){
            photoResponse.setSuccess(true);
            photoResponse.setError(ex.getMessage());
        }
        return photoResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public PhotoResponse getDeleted(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try{
            photoResponse = photoService.getDeleted(formData);
        }catch (Exception ex){
            photoResponse.setSuccess(true);
            photoResponse.setError(ex.getMessage());
        }
        return photoResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public PhotoResponse getAll(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try{
            photoResponse = photoService.getAll(formData);
        }catch (Exception ex){
            photoResponse.setSuccess(true);
            photoResponse.setError(ex.getMessage());
        }
        return photoResponse;
    }

    @RequestMapping(value = "/get-photos", method = RequestMethod.POST)
    public PhotoResponse getPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try{
            photoResponse = photoService.getPaginated(formData);
        }catch (Exception ex){
            photoResponse.setSuccess(true);
            photoResponse.setError(ex.getMessage());
        }
        return photoResponse;
    }

    @RequestMapping(value = "/search-photos", method = RequestMethod.POST)
    public PhotoResponse searchPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try{
            photoResponse = photoService.searchPaginated(formData);
        }catch (Exception ex){
            photoResponse.setSuccess(true);
            photoResponse.setError(ex.getMessage());
        }
        return photoResponse;
    }


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public PhotoResponse search(@RequestBody SearchRequest searchRequest) {
        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try{
            photoResponse = photoService.search(searchRequest);
        }catch (Exception ex){
            photoResponse.setSuccess(true);
            photoResponse.setError(ex.getMessage());
        }
        return photoResponse;
    }

}
