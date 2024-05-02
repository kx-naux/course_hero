const addQtyBtn = document.querySelector("button#order_qty_add");
const substractQtyBtn = document.querySelector("button#order_qty_substract");
const qtyInput = document.querySelector("input#order_qty");
const addToCartBtn = document.querySelector("button.add-cart-btn");
const reviewForm = document.querySelector("form#merchReview");
const previousBtn = document.querySelector("p.page-previous-btn");
const nextBtn = document.querySelector("p.page-next-btn");
const pageInput = document.querySelector("input#current_page");

// add button
addQtyBtn.addEventListener('click', () => {

    var qty = parseInt(qtyInput.value);

    if (qty < 99) {
        qtyInput.value = qty + 1;
    }

});

// substract button
substractQtyBtn.addEventListener('click', () => {

    var qty = parseInt(qtyInput.value);

    if (qty > 1) {
        qtyInput.value = qty - 1;
    }

});

// input field check value accept digit only first cannot be zero
qtyInput.addEventListener('input', (event) => {
     // Get the input value
    let inputValue = event.target.value;

    // Remove any non-digit characters
    inputValue = inputValue.replace(/\D/g, '');

    // Remove leading zeros
    inputValue = inputValue.replace(/^0+/, '');

    // Limit the value to 999
    if (parseInt(inputValue) > 99) {
        inputValue = inputValue.slice(0, 2);
    }

    // Update the input value
    event.target.value = inputValue;
});

//default must be 1
qtyInput.addEventListener('blur', function(event) {
    let inputValue = event.target.value;
    if (inputValue === '') {
        event.target.value = '1';
    }
});

// add to cart
addToCartBtn.addEventListener('click', () => {
    const id = document.querySelector("section.merch-section").getAttribute("merchID");
    
    console.log(id);
});

// pagination
previousBtn.addEventListener('click', (event) => {
    const btn = event.target;

    // not allow btn
    if (!btn.classList.contains("allow")) {
        return;
    }

    pageInput.value = parseInt(pageInput.value) - 1;
    reviewForm.submit();
});

nextBtn.addEventListener('click', (event) => {
    const btn = event.target;

    // not allow btn
    if (!btn.classList.contains("allow")) {
        return;
    }

    pageInput.value = parseInt(pageInput.value) + 1;
    reviewForm.submit();
});

// set event handler for page number
const pageNumbers = document.querySelectorAll("p.page-number");

// Loop through each element and add the event listener
pageNumbers.forEach(pageNumber => {
    pageNumber.addEventListener('click', (event) => {

        pageInput.value = pageNumber.innerText;
        reviewForm.submit();

    });
});