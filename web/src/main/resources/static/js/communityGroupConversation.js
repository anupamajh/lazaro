   function viewDropdown(){
        if(document.getElementById("drop-down-menu").style.display === "none"){
        document.getElementById("drop-down-menu").style.display = "block";
        }else{
            document.getElementById("drop-down-menu").style.display = "none";
        }
    }
        function ignoreInvite(){
            document.getElementById("ignore").style.display = "block";
        }
        function backToGroupView(){
            location.replace("/community/community-groups/community-groups");
        }
        function viewExistingGroup(){
            location.replace("/community/community-groups/community-existing-group-view");
        }
window.addEventListener('load', function(){
            var link = document.getElementById("community-link");
            link.className += " active";
        })