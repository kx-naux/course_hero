// cart button on click
if (document.querySelector("button.course-cart") !== null) {
    document.querySelector("button.course-cart").addEventListener('click', () => {
        const id = document.querySelector("section.course-section").getAttribute("courseID");
        console.log(id);
    });
}

// wishlist button on click
if (document.querySelector("button.course-wish") !== null) {
    document.querySelector("button.course-wish").addEventListener('click', () => {
        const id = document.querySelector("section.course-section").getAttribute("courseID");
        console.log(id);
    });
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
document.querySelector("span.close-btn").addEventListener('click', () => {
    reviewSection.classList.remove('active');
});

// add review form validation check
document.getElementById("addReviewBtn").addEventListener("click", () => {
    const rating = document.querySelector('div.review-rating input[name="rate"]:checked');

    if (rating === null) {
        document.getElementById("reviewInvalidMsg").innerText = "Please select rate";
        return;
    }

    const addReviewForm = document.getElementById("addReviewForm");
    addReviewForm.submit();
});

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
document.querySelector("button.show-more-btn").addEventListener('click', () => {

    // fetch more comment
    const courseID = document.querySelector("section.course-section").getAttribute("courseID");
    const lastID = document.getElementById("lastRatingId").value;

    const url = '/course_hero/get-course-review';
    const data = {
        courseID: courseID,
        lastID: lastID
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
        if (responseData.status === "success") {
            insertReview(responseData);
            console.log(responseData);
        } else {
            toast_msg(TOAST_ERROR, "Server Error", `Cannot load more review`);
        }
    }).catch(error => {
        console.error('Fetch error:', error);
    });

});

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