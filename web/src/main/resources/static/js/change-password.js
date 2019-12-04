 function ToggleCurrentPassword() {
        var temp = document.getElementById("newEmail1");
        if (temp.type === "password") {
            temp.type = "text";
        } else {
            temp.type = "password";
        }
    }

    function ToggleNewPassword() {
        var temp = document.getElementById("newEmail2");
        if (temp.type === "password") {
            temp.type = "text";
        } else {
            temp.type = "password";
        }
    }

    function ToggleConfirmPassword() {
        var temp = document.getElementById("newEmail3");
        if (temp.type === "password") {
            temp.type = "text";
        } else {
            temp.type = "password";
        }
    }

    function ChangePasswordSuccess() {
        location.replace("/change-password/change-password");
    }


    function changePasswordButton() {
        var password = document.getElementById("newEmail1").value;
        var password = document.getElementById("newEmail2").value;
        var password = document.getElementById("newEmail3").value;

        if (password == "guestjini") {
            document.getElementById("change-group").style.display = "block";
        } else {
            document.getElementById("FieldEmailError1").style.display = "block";
            document.getElementById("FieldEmailError2").style.display = "block";
            document.getElementById("FieldEmailError3").style.display = "block";
            document.getElementById("inputError1").style.border = "1px solid #BF574A";
            document.getElementById("inputError2").style.border = "1px solid #BF574A";
            document.getElementById("inputError3").style.border = "1px solid #BF574A";
            document.getElementById("exit-group").style.display = "block";
        }
    }