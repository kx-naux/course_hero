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
var blurPasswordInputs = document.querySelectorAll("div.sign-up-p input.password-input");
var passwordEyes = document.querySelectorAll("div.sign-up-p span.password-eye");
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
    var dob = document.querySelector("input#dob");

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


// view and hide password
passwordEyes.forEach((eye) => {
    eye.addEventListener('click', () => {
        // Get the input element associated with this eye icon
        var passwordInput = eye.parentElement.querySelector('input');
        var eyeIcon = eye.querySelector("i");
        eyeIcon.classList = "";

        // Toggle the type attribute between "password" and "text"
        if (passwordInput.type === "password") {
            passwordInput.type = "text";
            eyeIcon.classList.add("ri-eye-off-line");
        } else {
            passwordInput.type = "password";
            eyeIcon.classList.add("ri-eye-line");
        }
    });
});

// able submit button
const passwordInput = document.getElementById("password");
const confirmPasswordInput = document.getElementById("password2");
const termAgreeCheckbox = document.getElementById("termAgree");
const submitButton = document.querySelector("input.sign-up-btn.submit-btn");

// Function to check if the password meets the requirements
function isPasswordValid(password) {
    // Define regular expressions for each requirement
    const uppercaseRegex = /[A-Z]/;
    const lowercaseRegex = /[a-z]/;
    const digitRegex = /\d/;
    const symbolRegex = /[$&+,:;=?@#|'<>.^*()%!-]/;
    const lengthRequirement = password.length >= 8;

    // Check if all requirements are met
    return (
            uppercaseRegex.test(password) &&
            lowercaseRegex.test(password) &&
            digitRegex.test(password) &&
            symbolRegex.test(password) &&
            lengthRequirement
            );
}

// Function to check if the passwords match
function doPasswordsMatch() {
    return passwordInput.value === confirmPasswordInput.value;
}

// Function to check if all conditions are met and enable the submit button
function checkConditions() {
    const passwordValid = isPasswordValid(passwordInput.value);
    const passwordsMatch = doPasswordsMatch();
    const termsAgreed = termAgreeCheckbox.checked;

    submitButton.disabled = !(passwordValid && passwordsMatch && termsAgreed);
}

// Function to update the icon based on whether each requirement is met
function updateRequirementIcons(password) {
    // Define regular expressions for each requirement
    const requirements = [
        /[A-Z]/, // Include at least 1 uppercase letter
        /[a-z]/, // Include at least 1 lowercase letter
        /\d/, // Include at least 1 digit
        /[$&+,:;=?@#|'<>.^*()%!-]/, // Include at least 1 symbol
        /.{8,}/  // Have a minimum length of 8
    ];

    const requirementListItems = document.querySelectorAll(".input-requirement li");

    // Loop through each requirement and update the corresponding icon
    requirements.forEach((regex, index) => {
        const listItem = requirementListItems[index];
        const icon = listItem.querySelector("i");
        if (regex.test(password)) {
            icon.classList.remove("ri-close-circle-line");
            icon.classList.add("ri-checkbox-circle-fill");
        } else {
            icon.classList.remove("ri-checkbox-circle-fill");
            icon.classList.add("ri-close-circle-line");
        }
    });
}

confirmPasswordInput.addEventListener('input', checkConditions);
termAgreeCheckbox.addEventListener('change', checkConditions);
passwordInput.addEventListener('input', (event) => {
    checkConditions();
    updateRequirementIcons(event.target.value);
});

// otp field
function handleOTPInput(inputElement) {
    const maxLength = parseInt(inputElement.getAttribute('maxlength'));
    let enteredValue = inputElement.value;

    // Remove non-digit characters
    enteredValue = enteredValue.replace(/\D/g, '');

    // Limit entered value to maximum length
    enteredValue = enteredValue.slice(0, maxLength);

    inputElement.value = enteredValue;

    if (enteredValue.length >= maxLength) {
        // If current input is at max length, focus on the next input
        const nextInput = inputElement.nextElementSibling;
        if (nextInput) {
            nextInput.focus();
        }

        // Check if all OTP input fields are filled
        const allInputsFilled = Array.from(document.querySelectorAll('.otp')).every(input => input.value.length === maxLength);

        // Enable submit button if all inputs are filled
        if (allInputsFilled) {
            document.querySelector('div.otp-submit-div .submit-btn').disabled = false;
        }
    } else {
        // If any input field is not filled, disable the submit button
        document.querySelector('div.otp-submit-div .submit-btn').disabled = true;
    }
}

// Function to handle backspace key press
function handleBackspace(inputElement) {
    const previousInput = inputElement.previousElementSibling;
    if (previousInput && inputElement.value === '') {
        // If backspace is pressed and the current input is empty, focus on the previous input
        previousInput.focus();
    }
}

// Add event listeners to OTP input fields
document.querySelectorAll('.otp').forEach(input => {
    input.addEventListener('input', function () {
        handleOTPInput(this);
    });

    input.addEventListener('keydown', function (event) {
        if (event.key === 'Backspace') {
            handleBackspace(this);
        }
    });
});

// otp onsubmit
// Function to compile OTP code
function compileOTPCode(event) {
    const otpInputs = document.querySelectorAll('.otp');
    let otpValue = '';

    // Concatenate values of all OTP input fields
    otpInputs.forEach(input => {
        otpValue += input.value;
    });

    // Store compiled OTP value into hidden input field
    const otpHiddenInput = document.getElementById('otp');
    otpHiddenInput.value = otpValue;
}

function otpInvalid() {
    document.querySelectorAll('.otp').forEach(input => {
        input.classList.add("invalid-otp");
    });
}

function removeotpInvalid() {
    document.querySelectorAll('.otp').forEach(input => {
        input.classList.remove("invalid-otp");
    });
}