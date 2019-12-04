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

     function closeFrame(){
<!--            document.getElementById("photo-upload").style.display = "none";-->
<!--            document.getElementById("origin-frame").style.display = "none";-->
<!--            document.getElementById("upload-failed").style.display = "none";-->
            document.getElementById("upload-success").style.display = "none";
        }
         function joinConversation(){
            location.replace("/community/community-groups/community-group-conversation");
        }
         function backToGroupView(){
            location.replace("/community/community-groups/community-groups");
        }
        function viewConversation(){
            var x = document.getElementById("snackbar-group");
            x.className = "show";
            setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
        }
        function requestAwaiting(){
            location.replace("/community/community-groups/community-awaiting-group");
        }
        function openGroup(){
            location.replace("/community/community-groups/community-open-group");
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
                    document.getElementById("show-more").style.display = "flex";
                    count = count+1;
                }else{
                    group[i].style.display = "none";
                    document.getElementById("show-more").style.display = "none";
                }
                if(count === 0){
                    document.getElementById("search-not-found").style.display = "flex";
                    document.getElementById("back-btn").style.display = "flex";
                    document.getElementById("show-more").style.display = "none";
                }else{
                    document.getElementById("search-not-found").style.display = "none";
                    document.getElementById("back-btn").style.display = "flex";
                }
            }
        }
        window.addEventListener('load', function(){
            var link = document.getElementById("community-link");
            var linkImg = document.getElementById("community-img");
            link.className += " active";
            linkImg.src = "/images/burger-menu/extra-large-256px/Artboard 1 copy 42@16x1.png";
        })