function johnDoe() {
        location.replace("/community/community-groups/people-details");
        //                          window.location = src = "/community/community-groups/people-details";
    }

    function likeJohn() {
        document.getElementById("like-john").style.display = "none";
        document.getElementById("unlike-john").style.display = "block";
    }

    function likePeter() {
        document.getElementById("like-peter").style.display = "none";
        document.getElementById("unlike-peter").style.display = "block";
    }

    function likeLuke() {
        document.getElementById("like-luke").style.display = "none";
        document.getElementById("unlike-luke").style.display = "block";
    }

    function likeDaisy() {
        document.getElementById("like-daisy").style.display = "none";
        document.getElementById("unlike-daisy").style.display = "block";
    }

    function likeJeret() {
        document.getElementById("like-jeret").style.display = "none";
        document.getElementById("unlike-jeret").style.display = "block";
    }


    function unlikeJohn() {
        document.getElementById("like-john").style.display = "block";
        document.getElementById("unlike-john").style.display = "none";
    }

    function unlikePeter() {
        document.getElementById("like-peter").style.display = "block";
        document.getElementById("unlike-peter").style.display = "none";
    }

    function unlikeLuke() {
        document.getElementById("like-luke").style.display = "block";
        document.getElementById("unlike-luke").style.display = "none";
    }

    function unlikeDaisy() {
        document.getElementById("like-daisy").style.display = "block";
        document.getElementById("unlike-daisy").style.display = "none";
    }

    function unlikeJeret() {
        document.getElementById("like-jeret").style.display = "block";
        document.getElementById("unlike-jeret").style.display = "none";
    }


    function favourites() {
        document.getElementById("filter-clear-link").style.display = "inline-block";
        document.getElementById("drop-down-menu").style.display = "none";
        document.getElementById("red-dot").style.display = "none";
        document.getElementById("john").style.display = "block";
        document.getElementById("PeterLaw").style.display = "block";
        document.getElementById("luke").style.display = "none";
        document.getElementById("DaisyLake").style.display = "block";
        document.getElementById("Jeret").style.display = "block";
        document.getElementById("danQuartz").style.display = "none";
        var i, j;
        for (i = 0; i < all.length; i++) {
            all[i].style.display = "none";
        }
        for (j = 0; j < fav.length; j++) {
            fav[j].style.display = "flex";
        }
    }
    document.getElementById("people-filter-clear").style.display = "block";
    document.getElementById("drop-down-menu").style.display = "none";


    function viewDropdown() {
        if (document.getElementById("drop-down-menu").style.display === "none") {
            document.getElementById("drop-down-menu").style.display = "block";
        } else {
            document.getElementById("drop-down-menu").style.display = "none";
        }
    }
    window.onclick = function(event) {
        if (!event.target.matches('.dropbtn')) {
            var dropdowns = document.getElementsByClassName("dropdown-content");
            var i;
            for (i = 0; i < dropdowns.length; i++) {
                var openDropdown = dropdowns[i];
                if (openDropdown.classList.contains('show')) {
                    openDropdown.classList.remove('show');
                }
            }
        }
    }

    function clearFilter() {
        var all = document.getElementsByClassName("group-members");
        document.getElementById("drop-down-menu").style.display = "none";
        document.getElementById("john").style.display = "block";
        document.getElementById("PeterLaw").style.display = "block";
        document.getElementById("luke").style.display = "block";
        document.getElementById("DaisyLake").style.display = "block";
        document.getElementById("Jeret").style.display = "block";
        document.getElementById("danQuartz").style.display = "block";
        document.getElementById("filter-clear-link").style.display = "none";
        var i, j;
        for (i = 0; i < all.length; i++) {
            all[i].style.display = "flex";
        }
    }

    function AddFavourite() {
        document.getElementById("like-favourite").style.display = "none";
        document.getElementById("remove-favourite").style.display = "block";
        document.getElementById("remove-favourites").style.display = "block";
        document.getElementById("unlike-favourite").style.display = "block";
        document.getElementById("add-favourite").style.display = "none";
        document.getElementById("add-favourites").style.display = "none";
    }

    function Nodetails() {
        document.getElementById("noJohnDetails").style.display = "block";
        document.getElementById("people-details").style.display = "none";
    }

    function RemoveFavourite() {
        document.getElementById("like-favourite").style.display = "block";
        document.getElementById("remove-favourite").style.display = "none";
        document.getElementById("remove-favourites").style.display = "none";
        document.getElementById("unlike-favourite").style.display = "none";
        document.getElementById("add-favourite").style.display = "block";
        document.getElementById("add-favourites").style.display = "block";
    }

    function backPeopleList() {
        location.replace("/community/community-groups/people-list");
        //             window.location = src = "/community/community-groups/people-list";
    }