package repository

import entity.ContactEntity

class ContactRepository {

    companion object {
        private val contacts = mutableListOf<ContactEntity>()

        fun save(contact: ContactEntity) {
            contacts.add(contact)
        }

        fun delete(contact: ContactEntity) {

            for (item in contacts.withIndex()) {
                if (item.value.name.equals(contact.name, true) || item.value.phone.equals(contact.phone, true)) {
                    contacts.removeAt(item.index)
                    break;
                }
            }
        }

        fun getList(): List<ContactEntity> {
            return this.contacts
        }
    }


}