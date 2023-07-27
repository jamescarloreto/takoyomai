$(document).ready(function() {
	
    retrieveMenuForUsers();
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
                type: POST,
                dataType: dataJSON,
                data: JSON.stringify({
                    userInformationDetail: formData
                }),
                beforeSend: function() {
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

    $("#btnSignIn").on('click', function() {
        $.ajax({
            url: takoyomai + '/login?username=' + $("#username").val() + '&password=' + $("#password").val(),
            contentType: 'application/json',
            type: POST,
            beforeSend: function() {
                
                $("#signInLoading").attr('hidden', false)
            },
            success: function(data) {
                signInClearField();
                getUserDetails();
                retrieveMenuForUsers();
                window.location.reload(true);
            },
            error: function(jq,status,message) {
                if (jq.status == 406) {
                    displayMessage("#sign-in-message", "Email or Password not matched!", 5000, false);
                }
            },
            complete: function() {
                $("#signInLoading").attr('hidden', true)
            }
        });
    });

    $("#ddSignOut").on('click', function() {
        $.ajax({
            url: takoyomai + '/logout',
            type: GET,
            success: function(data) {
                window.location.reload(true);
            }
        });
    });

    $("#tableMenu tbody").on('click', 'button#btnCreateMenu', function() {

        var formData = new FormData();
        formData.append('file', $("#fileData")[0].files[0]);
        formData.append('name', $("#menuName").val());
        formData.append('description', $("#menuDesc").val());
        formData.append('price', $("#menuPrice").val());
        formData.append('type', $("#dishDropdown").val());

        console.log($("#fileData").val())
        console.log(formData)
        
        $.ajax({
            url: takoyomai + '/menu/create',
            type: POST,
            processData: false,
            contentType: false,
            enctype: 'multipart/form-data',
            data: formData,
            beforeSend: function() {
                //$("#signUpLoading").attr('hidden', false)
            },
            success: function() {

            }
        });
     });

     function retrieveMenuForUsers() {
        $.ajax({
            url: takoyomai + '/menu/retrieve',
            contentType: 'application/json',
            type: GET,
            success: function(data) {
                if (data.status === "success") {
                    if (data.object != null) {
                        fillMenusForUsers(data.object.menuDtos);
                    }
                }
            },
            error: function(jq,status,message) {
                console.log(message)
            },
        });
    }

    $("#tableMenu tbody").on('change', 'tr', function() {
        var index = $(this).find('input:checkbox')[0].checked;
        alert($(this).find('input:checkbox')[0].id)
        //console.log($(this).find('input:checkbox')[0].checked)
        $.ajax({
            url: takoyomai + '/menu/menufortoday/' + $(this).find('input:checkbox')[0].id,
            contentType: 'application/json',
            type: ($(this).find('input:checkbox')[0].checked ? POST : DELETE),
            success: function(data) {
            }
        });
    });

    $("#tableMenu tbody").on('click', 'button#btnShowFieldMenu', function() {
    var index = $(this).index();

        hideFields(false);
    });

    $("#tableMenu tbody").on('click', 'button#btnCloseCreateMenu', function() {
    var index = $(this).index();

        hideFields(true);
    });

    $("#btnSignInModal").on('click', function() {
        $("#signInModal").modal('toggle');
    });
    
    $("#btnSignInClose").on('click', function() {
        $("#signInModal").modal('toggle');
    });

    $("#linkSignInClose").on('click', function() {
        $("#signInModal").modal('toggle');
    });
    
    $("#btnProfileModal").on('click', function() {
        $("#profileModal").modal('toggle');
    });

    $("#btnProfileClose").on('click', function() {
        $("#profileModal").modal('toggle');
    });

    function hideFields(isHide) {
        $("#columnDish input").attr('hidden', isHide);
        $("#columnPrice input").attr('hidden', isHide);
        $("#columnDesc input").attr('hidden', isHide);
        $("#columnName input").attr('hidden', isHide);
        $("#columnPicture input").attr('hidden', isHide);
        $("#columnDish div").attr('hidden', isHide);
        $("#columnButton div").attr('hidden', isHide);
        // $("#columnButton button#btnCloseCreateMenu").attr('hidden', isHide);
        $("#btnShowFieldMenu").attr('hidden', !isHide)
        
    }

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

    function signInClearField() {

        $("#signInModal").modal('hide');
        $("#signInUsername").val("");
        $("#signInPw").val("");
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