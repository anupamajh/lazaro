    function viewGroups(){
        document.getElementById("groups-list").style.display = "block";
        document.getElementById("no-groups").style.display = "none";
        document.getElementById("drop-down-btn").style.display = "inline";
    }
    function viewDropdown(){
        if(document.getElementById("drop-down-menu").style.display === "none"){
        document.getElementById("drop-down-menu").style.display = "block";
        }else{
            document.getElementById("drop-down-menu").style.display = "none";
        }
    }

    function groupView(){
        console.log("msg");
        location.replace("/community/community-groups/community-group-view");
<!--        document.getElementById("group-view").style.display = "block";-->
<!--        document.getElementById("group-content").style.display = "none";-->
<!--        document.getElementById("drop-down-btn").style.display = "none";-->
    }

        function createGroup(){
            location.replace("/my-groups/my-groups")
        }

     function closeFrame(){
<!--            document.getElementById("photo-upload").style.display = "none";-->
<!--            document.getElementById("origin-frame").style.display = "none";-->
<!--            document.getElementById("upload-failed").style.display = "none";-->
            document.getElementById("upload-success").style.display = "none";
        }
        function viewConversation(){
            location.assign("/my-groups/my-group-view");
        }
        function searchGroups(){
            var str = document.getElementById("search-groups").value.toUpperCase().trim();
            var group_title = document.getElementsByClassName("group_info");
            var group = document.getElementsByClassName("group");
            var i;
            var count = 0;
            var groups = group_title.length;

            for(i=0;i<groups;i++){
                var res = group_title[i].textContent.toUpperCase().trim();
                var result = res.match(str);
                console.log(result);
                var empty = "";
                if(result != null){
                    group[i].style.display = "flex";
                    count = count+1;
                }else{
                    group[i].style.display = "none";
                }
                if(count === 0){
                    document.getElementById("search-not-found").style.display = "flex";
                }else{
                    document.getElementById("search-not-found").style.display = "none";
                }
            }
        }