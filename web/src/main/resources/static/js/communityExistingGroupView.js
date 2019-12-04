 function closeFrame(){
            document.getElementById("exit-group").style.display= "none";
        }
        function backToGroupView(){
            location.replace("/community/community-groups/community-group-conversation");
        }
        function exitGroup(){
            document.getElementById("exit-group").style.display= "block";
        }
        window.addEventListener('load', function(){
            var link = document.getElementById("community-link");
            var linkImg = document.getElementById("community-img");
            link.className += " active";
            linkImg.src = "/images/burger-menu/extra-large-256px/Artboard 1 copy 42@16x1.png";
        })