// set event handler for pagination button 
const searchForm = document.querySelector("form#manage-merch");
const previousBtn = document.querySelector(".pagination-links .prev-page");
const nextBtn = document.querySelector(".pagination-links .prev-page");
const pageInput = document.querySelector("input.current-page");

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


