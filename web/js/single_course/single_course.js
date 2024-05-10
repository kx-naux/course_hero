// check is user login
function isLogin() {
    return document.getElementById("isLogin") === null ? false : true;
}

const courseId = document.querySelector("section.course-section").getAttribute("courseID");

// cart button on click
const cartBtn = document.querySelector("button.course-cart");
if (cartBtn !== null) {
    cartBtn.addEventListener('click', () => {
        const status = cartBtn.getAttribute("status");

        if (status === '0') {
            if (!isLogin()) {
                toast_msg(TOAST_WARNING, "Alert", "Login before add to cart");
                return;
            }

            const url = '/course_hero/update-cart';
            const data = {
                productID: courseId,
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
                    toast_msg(TOAST_ERROR, "Network Issue", `Fail to add to cart`);
                }
                return response.json();
            }).then(responseData => {
                if (responseData.status === "success") {
                    addCartItem(responseData);
                    toast_msg(TOAST_SUCCESS, "Success", "Added to cart");
                    cartBtn.setAttribute("status", "1");
                    cartBtn.innerText = "Go to cart";
                } else {
                    toast_msg(TOAST_ERROR, "Server Error", `Fail to add to cart`);
                }
            }).catch(error => {
                console.error('Fetch error:', error);
            });
            console.log(courseId);
        } else {
            window.location.href = "http://localhost:8080/course_hero/cart";
        }
    });
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


// wishlist button on click
const wishBtn = document.querySelector("button.course-wish");
if (wishBtn !== null) {
    wishBtn.addEventListener('click', () => {
        if (!isLogin()) {
            toast_msg(TOAST_WARNING, "Alert", "Login before add to cart");
            return;
        }

        const status = wishBtn.getAttribute("status");
        const url = '/course_hero/update-wishlist';
        const action = status === "0" ? "add" : "remove";
        const data = {
            productID: courseId,
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
                toast_msg(TOAST_ERROR, "Network Issue", `Fail to ${action} to wishlist`);
            }
            return response.json();
        }).then(responseData => {
            if (responseData.status === "success") {
                let icon = wishBtn.querySelector("i");
                let iconCode = Array.from(icon.classList)[0]; // Convert classList to an array

                // update the the cart list
                if (responseData.action === "add") {
                    addWishItem(responseData);
                    toast_msg(TOAST_SUCCESS, "Success", "Added to wishlist");
                    icon.classList = iconCode.replace("line", "fill");
                    wishBtn.setAttribute("status", "1");
                } else {
                    removeWishItem(responseData);
                    toast_msg(TOAST_SUCCESS, "Success", "Remove from wishlist");
                    icon.classList = iconCode.replace("fill", "line");
                    wishBtn.setAttribute("status", "0");
                }
            } else {
                toast_msg(TOAST_ERROR, "Server Error", `Fail to ${action} to wishlist`);
            }
        }).catch(error => {
            console.error('Fetch error:', error);
        });


        console.log(courseId);
    });
}


function addWishItem(data) {
    let wishlistDiv = document.getElementById("wishlistDiv");
    let wishEmptyDiv = document.getElementById("wishlistEmpty");
    let wishlistLink = document.getElementById("wishlistLink");
    let wishlistItems = document.querySelectorAll("divwishlistDiv div.course-item");

    // Create a new cart item element
    let newItem = document.createElement("div");
    let newItemPrice = data.productPrice.toFixed(2);
    newItem.classList.add("flex-col");
    newItem.innerHTML = `
            <div class="course-item flex-row">
                <div class="course-item-img">
                    <img src="${data.productImgPath}" alt=""  draggable="false"  />
                </div>
                <div class="course-item-info flex-col">
                    <h1 class="course-title">${data.productName}</h1>
                    <p class="course-author">${data.productCategory}</p>
                    <p class="course-price">RM ${newItemPrice}</p>
                </div>
            </div>
            <div class="course-move-cart-div flex-col">
                <button class="move-cart-btn" courseID="${data.productID}" onclick="moveToCart(event)">Add to cart</button>
            </div>
         `;

    // insert item
    wishlistDiv.appendChild(newItem);

    // check list is empty before adding
    if (wishlistItems.length < 1) {
        wishlistDiv.classList.add("active");
        wishlistLink.classList.add("active");
        wishEmptyDiv.classList.remove("active");
    }
}

function removeWishItem(data) {
    let wishlistDiv = document.getElementById("wishlistDiv");
    let wishEmptyDiv = document.getElementById("wishlistEmpty");
    let wishlistLink = document.getElementById("wishlistLink");
    let wishlistItems = document.querySelectorAll("div#wishlistDiv div.flex-col");

    let removeItem = wishlistDiv.querySelector(`div.flex-col:has(div.course-move-cart-div button.move-cart-btn[courseID="${data.productID}"])`);

    removeItem.remove();

    // check list is empty before adding
    if (wishlistItems.length <= 1) {
        wishlistDiv.classList.remove("active");
        wishlistLink.classList.remove("active");
        wishEmptyDiv.classList.add("active");
    }
}

// click and copy link to clipboard
document.querySelector("button.share-btn").addEventListener('click', () => {

    // Copy the text inside the text field
    navigator.clipboard.writeText(window.location.href);

    // Alert the copied text
    toast_msg(TOAST_DEFAULT, "", "Link copied");
});

