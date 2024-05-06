//Move to product page onclick
function redirectToProductPage(product) {
    const id = product.getAttribute('courseID');
    window.location.href = "/course_hero/course?id=" + id;
}

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
    const action = evt.target.getAttribute("status") === "0" ? "add" : "remove";
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
        console.log('Response from servlet:', responseData);
        toast_msg(TOAST_SUCCESS, "Success", "Added to cart");
    }).catch(error => {
        console.error('Fetch error:', error);
    });

    console.log("Cart Clicked - Course ID:", courseID);
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
    const action = evt.target.getAttribute("status") === "0" ? "add" : "remove";
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
        console.log('Response from servlet:', responseData);
        toast_msg(TOAST_SUCCESS, "Success", "Added to wishlist");
    }).catch(error => {
        console.error('Fetch error:', error);
    });

    console.log("Like Clicked - Course ID:", courseID);
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
