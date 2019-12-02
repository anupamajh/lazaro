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
        if(document.getElementById("drop-down-menu1").style.display === "none"){
            document.getElementById("drop-down-menu1").style.display = "block";
        }else{
            document.getElementById("drop-down-menu1").style.display = "none";
        }
    }
    function invitePeople1(position){
        var list = document.getElementsByClassName("invite-btn1");
        var sentlist = document.getElementsByClassName("msg-sent");
        list = [].slice.call(list);
        var n = list.indexOf(position);
        list[n].style.display = "none";
        sentlist[n].style.display = "block";
        var x = document.getElementById("snackbar-invite");
        x.className = "show";
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
    }

     function closeFrame(){
<!--            document.getElementById("photo-upload").style.display = "none";-->
<!--            document.getElementById("origin-frame").style.display = "none";-->
<!--            document.getElementById("upload-failed").style.display = "none";-->
            document.getElementById("upload-success").style.display = "none";
        }
       function filterFav(){
            var all = document.getElementsByClassName("group-members");
            var fav = document.getElementsByClassName("favourites");
            document.getElementById("filter-clear-link").style.display = "inline-block";
            document.getElementById("drop-down-menu1").style.display = "none";
            document.getElementById("count-msg").style.display = "none";
            document.getElementById("fav-link").style.color = "#909090";
            document.getElementById("clear-link").style.color = "#32BDD2";
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
            document.getElementById("drop-down-menu1").style.display = "none";
            document.getElementById("filter-clear-link").style.display = "none";
            var i, j;
            document.getElementById("fav-link").style.color = "#464646";
            document.getElementById("clear-link").style.color = "#909090";
            for(i=0; i<all.length; i++){
                all[i].style.display = "flex";
            }
       }
       function backToGroupView(){
           location.replace("/my-groups/my-groups-list");
        }
        function searchPeople(){
            var str = document.getElementById("search-people").value.toUpperCase().trim();
            var person_name = document.getElementsByClassName("uname");
            var people = document.getElementsByClassName("group-members");
            var i;
            var count = 0;
            var people_list = person_name.length;

            for(i=0;i<people_list;i++){
                var res = person_name[i].textContent.toUpperCase().trim();
                var result = res.match(str);
                console.log(result);
                if(result != null){
                    people[i].style.display = "flex";
                    count = count+1;
                }else{
                    people[i].style.display = "none";
                }
                if(count === 0){
                    document.getElementById("search-not-found").style.display = "flex";
                    document.getElementById("back-btn1").style.display = "flex";
                    document.getElementById("show-more").style.display = "none";
                    document.getElementById("count-msg").style.display = "none";
                }else{
                    document.getElementById("search-not-found").style.display = "none";
                    document.getElementById("back-btn1").style.display = "flex";
<!--                    if(count != 1){-->
<!--                    -->
<!--                    }else{-->
<!--                        document.getElementById("count-msg").style.display = "block";-->
<!--                        document.getElementById("count-msg").innerHTML = "1 match found";-->
<!--                    }-->
                }
                document.getElementById("count-msg").style.display = "block";
                document.getElementById("count").innerHTML = count;
            }
        }