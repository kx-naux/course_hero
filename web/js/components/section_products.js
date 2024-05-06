//Move to product page onclick
function redirectToProductPage(product) {
    const id = product.getAttribute('courseID');
    window.location.href = "/course_hero/course?id=" + id;
}

// check is user login
function isLogin() {
    return document.getElementById("isLogin") === null ? false : true;
}

//Add to cart / remove from cart
function cartButtonClick(evt) {
    evt.stopPropagation();

    if (!isLogin()) {
        toast_msg(TOAST_WARNING, "Alert", "Login before add to cart");
        return;
    }

    const courseID = evt.target.closest('.course-product').getAttribute('courseID');
    const url = '/course_hero/update-cart';
    const action = evt.target.closest(".cart-Btn").getAttribute("status") === '0' ? "add" : "remove";
    const data = {
        productID: courseID,
        action: action
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (!response.ok) {
            toast_msg(TOAST_ERROR, "Network Issue", "Fail to add to cart");
        }
        return response.json();
    }).then(responseData => {
        if (responseData.status === "success") {
            let icon = evt.target.closest(".cart-Btn").querySelector("i");
            let iconCode = Array.from(icon.classList)[0]; // Convert classList to an array

            // update the the cart list
            if (responseData.action === "add") {
                addCartItem(responseData);
                toast_msg(TOAST_SUCCESS, "Success", "Added to cart");
                icon.classList = iconCode.replace("line", "fill");
                evt.target.closest(".cart-Btn").setAttribute("status", "1");
            } else {
                removeCartItem(responseData);
                toast_msg(TOAST_SUCCESS, "Success", "Remove from cart");
                icon.classList = iconCode.replace("fill", "line");
                evt.target.closest(".cart-Btn").setAttribute("status", "0");
            }
        }
    }).catch(error => {
        console.error('Fetch error:', error);
    });

    console.log("Cart Clicked - Course ID:", courseID);
}

// add item into cart list
function addCartItem(data) {
    let cartlistDiv = document.getElementById("cartlistDiv");
    let cartlistLink = document.getElementById("cartlistLink");
    let cartlistPrice = document.getElementById("cartlistPrice");
    let cartEmpltyDiv = document.getElementById("cartlistEmpty");
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
        cartEmpltyDiv.classList.remove("active");
    }

    // update price
    cartlistPrice.innerText = (parseFloat(cartlistPrice.innerText) + data.productPrice).toFixed(2);
}

// remove item from cart list
function removeCartItem(data) {
    let cartlistDiv = document.getElementById("cartlistDiv");
    let cartlistLink = document.getElementById("cartlistLink");
    let cartlistPrice = document.getElementById("cartlistPrice");
    let cartEmpltyDiv = document.getElementById("cartlistEmpty");
    let cartlistNumber = document.getElementById("cartlistNumber");
    let cartlistItems = document.querySelectorAll("div#cartlistDiv div.course-item");

    let removeItem = cartlistDiv.querySelector(`div.course-item[productID='${data.productID}']`);

    removeItem.remove();

    // udpate cart number
    cartlistNumber.innerText = parseInt(cartlistNumber.innerText) - 1;

    // check list is empty before adding
    if (cartlistItems.length < 1) {
        cartlistDiv.classList.remove("active");
        cartlistLink.classList.remove("active");
        cartlistNumber.classList.remove("active");
        cartEmpltyDiv.classList.add("active");
    }

    // update price
    cartlistPrice.innerText = (parseFloat(cartlistPrice.innerText) - data.productPrice).toFixed(2);
}

//Add to wishlist / remove from wishlist
function likeButtonClick(evt) {
    evt.stopPropagation();

    if (!isLogin()) {
        toast_msg(TOAST_WARNING, "Alert", "Login before add to wishlist");
        return;
    }

    const courseID = evt.target.closest('.course-product').getAttribute('courseID');
    const url = '/course_hero/update-wishlist';
    const action = evt.target.closest(".wish-Btn").getAttribute("status") === "0" ? "add" : "remove";
    const data = {
        productID: courseID,
        action: action
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (!response.ok) {
            toast_msg(TOAST_ERROR, "Network Issue", "Fail to add to wishlist");
        }
        return response.json();
    }).then(responseData => {
        if (responseData.status === "success") {
            let icon = evt.target.closest(".wish-Btn").querySelector("i");
            let iconCode = Array.from(icon.classList)[0]; // Convert classList to an array

            // update the the cart list
            if (responseData.action === "add") {
                addWishItem(responseData);
                toast_msg(TOAST_SUCCESS, "Success", "Added to wishlist");
                icon.classList = iconCode.replace("line", "fill");
                evt.target.closest(".cart-Btn").setAttribute("status", "1");
            } else {
                removeWishItem(responseData);
                toast_msg(TOAST_SUCCESS, "Success", "Remove from wishlist");
                icon.classList = iconCode.replace("fill", "line");
                evt.target.closest(".cart-Btn").setAttribute("status", "0");
            }
        }
    }).catch(error => {
        console.error('Fetch error:', error);
    });

    console.log("Like Clicked - Course ID:", courseID);
}

function addWishItem(data) {
    var wishlistDiv = document.getElementById("wishlistDiv");
    var wishEmptyDiv = document.getElementById("wishlistEmpty");
}

function removeWishItem(data) {

}

//Add scroll detect for the div
document.addEventListener("DOMContentLoaded", function () {
    const coursesDivs = document.querySelectorAll(".courses");
    const spdScrollLatest = 0.2 * window.screen.width;

    coursesDivs.forEach(coursesDiv => {
        coursesDiv.addEventListener("wheel", (evt) => {
            evt.preventDefault();
            coursesDiv.scrollBy({
                left: evt.deltaY < 0 ? -spdScrollLatest : spdScrollLatest
            });
        });
    });

    // change the cart and wishlist
    var cartBtns = document.querySelectorAll("button.cart-Btn[status='1']");
    var wishBtns = document.querySelectorAll("button.wish-Btn[status='1']");

    cartBtns.forEach(btn => {
        var icon = btn.querySelector("i");
        var iconCode = Array.from(icon.classList)[0]; // Convert classList to an array
        icon.classList = iconCode.replace("line", "fill");
    });

    wishBtns.forEach(btn => {
        var icon = btn.querySelector("i");
        var iconCode = Array.from(icon.classList)[0]; // Convert classList to an array
        icon.classList = iconCode.replace("line", "fill");
    });
});
