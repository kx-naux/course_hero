var signUpPages = document.querySelectorAll("div.sign-up-p");
var formProgress = document.querySelector("input#formProgress");
var errorField = document.querySelector("input#errorInput");
var errorMsg = document.querySelector("span#errorMsg");
var progressPoints = document.querySelectorAll("div.progress-point");
var progressLines = document.querySelectorAll("hr.progress-line");
var nextBtns = document.querySelectorAll("input.next-btn");
var backBtns = document.querySelectorAll("input.back-btn");
var inputs = document.querySelectorAll("div.sign-up-p input");
var selects = document.querySelectorAll("div.sign-up-p select");
var errorMsgs = document.querySelectorAll("div.sign-up-p p.invalid-msg");

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

    if (errorField.value !== "") {
        if (errorField.value !== "gender") {
            document.querySelector("div.sign-up-p input#" + errorField.value).classList.add("invalid-input");
        } else {
            document.querySelector("div.sign-up-p select#" + errorField.value).classList.add("invalid-input");
        }
    }

    if (errorMsg.innerText !== "") {
        showErrorMsg(errorMsg.innerText);
    }
});

// set error msg
function showErrorMsg(msg) {
    errorMsgs.forEach(errorMsg => {
        errorMsg.innerText = msg;
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
    var gender = document.querySelector("select#gender");

    // Check if email input is not null and matches the regex pattern
    email.value = email.value.trim();
    if (email.value === "") {
        // Handle empty email
        showErrorMsg("Please enter email address");
        email.classList.add("invalid-input");
        return;
    }
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email.value)) {
        // Handle invalid email format
        showErrorMsg("Invalid email address");
        email.classList.add("invalid-input");
        return;
    }

    // username null check and format check
    username.value = username.value.trim();
    if (username.value === "") {
        // Handle empty email
        showErrorMsg("Please enter username");
        username.classList.add("invalid-input");
        return;
    }
    const usernameRegex = /^[a-zA-Z0-9!#_]{8,}$/;
    if (!usernameRegex.test(username.value)) {
        // Handle invalid username format
        showErrorMsg("Invalid username. Username must contain only letters, digits, or the following special symbols: !, #, _ and have a minimum length of 8 characters.");
        username.classList.add("invalid-input");
        return;
    }

    // check name null check and format check
    name.value = name.value.trim();
    if (name.value === "") {
        // Handle empty email
        showErrorMsg("Please enter name");
        name.classList.add("invalid-input");
        return;
    }
    const nameRegex = /^[a-zA-Z'-]+$/;
    if (!nameRegex.test(name.value)) {
        // Handle invalid name format
        showErrorMsg("Invalid name. Name must contain only letters, apostrophes, or hyphens.");
        name.classList.add("invalid-input");
        return;
    }

    // gender null check
    if (gender.value === "" || gender.value === "Select gender") {
        // Handle gender not selected
        showErrorMsg("Please select gender");
        gender.classList.add("invalid-input");
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

// For the username input field
document.querySelector("input#username").addEventListener('input', (event) => {
    const inputValue = event.target.value;
    const regex = /^[a-zA-Z0-9!#_.]*$/; // Regular expression to allow only characters, digits, and specified special symbols

    if (!regex.test(inputValue)) {
        event.target.value = inputValue.slice(0, -1); // Remove the last character if it doesn't match the criteria
    }
});

// For the name input field
document.querySelector("input#name").addEventListener('input', (event) => {
    let inputValue = event.target.value;
    const regex = /^[a-zA-Z' ,\-]*$/; // Regular expression to allow only characters, single quotes, commas, and hyphens

    if (!regex.test(inputValue)) {
        event.target.value = inputValue.slice(0, -1); // Remove the last character if it doesn't match the criteria
    } else {
        // Convert the input to title case
        inputValue = inputValue.toLowerCase().replace(/\b\w/g, (char) => char.toUpperCase());
        event.target.value = inputValue;
    }
});

// input and select remove invalid while onclick
inputs.forEach((input) => {
    input.addEventListener('blur', () => {
        input.classList.remove("invalid-input");
        removeErrorMsg();
    });
});

selects.forEach((select) => {
    select.addEventListener('blur', () => {
        select.classList.remove("invalid-input");
        removeErrorMsg();
    });
});

