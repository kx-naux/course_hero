// When the user clicks on the button, scroll to the top of the document
function toTopFunc() {
    document.body.scrollTop = 0; // For Safari
    document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}

// Find selected option
document.addEventListener("DOMContentLoaded", function () {
    // Get all anchor tags inside nav-option-div
    const navLinks = document.querySelectorAll('.nav-option-div a');

    // Loop through each anchor tag
    navLinks.forEach(function (navLink) {
        // Check if the href attribute of the anchor tag matches the current URL
        if (navLink.href === window.location.href) {
            // If it matches, add the 'selected' class to its parent div
            navLink.querySelector('.nav-option').classList.add('selected');
        }
    });
});

// Navbar onscroll and to top button
document.addEventListener("DOMContentLoaded", function () {
    window.addEventListener('scroll', function () {
        if (window.scrollY > 15) {
            document.querySelector('.nav-bar').classList.add('nav-scroll');
        } else {
            document.querySelector('.nav-bar').classList.remove('nav-scroll');
        }

        // Get the button:
        let topTopBtn = document.getElementById("toTopBtn");

        if (window.scrollY > 15) {
            topTopBtn.classList.add('active');
        } else {
            topTopBtn.classList.remove('active');
        }
    });
});

// open and close search div
document.querySelector("p.toggle-search").addEventListener("click", (event) => {
    event.stopPropagation();

    let searchDiv = document.querySelector("div.nav-search");

    if (searchDiv.classList.contains("active")) {
        searchDiv.classList.remove("active");
    } else {
        searchDiv.classList.add("active");
    }

    document.querySelector("input#nav-search-input").value = "";
    document.querySelector("input#nav-search-input").focus();
});

// close search div
document.addEventListener("click", (event) => {
    const searchDiv = document.querySelector(".nav-search");
    const toggleButton = document.querySelector(".toggle-search");

    if (event.target !== searchDiv && event.target !== toggleButton) {
        if (!event.target.closest(".nav-search-div")) {
            if (searchDiv.classList.contains("active")) {
                searchDiv.classList.remove("active");
            }
        }
    }

    if (event.target === searchDiv) {
        if (searchDiv.classList.contains("active")) {
            searchDiv.classList.remove("active");
        }
    }
});

// insert search suggestion record 
function insert_suggestion(data) {
    let show_div = document.querySelector(".nav-search-result").innerText;
}

// event handler for suggestion query
function search_suggestion_click(e) {
    let query = e.querySelector(".suggest-query").innerText;
    window.location.href = "/course_hero/search?query=" + query;
}

// event handler for suggestion course
function search_suggestion_course_click(e) {
    let id = e.querySelector(".nav-search-suggestion-course").getAttribute("courseID");
    window.location.href = "/course_hero/search?query=" + id;
}

// event handler for popular search suggestion
function popular_search_click(e) {
    window.location.href = "/course_hero/search?query=" + e.innerText;
}


