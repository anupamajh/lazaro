package com.carmel.common.dbservice.Base.Client.Service;

import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.Client.Repository.ClientRepository;
import com.carmel.common.dbservice.Base.Client.Responce.ClientResponse;
import com.carmel.common.dbservice.Base.Role.Model.Role;
import com.carmel.common.dbservice.Base.Role.Service.RoleService;
import com.carmel.common.dbservice.Base.User.Model.User;
import com.carmel.common.dbservice.Base.User.Service.UserService;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.ClientDetails;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.services.ClientDetailService;
import com.carmel.common.dbservice.services.ClientDetailServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    private Logger logger = LoggerFactory.getLogger(ClientDetailServiceImpl.class);


    private ObjectMapper objectMapper = new ObjectMapper();
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

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> findById(String clientId) {
        return clientRepository.findById(clientId);
    }

    @Override
    public ClientResponse register(Client client) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();

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
            throw ex;
        }
        logger.trace("Exiting");
        return clientResponse;
    }

    @Override
    public ClientResponse getAll() throws Exception {
        logger.trace("Entering");
        ClientResponse ClientsResponse = new ClientResponse();
        try {
            ClientsResponse.setClientList(clientService.findAll());
            ClientsResponse.setSuccess(true);
            ClientsResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
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