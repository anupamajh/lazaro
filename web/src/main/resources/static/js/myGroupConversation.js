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
    function viewGroups(){
        document.getElementById("groups-list").style.display = "block";
        document.getElementById("no-groups").style.display = "none";
        document.getElementById("drop-down-btn").style.display = "inline";
    }
    function groupView(){
        console.log("msg");
        document.getElementById("group-view").style.display = "block";
        document.getElementById("group-content").style.display = "none";
        document.getElementById("drop-down-btn").style.display = "none";
    }
         function backToGroupView(){
            location.replace("/my-groups/may-groups-view");
        }
        function viewExistingGroup(){
            location.replace("/community/community-groups/community-existing-group-view");
        }