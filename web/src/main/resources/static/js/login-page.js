  function loginpage(){
             var email = document.getElementById("inputError").value;
            var password = document.getElementById("passwordError").value;
            if (email == "guestjini@gmail.com" && password == "guestjini")
             {
               location.replace("/community/community-profile");
            }
//<!--           else if(email != "guestjini@gmail.com" && password != "guestjini") {-->
//<!--                document.getElementById("invalidLoginCredit").style.visibility = "visible";-->
//<!--                }-->
           else{
            document.getElementById("passwordMask").style.bottom = "35%"
           document.getElementById("passwordUnmask").style.bottom = "35%";
//             document.getElementById("passwordMask").style.bottom = "38%";
            document.getElementById("invalidLoginCredit").style.visibility = "visible";
               document.getElementById("FieldEmailError").style.display = "block";
              document.getElementById("passwordUnmask").style.display = "block";
              document.getElementById("FieldPasswordError").style.display = "block";
              document.getElementById("inputError").style.border = "1px solid #BF574A";
            document.getElementById("passwordError").style.border = "1px solid #BF574A";
              }
            }

    function forgotPassword() {
        window.location = src = "/ForgotPassword";
    }



    function getNewAccount() {
        window.location = src = "/AccountLanding";
    }

    function type() { }

    function PasswordSuccess() {
        window.location = src = "/forgotpassword-otp";
    }

   function reset(){
                var email = document.getElementById("inputError").value;
               if (email == "guestjini@gmail.com")
                {
                  document.getElementById("passwordSuccess").style.display = "block";
               }
              else{
                  document.getElementById("FieldEmailError").style.display = "block";
                    document.getElementById("invalidLoginCredit").style.visibility = "visible";
                  document.getElementById("inputError").style.border = "1px solid #BF574A";
                 }
               }

    function SubmitlandingPage(){
                 var email = document.getElementById("inputError").value;
                var password = document.getElementById("passwordError").value;
                if (email == "guestjini@gmail.com" && password == "9912345678")
                 {
                   document.getElementById("appsuccess").style.display = "block";
                   document.getElementById("landingPage").style.display = "none";
                }
               else{
                   document.getElementById("invalidLoginCredit").style.visibility = "visible";
                   document.getElementById("FieldEmailError").style.display = "block";
                   document.getElementById("FieldPasswordError").style.display = "block";

                   document.getElementById("inputError").style.border = "1px solid #BF574A";
                  document.getElementById("passwordError").style.border = "1px solid #BF574A";
                 }
               }

    function successMessage() {
        window.location = src = "/AccountActivation";
    }

    function submitOTP() {
                var box1 = document.getElementById("inputErr1").value;
                var box2 = document.getElementById("inputErr2").value;
                var box3 = document.getElementById("inputErr3").value;
                var box4 = document.getElementById("inputErr4").value;
                if (box1 =="1" && box2=="2" && box3=="3" && box4=="4" )
                {
                document.getElementById("otpSuccessMessage").style.display = "block";
                 document.getElementById("otp").style.display = "none";
                }
                else{
                document.getElementById("enter-wrong-otp").style.visibility = "visible";
                 document.getElementById("inputErr1").style.border="1px solid #BF574A";
                document.getElementById("inputErr2").style.border="1px solid #BF574A";
                 document.getElementById("inputErr3").style.border="1px solid #BF574A";
                document.getElementById("inputErr4").style.border="1px solid #BF574A";
                document.getElementById("success").style.display="none";
                 document.getElementById("wrong-otp").style.display="block"; }
                }

       function ToggleConfirmPassword() {
                        var temp = document.getElementById("passwordError");
                        if (temp.type === "password") {
                            temp.type = "text";
                            document.getElementById("passwordMask").style.display = "block";
//                            document.getElementById("passwordUnmask").style.bottom = "38%";
document.getElementById("passwordUnmask").style.display = "none";
                            document.getElementById("passwordMask").style.bottom = "37%";
                        }
                        else {
                            temp.type = "password";
                            document.getElementById("passwordMask").style.display = "block";
  document.getElementById("passwordMask").style.bottom = "38%";
  document.getElementById("passwordUnmask").style.display = "none";
                        }

                    }

 function mask() {
  var temp = document.getElementById("passwordError");
                         if (temp.type === "password") {
                             temp.type = "text";
                             document.getElementById("passwordMask").style.display = "none";
                               document.getElementById("passwordUnmask").style.display = "block";

                         }
                         else {
                             temp.type = "password";
                             document.getElementById("passwordMask").style.display = "none";
                              document.getElementById("passwordUnmask").style.display = "block";
                               document.getElementById("passwordMask").style.bottom = "38%"
                         }
//        document.getElementById("passwordError").style.display = "block";
//        document.getElementById("inputError").style.display = "block";
//        document.getElementById("hidePassword").style.display = "none";
//        document.getElementById("hideEmail").style.display = "none";
//        document.getElementById("passwordUnmask").style.display = "block";
//        document.getElementById("passwordMask").style.display = "none";
//        document.getElementById("passwordUnmask").style.bottom = "38%";
//        document.getElementById("passwordMask").style.bottom = "46%";
    }
