function viewGroups() {
        document.getElementById("groups-list").style.display = "block";
        document.getElementById("no-groups").style.display = "none";
        document.getElementById("drop-down-btn").style.display = "inline";
    }

    function viewDropdown() {
        if (document.getElementById("drop-down-menu").style.display === "none") {
            document.getElementById("drop-down-menu").style.display = "block";
        } else {
            document.getElementById("drop-down-menu").style.display = "none";
        }
    }

    function groupView() {
        console.log("msg");
        document.getElementById("group-view").style.display = "block";
        document.getElementById("group-content").style.display = "none";
        document.getElementById("drop-down-btn").style.display = "none";
    }

    function joinGroup() {
        document.getElementById("upload-success").style.display = "block";
    }

    function closeFrame() {
        document.getElementById("exit-group").style.display = "none";
        document.getElementById("group-view").style.display = "block";
    }

    function ignoreInvite() {
        document.getElementById("ignore").style.display = "block";
    }

    function backToGroupView() {
        location.replace("/InterestGroups/interest-group-list");
    }

    function Exit() {
        document.getElementById("exit-group").style.display = "block";
        document.getElementById("group-view").style.display = "block";
    }

    function GroupInfo() {
        document.getElementById("group-view").style.display = "block";
        document.getElementById("group-conversation").style.display = "none";
        document.getElementById("drop-down-btn").style.display = "none";
    }

    function noExit() {
        document.getElementById("exit-group").style.display = "none";
        document.getElementById("group-view").style.display = "block";
    }

    function exitInterestGroup() {
        location.replace("/InterestGroups/interest-group-list");
    }

    function equalmark() {
        document.getElementById("myDropdown").classList.toggle("show");
        document.getElementById("cross-mark").style.display = "block";
        document.getElementById("equals").style.display = "none";
    }

    // Close the dropdown if the user clicks outside of it
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

    function crossmark() {
        document.getElementById("cross-mark").style.display = "none";
        document.getElementById("equals").style.display = "block";
        document.getElementById("myDropdown").classList.toggle("show");
    }

    function interestgroup() {
        location.replace("/InterestGroups/interest-group-list");
    }

    function hiking() {
        document.getElementById("group-view").style.display = "block";
        document.getElementById("interest-group").style.display = "none";
    }

    function filter() {
        if (document.getElementById("drop-down-menu").style.display === "none") {
            document.getElementById("drop-down-menu").style.display = "block";
        } else {
            document.getElementById("drop-down-menu").style.display = "none";
        }
    }

    function subscribe() {
        document.getElementById("clear-filter").style.display = "block";
        document.getElementById("drop-down-menu").style.display = "none";
        document.getElementById("hiking").style.display = "none";
        document.getElementById("sky").style.display = "none";
    }

    function SubscibeGroup() {
        document.getElementById("exit-group").style.display = "block";
    }

    function clear() {
        document.getElementById("hiking").style.display = "block";
        document.getElementById("sky").style.display = "block";
    }

    function doneGroupHike() {
        location.replace("/InterestGroups/group-conversation");
    }