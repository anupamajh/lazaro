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
    function acceptRequest(position){
        var requestlist = document.getElementsByClassName("request-status");
        var requestlisttxt = document.getElementsByClassName("request-status-txt");
        requestlist = [].slice.call(requestlist);
        var n = requestlist.indexOf(position);
        requestlist[n].src = "/images/extra-large-256px/Artboard 1 copy 46@16x.png";
        requestlist[n].height = "15";
        requestlist[n].width = "15";
        requestlisttxt[n].innerHTML = "ACCEPTED";
    }
      function closeFrame(){
<!--            document.getElementById("photo-upload").style.display = "none";-->
<!--            document.getElementById("origin-frame").style.display = "none";-->
<!--            document.getElementById("upload-failed").style.display = "none";-->
                document.getElementById("upload-success").style.display = "none";
                document.getElementById("delete").style.display = "none";
        }
         function backToGroupView(){
            location.replace("/my-groups/my-group-view");
        }