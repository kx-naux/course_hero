const cartCourseNumber = document.getElementById("cartCourseNumber");
const cartMerchNumber = document.getElementById("cartMerchNumber");
const cartContent = document.querySelector("div.cart-div");
const cartEmpty = document.querySelector("div.empty-div");
const cartTitle = document.getElementById("cartCourseTitle");
const cartList = document.getElementById("cartCourseList");
const merchTitle = document.getElementById("cartMerchTitle");
const merchList = document.getElementById("cartMerchList");
const errorMsg = document.getElementById("errorMsg");

// show or hide the div on load
document.addEventListener('DOMContentLoaded', () => {
    if ((parseInt(cartCourseNumber.innerText) + parseInt(cartMerchNumber.innerText)) < 1) {
        cartContent.style.display = "none";
        cartEmpty.style.display = "flex";
    } else {
        cartContent.style.display = "flex";
        cartEmpty.style.display = "none";

        if (parseInt(cartCourseNumber.innerText) < 1) {
            cartTitle.style.display = "none";
            cartList.style.display = "none";
        }

        if (parseInt(cartMerchNumber.innerText) < 1) {
            merchTitle.style.display = "none";
            merchList.style.display = "none";
        }
    }

    if (errorMsg.innerText !== "") {
        toast_msg(TOAST_ERROR, "Error", errorMsg.innerText);
    }
});

// select all course
document.getElementById("courseAllCheckbox").addEventListener("change", () => {
    let items = document.querySelectorAll("ul#cartCourseList div.course-item input.cart-check");
    items.forEach(item => {
        item.checked = document.getElementById("courseAllCheckbox").checked;
    });
    selectItem();
});

// select all merchandise
document.getElementById("merchAllCheckbox").addEventListener("change", () => {
    let items = document.querySelectorAll("ul#cartMerchList div.course-item input.cart-check");
    items.forEach(item => {
        item.checked = document.getElementById("merchAllCheckbox").checked;
    });
    selectItem();
});

// select cart item to checkout
const checkboxes = document.querySelectorAll("div.course-item  input.cart-check");
checkboxes.forEach(item => {
    item.addEventListener('change', () => {
        selectItem();
    });
});

function selectItem() {
    let selectedItems = document.querySelectorAll("div.course-item:has(input.cart-check:checked)");

    // update number of item selected
    document.getElementById("numberSelected").innerText = selectedItems.length;

    let price = 0;
    let normalPrice = 0;
    let selectedCourses = document.querySelectorAll("ul#cartCourseList div.course-item:has(input.cart-check:checked)");
    let selectedMerchs = document.querySelectorAll("ul#cartMerchList div.course-item:has(input.cart-check:checked)");

    selectedCourses.forEach(item => {

        // Update total price
        price = price + parseFloat(item.querySelector("span.span-price").innerText);

        // Update total normal price
        normalPriceField = item.querySelector("span.span-normal-price");
        if (normalPriceField !== null) {
            normalPrice = normalPrice + parseFloat(normalPriceField.innerText);
        } else {
            normalPrice = normalPrice + parseFloat(item.querySelector("span.span-price").innerText);
        }
    });

    selectedMerchs.forEach(item => {

        // get qty
        let qty = parseInt(item.querySelector("input.merch-qty-input").value);

        // Update total price
        price = price + (parseFloat(item.querySelector("span.span-price").innerText) * qty);

        // Update total normal price
        normalPriceField = item.querySelector("span.span-normal-price");
        if (normalPriceField !== null) {
            normalPrice = normalPrice + (parseFloat(normalPriceField.innerText) * qty);
        } else {
            normalPrice = normalPrice + (parseFloat(item.querySelector("span.span-price").innerText) * qty);
        }
    });

    // update price
    document.getElementById("totalPrice").innerText = price.toFixed(2);

    // update normal price and percentage
    if (normalPrice > price) {
        document.querySelector("h1.total-nornmal-price").style.display = "block";

        // update price
        document.getElementById("totalNormalPrice").innerText = normalPrice.toFixed(2);

        // Calculate percentage
        let percentage = (((normalPrice - price) / normalPrice) * 100).toFixed(0);
        if (percentage > 0) {
            document.querySelector("p.discount-percentage").style.display = "block";
            document.getElementById("discount-percentage").innerText = percentage;
        } else {
            document.querySelector("p.discount-percentage").style.display = "none";
        }
    } else {
        document.querySelector("h1.total-nornmal-price").style.display = "none";
        document.querySelector("p.discount-percentage").style.display = "none";
    }
}

// control for qty
document.querySelectorAll('.merch-qty').forEach(function (container) {
    const input = container.querySelector('.merch-qty-input');
    const subtractButton = container.querySelector('.substract');
    const addButton = container.querySelector('.add');
    const checkbox = container.closest('.course-item').querySelector('.cart-check');

    subtractButton.addEventListener('click', function () {
        decrementValue(input);
        if (checkbox.checked) {
            selectItem();
        }
    });

    addButton.addEventListener('click', function () {
        incrementValue(input);
        if (checkbox.checked) {
            selectItem();
        }
    });
});

function incrementValue(input) {
    let value = parseInt(input.value, 10);
    value = isNaN(value) ? 1 : value;
    value = value < 99 ? value + 1 : 99;
    input.value = value;
}

function decrementValue(input) {
    let value = parseInt(input.value, 10);
    value = isNaN(value) ? 1 : value;
    value = value > 1 ? value - 1 : 1;
    input.value = value;
}

// update qty

// remove from cart

// move to wishlist

