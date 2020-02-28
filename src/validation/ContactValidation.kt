package validation

import java.lang.Exception

fun validate(name: String, phone: String) {
    if (name.equals("")) {
        throw Exception("Nome é Obrigatorio!")
    }
    if (phone.equals("")) {
        throw Exception("Telefone é Obrigatorio!")
    }
}

fun validateDelete(name: String, phone: String) {
    if (name.equals("") || phone.equals("")) {
        throw Exception("É necessario selecionar um usuario antes de remover.")
    }

}