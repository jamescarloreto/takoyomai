$(document).ready(function($) {
	
    var confirmedPassword = false;
	$("#btnEye").on('mousedown', function() {
        $("#eyecon").attr('class', 'bi bi-eye');
        $("#password1").attr('type', 'text');
        $("#password2").attr('type', 'text');
    });

    $("#btnEye").on('mouseup', function() {
        $("#eyecon").attr('class', 'bi bi-eye-slash');
        $("#password1").attr('type', 'password');
        $("#password2").attr('type', 'password');
    });

    $("#password1").on('blur', function() {
        if (!fieldIsNullOrEmpty(this) && !fieldIsNullOrEmpty("#password2")) {
            if ($("#password1").val() != $("#password2").val()) {
                displayError("#passValidate", null);

                confirmedPassword = false;
            } else {
                confirmedPassword = true;
            }
        }
    });

    $("#password2").on('blur', function() {
        if (!fieldIsNullOrEmpty(this) && !fieldIsNullOrEmpty("#password1")) {
            if ($("#password1").val() != $("#password2").val()) {
                displayError("#passValidate", null);

                confirmedPassword = false;
            } else {
                confirmedPassword = true;
            }
        }
    });

    $("#btnSignUp").on('click', function() {

        var formData = {
            firstName: $("#firstName").val(),
            lastName: $("#lastName").val(),
            address: $("#address").val(),
            email: $("#signUpEmail").val(),
            phoneNo: $("#phoneNo").val(),
            password: $("#password1").val()
        };
        if (!signUpFieldValidation()) {
            $.ajax({
                contentType: 'application/json',
                type: "POST",
                dataType: dataJSON,
                data: JSON.stringify(formData),
                beforeSend: function() {
                    //alert("");
                },
                success: function(data) {
                    console.log(data)
                },
                error: function(jq,status,message) {
                    console.log(jq)
                    console.log(status)
                    console.log(message)
                }
            });
        }
    });

    function signUpFieldValidation() {
        var checkedAll = false;
        
        if (isEmptyFieldDisplayErrorMsg("#firstName", "#firstNameValidate", "Cannot be empty!")) {
            checkedAll = true;
        }
        
        if (isEmptyFieldDisplayErrorMsg("#lastName", "#lastNameValidate", "Can not be empty!")) {
            checkedAll = true;
        }

        if (isEmptyFieldDisplayErrorMsg("#address", "#addressValidate", "Can not be empty!")) {
            checkedAll = true;
        }

        if (isEmptyFieldDisplayErrorMsg("#signUpEmail", "#emailValidate", "Can not be empty!")) {
            checkedAll = true;
        }

        if (isEmptyFieldDisplayErrorMsg("#phoneNo", "#phoneValidate", "Can not be empty!")) {
            checkedAll = true;
        }

        if (!confirmedPassword) {
            checkedAll = true;
        }

        return checkedAll;
    }

});