package com.carmel.common.dbservice.Base.User.Service;

import com.carmel.common.dbservice.Base.AddressBook.DTO.AddressBookDTO;
import com.carmel.common.dbservice.Base.AddressBook.Model.AddressBook;
import com.carmel.common.dbservice.Base.AddressBook.Service.AddressBookService;
import com.carmel.common.dbservice.Base.AppFeature.Response.AppFeaturesResponse;
import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.User.Model.User;
import com.carmel.common.dbservice.Base.User.Repository.UserRepository;
import com.carmel.common.dbservice.Base.User.Response.UsersResponse;
import com.carmel.common.dbservice.common.Search.SearchBuilder;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.response.GenericResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import java.nio.charset.Charset;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.*;

import static com.carmel.common.dbservice.Base.User.Specification.UserSpecification.textInAllColumns;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private ObjectMapper objectMapper = new ObjectMapper();

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressBookService addressBookService;

    @Autowired
    UserInformation userInformation;

    @Autowired
    EntityManager entityManager;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public UsersResponse saveUser(User user) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            if (user.getId() == null) {
                user.setId("");
            }
            user.setClient(userInfo.getClient());
            logger.trace("Data:{}", objectMapper.writeValueAsString(user));
            if (checkDuplicate(user)) {
                usersResponse.setUser(user);
                usersResponse.setSuccess(false);
                usersResponse.setError("Duplicate user name!");
            } else {
                if (user.getId() == "") {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    user.setCreatedBy(userInfo.getId());
                    user.setCreationTime(new Date());
                    user.setClient(userInfo.getClient());
                } else {
                    Optional<User> optionalUser = userRepository.findById(user.getId());
                    user.setPassword(optionalUser.get().getPassword());
                    user.setLastModifiedBy(userInfo.getId());
                    user.setLastModifiedTime(new Date());
                }
                User savedUser = this.save(user);
                AddressBook addressBook = new AddressBook();
                Optional<AddressBook> optionalAddressBook = addressBookService.findByUserId(savedUser.getId());
                if (optionalAddressBook.isPresent()) {
                    addressBook = optionalAddressBook.get();
                }
                addressBook.setEmail1(user.getUserName());
                addressBook.setDisplayName(user.getFullName());
                addressBook.setUserId(savedUser.getId());
                addressBookService.save(addressBook);
                usersResponse.setUser(savedUser);
                usersResponse.setSuccess(true);
                usersResponse.setError("");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        return usersResponse;
    }

    @Override
    public UsersResponse guestSignUp(@Valid User user) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        user.setUserName(user.getPhone());
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            if (user.getId() == null) {
                user.setId("");
            }
            user.setClient(userInfo.getClient());
            logger.trace("Data:{}", objectMapper.writeValueAsString(user));
            User duplicateUser = getDuplicate(user);
            if (duplicateUser != null) {
                usersResponse.setUser(duplicateUser);
                usersResponse.setSuccess(true);
                usersResponse.setError("Duplicate user name!");
            } else {
                if (user.getId() == "") {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    user.setCreatedBy(userInfo.getId());
                    user.setCreationTime(new Date());
                    user.setClient(userInfo.getClient());
                } else {
                    Optional<User> optionalUser = userRepository.findById(user.getId());
                    user.setPassword(optionalUser.get().getPassword());
                    user.setLastModifiedBy(userInfo.getId());
                    user.setLastModifiedTime(new Date());
                }
                User savedUser = this.save(user);
                AddressBook addressBook = new AddressBook();
                Optional<AddressBook> optionalAddressBook = addressBookService.findByUserId(savedUser.getId());
                if (optionalAddressBook.isPresent()) {
                    addressBook = optionalAddressBook.get();
                }
                addressBook.setEmail1(user.getUserName());
                addressBook.setDisplayName(user.getFullName());
                addressBook.setUserId(savedUser.getId());
                addressBookService.save(addressBook);
                usersResponse.setUser(savedUser);
                usersResponse.setSuccess(true);
                usersResponse.setError("");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        return usersResponse;
    }

    @Override
    public UsersResponse phoneNumberSignUp(User user) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            if (user.getId() == null) {
                user.setId("");
            }
            user.setClient(userInfo.getClient());
            logger.trace("Data:{}", objectMapper.writeValueAsString(user));
            if (user.getId() == "") {
                user = isDuplicateUser(user);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setCreatedBy(userInfo.getId());
                user.setCreationTime(new Date());
                user.setClient(userInfo.getClient());
            } else {
                Optional<User> optionalUser = userRepository.findById(user.getId());
                user.setPassword(optionalUser.get().getPassword());
                user.setLastModifiedBy(userInfo.getId());
                user.setLastModifiedTime(new Date());
            }
            User savedUser = this.save(user);
            AddressBook addressBook = new AddressBook();
            Optional<AddressBook> optionalAddressBook = addressBookService.findByUserId(savedUser.getId());
            if (optionalAddressBook.isPresent()) {
                addressBook = optionalAddressBook.get();
            }
            addressBook.setEmail1(user.getEmail());
            addressBook.setPhone1(user.getPhone());
            addressBook.setDisplayName(user.getFullName());
            addressBook.setUserId(savedUser.getId());
            addressBookService.save(addressBook);
            usersResponse.setUser(savedUser);
            usersResponse.setSuccess(true);
            usersResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        return usersResponse;
    }

    @Override
    public UsersResponse checkPhoneNumber(User user) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            if (user.getId() == null) {
                user.setId("");
            }
            user.setClient(userInfo.getClient());
            logger.trace("Data:{}", objectMapper.writeValueAsString(user));
            user = isDuplicateUser(user);
            usersResponse.setSuccess(true);
            usersResponse.setUser(user);
        }catch (Exception ex){
            throw ex;
        }
        return usersResponse;
    }

    private User isDuplicateUser(User user) throws Exception {
        boolean hasError = false;
        if(user.getPhone() == null){
            throw new Exception("Phone number cannot be null");
        }
        if(user.getPhone().equals("")){
            throw new Exception("Phone number cannot be empty");
        }
        List<User> users = userRepository.findAllByIsDeletedAndPhone(0, user.getPhone());
        if (users.size() > 0) {
            hasError = true;
            throw new Exception("Phone number already registered");
        }

        if (user.getEmail() == null) {
            user.setEmail("");
        }
        if (!user.getEmail().equals("")) {
            users = userRepository.findAllByIsDeletedAndEmail(0, user.getEmail());
            if (users.size() > 0) {
                hasError = true;
                throw new Exception("Email already registered");
            }
        }
        user.setUserName(generateUserName().toUpperCase());
        users = userRepository.findAllByIsDeletedAndUserName(0, user.getUserName());
        while (users.size() != 0) {
            user.setUserName(generateUserName().toUpperCase());
            users = userRepository.findAllByIsDeletedAndUserName(0, user.getUserName());
        }
        user.setPassword(user.getUserName());
        return user;
    }

    private String generateUserName() {
        int len = 8;
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }


    @Override
    public UsersResponse moveToTrash(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<User> userOptional = userRepository.findById(formData.get("id"));
            if (userOptional != null) {
                User user = userOptional.get();
                user.setIsDeleted(1);
                user.setDeletedBy(userInfo.getId());
                user.setDeletedTime(new Date());
                usersResponse.setSuccess(true);
                usersResponse.setError("");
                usersResponse.setUser(this.save(user));
            } else {
                usersResponse.setSuccess(false);
                usersResponse.setError("Error occurred while moving user to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return usersResponse;
    }

    @Override
    public UsersResponse get(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<User> optionalUser = userRepository.findById(formData.get("id"));
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                Optional<AddressBook> optionalAddressBook = addressBookService.findByUserId(user.getId());
                if (optionalAddressBook.isPresent()) {
                    usersResponse.setAddressBook(new AddressBookDTO(optionalAddressBook.get()));
                }
                usersResponse.setSuccess(true);
                usersResponse.setError("");
                usersResponse.setUser(user);
            } else {
                usersResponse.setSuccess(false);
                usersResponse.setError("Error occurred while fetching user!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return usersResponse;
    }

    @Override
    public UsersResponse getAll() throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            usersResponse.setUserList(userRepository.findAllByIsDeleted(0));
            usersResponse.setSuccess(true);
            usersResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            usersResponse.setSuccess(false);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }

    @Override
    public UsersResponse getDeleted() throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            usersResponse.setUserList(userRepository.findAllByIsDeleted(1));
            usersResponse.setSuccess(true);
            usersResponse.setError("");
        } catch (Exception ex) {
            usersResponse.setSuccess(false);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }

    @Override
    public UsersResponse getPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("fullName"));
            Page<User> page = userRepository.findAllByIsDeletedAndClient(0, userInfo.getClient(), pageable);
            usersResponse.setTotalRecords(page.getTotalElements());
            usersResponse.setTotalPages(page.getTotalPages());
            usersResponse.setUserList(page.getContent());
            usersResponse.setCurrentRecords(usersResponse.getUserList().size());
            usersResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            usersResponse.setSuccess(false);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }

    @Override
    public UsersResponse searchPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        UsersResponse orgResponse = new UsersResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("userName"));
            Page<User> page;
            if (searchText == null) {
                page = userRepository.findAll(pageable);
            } else {
                page = userRepository.findAll(textInAllColumns(searchText, userInfo.getClient()), pageable);
            }
            orgResponse.setTotalRecords(page.getTotalElements());
            orgResponse.setTotalPages(page.getTotalPages());
            orgResponse.setUserList(page.getContent());
            orgResponse.setCurrentRecords(orgResponse.getUserList().size());
            orgResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            orgResponse.setSuccess(false);
            orgResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return orgResponse;
    }


    public boolean checkDuplicate(User user) throws Exception {
        List<User> userList;
        if (user.getId() == null) {
            user.setId("");
        }
        if (user.getId().isEmpty()) {
            userList = userRepository.findAllByUserName(user.getUserName());
        } else {
            userList = userRepository.findAllByUserNameAndIdIsNot(user.getUserName(), user.getId());
        }
        if (userList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getDuplicate(User user) throws Exception {
        List<User> userList;
        if (user.getId() == null) {
            user.setId("");
        }
        if (user.getId().isEmpty()) {
            userList = userRepository.findAllByUserName(user.getUserName());
        } else {
            userList = userRepository.findAllByUserNameAndIdIsNot(user.getUserName(), user.getId());
        }
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public UserInfo oldUser(Principal principal) throws Exception {
        return null;
    }

    @Override
    public UserInfo user(Principal principal) throws Exception {
        return null;
    }

    @Override
    public String myPic() throws Exception {
        return null;
    }

    @Override
    public UserInfo resetPassword(Map<String, String> formData) throws Exception {
        return null;
    }


    @Override
    public UsersResponse activateAccount(Map<String, String> formData) throws Exception {
        UsersResponse usersResponse = new UsersResponse();
        try {
            Optional<User> optionalUser = userRepository.findById(formData.get("id"));
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setPassword(passwordEncoder.encode(formData.get("password")));
                user.setAccountStatus(2);
                User savedUser = this.save(user);
                usersResponse.setUser(savedUser);
                ;
                usersResponse.setSuccess(true);
            } else {
                throw new Exception("User not found");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            usersResponse.setSuccess(false);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }

    @Override
    public GenericResponse changePassword(Map<String, String> formData) throws Exception {
        return null;
    }


    @Override
    public UsersResponse search(SearchRequest searchRequest) throws Exception {
        UsersResponse usersResponse = new UsersResponse();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery = SearchBuilder.buildSearch(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root,
                    User.class,
                    searchRequest
            );
            long totalRecords = SearchBuilder.getTotalRecordCount(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root
            );
            TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((searchRequest.getCurrentPage() - 1) * searchRequest.getPageSize());
            typedQuery.setMaxResults(searchRequest.getPageSize());
            List<User> userList = typedQuery.getResultList();
            usersResponse.setCurrentRecords(userList.size());
            usersResponse.setTotalRecords(totalRecords);
            usersResponse.setSuccess(true);
            usersResponse.setError("");
            usersResponse.setUserList(userList);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);
            usersResponse.setSuccess(false);
            usersResponse.setError(ex.getMessage());
        }
        return usersResponse;
    }

    @Override
    public UsersResponse findUsersIn(List<String> userIds) throws Exception {
        UsersResponse usersResponse = new UsersResponse();
        try {
            List<User> userList = userRepository.findAllByIdIn(userIds);
            usersResponse.setUserList(userList);
            usersResponse.setSuccess(true);
            usersResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            usersResponse.setSuccess(false);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;

    }

    @Override
    public Optional<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public UsersResponse findAllByUserName(String userName) {
        return null;
    }

    @Override
    public UsersResponse findAllByUserNameAndIdIsNot(String userName, String id) {
        return null;
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }


    @Override
    public UsersResponse findAllByDeletionStatus(int isDeleted, Client client) {
        return null;
    }

    @Override
    public UsersResponse findAllByClient(Pageable pageable, Client client) {
        return null;
    }

    @Override
    public UsersResponse findAll(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public UsersResponse findAll(Specification<User> textInAllColumns, Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public UsersResponse findAllByIdIn(List<String> userIds) throws Exception {
        return null;
    }
}
