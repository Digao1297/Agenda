package business

import entity.ContactEntity
import repository.ContactRepository
import validation.validate
import validation.validateDelete

class ContactBusiness {
    fun save(name: String, phone: String) {
        validate(name, phone)
        val contact: ContactEntity = ContactEntity(name,phone)
        ContactRepository.save(contact)
    }

    fun delete(name: String, phone: String) {
        validateDelete(name, phone)

        val contact = ContactEntity(name,phone)
        ContactRepository.delete(contact)
    }
    fun getList():List<ContactEntity>{
        return ContactRepository.getList()
    }
    fun getContactCount(): String{
        //return this.getList().size.toString()
        val list = getList()

        val _return  = when {
            list.isEmpty() -> "0 contacts"
            list.size == 1 -> "1 contact"
            else -> "${list.size} contacts"
        }
        return _return
    }
}