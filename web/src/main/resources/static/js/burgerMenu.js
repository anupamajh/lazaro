 function openBurgerMenu(){
        if(document.getElementById("burger-menu").style.display === "none"){
            document.getElementById("burger-menu").style.display = "flex";
            // Get the current page scroll position
            scrollTop = window.pageYOffset || document.documentElement.scrollTop;
            scrollLeft = window.pageXOffset || document.documentElement.scrollLeft,
            window.onscroll = function() {
                window.scrollTo(scrollLeft, scrollTop);
            };
        }
        else{
            document.getElementById("burger-menu").style.display = "none";
            window.onscroll = function() {};
        }
    }
function openCommunity(){
      location.assign("/community/community-profile");
}