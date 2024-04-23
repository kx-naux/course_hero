let btn = document.querySelector('.expand-btn');
let sidebar = document.querySelector('.global-sidebar');

btn.onclick = function(){
    sidebar.classList.toggle('active');
};

//dropdown submenu js
let mainMenu = document.querySelectorAll(".sidebar-navigation ul li a");
for (var i = 0; i < mainMenu.length; i++) {
    mainMenu[i].addEventListener("click", function (e) {
        e.preventDefault();
        let submenu = this.nextElementSibling;
        let subMenuArrow = this.querySelector(".arrow");
        // Remove "active" class from other sidebars
        document.querySelectorAll(".sidebar-navigation ul li ul").forEach(function(otherSubmenu) {
            if (otherSubmenu !== submenu) {
                otherSubmenu.classList.remove("active");
            }
        });
        //toggle the active classlist in submenu
        submenu.classList.toggle("active");
        //if submenu active make the arrow go up
        if(submenu.classList.contains("active")){
            subMenuArrow.classList.add("ri-arrow-up-s-line");
            subMenuArrow.classList.remove("ri-arrow-down-s-line");
            // make the "arrow" class to default from other menu
            document.querySelectorAll(".arrow").forEach(function(otherSubMenuArrow) {
                if (otherSubMenuArrow !== subMenuArrow) {
                    otherSubMenuArrow.classList.remove("ri-arrow-up-s-line");
                    otherSubMenuArrow.classList.add("ri-arrow-down-s-line");
                }
            });
        }
        else{
            subMenuArrow.classList.add("ri-arrow-down-s-line");
            subMenuArrow.classList.remove("ri-arrow-up-s-line");
        }
    });
}

// Function to close other submenus
function closeOtherSubmenus(clickedItem) {
    var parent = clickedItem.parentNode;
    var siblings = parent.parentNode.children;
    for (var i = 0; i < siblings.length; i++) {
        var sibling = siblings[i];
        if (sibling !== parent) {
            var submenu = sibling.querySelector(".submenu");
            submenu.style.display = "none";
            // Remove active class from other menu items
            sibling.querySelector("a").classList.remove("active");
        }
    }
}

document.addEventListener("DOMContentLoaded", function () {
    // Get all anchor tags inside nav-option-div
    const navLinks = document.querySelectorAll('.sidebar-navigation ul li a');

    // Loop through each anchor tag
    navLinks.forEach(function(navLink) {
        // Check if the href attribute of the anchor tag matches the current URL
        if(navLink.href === window.location.href) {
            // If it matches, add the 'selected' class to its parent div
            navLink.classList.add('active');
        }
    });
});