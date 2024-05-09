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
    'keyword': ["Python", "Java", "C++"],
    'course': [
        {
            'courseID': "1231231231",
            'img': "./img/course/beginner_excel.jpg",
            'courseTitle': "The Ultimate Course Programming",
            'courseAuthor': "Woo Yu Beng, Snijders"
        },
        {
            'courseID': "1231231231",
            'img': "./img/course/beginner_excel.jpg",
            'courseTitle': "The Ultimate Course Programming",
            'courseAuthor': "Woo Yu Beng, Snijders"
        },
        {
            'courseID': "1231231231",
            'img': "./img/course/beginner_excel.jpg",
            'courseTitle': "The Ultimate Course Programming",
            'courseAuthor': "Woo Yu Beng, Snijders"
        }
    ]
};

const searchInputField = document.getElementById("nav-search-input");
searchInputField.addEventListener("keyup", () => {
    if (searchInputField.value.length === 0) {
        document.querySelector("div.nav-search-result").innerHTML = "";
    } else {
        // get suggestino from server
        const url = '/course_hero/search-suggest';
        const keyword = searchInputField.value;
        const data = {keyword: keyword};

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(response => {
            if (!response.ok) {
                return null;
            }
            return response.json();
        }).then(responseData => {
            insert_suggestion(responseData);
        }).catch(error => {
            console.error('Fetch error:', error);
        });
    }
});

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


// move wish to cart
// move item from wish to cart
function moveToCart(evt) {
    evt.stopPropagation();

    const url = '/course_hero/update-cart';
    const courseID = evt.target.closest('.move-cart-btn').getAttribute('courseID');
    const data = {
        productID: courseID,
        action: "add",
        qty: 1
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (!response.ok) {
            toast_msg(TOAST_ERROR, "Network Issue", "Fail to move to cart");
        }
        return response.json();
    }).then(responseData => {
        if (responseData.status === "success") {
            addCartItem(responseData);

            const url = '/course_hero/update-wishlist';
            const data = {
                productID: courseID,
                action: "remove",
                qty: 1
            };

            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            }).then(response => {
                return response.json();
            }).then(responseData => {
                if (responseData.status === "success") {
                    removeWishItem(responseData);
                    toast_msg(TOAST_SUCCESS, "Success", "Move to cart");
                } else {
                    toast_msg(TOAST_ERROR, "Server Error", "Fail to move to cart");
                }
            }).catch(error => {
                console.error('Fetch error:', error);
            });
        } else {
            toast_msg(TOAST_ERROR, "Server Error", "Fail to move to cart");
        }
    }).catch(error => {
        console.error('Fetch error:', error);
    });

    console.log("Move to Cart Clicked - Course ID:", courseID);
}

// add item into cart list
function addCartItem(data) {
    let cartlistDiv = document.getElementById("cartlistDiv");
    let cartlistLink = document.getElementById("cartlistLink");
    let cartlistPrice = document.getElementById("cartlistPrice");
    let cartEmptyDiv = document.getElementById("cartlistEmpty");
    let cartlistNumber = document.getElementById("cartlistNumber");
    let cartlistItems = document.querySelectorAll("div#cartlistDiv div.course-item");

    if (data.productType === "course") {
        // Create a new cart item element
        let newItem = document.createElement("div");
        let newItemPrice = data.productPrice.toFixed(2);
        newItem.classList.add("course-item", "flex-row");
        newItem.setAttribute("productID", data.productID);
        newItem.setAttribute("productType", data.productType);
        newItem.innerHTML = `
            <div class="course-item-img">
                <img src="${data.productImgPath}" alt="" draggable="false" />
            </div>
            <div class="course-item-info flex-col">
                <h1 class="course-title">${data.productName}</h1>
                <p class="course-author">${data.productCategory}</p>
                <p class="course-price">RM ${newItemPrice}</p>
            </div>
         `;

        // insert item
        cartlistDiv.appendChild(newItem);

        // udpate cart number
        cartlistNumber.innerText = parseInt(cartlistNumber.innerText) + 1;
    } else if (data.productType === "merchandise") {

        // check is merch exist in list

    }

    // check list is empty before adding
    if (cartlistItems.length < 1) {
        cartlistDiv.classList.add("active");
        cartlistLink.classList.add("active");
        cartlistNumber.classList.add("active");
        cartEmptyDiv.classList.remove("active");
    }

    // update price
    cartlistPrice.innerText = (parseFloat(cartlistPrice.innerText) + data.productPrice).toFixed(2);
}

function removeWishItem(data) {
    let wishlistDiv = document.getElementById("wishlistDiv");
    let wishEmptyDiv = document.getElementById("wishlistEmpty");
    let wishlistLink = document.getElementById("wishlistLink");
    let wishlistItems = document.querySelectorAll("div#wishlistDiv div.course-item");

    let removeItem = wishlistDiv.querySelector(`div.flex-col:has(div.course-move-cart-div button.move-cart-btn[courseID="${data.productID}"])`);

    removeItem.remove();

    // check list is empty before adding
    if (wishlistItems.length <= 1) {
        wishlistDiv.classList.remove("active");
        wishlistLink.classList.remove("active");
        wishEmptyDiv.classList.add("active");
    }
}

document.addEventListener("DOMContentLoaded", function () {
    let cartlistDiv = document.getElementById("cartlistDiv");
    let cartlistLink = document.getElementById("cartlistLink");
    let cartEmptyDiv = document.getElementById("cartlistEmpty");
    let cartlistNumber = document.getElementById("cartlistNumber");
    let cartlistItems = document.querySelectorAll("div#cartlistDiv div.course-item");
    
    // check list is empty before adding
    if (cartlistItems.length < 1) {
        cartlistDiv.classList.remove("active");
        cartlistLink.classList.remove("active");
        cartlistNumber.classList.remove("active");
        cartEmptyDiv.classList.add("active");
    }
    
    let wishlistDiv = document.getElementById("wishlistDiv");
    let wishEmptyDiv = document.getElementById("wishlistEmpty");
    let wishlistLink = document.getElementById("wishlistLink");
    let wishlistItems = document.querySelectorAll("div#wishlistDiv div.course-item");

    // check list is empty before adding
    if (wishlistItems.length <= 1) {
        wishlistDiv.classList.remove("active");
        wishlistLink.classList.remove("active");
        wishEmptyDiv.classList.add("active");
    }
});