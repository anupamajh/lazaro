    function createGroup(){
        document.getElementById("groups-list").style.display = "block";
        document.getElementById("no-groups").style.display = "none";
        document.getElementById("create-group-btn").style.display = "none";
    }
    function viewDropdown(img){
        if(document.getElementById("drop-down-my-groups").style.display === "none"){
        document.getElementById("drop-down-my-groups").style.display = "block";
        img.src = "http://localhost:8000/images/extra-large-256px/Artboard 1 copy 53@16x.png";
        document.getElementById("drop-down-btn1").style.borderRadius = "0%";
        }else{
            document.getElementById("drop-down-my-groups").style.display = "none";
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
    function groupCreated(){
        document.getElementById("groups-list").style.display = "none";
        document.getElementById("no-groups").style.display = "block";
        document.getElementById("invite-part").style.display = "block";
        document.getElementById("create-group-btn").style.display = "none";
        document.getElementById("upload-success").style.display = "none";
    }
    function invitePeople1(position){
        var list = document.getElementsByClassName("invite-btn");
        var sentlist = document.getElementsByClassName("msg-sent");
        list = [].slice.call(list);
        var n = list.indexOf(position);
        list[n].style.display = "none";
        sentlist[n].style.display = "block";
        var x = document.getElementById("snackbar-invite");
        x.className = "show";
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
    }

    function createGroupDone(){
<!--            document.getElementById("upload-failed").style.display = "none";-->
<!--            document.getElementById("photo-upload").style.display = "none";-->
<!--            document.getElementById("origin-frame").style.display = "none";-->
                document.getElementById("upload-success").style.display = "block";
        }

     function closeFrame(){
<!--            document.getElementById("photo-upload").style.display = "none";-->
<!--            document.getElementById("origin-frame").style.display = "none";-->
<!--            document.getElementById("upload-failed").style.display = "none";-->
            document.getElementById("upload-success").style.display = "none";
        }
       function invitePeople(){
            location.replace("/my-groups/invite-people");
       }
       function inviteLater(){
            location.assign("/my-groups/my-group-view1");
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
         function backToGroupView(){
            location.replace("/community/community-groups/community-groups");
        }
    function openBurgerMenu(){
        if(document.getElementById("burger-menu").style.display === "none"){
            document.getElementById("burger-menu").style.display = "block";
        }
        else{
            document.getElementById("burger-menu").style.display = "none";
        }
    }