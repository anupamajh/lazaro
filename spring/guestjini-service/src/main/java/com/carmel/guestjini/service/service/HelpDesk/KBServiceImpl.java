package com.carmel.guestjini.service.service.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.KB;
import com.carmel.guestjini.service.repository.HelpDesk.KBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KBServiceImpl implements KBService {

    @Autowired
    KBRepository kbRepository;

    @Override
    public KB save(KB kb) {
        return kbRepository.save(kb);
    }

    @Override
    public List<KB> findAllByClientIdAndIsDeletedAndTopicTitle(String clientId, int isDeleted, String topicTitle) {
        return kbRepository
                .findAllByClientIdAndIsDeletedAndTopicTitle(
                clientId,
                        isDeleted,
                        topicTitle
                );
    }

    @Override
    public List<KB> findAllByClientIdAndIsDeletedAndTopicTitleAndIdIsNot(String clientId, int isDeleted, String topicTitle, String id) {
        return kbRepository
                .findAllByClientIdAndIsDeletedAndTopicTitleAndIdIsNot(
                        clientId,
                        isDeleted,
                        topicTitle,
                        id
                );
    }

    @Override
    public Optional<KB> findById(String id) {
        return kbRepository.findById(id);
    }

    @Override
    public List<KB> findAllByClientIdAndIsDeleted(String clientId, int isDeleted) {
        return kbRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted);
    }

    @Override
    public Page<KB> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return kbRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public Page<KB> findAll(Specification<KB> textInAllColumns, Pageable pageable) {
        return kbRepository.findAll(textInAllColumns, pageable);
    }
}
