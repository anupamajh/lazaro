function viewInterestList(){
            document.getElementById("view-interest-list").classList.toggle("show");
            document.getElementById("drop-down-list").style.display = "none";
            document.getElementById("drop-down-btn").src="/images/extra-large-256px/Artboard 1 copy@16x.png";
}

        function viewDropDownList(){
            console.log("entered");
            if(document.getElementById("drop-down-list").style.display === "none"){
                 document.getElementById("drop-down-list").style.display = "block";
                 document.getElementById("drop-down-btn-interest").src="/images/extra-large-256px/Artboard 1 copy 2@16x.png";
            }else{
                document.getElementById("drop-down-list").style.display = "none";
                document.getElementById("drop-down-btn-interest").src="/images/extra-large-256px/Artboard 1 copy@16x.png";
            }
        }

        function goBack(){
            document.getElementById("profile-setup").style.display = "block";
            document.getElementById("interests-setup").style.display = "none";
        }

        function interestsSetup(){
            document.getElementById("profile-setup").style.display = "none";
            document.getElementById("interests-setup").style.display = "block";
        }

        function uploadPicture(){
            document.getElementById("photo-upload").style.display = "block";
        }
        window.addEventListener('load', function() {
            document.querySelector('input[type="file"]').addEventListener('change', function() {
                if (this.files && this.files[0]) {
                    var img = document.getElementById("upload-profile-pic");  // $('img')[0]
                    img.src = URL.createObjectURL(this.files[0]); // set src to blob url
                    img.onload = imageIsLoaded;
                    var txt = document.getElementById("Upload-pic");
                    txt.style.display = "none";
                }
        });
    });


        function closeFrame(){
            document.getElementById("photo-upload").style.display = "none";
            document.getElementById("origin-frame").style.display = "none";
            document.getElementById("upload-failed").style.display = "none";
            document.getElementById("upload-success").style.display = "none";
        }

        function visibilityToggle(img){
                if(img.src === "http://localhost:8000/images/extra-large-256px/Artboard%201%20copy%203@16x.png"){
                    console.log(img.src);
                    img.src = "/images/extra-large-256px/Artboard 1 copy 4@16x.png";
                }
                else{
                    console.log(img.src);
                    img.src = "/images/extra-large-256px/Artboard 1 copy 3@16x.png";
                }
         }

        function originFrame(){
            document.getElementById("origin-frame").style.display = "block";
        }

        function chooseImg(){
           var img = document.getElementById("fileInput").click();
           console.log("img");
        }

        function upload(){
            document.getElementById("upload-failed").style.display = "none";
            document.getElementById("photo-upload").style.display = "none";
            document.getElementById("origin-frame").style.display = "none";
            document.getElementById("upload-success").style.display = "block";
        }
        function uploaded(){
            document.getElementById("edit-part").style.visibility = "visible";
            document.getElementById("upload-failed").style.display = "none";
            document.getElementById("photo-upload").style.display = "none";
            document.getElementById("origin-frame").style.display = "none";
            document.getElementById("upload-success").style.display = "none";
            document.getElementById("visible-pic").style.display = "block";
            document.getElementById("upload-pic").style.display = "none";
        }

        function uploadSuccess(){
            document.getElementById("upload-failed").style.display = "none";
            document.getElementById("photo-upload").style.display = "none";
            document.getElementById("origin-frame").style.display = "none";
            document.getElementById("upload-success").style.display = "block";
        }

        function myFunction(img) {
                            if(img.src === "http://localhost:8000/images/extra-large-256px/Artboard%201%20copy%203@16x.png"){
                             var x = document.getElementById("snackbar1");
                             x.className = "show";
                             setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
                            }
                            else{
                             var x = document.getElementById("snackbar");
                             x.className = "show";
                             setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
                            }
        }
        function myFunction1(img) {
                            if(img.src === "http://localhost:8000/images/extra-large-256px/Artboard%201%20copy%203@16x.png"){
                             var x = document.getElementById("snackbar1-mobile");
                             x.className = "show";
                             setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
                            }
                            else{
                             var x = document.getElementById("snackbar-mobile");
                             x.className = "show";
                             setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
                            }
        }
         function myFunctionAge(img) {
                            if(img.src === "http://localhost:8000/images/extra-large-256px/Artboard%201%20copy%203@16x.png"){
                             var x = document.getElementById("snackbar1-age");
                             x.className = "show";
                             setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
                            }
                            else{
                             var x = document.getElementById("snackbar-age");
                             x.className = "show";
                             setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
                            }
        }
         function myFunctionEmail(img) {
                            if(img.src === "http://localhost:8000/images/extra-large-256px/Artboard%201%20copy%203@16x.png"){
                             var x = document.getElementById("snackbar1-email");
                             x.className = "show";
                             setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
                            }
                            else{
                             var x = document.getElementById("snackbar-email");
                             x.className = "show";
                             setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
                            }
        }
         function myFunctionOrigin(img) {
                            if(img.src === "http://localhost:8000/images/extra-large-256px/Artboard%201%20copy%203@16x.png"){
                             var x = document.getElementById("snackbar1-origin");
                             x.className = "show";
                             setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
                            }
                            else{
                             var x = document.getElementById("snackbar-origin");
                             x.className = "show";
                             setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
                            }
        }
        function myFunctionProfile(img){
                            if(img.src === "http://localhost:8000/images/extra-large-256px/Artboard%201%20copy%203@16x.png"){
                             var x = document.getElementById("snackbar1-profile");
                             x.className = "show";
                             setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
                            }
                            else{
                             var x = document.getElementById("snackbar-profile");
                             x.className = "show";
                             setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
                            }
        }
        function changePicture(){
            if(document.getElementById("change-delete-picture").style.visibility === "hidden"){
                var img = document.getElementById("picture-edit-btn");
                img.src = "/images/extra-large-256px/Artboard 1 copy 53@16x.png";
                document.getElementById("change-delete-picture").style.visibility = "visible";
            }
            else{
                var img = document.getElementById("picture-edit-btn");
                img.src = "/images/extra-large-256px/Artboard 1 copy 51@16x.png";
                document.getElementById("change-delete-picture").style.visibility = "hidden";
            }
        }
        function deletePhoto(){
            var img = document.getElementById("upload-profile-pic").src = "/images/extra-large-256px/Artboard 1 copy 40@16x.png";
            document.getElementById("visible-pic").style.display = "none";
            document.getElementById("change-delete-picture").style.visibility = "visible";
        }
        function openGroupsPage(){
            location.replace("/community/community-groups/community-groups");
        }
        window.addEventListener('load', function(){
            var link = document.getElementById("community-link");
            link.className += " active";
        })