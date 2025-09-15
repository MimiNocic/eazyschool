package com.eng.it.eazybytes.eazyschool.service;

import com.eng.it.eazybytes.eazyschool.constants.EazySchoolConstants;
import com.eng.it.eazybytes.eazyschool.model.Contact;
import com.eng.it.eazybytes.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
//@RequestScope - 1 request 1 bean created
//@SessionScope - 1 session 1 bean created if try another browser new session will be created
//@ApplicationScope  only 1 bean for 1 app is created for every user and browser
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    private int counter = 0;

    public ContactService(){
        System.out.println("Contact Service Bean initialized");
    }

    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(EazySchoolConstants.OPEN);
        //contact.setCreatedBy(EazySchoolConstants.ANONYMUS);
        //contact.setCreatedAt(LocalDateTime.now());
        //int result = contactRepository.saveContactMsg(contact);

        //if(result > 0) {
        //    isSaved = true;
        //}
        //log.info(contact.toString());

        Contact savedContact = contactRepository.save(contact);
        if(null != savedContact && savedContact.getContactId()>0){
            isSaved=true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgs = contactRepository.findByStatus(EazySchoolConstants.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactId) { //, String updatedBy){
        boolean isUpdated = false;
        /*int result = contactRepository.updateMsgStatus(contactId,EazySchoolConstants.CLOSE, updatedBy);
        if(result>0) {
            isUpdated = true;
        }*/
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(EazySchoolConstants.CLOSE);
            //contact1.setUpdatedBy(updatedBy);
            //contact1.setUpdatedAt(LocalDateTime.now());
        });
        Contact updatedContact = contactRepository.save(contact.get());
        if(null != updatedContact && updatedContact.getUpdatedBy()!=null){
            isUpdated=true;
        }
        return isUpdated;
    }

    /*public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }*/
}
