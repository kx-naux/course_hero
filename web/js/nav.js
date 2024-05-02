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

const dummySearchSuggestion = {
    'keyword' : ["Python","Java","C++"],
    'course' : [
        {
            'courseID' : "1231231231",
            'img' : "./img/course/beginner_excel.jpg",
            'courseTitle' : "The Ultimate Course Programming",
            'courseAuthor' : "Woo Yu Beng, Snijders"
        },
        {
            'courseID' : "1231231231",
            'img' : "./img/course/beginner_excel.jpg",
            'courseTitle' : "The Ultimate Course Programming",
            'courseAuthor' : "Woo Yu Beng, Snijders"
        },
        {
            'courseID' : "1231231231",
            'img' : "./img/course/beginner_excel.jpg",
            'courseTitle' : "The Ultimate Course Programming",
            'courseAuthor' : "Woo Yu Beng, Snijders"
        }
    ]
};

// insert search suggestion record 
function insert_suggestion(data) {
    let show_div = document.querySelector("div.nav-search-result");
    
    // remove previous suggection
    show_div.innerHTML = "";
    
    // Insert keyword suggestions
    data.keyword.forEach(keyword => {
        let suggestionLink = document.createElement("a");
        suggestionLink.setAttribute("onclick", "search_suggestion_click(this)");
        let suggestionQuery = document.createElement("div");
        suggestionQuery.classList.add("nav-search-suggestion-query", "flex-row");
        suggestionQuery.innerHTML = `
            <p class="suggest-icon"><i class="ri-search-line"></i></p>
            <p class="suggest-query">${keyword}</p>
        `;
        suggestionLink.appendChild(suggestionQuery);
        show_div.appendChild(suggestionLink);
    });
    
    // Insert course suggestions
    data.course.forEach(course => {
        let courseLink = document.createElement("a");
        courseLink.setAttribute("onclick", "search_suggestion_course_click(this)");
        let suggestionCourse = document.createElement("div");
        suggestionCourse.classList.add("nav-search-suggestion-course", "flex-row");
        suggestionCourse.setAttribute("courseID", course.courseID);
        suggestionCourse.innerHTML = `
            <img src="${course.img}" alt="" />
            <div class="suggestion-course-detail flex-col">
                <h1>${course.courseTitle}</h1>
                <p>${course.courseAuthor}</p>
            </div>
        `;
        courseLink.appendChild(suggestionCourse);
        show_div.appendChild(courseLink);
    });
}

// event handler for suggestion query
function search_suggestion_click(e) {
    let query = e.querySelector(".suggest-query").innerText;
    window.location.href = "/course_hero/search?query=" + query;
}

// event handler for suggestion course
function search_suggestion_course_click(e) {
    let id = e.querySelector(".nav-search-suggestion-course").getAttribute("courseID");
    window.location.href = "/course_hero/course?id=" + id;
}

// event handler for popular search suggestion
function popular_search_click(e) {
    window.location.href = "/course_hero/search?query=" + e.innerText;
}

// move wishlist item to cart 
function moveTOCart(e) {
    const id = e.getAttribute("courseID");
    
}


