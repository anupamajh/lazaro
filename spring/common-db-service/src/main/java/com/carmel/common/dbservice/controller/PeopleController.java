package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.*;
import com.carmel.common.dbservice.model.DTO.InterestCategoryDTO;
import com.carmel.common.dbservice.model.DTO.PersonDTO;
import com.carmel.common.dbservice.model.DTO.UserInterestCategoryDTO;
import com.carmel.common.dbservice.model.DTO.UserInterestsDTO;
import com.carmel.common.dbservice.response.PeopleResponse;
import com.carmel.common.dbservice.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PeopleController {
    Logger logger = LoggerFactory.getLogger(PeopleController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    AddressBookService addressBookService;

    @Autowired
    UserInterestsService userInterestsService;

    @Autowired
    UserService userService;

    @Autowired
    FavouritePeopleService favouritePeopleService;

    @Autowired
    InterestCategoryService interestCategoryService;

    @Autowired
    InterestService interestService;

    @RequestMapping(value = "/get-people", method = RequestMethod.POST)
    public PeopleResponse getPeople() {
        UserInfo userInfo = userInformation.getUserInfo();
        PeopleResponse peopleResponse = new PeopleResponse();
        try {
            List<AddressBook> addressBookList = addressBookService.findAllByIsDeleted(0);
            List<AddressBook> filteredAddressBook = addressBookList.stream().filter(
                    addressBook ->
                            addressBook.getUserId().equals(userInfo.getId())
            ).collect(Collectors.toList());
            List<AddressBook> othersAddressBook = addressBookList.stream().filter(
                    addressBook ->
                            !addressBook.getUserId().equals(userInfo.getId())
            ).collect(Collectors.toList());
            AddressBook myAddressBook = filteredAddressBook.size() > 0 ? filteredAddressBook.get(0) : null;
            List<UserInterestsDTO> userInterests = userInterestsService.findAll()
                    .stream().map(UserInterestsDTO::new).collect(Collectors.toList()).stream()
                    .filter(uidto -> uidto.getIsInterested() == 1).collect(Collectors.toList());
            List<UserInterestsDTO> myInterests = userInterests.stream().filter(
                    ui -> ui.getUserId().equals(userInfo.getId())
            ).collect(Collectors.toList());
            List<UserInterestsDTO> othersInterests = userInterests.stream().filter(
                    ui -> !ui.getUserId().equals(userInfo.getId())
            ).collect(Collectors.toList());
            List<PersonDTO> personList = new ArrayList<>();
            othersAddressBook.forEach(addressBook -> {
                PersonDTO person = new PersonDTO();
                person.setAddressBook(addressBook);
                List<UserInterestsDTO> userInterestsList = userInterests.stream().filter(
                        ui -> ui.getUserId().equals(addressBook.getUserId())
                ).collect(Collectors.toList());

                person.setUserInterestsList(userInterestsList);
                personList.add(person);

            });
            peopleResponse.setPersonList(personList);
            peopleResponse.setTotalInterestCount(interestService.countByIsDeleted(0));
            peopleResponse.setMyAddressBook(myAddressBook);
            peopleResponse.setPeopleList(othersAddressBook);
            peopleResponse.setMyInterests(myInterests);
            peopleResponse.setOtherInterests(othersInterests);
            peopleResponse.setSuccess(true);
            peopleResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            peopleResponse.setSuccess(false);
            peopleResponse.setError(ex.getMessage());
        }
        return peopleResponse;
    }

    @RequestMapping(value = "/get-person", method = RequestMethod.POST)
    public PeopleResponse getPerson(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        PeopleResponse peopleResponse = new PeopleResponse();

        try {
            peopleResponse.setMyUserInfo(userInfo);
            Optional<User> optionalUser = userService.findById(formData.get("userId"));
            if (optionalUser.isPresent()) {
                UserInfo otherUserInfo = new UserInfo(optionalUser.get());
                peopleResponse.setOtherUserInfo(otherUserInfo);
            }
            Optional<AddressBook> optionalAddressBook = addressBookService.findByUserId(userInfo.getId());
            optionalAddressBook.ifPresent(peopleResponse::setMyAddressBook);
            if (!optionalAddressBook.isPresent()) {
                AddressBook addressBook = new AddressBook();
                addressBook.setEmail1(userInfo.getUserName());
                addressBook.setDisplayName(userInfo.getFullName());
                addressBook.setUserId(userInfo.getId());
                peopleResponse.setMyAddressBook(addressBookService.save(addressBook));
            }
            optionalAddressBook = addressBookService.findByUserId(formData.get("userId"));
            optionalAddressBook.ifPresent(peopleResponse::setOthersAddressBook);
            List<UserInterestsDTO> myInterests = userInterestsService.findByUserId(userInfo.getId())
                    .stream().map(UserInterestsDTO::new).collect(Collectors.toList()).stream()
                    .filter(uidto -> uidto.getIsInterested() == 1).collect(Collectors.toList());
            List<UserInterestsDTO> otherInterests = userInterestsService.findByUserId(formData.get("userId"))
                    .stream().map(UserInterestsDTO::new).collect(Collectors.toList()).stream()
                    .filter(uidto -> uidto.getIsInterested() == 1).collect(Collectors.toList());
            List<InterestCategory> interestCategoryList =
                    interestCategoryService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0);
            List<Interest> interestList =
                    interestService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0);
            for (int i = 0; i < myInterests.size(); i++) {
                int finalI = i;
                Optional<Interest> optionalInterest = interestList.stream()
                        .filter(
                                interest -> interest.getId()
                                        .equals(
                                                myInterests
                                                        .get(finalI)
                                                        .getInterestId()
                                        )
                        )
                        .findFirst();

                if (optionalInterest.isPresent()) {
                    myInterests.get(i).setInterestName(optionalInterest.get().getName());
                    Optional<InterestCategory> optionalInterestCategory
                            = interestCategoryList.stream().filter(
                            ic -> {
                                return ic.getId()
                                        .equals(
                                                optionalInterest.get()
                                                        .getInterestCategoryId()
                                        );
                            }
                    ).findFirst();
                    if (optionalInterestCategory.isPresent()) {
                        myInterests.get(i).setInterestCategoryName(optionalInterestCategory.get().getName());
                        myInterests.get(i).setInterestCategoryId(optionalInterestCategory.get().getId());
                        myInterests.get(i).setInterestCategory(optionalInterestCategory.get());
                    }
                }
            }


            for (int i = 0; i < otherInterests.size(); i++) {
                int finalI = i;
                Optional<Interest> optionalInterest = interestList.stream()
                        .filter(
                                interest -> interest.getId()
                                        .equals(
                                                otherInterests
                                                        .get(finalI)
                                                        .getInterestId()
                                        )
                        )
                        .findFirst();

                if (optionalInterest.isPresent()) {
                    otherInterests.get(i).setInterestName(optionalInterest.get().getName());
                    Optional<InterestCategory> optionalInterestCategory
                            = interestCategoryList.stream().filter(
                            ic -> {
                                return ic.getId()
                                        .equals(
                                                optionalInterest.get()
                                                        .getInterestCategoryId()
                                        );
                            }
                    ).findFirst();
                    if (optionalInterestCategory.isPresent()) {
                        otherInterests.get(i).setInterestCategoryName(optionalInterestCategory.get().getName());
                        otherInterests.get(i).setInterestCategoryId(optionalInterestCategory.get().getId());
                        otherInterests.get(i).setInterestCategory(optionalInterestCategory.get());

                    }
                }
            }

            List<UserInterestsDTO> commonInterests = otherInterests.stream().distinct()
                    .filter(myInterests::contains).collect(Collectors.toList());
            List<UserInterestsDTO> unCommonInterests = myInterests.stream().distinct()
                    .filter(userInterestsDTO -> !otherInterests.contains(userInterestsDTO)).collect(Collectors.toList());
            Map<InterestCategory, List<UserInterestsDTO>> myInterestResult =
                    myInterests.stream().collect(Collectors.groupingBy(UserInterestsDTO::getInterestCategory, Collectors.toList()));
            Map<InterestCategory, List<UserInterestsDTO>> othersInterestResult =
                    otherInterests.stream().collect(Collectors.groupingBy(UserInterestsDTO::getInterestCategory, Collectors.toList()));

            Map<InterestCategory, List<UserInterestsDTO>> commonInterestsResult =
                    commonInterests.stream().collect(Collectors.groupingBy(UserInterestsDTO::getInterestCategory, Collectors.toList()));
            Map<InterestCategory, List<UserInterestsDTO>> unCommonInterestResult =
                    unCommonInterests.stream().collect(Collectors.groupingBy(UserInterestsDTO::getInterestCategory, Collectors.toList()));


            List<UserInterestCategoryDTO> commonUserInterestCategoryDTOList = new ArrayList<>();
            commonInterestsResult.forEach((interestCategory, userInterestsDTOS) -> {
                UserInterestCategoryDTO userInterestCategoryDTO = new UserInterestCategoryDTO();
                userInterestCategoryDTO.setInterestCategory(new InterestCategoryDTO(interestCategory));
                userInterestCategoryDTO.setInterestList(userInterestsDTOS);
                commonUserInterestCategoryDTOList.add(userInterestCategoryDTO);
            });

            List<UserInterestCategoryDTO> myInterestCategoryDTOList = new ArrayList<>();
            myInterestResult.forEach((interestCategory, userInterestsDTOS) -> {
                UserInterestCategoryDTO userInterestCategoryDTO = new UserInterestCategoryDTO();
                userInterestCategoryDTO.setInterestCategory(new InterestCategoryDTO(interestCategory));
                userInterestCategoryDTO.setInterestList(userInterestsDTOS);
                myInterestCategoryDTOList.add(userInterestCategoryDTO);
            });

            List<UserInterestCategoryDTO> otherInterestCategoryDTOList = new ArrayList<>();
            othersInterestResult.forEach((interestCategory, userInterestsDTOS) -> {
                UserInterestCategoryDTO userInterestCategoryDTO = new UserInterestCategoryDTO();
                userInterestCategoryDTO.setInterestCategory(new InterestCategoryDTO(interestCategory));
                userInterestCategoryDTO.setInterestList(userInterestsDTOS);
                myInterestCategoryDTOList.add(userInterestCategoryDTO);
            });

            List<UserInterestCategoryDTO> unCommonInterestCategoryDTOList = new ArrayList<>();
            unCommonInterestResult.forEach((interestCategory, userInterestsDTOS) -> {
                UserInterestCategoryDTO userInterestCategoryDTO = new UserInterestCategoryDTO();
                userInterestCategoryDTO.setInterestCategory(new InterestCategoryDTO(interestCategory));
                userInterestCategoryDTO.setInterestList(userInterestsDTOS);
                unCommonInterestCategoryDTOList.add(userInterestCategoryDTO);
            });

            peopleResponse.setCommonInterest(commonUserInterestCategoryDTOList);
            peopleResponse.setUnCommonInterest(unCommonInterestCategoryDTOList);
            peopleResponse.setMyInterestMap(myInterestCategoryDTOList);
            peopleResponse.setOtherInterestMap(otherInterestCategoryDTOList);
            peopleResponse.setMyInterests(myInterests);
            peopleResponse.setOtherInterests(otherInterests);
            peopleResponse.setPeopleList(new ArrayList<>());
            peopleResponse.setSuccess(true);
            peopleResponse.setError("");
        } catch (
                Exception ex) {
            logger.error(ex.getMessage(), ex);
            peopleResponse.setSuccess(false);
            peopleResponse.setError(ex.getMessage());
        }
        return peopleResponse;
    }


    @RequestMapping(value = "/add-remove-favourite", method = RequestMethod.POST)
    public PeopleResponse addRemoveFavourite(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        PeopleResponse peopleResponse = new PeopleResponse();
        try {
            String userId = formData.get("userId");
            int isFavourite = Integer.parseInt(formData.get("isFavourite"));
            Optional<FavouritePeople> optionalFavouritePeople = favouritePeopleService.findByUserIdAndOtherUserId(userInfo.getId(), userId);
            FavouritePeople favouritePeople;
            if (!optionalFavouritePeople.isPresent()) {
                favouritePeople = new FavouritePeople();
                favouritePeople.setClientId(userInfo.getClient().getClientId());
                favouritePeople.setOrgId(userInfo.getDefaultOrganization().getId());
                favouritePeople.setUserId(userInfo.getId());
                favouritePeople.setOtherUserId(userId);
            } else {
                favouritePeople = optionalFavouritePeople.get();
            }
            favouritePeople.setIsFavourite(isFavourite);
            favouritePeopleService.save(favouritePeople);
            peopleResponse.setSuccess(true);
            peopleResponse.setError("");

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            peopleResponse.setSuccess(false);
            peopleResponse.setError(ex.getMessage());
        }
        return peopleResponse;
    }

    private UserInterestsDTO setupInterestCategory() {
        return null;
    }


}
