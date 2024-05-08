const wishNumber = document.getElementById("wishNumber");
const wishContent = document.querySelector("div.wishlist-div");
const wishEmpty = document.querySelector("div.empty-div");
const removeBtns = document.querySelectorAll("button.remove-btn");
const moveBtns = document.querySelectorAll("button.move-btn");

// show or hide the div on load
document.addEventListener('DOMContentLoaded', () => {
    if (parseInt(wishNumber.innerText) < 1) {
        wishContent.style.display = "none";
        wishEmpty.style.display = "flex";
    } else {
        wishContent.style.display = "flex";
        wishEmpty.style.display = "none";
    }
});

// event listener for remove from wish
removeBtns.forEach(remove => {
    remove.addEventListener('click', (evt) => {

        const productID = evt.target.closest('.course-item').getAttribute('courseID');
        const url = '/course_hero/update-wishlist';
        const data = {
            productID: productID,
            action: "remove"
        };

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(response => {
            if (!response.ok) {
                toast_msg(TOAST_ERROR, "Network Issue", `Fail to remove from wishlist`);
            }
            return response.json();
        }).then(responseData => {
            if (responseData.status === "success") {
                removeWishItem(responseData);
                toast_msg(TOAST_SUCCESS, "Success", "Remove from wishlist");
            } else {
                toast_msg(TOAST_ERROR, "Server Error", `Fail to remove from wishlist`);
            }
        }).catch(error => {
            console.error('Fetch error:', error);
        });

        console.log("Remove from wishlist - Course ID:", productID);

    });
});

function removeWishItem(data) {
    let wishlistDiv = document.getElementById("wishlistDiv");
    let wishEmptyDiv = document.getElementById("wishlistEmpty");
    let wishlistLink = document.getElementById("wishlistLink");
    let wishlistItems = document.querySelectorAll("div#wishlistDiv div.course-item");

    let removeItem = wishlistDiv.querySelector(`div.flex-col:has(div.course-move-cart-div button.move-cart-btn[courseID="${data.productID}"])`);
    removeItem.remove();

    let removeItemFromPage = document.querySelector(`ul#courseList div.course-item[courseID="${data.productID}"]`);
    removeItemFromPage.remove();

    //update number show
    let wishlistNumber = document.getElementById("wishNumber");
    wishlistNumber.innerText = parseInt(wishlistNumber.innerText) - 1;

    // check list is empty before adding
    if (wishlistItems.length <= 1) {
        wishlistDiv.classList.remove("active");
        wishlistLink.classList.remove("active");
        wishEmptyDiv.classList.add("active");
        wishContent.style.display = "none";
        wishEmpty.style.display = "flex";
    }
    
    updateWishItemNumber();
}

// event listener for move to cart
moveBtns.forEach(move => {
    move.addEventListener('click', (evt) => {
        const url = '/course_hero/update-cart';
        const courseID = evt.target.closest('.course-item').getAttribute('courseID');
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
    });
});

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

function updateWishItemNumber() {
    let itemNumber = document.getElementById("wishNumber");
    let count = document.querySelectorAll("ul#courseList div.course-item").length;
    let noun = document.getElementById("wishNumberNoun");

    itemNumber.innerText = count;
    noun.innerText = count > 1 ? "courses" : "course";

    const wishContent = document.querySelector("div.wishlist-div");
    const wishEmpty = document.querySelector("div.empty-div");
        
    if (count < 1) {
        wishContent.style.display = "none";
        wishEmpty.style.display = "flex";
    } else {
        wishContent.style.display = "flex";
        wishEmpty.style.display = "none";
    }
}