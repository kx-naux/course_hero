// set event handler for pagination button 
const searchForm = document.querySelector("form#authorForm");
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


