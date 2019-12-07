 function ToggleCurrentPassword() {
        var temp = document.getElementById("newEmail1");
                              if (temp.type === "password") {
                                  temp.type = "text";
                                  document.getElementById("passwordMask1").style.display = "block";
      //                            document.getElementById("passwordUnmask").style.bottom = "38%";
      document.getElementById("passwordUnmask1").style.display = "none";
                                  document.getElementById("passwordMask1").style.bottom = "37%";
                              }
                              else {
                                  temp.type = "password";
                                  document.getElementById("passwordMask1").style.display = "block";
        document.getElementById("passwordMask1").style.bottom = "38%";
        document.getElementById("passwordUnmask1").style.display = "none";
                              }
    }
function mask1() {
  var temp = document.getElementById("newEmail1");
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
                         }}
    function ToggleNewPassword() {
        var temp = document.getElementById("newEmail2");
                                     if (temp.type === "password") {
                                         temp.type = "text";
                                         document.getElementById("passwordMask2").style.display = "block";
             //                            document.getElementById("passwordUnmask").style.bottom = "38%";
             document.getElementById("passwordUnmask2").style.display = "none";
                                         document.getElementById("passwordMask2").style.bottom = "37%";
                                     }
                                     else {
                                         temp.type = "password";
                                         document.getElementById("passwordMask2").style.display = "block";
               document.getElementById("passwordMask2").style.bottom = "38%";
               document.getElementById("passwordUnmask2").style.display = "none";
                                     }
    }
function mask2() {
  var temp = document.getElementById("newEmail2");
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
                         }}

    function ToggleConfirmPassword() {
        var temp = document.getElementById("newEmail3");
                                     if (temp.type === "password") {
                                         temp.type = "text";
                                         document.getElementById("passwordMask3").style.display = "block";
             //                            document.getElementById("passwordUnmask").style.bottom = "38%";
             document.getElementById("passwordUnmask3").style.display = "none";
                                         document.getElementById("passwordMask3").style.bottom = "37%";
                                     }
                                     else {
                                         temp.type = "password";
                                         document.getElementById("passwordMask3").style.display = "block";
               document.getElementById("passwordMask3").style.bottom = "38%";
               document.getElementById("passwordUnmask3").style.display = "none";
                                     }
    }
function mask3() {
  var temp = document.getElementById("newEmail3");
                         if (temp.type === "password") {
                             temp.type = "text";
                             document.getElementById("passwordMask3").style.display = "none";
                               document.getElementById("passwordUnmask3").style.display = "block";

                         }
                         else {
                             temp.type = "password";
                             document.getElementById("passwordMask3").style.display = "none";
                              document.getElementById("passwordUnmask3").style.display = "block";
                               document.getElementById("passwordMask3").style.bottom = "38%"
                         }}







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
            window.addEventListener('load', function(){
                var link = document.getElementById("change-password-link");
                var linkImg = document.getElementById("change-password-img");
                link.className += " active";
                linkImg.src = "/images/burger-menu/extra-large-256px/Artboard 1 copy 57@16x1.png";
            });