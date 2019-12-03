function openCommunity(){
    var link = document.getElementById("community-link");
    link.className += " active";
    location.assign("/community/community-profile");
}
 function openBurgerMenu(){
        if(document.getElementById("burger-menu").style.display === "none"){
            document.getElementById("burger-menu").style.display = "block";
        }
        else{
            document.getElementById("burger-menu").style.display = "none";
        }
    }