// show clear filter if there is filter
document.addEventListener('DOMContentLoaded', () => {
    const filterActive = document.querySelectorAll("div.filter-div.active");

    if (filterActive.length > 0) {
        document.querySelector("button.clear-filter-btn").classList.add("active");
        document.querySelector("span.filter-count").innerText = "(" + filterActive.length + ")";
    }
});

// Get all elements with the class "filter-div"
const filterDivs = document.querySelectorAll("div.filter-type-title");

// Loop through each element and add the event listener
filterDivs.forEach(divElement => {
    divElement.addEventListener('click', (event) => {

        // Target the parent div element with class "filter-div"
        const divElement = event.target.parentElement;

        // Toggle the 'active' class on the div
        if (divElement.classList.contains("active")) {
            divElement.classList.remove('active');
        } else {
            divElement.classList.add('active');
        }
    });
});

// set event handler for pagination button 
const searchForm = document.querySelector("form#searchForm");
const previousBtn = document.querySelector("p.page-previous-btn");
const nextBtn = document.querySelector("p.page-next-btn");
const pageInput = document.querySelector("input#current_page");

previousBtn.addEventListener('click', (event) => {
    const btn = event.target;

    // not allow btn
    if (!btn.classList.contains("allow")) {
        return;
    }

    pageInput.value = parseInt(pageInput.value) - 1;
    searchForm.submit();
});

nextBtn.addEventListener('click', (event) => {
    const btn = event.target;

    // not allow btn
    if (!btn.classList.contains("allow")) {
        return;
    }

    pageInput.value = parseInt(pageInput.value) + 1;
    searchForm.submit();
});

// set event handler for page number
const pageNumbers = document.querySelectorAll("p.page-number");

// Loop through each element and add the event listener
pageNumbers.forEach(pageNumber => {
    pageNumber.addEventListener('click', (event) => {

        pageInput.value = pageNumber.innerText;
        searchForm.submit();

    });
});

// goto product page
function redirectToProductPage(e) {
    const id = e.getAttribute('courseID');
    window.location.href = "/course_hero/course?id=" + id;
}