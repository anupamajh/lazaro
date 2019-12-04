    function joinGroup(){
<!--            document.getElementById("upload-failed").style.display = "none";-->
<!--            document.getElementById("photo-upload").style.display = "none";-->
<!--            document.getElementById("origin-frame").style.display = "none";-->
            document.getElementById("upload-success").style.display = "block";
        }
     function closeFrame(){
<!--            document.getElementById("photo-upload").style.display = "none";-->
<!--            document.getElementById("origin-frame").style.display = "none";-->
<!--            document.getElementById("upload-failed").style.display = "none";-->
            document.getElementById("ignore").style.display = "none";
            document.getElementById("upload-success").style.display = "none";
        }
        function ignoreInvite(){
            document.getElementById("ignore").style.display = "block";
        }
        function backToGroupView(){
            location.replace("/community/community-groups/community-groups");
        }
        function joinConversation(){
            location.replace("/community/community-groups/community-group-conversation");
        }
          window.addEventListener('load', function(){
              var link = document.getElementById("community-link");
              var linkImg = document.getElementById("community-img");
              link.className += " active";
              linkImg.src = "/images/burger-menu/extra-large-256px/Artboard 1 copy 42@16x1.png";
          })