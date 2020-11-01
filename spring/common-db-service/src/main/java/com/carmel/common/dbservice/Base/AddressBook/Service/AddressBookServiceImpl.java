package com.carmel.common.dbservice.Base.AddressBook.Service;

import com.carmel.common.dbservice.Base.AddressBook.Model.AddressBook;
import com.carmel.common.dbservice.Base.AddressBook.Repository.AddressBookRepository;
import com.carmel.common.dbservice.Base.AddressBook.Responce.AddressBookResponse;
import com.carmel.common.dbservice.common.Search.SearchBuilder;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.carmel.common.dbservice.config.YAMLConfig;
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

@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    AddressBookRepository addressBookRepository;

    Logger logger = LoggerFactory.getLogger(AddressBookServiceImpl.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    AddressBookService addressBookService;

    @Autowired
    YAMLConfig yamlConfig;

    @Autowired
    EntityManager entityManager;


    @Override
    public AddressBookResponse saveProfilePic(Map<String, String> formData) throws Exception {
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
            throw  ex;
        }
        return addressBookResponse;
    }

    @Override
    public AddressBookResponse getMyAddressBook() throws Exception {
        return null;
    }


    @Override
    public AddressBookResponse search(SearchRequest searchRequest) throws Exception {
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
            throw ex;
        }
        return addressBookResponse;
    }


    @Override
    public Optional<AddressBook> findByUserId(String id) {
        return addressBookRepository.findByUserId(id);
    }

    @Override
    public AddressBook save(AddressBook addressBook) {
        return addressBookRepository.save(addressBook);
    }

    @Override
    public AddressBookResponse findAllByIsDeleted(int isDeleted) {
        AddressBookResponse addressBookResponse = new AddressBookResponse();
        addressBookResponse.setSuccess(true);
        addressBookResponse.setAddressBookList(addressBookRepository.findAllByIsDeletedAndUserIdIsNotNull(isDeleted));
        return addressBookResponse;

    }

    @Override
    public AddressBookResponse findAllByIsDeletedAndUserIdIsNot(int isDeleted, String userId) {
        AddressBookResponse addressBookResponse = new AddressBookResponse();
        addressBookResponse.setSuccess(true);
        addressBookResponse.setAddressBookList(addressBookRepository.findAllByIsDeletedAndUserIdIsNot(isDeleted, userId));
        return addressBookResponse;
    }
}
