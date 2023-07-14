$(document).ready(function() {
	
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
                displayMessage("#passValidate", null);

                confirmedPassword = false;
            } else {
                confirmedPassword = true;
            }
        }
    });

    $("#password2").on('blur', function() {
        if (!fieldIsNullOrEmpty(this) && !fieldIsNullOrEmpty("#password1")) {
            if ($("#password1").val() != $("#password2").val()) {
                displayMessage("#passValidate", null);

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
                url: takoyomai + '/user/create',
                contentType: 'application/json',
                type: "POST",
                dataType: dataJSON,
                data: JSON.stringify({
                    fromAdmin: false,
                    userInformationDetail: formData
                }),
                beforeSend: function() {
                    //alert("");
                    $("#signUpLoading").attr('hidden', false)
                },
                success: function(data) {
                    displayMessage("#sign-up-message", "Registered Successfully!", 5000, true);
                    signUpClearField();
                },
                error: function(jq,status,message) {
                    if (jq.status == 403) {
                        displayMessage("#sign-up-message", "Email already exist!", 5000, false);
                    }
                },
                complete: function() {
                    $("#signUpLoading").attr('hidden', true)
                }
            });
        }
    });

    $("#btnSignInModal").on('click', function() {
        $("#signInModal").modal('show');
    });
    
    $("#btnSignInClose").on('click', function() {
        $("#signInModal").modal('hide');
    });

    $("#linkSignInClose").on('click', function() {
        $("#signInModal").modal('hide');
    });

    function signUpClearField() {

        confirmedPassword = false;
        $("#firstName").val("");
        $("#lastName").val("");
        $("#address").val("");
        $("#signUpEmail").val("");
        $("#phoneNo").val("");
        $("#password1").val("");
        $("#password2").val("");
    }

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