const wishNumber = document.getElementById("wishNumber");
const wishContent = document.querySelector("div.wishlist-div");
const wishEmpty = document.querySelector("div.empty-div");
const removeBtns = document.querySelectorAll("button.remove-btn");
const moveBtns = document.querySelectorAll("button.move-btn");

// show or hide the div on load
document.addEventListener('DOMContentLoaded',()=>{
    if (parseInt(wishNumber.innerText) < 1) {
        wishContent.style.display = "none";
        wishEmpty.style.display = "flex";
    }
    else {
        wishContent.style.display = "flex";
        wishEmpty.style.display = "none";
    }
});

// event listener for remove from wish
removeBtns.forEach(remove=>{
    remove.addEventListener('click',(evt)=>{
        
    });
});

// event listener for move to cart
moveBtns.forEach(move=>{
    move.addEventListener('click',(evt)=>{
        
    });
});

