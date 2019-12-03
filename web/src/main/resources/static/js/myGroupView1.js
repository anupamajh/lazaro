
    function viewDropdown(img){
        if(document.getElementById("drop-down-my-groups").style.display === "none"){
        document.getElementById("drop-down-my-groups").style.display = "block";
        img.src = "http://localhost:8000/images/extra-large-256px/Artboard 1 copy 53@16x.png";
        document.getElementById("drop-down-btn-my-groups").style.borderRadius = "0%";
        }else{
            document.getElementById("drop-down-my-groups").style.display = "none";
            img.src = "http://localhost:8000/images/extra-large-256px/Artboard%201%20copy%2047@16x.png";
            document.getElementById("drop-down-btn-my-groups").style.borderRadius = "50%";
        }
    }
    function viewDropdown1(){
        if(document.getElementById("drop-down-menu").style.display === "none"){
            document.getElementById("drop-down-menu").style.display = "block";
        }else{
            document.getElementById("drop-down-menu").style.display = "none";
        }
    }
    function invitePeople(){
            location.assign("/my-groups/invite-people");
       }
        function exitGroup(){
                document.getElementById("delete").style.display = "block";
        }

     function closeFrame(){
<!--            document.getElementById("photo-upload").style.display = "none";-->
<!--            document.getElementById("origin-frame").style.display = "none";-->
<!--            document.getElementById("upload-failed").style.display = "none";-->
                document.getElementById("upload-success").style.display = "none";
                document.getElementById("delete").style.display = "none";
        }

       function filterFav(){
            var all = document.getElementsByClassName("group-members");
            var fav = document.getElementsByClassName("favourites");
            document.getElementById("filter-clear-link").style.display = "inline-block";
            document.getElementById("drop-down-menu").style.display = "none";
            document.getElementById("count-msg").style.display = "none";
            var i, j;
            for(i=0; i<all.length; i++){
                all[i].style.display = "none";
            }
            for(j=0; j<fav.length; j++){
                fav[j].style.display = "flex";
            }
       }
       function clearFilter(){
            var all = document.getElementsByClassName("group-members");
            document.getElementById("drop-down-menu").style.display = "none";
            document.getElementById("filter-clear-link").style.display = "none";
            var i, j;
            for(i=0; i<all.length; i++){
                all[i].style.display = "flex";
            }
       }
        function viewRequestList(){
            location.assign("/my-groups/my-group-requests");
       }
        function openConversation(){
            location.replace("/my-groups/my-group-conversation");
        }
        function backToGroupView(){
            location.replace("/my-groups/my-groups-list");
        }