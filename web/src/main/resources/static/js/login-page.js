 function Login() {
            document.getElementById("FieldEmailError").style.display = "block";
            document.getElementById("hidePassword").style.display = "none";
             document.getElementById("hideEmail").style.display = "none";
            document.getElementById("FieldPasswordError").style.display = "block";
            document.getElementById("passwordMask").style.display = "none";
            document.getElementById("inputError").style.border = "1px solid #BF574A";
            document.getElementById("passwordError").style.border = "1px solid #BF574A";
            document.getElementById("passwordUnmask").style.display = "none";
        }
        function unmask() {
             document.getElementById("passwordUnmask").style.display = "none";
            document.getElementById("passwordMask").style.display = "block";
             document.getElementById("hidePassword").style.display = "block";
              document.getElementById("hideEmail").style.display = "block";
              document.getElementById("passwordError").style.display="none";
               document.getElementById("inputError").style.display="none";
            document.getElementById("passwordMask").style.bottom = "40%";
            document.getElementById("passwordUnmask").style.bottom = "46%";

        }
         function forgotPassword() {
                            window.location = src = "/ForgotPassword";
                        }

         function mask(){
          document.getElementById("passwordError").style.display="block";
          document.getElementById("inputError").style.display="block";
          document.getElementById("hidePassword").style.display = "none";
           document.getElementById("hideEmail").style.display = "none";
           document.getElementById("passwordUnmask").style.display = "block";
            document.getElementById("passwordMask").style.display = "none";
             document.getElementById("passwordUnmask").style.bottom = "40%";
             document.getElementById("passwordMask").style.bottom = "46%";
        }
     function getNewAccount() {
                            window.location = src = "/AccountLanding";
                        }
        function type() {
        }
        function PasswordSuccess() {
                                    window.location = src = "/forgotpassword-otp";
                                }

               function ResetPassword() {
                       document.getElementById("passwordSuccess").style.display = "block";
                        document.getElementById("forgotPassword").style.display = "none";
                   }

                    function landingPageSubmit(){
                           document.getElementById("appsuccess").style.display = "block";
                               document.getElementById("landingPage").style.display = "none";
                           }

                             function successMessage() {
                                               window.location = src = "/AccountActivation";

                                          }

                  function AccountActivationSubmit() {
                             document.getElementById("Activation").style.display = "none";
                             document.getElementById("congrats").style.display = "block";
                              document.getElementById("SubmitActivation").style.display = "none";
                         }
                 function LoginWeb(){
                 document.getElementById("link-expire").style.display = "block";
                 document.getElementById("congrats").style.display = "none";
                 }

                 function submitOtp(){
                         document.getElementById("otpSuccessMessage").style.display = "block";
                           document.getElementById("otp").style.display = "none";
                                    }

//function submitOtp() {
// document.getElementById("inputErr1").style.border="1px solid #BF574A";
// document.getElementById("inputErr2").style.border="1px solid #BF574A";
// document.getElementById("inputErr3").style.border="1px solid #BF574A";
// document.getElementById("inputErr4").style.border="1px solid #BF574A";
// document.getElementById("success").style.display="none";
// document.getElementById("wrong-otp").style.display="block";
//
//        }

     function LoginActivate(){
               location.replace("/");
            }