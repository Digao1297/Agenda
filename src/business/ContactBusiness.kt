package business

import entity.ContactEntity
import repository.ContactRepository
import validation.validate
import validation.validateDelete

class ContactBusiness {
    fun save(name: String, phone: String) {
        validate(name, phone)
        val contact: ContactEntity = ContactEntity(name,phone)
        ContactRepository().save(contact)
    }

    fun delete(name: String, phone: String) {
        validateDelete(name, phone)
    }
}