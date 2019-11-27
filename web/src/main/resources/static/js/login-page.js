 function Login() {
            document.getElementById("fieldError1").style.display = "block";
            document.getElementById("hidePassword").style.display = "none";
             document.getElementById("hideEmail").style.display = "none";
            document.getElementById("fieldError2").style.display = "block";
            document.getElementById("eyeMask").style.display = "none";
            document.getElementById("inputErr").style.border = "1px solid #BF574A";
            document.getElementById("passwordError").style.border = "1px solid #BF574A";
            document.getElementById("eyeUnmask").style.display = "none";
        }
        function unmask() {
             document.getElementById("eyeUnmask").style.display = "none";
            document.getElementById("eyeMask").style.display = "block";
             document.getElementById("hidePassword").style.display = "block";
              document.getElementById("hideEmail").style.display = "block";
              document.getElementById("passwordError").style.display="none";
               document.getElementById("inputErr").style.display="none";
            document.getElementById("eyeMask").style.bottom = "39%";
            document.getElementById("eyeUnmask").style.bottom = "46%";

        }
         function forgotPassword() {
                            window.location = src = "/ForgotPassword";
                        }

         function mask(){
          document.getElementById("passwordError").style.display="block";
          document.getElementById("inputErr").style.display="block";
          document.getElementById("hidePassword").style.display = "none";
           document.getElementById("hideEmail").style.display = "none";
           document.getElementById("eyeUnmask").style.display = "block";
            document.getElementById("eyeMask").style.display = "none";
             document.getElementById("eyeUnmask").style.bottom = "39%";
             document.getElementById("eyeMask").style.bottom = "46%";
        }
     function getNewAccount() {
                            window.location = src = "/AccountLanding";
                        }
        function type() {
        }
        function popupPassword() {
                                    window.location = src = "/forgotpassword-otp";
                                }

               function resetPassword() {
                       document.getElementById("passwordSuccess").style.display = "block";
                        document.getElementById("forgotPassword").style.display = "none";
                   }