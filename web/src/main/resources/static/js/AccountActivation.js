   function SubmitAccountActivation() {
        var email = document.getElementById("inputError").value;
        var password = document.getElementById("passwordError").value;
        if (email == "9912345678" && password == "9912345678") {
            document.getElementById("congrats").style.display = "block";
            document.getElementById("Activation").style.display = "none";
        }
        else {
            document.getElementById("passwordUnmask2").style.bottom = "21%";
//             document.getElementById("passwordMask2").style.bottom = "21%";
            document.getElementById("FieldEmailError").style.display = "block";
            document.getElementById("FieldPasswordError").style.display = "block";
            document.getElementById("inputError").style.border = "1px solid #BF574A";
            document.getElementById("passwordError").style.border = "1px solid #BF574A";
        }
    }



 function mask1() {
  var temp = document.getElementById("inputError");
                         if (temp.type === "password") {
                             temp.type = "text";
                             document.getElementById("passwordMask1").style.display = "none";
                               document.getElementById("passwordUnmask1").style.display = "block";
                         }

                         else {
                             temp.type = "password";
                             document.getElementById("passwordMask1").style.display = "none";
                              document.getElementById("passwordUnmask1").style.display = "block";
                               document.getElementById("passwordMask1").style.bottom = "38%"
                         }

    }
    function mask2() {
      var temp = document.getElementById("passwordError");
                             if (temp.type === "password") {
                                 temp.type = "text";
                                 document.getElementById("passwordMask2").style.display = "none";
                                   document.getElementById("passwordUnmask2").style.display = "block";
                             }

                             else {
                                 temp.type = "password";
                                 document.getElementById("passwordMask2").style.display = "none";
                                  document.getElementById("passwordUnmask2").style.display = "block";
                                   document.getElementById("passwordMask2").style.bottom = "38%"
                             }

        }

    function ToggleConfirmPassword() {
        var temp = document.getElementById("passwordError");
          if (temp.type === "password") {
                                           temp.type = "text";
                                           document.getElementById("passwordMask2").style.display = "block";
                                           document.getElementById("passwordUnmask2").style.display = "none";
                                           document.getElementById("passwordMask2").style.bottom = "30%";
                                       }
                                       else {
                                           temp.type = "password";
                                           document.getElementById("passwordMask2").style.display = "block";
                 document.getElementById("passwordMask2").style.bottom = "38%";
                 document.getElementById("passwordUnmask2").style.display = "none";
                                       }
    }

    function ToggleNewPassword() {
        var temp = document.getElementById("inputError");
                               if (temp.type === "password") {
                                   temp.type = "text";
                                   document.getElementById("passwordMask1").style.display = "block";
                                   document.getElementById("passwordUnmask1").style.display = "none";
                                   document.getElementById("passwordMask1").style.bottom = "42%";
                               }
                               else {
                                   temp.type = "password";
                                   document.getElementById("passwordMask1").style.display = "block";
         document.getElementById("passwordMask1").style.bottom = "38%";
         document.getElementById("passwordUnmask1").style.display = "none";
                               }

    }


    function AccountActivationSubmit() {
        document.getElementById("Activation").style.display = "none";
        document.getElementById("congrats").style.display = "block";
        document.getElementById("SubmitActivation").style.display = "none";
    }

    function LoginActivate() {
        location.replace("/");
    }

    function LoginWeb() {
        document.getElementById("link-expire").style.display = "block";
        document.getElementById("congrats").style.display = "none";
    }