const reviewSection = document.querySelector("section.all-review-section");

// show all reviews button
document.querySelector("button.all-review-btn").addEventListener('click', () => {
    // Toggle the 'active' class on the div
    if (reviewSection.classList.contains("active")) {
        reviewSection.classList.remove('active');
    } else {
        reviewSection.classList.add('active');
    }
});

// close button for all review section
if (document.querySelector("span.close-btn") !== null) {
    document.querySelector("span.close-btn").addEventListener('click', () => {
        reviewSection.classList.remove('active');
    });
}

// add review form validation check
if (document.getElementById("addReviewBtn") !== null) {
    document.getElementById("addReviewBtn").addEventListener("click", () => {
        const rating = document.querySelector('div.review-rating input[name="rate"]:checked');

        if (rating === null) {
            document.getElementById("reviewInvalidMsg").innerText = "Please select rate";
            return;
        }

        const addReviewForm = document.getElementById("addReviewForm");
        addReviewForm.submit();
    });
}

// show / hide add review section
document.addEventListener("DOMContentLoaded", (event) => {
    const addReview = document.getElementById("addReviewStatus").value;
    var addReviewDiv = document.getElementById("addReviewDiv");

    if (addReview === "1") {
        addReviewDiv.style.display = "flex";
    } else {
        addReviewDiv.style.display = "none";
    }
});

// clear invalid msg after select rate
const rateInputs = document.querySelectorAll('div.review-rating input[name="rate"]');
rateInputs.forEach(input => {
    input.addEventListener("change", () => {
        document.getElementById("reviewInvalidMsg").innerText = "";
    });
});

// show more reviews button
if (document.querySelector("button.show-more-btn") !== null) {
    document.querySelector("button.show-more-btn").addEventListener('click', () => {

        // fetch more comment
        const courseID = document.querySelector("section.course-section").getAttribute("courseID");
        const lastID = document.getElementById("lastRatingId").value;
        const submitCount = document.getElementById("submitCount").value;

        const url = '/course_hero/get-course-review';
        const data = {
            courseID: courseID,
            lastID: lastID,
            submitCount: parseInt(submitCount)
        };

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(response => {
            if (!response.ok) {
                toast_msg(TOAST_ERROR, "Network Issue", `Cannot load more review`);
            }
            return response.json();
        }).then(responseData => {
            console.log(responseData);
            if (responseData.status === "success") {
                insertReview(responseData);
                document.getElementById("submitCount").value = responseData.submitCount;
                console.log(responseData);
            } else {
                toast_msg(TOAST_ERROR, "Server Error", `Cannot load more review`);
            }
        }).catch(error => {
            console.error('Fetch error:', error);
        });

    });
}

function insertReview(data) {
    let reviewsDiv = document.getElementById("reviewsDiv");

    // insert review
    data.reviews.forEach(reviewData => {
        let newReview = document.createElement("div");
        newReview.classList.add("user-review", "flex-col");

        // Create elements for user details
        let userTop = document.createElement("div");
        userTop.classList.add("user-review-top", "flex-row");

        let userImgDiv = document.createElement("div");
        userImgDiv.classList.add("user-img");
        let userImg = document.createElement("img");
        userImg.setAttribute("src", reviewData.userImgPath);
        userImg.setAttribute("alt", "User Image");
        userImgDiv.appendChild(userImg);

        let userDetails = document.createElement("div");
        userDetails.classList.add("flex-col");

        let userName = document.createElement("p");
        userName.classList.add("user-name");
        userName.textContent = reviewData.userName;

        let divRate = document.createElement("div");
        divRate.classList.add("review-top-bot", "flex-row");

        let ratingStars = document.createElement("div");
        ratingStars.classList.add("rating-stars", "flex-row");
        for (let i = 0; i < reviewData.rating; i++) {
            let star = document.createElement("i");
            star.classList.add("ri-star-fill");
            ratingStars.appendChild(star);
        }
        for (let i = 0; i < 5 - reviewData.rating; i++) {
            let star = document.createElement("i");
            star.classList.add("ri-star-line");
            ratingStars.appendChild(star);
        }

        let reviewDate = document.createElement("p");
        reviewDate.classList.add("review-date");
        reviewDate.textContent = reviewData.ratingDate;

        divRate.appendChild(ratingStars);
        divRate.appendChild(reviewDate);

        userDetails.appendChild(userName);
        userDetails.appendChild(divRate);

        userTop.appendChild(userImgDiv);
        userTop.appendChild(userDetails);

        // Create element for review comments
        let userBot = document.createElement("div");
        userBot.classList.add("user-review-bot", "flex-col");
        let userComments = document.createElement("p");
        userComments.textContent = reviewData.comments;
        userBot.appendChild(userComments);

        // Append user details and comments to the new review div
        newReview.appendChild(userTop);
        newReview.appendChild(userBot);

        // Append the new review div to the reviews container
        reviewsDiv.appendChild(newReview);
    });

    // move button to last
    let moreBtn = document.getElementById("showMoreReviewBtn");
    moreBtn.remove();

    reviewsDiv.appendChild(moreBtn);

    // disable button if reach max
    if (!data.isMore) {
        moreBtn.innerText = "No more reviews";
        moreBtn.disabled = true;
        moreBtn.style.opacity = 0.6;
    }
}