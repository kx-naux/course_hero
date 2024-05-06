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
    let maxValue = qtyInput.getAttribute("max");

    // Remove any non-digit characters
    inputValue = inputValue.replace(/\D/g, '');

    // Remove leading zeros
    inputValue = inputValue.replace(/^0+/, '');

    // Limit the value to 999
    if (parseInt(inputValue) > parseInt(maxValue)) {
        inputValue = maxValue;
    }

    // Update the input value
    event.target.value = inputValue;
});

//default must be 1
qtyInput.addEventListener('blur', function (event) {
    let inputValue = event.target.value;
    if (inputValue === '') {
        event.target.value = '1';
    }
});

// check is user login
function isLogin() {
    return document.getElementById("isLogin") === null ? false : true;
}

// add to cart
addToCartBtn.addEventListener('click', () => {
    if (!isLogin()) {
        toast_msg(TOAST_WARNING, "Alert", "Login before add to cart");
        return;
    }

    const url = '/course_hero/update-cart';
    const merchId = document.querySelector("section.merch-section").getAttribute("merchID");
    const data = {
        productID: merchId,
        action: "add",
        qty: parseInt(qtyInput.value)
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (!response.ok) {
            toast_msg(TOAST_ERROR, "Network Issue", `Fail to add to cart`);
        }
        return response.json();
    }).then(responseData => {
        if (responseData.status === "success") {
            addCartItem(responseData);
            toast_msg(TOAST_SUCCESS, "Success", "Added to cart");
        } else {
            toast_msg(TOAST_ERROR, "Server Error", `Fail to add to cart`);
        }
    }).catch(error => {
        console.error('Fetch error:', error);
    });

    console.log("Add to cart:" + merchId);
});

// add item into cart list
function addCartItem(data) {
    let cartlistDiv = document.getElementById("cartlistDiv");
    let cartlistLink = document.getElementById("cartlistLink");
    let cartlistPrice = document.getElementById("cartlistPrice");
    let cartEmptyDiv = document.getElementById("cartlistEmpty");
    let cartlistNumber = document.getElementById("cartlistNumber");
    let cartlistItems = document.querySelectorAll("div#cartlistDiv div.course-item");

    if (data.productType === "merchandise") {

        // check is merch exist in list
        let sameItem = document.querySelector(`div#cartlistDiv div.course-item[productid="${data.productID}"]`);

        if (sameItem === null) {
            // insert new item
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
                <div class="flex-row">
                    <p class="course-price">RM ${newItemPrice}</p>
                    <p class="merch-qty">Qty: <span id="merch-span-qty">${data.quantity}</span></p>
                </div>
            </div>
            `;

            // insert item
            cartlistDiv.appendChild(newItem);

            // udpate cart number
            cartlistNumber.innerText = parseInt(cartlistNumber.innerText) + 1;
        } else {
            // update existed data            
            let sameItemQty = sameItem.querySelector("p.merch-qty span");
            sameItemQty.innerText = parseInt(sameItemQty.innerText) + data.quantity;
        }

        // update price
        cartlistPrice.innerText = (parseFloat(cartlistPrice.innerText) + (data.productPrice * data.quantity)).toFixed(2);
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