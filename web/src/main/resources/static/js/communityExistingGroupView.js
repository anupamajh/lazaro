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
                    link.className += " active";
                })