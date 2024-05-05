var profilePages = document.querySelectorAll("div.profile-right-page");
var profileSidebarLi = document.querySelectorAll("ul.profile-sidebar li");
var errorMsgs = document.querySelectorAll("p.invalid-msg");
var inputs = document.querySelectorAll("div.profile-right-page input");
var selects = document.querySelectorAll("div.profile-right-page select");

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

// function change active page
function showPage(n) {
    var idx = parseInt(n-1);
    for (let i = 0; i < profilePages.length; i++) {
        profilePages[i].classList.remove("active");
        profileSidebarLi[i].classList.remove("active");
    }

    profilePages[idx].classList.add("active");
    profileSidebarLi[idx].classList.add("active");
}

document.addEventListener('DOMContentLoaded', () => {

    // show corresponding page to user base on hidden value
    var successMsg = document.querySelector("input#succssMsg").value;
    if (successMsg !== null && successMsg !== "") {
        toast_msg(TOAST_SUCCESS, "Sucessful", successMsg);
    }

    // show success msg base on the hidden value
    var n = document.querySelector("input#profilePage").value;
    showPage(n);

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

// for the dob input field
const startDate = new Date(1900, 0, 1);
const currentDate = new Date();
const endDate = new Date(currentDate.getFullYear() - 3, currentDate.getMonth(), currentDate.getDate());
var dobInput = document.querySelector("input#dob");
dobInput.setAttribute("min", startDate.toISOString().slice(0, 10));
dobInput.setAttribute("max", endDate.toISOString().slice(0, 10));

// for postal code input field
document.querySelector("input#postalCode").addEventListener('input', (event) => {
    const inputValue = event.target.value;
    const regex = /^\d*$/; // Regular expression to allow only digits

    if (!regex.test(inputValue)) {
        event.target.value = inputValue.replace(/\D/g, ''); // Remove any non-digit characters
    }
});

// validation
function form1_submit() {
    var name = document.querySelector("input#name");
    var gender = document.querySelector("select#gender");
    var dob = document.querySelector("input#dob");
    
    // check name null check and format check
    name.value = name.value.trim();
    if (name.value === "") {
        // Handle empty email
        showErrorMsg("Please enter name");
        name.classList.add("invalid-input");
        return;
    }
    const nameRegex = /^[a-zA-Z\s'-]+$/;
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

    // check date null check
    if (!dob.value) {
        // Handle gender not selected
        showErrorMsg("Please select date of birth");
        dob.classList.add("invalid-input");
        return;
    }
    
    document.querySelector("form#editProfileForm").submit();
}