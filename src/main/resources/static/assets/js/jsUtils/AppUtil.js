//
var takoyomai = "http://localhost:8080";
var GET = "get";
var POST = "POST";
var DELETE = "delete";
var PUT = "put";
var dataJSON = "json";

function fieldIsNullOrEmpty(field) {
    if ($(field).val() == null || $(field).val() == "") {
        return true
    } else {
        return false;
    }
}

function displayError(field, message) {
    $(field).attr('hidden', false)

    if (!fieldIsNullOrEmpty(message)) {
        $(field).attr('html', message)
    }

    setTimeout(function() { 
        $(field).fadeOut(); 
    }, 2500);
}

function isEmptyFieldDisplayErrorMsg(field, errorField, message) {
    if (fieldIsNullOrEmpty(field)) {
        displayError(errorField, message)

        return true;
    } else {
        return false;
    }
}