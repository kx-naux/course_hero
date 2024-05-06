var signUpPages = document.querySelectorAll("div.forget-p");
var formProgress = document.querySelector("input#formProgress");
var errorField = document.querySelector("input#errorInput");
var errorMsg = document.querySelector("span#errorMsg");
var progressPoints = document.querySelectorAll("div.progress-point");
var progressLines = document.querySelectorAll("hr.progress-line");
var inputs = document.querySelectorAll("div.forget-p input");
var blurPasswordInputs = document.querySelectorAll("div.forget-p input.password-input");
var passwordEyes = document.querySelectorAll("div.forget-p span.password-eye");
var errorMsgs = document.querySelectorAll("div.forget-p p.invalid-msg");

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

//content load finish
document.addEventListener("DOMContentLoaded", (event) => {
    showSignUpPage(formProgress.value);

    if (errorField.value !== "") {
        document.querySelector("div.forget-p input#" + errorField.value).classList.add("invalid-input");
    }

    if (errorMsg.innerText !== "") {
        showErrorMsg(errorMsg.innerText);
    }

    if (formProgress.value == 2 && errorMsg.value !== "") {
        otpInvalid();
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

// input and select remove invalid while onclick
inputs.forEach((input) => {
    input.addEventListener('blur', () => {
        input.classList.remove("invalid-input");
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

// email form submission
document.getElementById("submitEmailBtn").addEventListener("click", () => {
    var emailField = document.getElementById("email");
    
    if (emailField.value === "") {
         showErrorMsg("Please enter email");
        emailField.classList.add("invalid-input");
        return;
    }
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(emailField.value)) {
        // Handle invalid email format
        showErrorMsg("Invalid email address");
        emailField.classList.add("invalid-input");
        return;
    }

    document.getElementById("emailForm").submit();
});