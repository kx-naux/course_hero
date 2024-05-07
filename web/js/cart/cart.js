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
        toast_msg(TOAST_ERROR,"Error",errorMsg.innerText);
    }
});
