package com.carmel.common.dbservice.Base.AddressBook.Controller;

import com.carmel.common.dbservice.Base.AddressBook.Responce.AddressBookResponse;
import com.carmel.common.dbservice.Base.AddressBook.Service.AddressBookService;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.Base.Album.Controller.AlbumController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/address-book")
public class AddressBookController {

    Logger logger = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    AddressBookService addressBookService;

    @RequestMapping(value = "/save-profile-pic", method = RequestMethod.POST)
    public AddressBookResponse saveProfilePic(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        AddressBookResponse addressBookResponse = new AddressBookResponse();
        try{
            addressBookResponse = addressBookService.saveProfilePic(formData);
        }catch (Exception ex){
            addressBookResponse.setSuccess(true);
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
        logger.trace("Entering");
        AddressBookResponse addressBookResponse = new AddressBookResponse();
        try{
            addressBookResponse = addressBookService.search(searchRequest);
        }catch (Exception ex){
            addressBookResponse.setSuccess(true);
            addressBookResponse.setError(ex.getMessage());
        }
        return addressBookResponse;

    }

}
