const signUpPages = document.querySelectorAll("div.sign-up-p");
const formProgress = document.querySelector("input#formProgress");
const progressPoints = document.querySelectorAll("div.progress-point");
const progressLines = document.querySelectorAll("hr.progress-line");
const nextBtns = document.querySelectorAll("input.next-btn");
const backBtns = document.querySelectorAll("input.back-btn");
const inputs = document.querySelectorAll("div.sign-up-p input");
const selects = document.querySelectorAll("div.sign-up-p select");
const errorMsgs = document.querySelectorAll("p.invalid-msg");

function showSignUpPage(n) {

    formProgress.value = n;

    // scroll to top
    window.scrollTo(0, 0);

    // Update progress points
    progressPoints.forEach((point, i) => {
        point.classList.remove("complete", "current");
        if (i + 1 < n) {
            point.classList.add("complete");
            point.querySelector("span").innerText = "✔";
        } else if (i + 1 == n) {
            point.classList.add("current");
            point.querySelector("span").innerText = i + 1;
        } else {
            point.querySelector("span").innerText = i + 1;
        }
    });

    // Update progress lines
    progressLines.forEach((line, i) => {
        line.classList.remove("complete");
        if (i + 1 < n) {
            line.classList.add("complete");
        }
    });

    // show coreesponding div
    signUpPages.forEach((page, i) => {
        if (i + 1 == n) {
            page.classList.add("active");
        } else {
            page.classList.remove("active");
        }
    });
}

// document on load update the progress bar
document.addEventListener("DOMContentLoaded", (event) => {
    showSignUpPage(formProgress.value);
});

// set error msg
function showErrorMsg(msg) {
    errorMsgs.forEach(msgBox => {
        msgBox.innerText = msg;
    });
}

function removeErrorMsg() {
    errorMsgs.forEach(msgBox => {
        msgBox.innerText = "";
    });
}

// next button event handler
nextBtns[0].addEventListener('click', () => {

    var email = document.querySelector("input#email");
    var username = document.querySelector("input#username");
    var name = document.querySelector("input#name");
    var gender = document.querySelector("input#gender");

    // Check if email input is not null and matches the regex pattern
    email.value = email.value.trim();
    if (email === '') {
        // Handle empty email
        showErrorMsg("Enter email address");
        return;
    }
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email.value)) {
        // Handle invalid email format
        showErrorMsg("invalid email address");
        return;
    }

    showSignUpPage(parseInt(formProgress.value) + 1);
});

nextBtns[1].addEventListener('click', () => {

    showSignUpPage(parseInt(formProgress.value) + 1);
});

// back button event handler
backBtns.forEach(btn => {
    btn.addEventListener('click', () => {
        showSignUpPage(parseInt(formProgress.value) - 1);
    });
});

// on submit validation check 

// force input policy for certain input field

// input and select remove invalid while onclick
inputs.forEach((input) => {
    input.addEventListener('click', () => {
        input.classList.remove("invalid-input");
        removeErrorMsg();
    });
});

selects.forEach((select) => {
    select.addEventListener('click', () => {
        select.classList.remove("invalid-input");
        removeErrorMsg();
    });
});

