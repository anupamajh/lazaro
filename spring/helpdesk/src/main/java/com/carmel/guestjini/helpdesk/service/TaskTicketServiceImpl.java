package com.carmel.guestjini.helpdesk.service;

import com.carmel.guestjini.helpdesk.model.TaskTicket;
import com.carmel.guestjini.helpdesk.repository.TaskTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskTicketServiceImpl implements TaskTicketService{

    @Autowired
    TaskTicketRepository taskTicketRepository;

    @Override
    public TaskTicket save(TaskTicket taskTicket) {
        return taskTicketRepository.save(taskTicket);
    }

    @Override
    public Optional<TaskTicket> findById(String id) {
        return taskTicketRepository.findById(id);
    }

    @Override
    public List<TaskTicket> findAllByIsDeletedAndRequesterId(int isDeleted, String requesterId) {
        return taskTicketRepository.findAllByIsDeletedAndRequesterId(isDeleted, requesterId);
    }

    @Override
    public Page<TaskTicket> findAll(Specification<TaskTicket> textInAllColumns, Pageable pageable) {
        return taskTicketRepository.findAll(textInAllColumns, pageable);
    }

    @Override
    public Page<TaskTicket> findAllByIsDeletedAndRequesterId(int isDeleted, String id, Pageable pageable) {
        return taskTicketRepository.findAllByIsDeletedAndRequesterId(isDeleted, id, pageable);
    }
}
