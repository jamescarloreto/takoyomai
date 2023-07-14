var takoyomai = 'http://localhost:8080';
var GET = "get";
var POST = "POST";
var DELETE = "delete";
var PUT = "put";
var dataJSON = "json";

function fieldIsNullOrEmpty(field) {
    if ($(field).val() === null || $(field).val() === "") {
        return true
    } else {
        return false;
    }
}

function isNullOrEmpty(strng) {
    if (strng === null || strng === "") {
        return true
    } else {
        return false;
    }
}

function displayMessage(field, message, duration, isSuccess) {
    $(field).attr('hidden', false)

    if (!isNullOrEmpty(message)) {
        $(field).text(message)
    }

    if (isSuccess) {
        $(field).css('color', 'green');
    } else {
        $(field).css('color', 'red');
    }

    if (isNullOrEmpty(duration)) {
        duration = 2500;
    }

    setTimeout(function() { 
        $(field).attr('hidden', true)
    }, duration);
}

function isEmptyFieldDisplayErrorMsg(field, errorField, message) {
    if (fieldIsNullOrEmpty(field)) {
        displayError(errorField, message)

        return true;
    } else {
        return false;
    }
}