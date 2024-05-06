var profilePages = document.querySelectorAll("div.profile-right-page");
var profileSidebarLi = document.querySelectorAll("ul.profile-sidebar li");
var errorMsgs = document.querySelectorAll("p.invalid-msg");
var inputs = document.querySelectorAll("div.profile-right-page input");
var selects = document.querySelectorAll("div.profile-right-page select");
var passwordEyes = document.querySelectorAll("div.profile-right-page span.password-eye");

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
    var idx = parseInt(n - 1);
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

    // check is otp field div
    let otp = document.getElementById("otpDiv").value;
    if (n === '4' && otp == '1') {
        showOtpDiv();
    }
    
    let otpError = document.getElementById("otpError").value;
    if (n === '4' && otp === '1' && otpError === '1') {
        otpInvalid();
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

// show file path on input
document.getElementById('profilePic').addEventListener('change', function () {
    var input = this;
    var fileName = input.files[0].name;
    var fileLabel = input.parentNode.querySelector("span.file-value");
    var file = input.files[0];
    var reader = new FileReader();

    // show file name
    fileLabel.textContent = fileName;

    // show preview
    reader.onload = function (e) {
        document.getElementById('profilePreview').src = e.target.result;
    };

    if (file) {
        reader.readAsDataURL(file);
    }
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

document.querySelector("div.profile-right-page input.form-5-submit").addEventListener('click', () => {

    var closeAccForm = document.getElementById("closeAccountForm");
    var passwordField = document.getElementById("passwordCloseAcc");

    if (passwordField.value === "") {
        showErrorMsg("Please enter password");
        passwordField.classList.add("invalid-input");
        return;
    }

    closeAccForm.submit();

});

// able submit button
const passwordInput = document.getElementById("passwordChange");
const confirmPasswordInput = document.getElementById("passwordChange2");

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

passwordInput.addEventListener('input', (event) => {
    updateRequirementIcons(event.target.value);
});

// change password form submission
document.getElementById("changePasswordBtn").addEventListener('click', () => {

    if (passwordInput.value === "") {
        document.getElementById("changePassword").innerText = "Please enter password";
        passwordInput.classList.add("invalid-input");
        return;
    }
    if (confirmPasswordInput.value === "") {
        document.getElementById("changePassword").innerText = "Please enter password";
        confirmPasswordInput.classList.add("invalid-input");
        return;
    }
    if (!isPasswordValid(passwordInput.value)) {
        document.getElementById("changePassword").innerText = "Password does not follow the requiremnet";
        passwordInput.classList.add("invalid-input");
        return;
    }
    if (passwordInput.value !== confirmPasswordInput.value) {
        document.getElementById("changePassword").innerText = "Confirm password is not same with password";
        passwordInput.classList.add("invalid-input");
        confirmPasswordInput.classList.add("invalid-input");
        return;
    }

    document.getElementById("changePassowrdForm").submit();
});

var otpForm = document.getElementById("otpForm");
var formPage4 = document.querySelectorAll("div.profile-right-page-4 form");
var showOtpInput = document.getElementById("otpDiv");

// show otp div
function showOtpDiv() {
    if (showOtpInput.value === '1') {
        formPage4.forEach(formPage => {
            formPage.style.display = "none";
        });
        otpForm.style.display = "flex";
    } else {
        otpForm.style.display = "none";
    }
}

var newEmailField = document.getElementById("newEmail");

document.getElementById("changeEmailBtn").addEventListener('click', () => {
    if (newEmailField.value === "") {
        document.getElementById("changeEmail").innerText = "Please enter new email";
        newEmailField.classList.add("invalid-input");
        return;
    }
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(newEmailField.value)) {
        // Handle invalid email format
        document.getElementById("changeEmail").innerText = "Invalid email address";
        newEmailField.classList.add("invalid-input");
        return;
    }

    document.getElementById("changeEmailForm").submit();
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
        removeotpInvalid();
        if (event.key === 'Backspace') {
            handleBackspace(this);
        }
    });
});

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

// otp onsubmit
document.getElementById("otpSubmitBtn").addEventListener('click', () => {

    const otpInputs = document.querySelectorAll('.otp');
    let otpValue = '';

    // Concatenate values of all OTP input fields
    otpInputs.forEach(input => {
        otpValue += input.value;
    });

    // Store compiled OTP value into hidden input field
    const otpHiddenInput = document.getElementById('otp');
    otpHiddenInput.value = otpValue;

    if (otpValue.length !== 6) {
        // Handle invalid email format
        document.getElementById("otpVerify").innerText = "invalid OTP code";
        otpInvalid();
        return;
    }

    document.getElementById("otpForm").submit();
});