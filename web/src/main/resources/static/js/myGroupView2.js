function viewDropdown(img){
        if(document.getElementById("drop-down-1").style.display === "none"){
        document.getElementById("drop-down-1").style.display = "block";
        img.src = "http://localhost:8000/images/extra-large-256px/Artboard 1 copy 53@16x.png";
        document.getElementById("drop-down-btn1").style.borderRadius = "0%";
        }else{
            document.getElementById("drop-down-1").style.display = "none";
            img.src = "http://localhost:8000/images/extra-large-256px/Artboard%201%20copy%2047@16x.png";
            document.getElementById("drop-down-btn1").style.borderRadius = "50%";
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
            location.replace("/my-groups/invite-people");
       }
       function viewRequestList(){
            location.assign("/my-groups/my-group-requests");
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
        function viewJoined(){
                console.log("entered");
            if(document.getElementById("joined-members").style.display === "none"){
                 document.getElementById("joined-members").style.display = "block";
                 document.getElementById("view-joined-btn").src="/images/extra-large-256px/Artboard 1 copy 2@16x.png";
            }else{
                document.getElementById("joined-members").style.display = "none";
                document.getElementById("view-joined-btn").src="/images/extra-large-256px/Artboard 1 copy@16x.png";
            }
        }
         function viewPending(){
                console.log("entered");
            if(document.getElementById("pending-members").style.display === "none"){
                 document.getElementById("pending-members").style.display = "block";
                 document.getElementById("view-pending-btn").src="/images/extra-large-256px/Artboard 1 copy 2@16x.png";
            }else{
                document.getElementById("pending-members").style.display = "none";
                document.getElementById("view-pending-btn").src="/images/extra-large-256px/Artboard 1 copy@16x.png";
            }
        }

        function openConversation(){
            location.replace("/my-groups/my-group-conversation");
        }
         function backToGroupView(){
            location.replace("/my-groups/my-groups-list");
        }