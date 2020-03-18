package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.*;
import com.carmel.common.dbservice.response.ClientResponse;
import com.carmel.common.dbservice.response.ClientsResponse;
import com.carmel.common.dbservice.services.ClientDetailService;
import com.carmel.common.dbservice.services.ClientService;
import com.carmel.common.dbservice.services.RoleService;
import com.carmel.common.dbservice.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    Logger logger = LoggerFactory.getLogger(ClientController.class);
    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    ClientService clientService;

    @Autowired
    ClientDetailService clientDetailService;

    @Autowired
    UserInformation userInformation;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ClientResponse register(@Valid @RequestBody Client client) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        ClientResponse clientResponse = new ClientResponse();
        if (client.getClientId() == null) {
            client.setClientId("");
        }
        try {
            if (checkDuplicate(client)) {
                clientResponse.setClient(client);
                clientResponse.setSuccess(false);
                clientResponse.setError("We found a client with same email id, Kindly contact administrator");
            } else {
                ClientDetails clientDetails = client.getClientDetails();
                clientDetails.setCreatedBy(userInfo.getId());
                clientDetails.setCreationTime(new Date());
                Client savedClient;
                if (client.getClientId().equals("")) {
                    client.setClientDetails(null);
                    client.setClientSecrete(passwordEncoder.encode(client.getClientSecrete()));
                    savedClient = clientService.save(client);
                    Role role = new Role();
                    role.setClient(savedClient);
                    role.setRoleName("Super Admin");
                    role.setDescription(clientDetails.getClientName());
                    role.setIsDeleted(0);
                    role.setCreatedBy(userInfo.getId());
                    role.setCreationTime(new Date());
                    role.setAppFeatures(clientDetails.getAppFeatures());
                    Role savedRole = roleService.save(role);
                    User user = new User(savedRole);
                    user.setClient(savedClient);
                    user.setFullName(clientDetails.getEmail());
                    user.setUserName(clientDetails.getEmail());
                    user.setPassword(savedClient.getClientSecrete());
                    user.setAccountStatus(2);
                    user.setIsDeleted(0);
                    User savedUser = userService.save(user);
                } else {
                    Optional<Client> optionalClient = clientService.findById(client.getClientId());
                    savedClient = optionalClient.get();
                    client.setClientSecrete(optionalClient.get().getClientSecrete());
                    clientDetails.setId(savedClient.getClientDetails().getId());
                }
                clientDetails.setClient(savedClient);
                savedClient.setClientDetails(clientDetails);
                clientResponse.setClient(clientService.save(savedClient));
                clientResponse.setSuccess(true);
                clientResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            clientResponse.setSuccess(false);
            clientResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return clientResponse;
    }


    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ClientsResponse getAll() {
        logger.trace("Entering");
        ClientsResponse ClientsResponse = new ClientsResponse();
        try {
            ClientsResponse.setClientList(clientService.findAll());
            ClientsResponse.setSuccess(true);
            ClientsResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ClientsResponse.setSuccess(false);
            ClientsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return ClientsResponse;
    }

    private boolean checkDuplicate(Client client) {
        List<ClientDetails> clientDetailsList;
        if (client.getClientId() == "") {
            clientDetailsList = clientDetailService.findAllByEmail(client.getClientDetails().getEmail());
        } else {
            clientDetailsList = clientDetailService.findAllByEmailAndClientIsNot(client.getClientDetails().getEmail(), client);
        }
        if (!clientDetailsList.isEmpty()) {
            return true;
        }
        return false;
    }


}
