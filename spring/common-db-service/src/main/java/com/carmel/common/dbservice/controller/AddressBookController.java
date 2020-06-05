package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.common.Search.SearchBuilder;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.config.YAMLConfig;
import com.carmel.common.dbservice.model.AddressBook;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.response.AddressBookResponse;
import com.carmel.common.dbservice.services.AddressBookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/address-book")
public class AddressBookController {

    Logger logger = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    AddressBookService addressBookService;

    @Autowired
    YAMLConfig yamlConfig;

    @Autowired
    EntityManager entityManager;

    @RequestMapping(value = "/save-profile-pic", method = RequestMethod.POST)
    public AddressBookResponse saveProfilePic(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AddressBookResponse addressBookResponse = new AddressBookResponse();
        try {
            Optional<AddressBook> optionalAddressBook = addressBookService.findByUserId(userInfo.getId());
            AddressBook addressBook;
            if (optionalAddressBook.isPresent()) {
                addressBook = optionalAddressBook.get();
            } else {
                addressBook = new AddressBook();
                addressBook.setUserId(userInfo.getId());
                addressBook.setDisplayName(userInfo.getFullName());
                addressBook.setEmail1(userInfo.getUserName());
            }
            String imageData = formData.get("imageData");

            String imageSaveBasePath = yamlConfig.getImageSavePath();

            byte[] scanBytes = Base64.getDecoder().decode(imageData);
            File fileSaveDir = new File(imageSaveBasePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            String filePath = fileSaveDir.getAbsolutePath() + File.separator + userInfo.getId() + "_" + System.nanoTime() + ".png";
            File scanFile = new File(filePath);
            BufferedOutputStream scanStream = new BufferedOutputStream(new FileOutputStream(scanFile));
            scanStream.write(scanBytes);
            scanStream.close();
            addressBook.setLogoPath(filePath);
            addressBookResponse.setAddressBook(addressBookService.save(addressBook));
            addressBookResponse.setSuccess(true);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            addressBookResponse.setSuccess(false);
            addressBookResponse.setError(ex.getMessage());
        }
        return addressBookResponse;
    }


    @RequestMapping(value = "/get-my-address-book", method = RequestMethod.POST)
    public AddressBookResponse getMyAddressBook() {
        return null;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public AddressBookResponse search(@RequestBody SearchRequest searchRequest) {
        AddressBookResponse addressBookResponse = new AddressBookResponse();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<AddressBook> criteriaQuery = criteriaBuilder.createQuery(AddressBook.class);
            Root<AddressBook> root = criteriaQuery.from(AddressBook.class);
            criteriaQuery = SearchBuilder.buildSearch(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root,
                    AddressBook.class,
                    searchRequest
            );
            long totalRecords = SearchBuilder.getTotalRecordCount(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root
            );
            TypedQuery<AddressBook> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((searchRequest.getCurrentPage() - 1) * searchRequest.getPageSize());
            typedQuery.setMaxResults(searchRequest.getPageSize());
            List<AddressBook> addressBookList = typedQuery.getResultList();
            addressBookResponse.setCurrentRecords(addressBookList.size());
            addressBookResponse.setTotalRecords(totalRecords);
            addressBookResponse.setSuccess(true);
            addressBookResponse.setError("");
            addressBookResponse.setAddressBookList(addressBookList);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);
            addressBookResponse.setSuccess(false);
            addressBookResponse.setError(ex.getMessage());
        }
        return addressBookResponse;
    }

}
