// show success or errorMsg
document.addEventListener("DOMContentLoaded", () => {

    const errorMsg = document.getElementById("errorMsg");
    if (errorMsg !== null && errorMsg.innerText !== "") {
        toast_msg(TOAST_ERROR, "Error", errorMsg.innerText);
    }

    const successMsg = document.getElementById("successMsg");
    if (successMsg !== null && successMsg.innerText !== "") {
        toast_msg(TOAST_SUCCESS, "Sucessful", successMsg.innerText);
    }
});

// submit form when need refresh
function submitForm(action) {
    const form = document.getElementById("checkOutForm");
    const formAction = document.getElementById("fromAction");

    formAction.value = action;
    form.submit();
}

// listener for shippiing method
const shippingRadios = document.querySelectorAll('div.shipping-method');

shippingRadios.forEach(shippingMethod => {
    shippingMethod.addEventListener('click', function () {
        // Find the radio button inside the clicked shipping method div
        const radioButton = this.querySelector('input[type="radio"]');
        radioButton.checked = true;

        submitForm("shipping method change");

    });
});

// listener for address
if (document.querySelector(".stored-address-div") !== null) {

    document.addEventListener("DOMContentLoaded", () => {
        const confirmStoreDiv = document.querySelector("div.ship-div div.confirma-store-div");
        const confirmStoreBox = confirmStoreDiv.querySelector('input[type="checkbox"]');
        confirmStoreBox.checked = false;

        if (document.querySelector("div.current-address input[type='radio']:checked") !== null) {
            confirmStoreDiv.style.display = "none";
        } else {
            confirmStoreDiv.style.display = "flex";
        }
    });

    const addressRadios = document.querySelectorAll('div.address-div');

    addressRadios.forEach(addressDiv => {
        addressDiv.addEventListener('click', function () {
            // Find the radio button inside the clicked shipping method div
            const radioButton = this.querySelector('input[type="radio"]');
            radioButton.checked = true;

            const confirmStoreDiv = document.querySelector("div.ship-div div.confirma-store-div");
            const confirmStoreBox = confirmStoreDiv.querySelector('input[type="checkbox"]');
            confirmStoreBox.checked = false;

            if (document.querySelector("div.current-address input[type='radio']:checked") !== null) {
                confirmStoreDiv.style.display = "none";
            } else {
                confirmStoreDiv.style.display = "flex";
            }
        });
    });
}

// listener for address format
const postalCodeField = document.querySelector("input.postal-code");
if (postalCodeField !== null) {
    postalCodeField.addEventListener('input', (event) => {
        const inputValue = event.target.value;
        const regex = /^\d*$/; // Regular expression to allow only digits

        if (!regex.test(inputValue)) {
            event.target.value = inputValue.replace(/\D/g, ''); // Remove any non-digit characters
        }
    });
}

// listener for promo code format
const promoField = document.getElementById("promo");
if (promoField !== null) {
    promoField.addEventListener("input", (event) => {
        let inputValue = event.target.value;
        const regex = /^[a-zA-Z0-9]*$/;
        if (!regex.test(inputValue)) {
            inputValue = inputValue.replace(/[^a-zA-Z0-9]/g, '');
        }
        event.target.value = inputValue.toUpperCase();
    });
}

// show payment input div if checked
document.addEventListener("DOMContentLoaded", () => {
    showPaymentInputDiv();
});

// listener for payment method
const paymentRadios = document.querySelectorAll('div.payment-method');
paymentRadios.forEach(paymentMethod => {
    paymentMethod.addEventListener('click', function () {
        // Find the radio button inside the clicked shipping method div
        const radioButton = this.querySelector('input[type="radio"]');
        radioButton.checked = true;

        showPaymentInputDiv();

    });
});


document.addEventListener("DOMContentLoaded", () => {
    document.querySelector("div.payment-div div.confirma-store-div").style.display = "none";
});

function showPaymentInputDiv() {
    const paymentRadios = document.querySelectorAll('div.payment-method');
    const paymentInputDiv = document.querySelectorAll('div.payment-method-input');

    paymentInputDiv.forEach(inputDiv => {
        inputDiv.style.display = "none";
    });

    let selectedIndex = -1; // Default value if no radio button is checked

    paymentRadios.forEach((paymentMethod, index) => {
        const radio = paymentMethod.querySelector('input[type="radio"]');
        if (radio.checked) {
            selectedIndex = index;
        }
    });

    if (selectedIndex >= 0) {

        paymentInputDiv[selectedIndex].style.display = "flex";

        let checkbox = paymentInputDiv[selectedIndex].querySelector("input[type='checkbox']");
        if (checkbox !== null) {
            checkbox.checked = false;
        }

        const inputFields = paymentInputDiv[selectedIndex].querySelectorAll("div.payment-input-div");
        inputFields.forEach(field => {
            field.style.display = "flex";
        });
    }

    document.getElementById("storingPayment").checked = false;
    document.querySelector("div.payment-div  div.confirma-store-div").style.display = "flex";

    document.querySelector(".payment-div p.invalid-msg").innerText = "";
}

// credit card listener
const logo = {
    'visa': "./img/check_out/visa.png",
    'master': "./img/check_out/master.png",
    'american': "./img/check_out/american.png"
};

document.addEventListener("DOMContentLoaded", () => {

    const first4No = document.getElementById("storedCardNo").getAttribute("first");

    if (first4No !== null) {

        const cardLogo = document.getElementById("cardLogo");

        // Get the first digit of the credit card number
        const firstDigit = first4No.charAt(0);

        // Determine the card type based on the first digit
        let cardType;
        if (firstDigit === '4') {
            cardType = 'visa';
        } else if (firstDigit === '5') {
            cardType = 'master';
        } else if (firstDigit === '3') {
            cardType = 'american';
        } else {
            cardType = 'visa';
        }

        // Set the src attribute of cardLogo based on the card type
        cardLogo.src = logo[cardType];
    }
});

document.getElementById("cardHolder").addEventListener("input", function (event) {
    const inputText = event.target.value;
    const titleCaseText = toTitleCase(inputText);
    const validatedText = titleCaseText.replace(/[^a-zA-Z\s]/g, ''); // Remove any characters that are not letters or whitespace
    event.target.value = validatedText;
});

function toTitleCase(str) {
    return str.replace(/\b\w+/g, function (txt) {
        return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
    });
}

document.getElementById("cardNo").addEventListener("input", function (event) {
    const inputText = event.target.value;
    const digitsOnlyText = inputText.replace(/\D/g, ''); // Replace any non-digit characters with an empty string
    event.target.value = digitsOnlyText;
});

document.getElementById("expDate").addEventListener("input", function (event) {
    const inputText = event.target.value;

    // Remove any non-digit characters
    const cleanedInput = inputText.replace(/\D/g, '');

    // Format the input as MM/YY
    const formattedInput = cleanedInput.replace(/^(\d{2})(\d{0,2})$/, '$1/$2');

    // Update the input field with the formatted value
    event.target.value = formattedInput;
});

document.getElementById("ccv").addEventListener("input", function (event) {
    const inputText = event.target.value;
    const digitsOnlyText = inputText.replace(/\D/g, ''); // Replace any non-digit characters with an empty string
    event.target.value = digitsOnlyText;
});

// bank listener
document.getElementById("bankAccNo").addEventListener("input", function (event) {
    const inputText = event.target.value;
    const digitsOnlyText = inputText.replace(/\D/g, ''); // Replace any non-digit characters with an empty string
    event.target.value = digitsOnlyText;
});

// tng e-wallet listener
document.getElementById("tngPhoneNo").addEventListener("input", function (event) {
    const inputText = event.target.value;
    const digitsOnlyText = inputText.replace(/\D/g, ''); // Replace any non-digit characters with an empty string
    event.target.value = digitsOnlyText;
});


// listenr on user choose to user stored detail
const storedDetails = document.querySelectorAll("div.payment-method-input  label.storedDetailLabel");
if (storedDetails.length > 0) {
    storedDetails.forEach(detail => {
        detail.addEventListener('change', () => {

            const input = detail.querySelector("input");
            const confirmStoreDiv = document.querySelector("div.payment-div  div.confirma-store-div");
            const inputFields = detail.parentNode.querySelectorAll("div.payment-input-div");

            if (input.checked === true) {

                inputFields.forEach(field => {
                    field.style.display = "none";
                });

                confirmStoreDiv.style.display = "none";
                document.getElementById("storingPayment").checked = false;
            } else {
                inputFields.forEach(field => {
                    field.style.display = "flex";
                });

                confirmStoreDiv.style.display = "flex";
            }

        });
    });
}

// form submission
document.getElementById("placeOrderBtn").addEventListener("click", () => {
    submitCheckoutForm();
});

function submitCheckoutForm() {

    // address
    const addressDiv = document.querySelector(".check-address");
    const addressRadio = addressDiv.querySelector("input[type='radio']");
    const addressInvalidMsg = document.querySelector(".ship-div p.invalid-msg");

    if (addressRadio.checked) {
        const addressLine = addressDiv.querySelector(".address-1");
        if (addressLine.value === "") {
            addressLine.focus();
            addressInvalidMsg.innerText = "Please enter address";
            addressLine.classList.add("invalid-input");
            return;
        }

        const addressCity = addressDiv.querySelector(".city");
        if (addressCity.value === "") {
            addressCity.focus();
            addressInvalidMsg.innerText = "Please enter city";
            addressCity.classList.add("invalid-input");
            return;
        }

        const addressPostalCode = addressDiv.querySelector(".postal-code");
        if (addressPostalCode.value === "") {
            addressPostalCode.focus();
            addressInvalidMsg.innerText = "Please enter postal code";
            addressPostalCode.classList.add("invalid-input");
            return;
        }

        const addressState = addressDiv.querySelector(".state");
        if (addressState.value === "") {
            addressState.focus();
            addressInvalidMsg.innerText = "Please enter state";
            addressState.classList.add("invalid-input");
            return;
        }

        const addressCountry = addressDiv.querySelector(".country");
        if (addressCountry.value === "") {
            addressCountry.focus();
            addressInvalidMsg.innerText = "Please enter country";
            addressCountry.classList.add("invalid-input");
            return;
        }
    }

    // payment
    const payRadio = document.querySelector(".payment-div input[type='radio']:checked");
    const payInvalidMsg = document.querySelector(".payment-div p.invalid-msg");
    if (payRadio === null) {
        payInvalidMsg.innerText = "Please select a payment method";
        return;
    }

    const payCardDiv = document.querySelector(".payment-div .payment-card");
    const useStoredCard = document.querySelector(".payment-div input#storedCard:checked");
    if (document.querySelector(".payment-method-div input#cardMethod:checked") !== null && useStoredCard === null) {
        const cardHolder = payCardDiv.querySelector("#cardHolder");
        if (cardHolder.value === "") {
            cardHolder.focus();
            payInvalidMsg.innerText = "Please enter card holder name";
            cardHolder.classList.add("invalid-input");
            return;
        }

        const cardNumber = payCardDiv.querySelector("#cardNo");
        if (cardNumber.value === "") {
            cardNumber.focus();
            payInvalidMsg.innerText = "Please enter card no.";
            cardNumber.classList.add("invalid-input");
            return;
        }

        const cardNumberInput = document.getElementById("cardNo");
        const cardNumberValue = cardNumberInput.value.replace(/\s/g, ''); // Remove whitespace if any
        const cardNumberRegex = /^[0-9]{16}$/; // Regex to match 16 digits
        if (!cardNumberRegex.test(cardNumberValue)) {
            cardNumberInput.focus();
            payInvalidMsg.innerText = "Please enter a valid 16-digit card number";
            cardNumberInput.classList.add("invalid-input");
            return;
        }
        if (!isValidCardNumber(cardNumberValue)) {
            // 4556737586899855 valid number
            cardNumberInput.focus();
            payInvalidMsg.innerText = "Please enter a valid card number";
            cardNumberInput.classList.add("invalid-input");
            return;
        }

        const expDateInput = document.getElementById("expDate");
        const expDateValue = expDateInput.value;
        if (expDateValue === "") {
            expDateInput.focus();
            payInvalidMsg.innerText = "Please enter expiration date";
            expDateInput.classList.add("invalid-input");
            return;
        }
        if (!isValidExpDate(expDateValue)) {
            expDateInput.focus();
            payInvalidMsg.innerText = "Please enter a valid expiration date in the format MM/YY";
            expDateInput.classList.add("invalid-input");
            return;
        }

        const ccvInput = document.getElementById("ccv");
        const ccvValue = ccvInput.value;
        if (ccvValue === "") {
            ccvInput.focus();
            payInvalidMsg.innerText = "Please enter ccv";
            ccvInput.classList.add("invalid-input");
            return;
        }
        if (!isValidCCV(ccvValue)) {
            ccvInput.focus();
            payInvalidMsg.innerText = "Please enter a valid CCV consisting of 3 digits.";
            ccvInput.classList.add("invalid-input");
            return;
        }

    }

    const payBankDiv = document.querySelector(".payment-div .payment-bank");
    const useStoredBank = document.querySelector(".payment-div input#storedBank:checked");
    if (document.querySelector(".payment-method-div input#bankMethod:checked") !== null && useStoredBank === null) {
        const bankSelect = document.getElementById("bank");
        const selectedBank = bankSelect.value;
        if (selectedBank === "Select a bank") {
            bankSelect.focus();
            payInvalidMsg.innerText = "Please select a bank.";
            bankSelect.classList.add("invalid-input");
            return;
        }

        const bankAccNoInput = document.getElementById("bankAccNo");
        const bankAccNoValue = bankAccNoInput.value;
        if (bankAccNoValue === "") {
            bankAccNoInput.focus();
            payInvalidMsg.innerText = "Please enter account number";
            bankAccNoInput.classList.add("invalid-input");
            return;
        }
        if (!isValidBankAccountNumber(bankAccNoValue)) {
            bankAccNoInput.focus();
            payInvalidMsg.innerText = "Please enter a valid 12-digit bank account number.";
            bankAccNoInput.classList.add("invalid-input");
            return;
        }
    }

    const payTngDiv = document.querySelector(".payment-div .payment-tng");
    const useStoredTng = document.querySelector(".payment-div input#storedTng:checked");
    if (document.querySelector(".payment-method-div input#tngMethod:checked") !== null && useStoredTng === null) {
        const tngPhoneNoInput = document.getElementById("tngPhoneNo");
        const tngPhoneNoValue = tngPhoneNoInput.value;
        if (tngPhoneNoValue === "") {
            tngPhoneNoInput.focus();
            payInvalidMsg.innerText = "Please enter phone number";
            tngPhoneNoInput.classList.add("invalid-input");
            return;
        }
        if (!isValidMalaysianPhoneNumber(tngPhoneNoValue)) {
            tngPhoneNoInput.focus();
            payInvalidMsg.innerText = "Please enter a valid Malaysian phone number.";
            tngPhoneNoInput.classList.add("invalid-input");
            return;
        }
    }

    // submit form
    const form = document.getElementById("checkOutForm");
    form.submit();
}

function isValidCardNumber(cardNumber) {
    // Remove any whitespace from the card number
    cardNumber = cardNumber.replace(/\s/g, '');

    // Check if the card number is composed entirely of digits
    if (!/^\d+$/.test(cardNumber)) {
        return false;
    }

    // Check if the card number length is valid (16 digits)
    if (cardNumber.length !== 16) {
        return false;
    }

    // Apply the Luhn algorithm
    let sum = 0;
    let shouldDouble = false;
    for (let i = cardNumber.length - 1; i >= 0; i--) {
        let digit = parseInt(cardNumber.charAt(i), 10);

        if (shouldDouble) {
            digit *= 2;
            if (digit > 9) {
                digit -= 9;
            }
        }

        sum += digit;
        shouldDouble = !shouldDouble;
    }

    return (sum % 10) === 0;
}

function isValidExpDate(expDate) {
    // Remove any whitespace from the expiration date
    expDate = expDate.replace(/\s/g, '');

    // Check if the expiration date matches the MM/YY format
    if (!/^\d{2}\/\d{2}$/.test(expDate)) {
        return false;
    }

    // Extract month and year parts
    const [month, year] = expDate.split('/');
    const currentYear = new Date().getFullYear() % 100; // Get last two digits of current year

    // Check if month and year are valid
    if (
            isNaN(parseInt(month, 10)) ||
            isNaN(parseInt(year, 10)) ||
            parseInt(month, 10) < 1 ||
            parseInt(month, 10) > 12 ||
            parseInt(year, 10) < currentYear ||
            (parseInt(year, 10) === currentYear && parseInt(month, 10) < new Date().getMonth() + 1)
            ) {
        return false;
    }

    return true;
}

function isValidCCV(ccv) {
    // Remove any whitespace from the CCV
    ccv = ccv.replace(/\s/g, '');

    // Check if the CCV consists only of digits and has a length of 3
    if (!/^\d{3}$/.test(ccv)) {
        return false;
    }

    return true;
}

function isValidBankAccountNumber(accountNo) {
    // Remove any whitespace from the account number
    accountNo = accountNo.replace(/\s/g, '');

    // Check if the account number consists only of digits and has a length of 12
    if (!/^\d{8,12}$/.test(accountNo)) {
        return false;
    }

    return true;
}

function isValidMalaysianPhoneNumber(phoneNo) {
    // Remove any whitespace from the phone number
    phoneNo = phoneNo.replace(/\s/g, '');

    // Check if the phone number matches the Malaysian format (e.g., +60XXXXXXXXX)
    if (!/^(\+?6?0)-?1\d{8}$/.test(phoneNo)) {
        return false;
    }

    return true;
}

// listener for remove invalid class for address
const addressDiv = document.querySelector(".check-address");
if (addressDiv !== null) {
    const inputFields = addressDiv.querySelectorAll("input[type='text']");
    inputFields.forEach(input => {
        input.addEventListener('blur', () => {
            input.classList.remove("invalid-input");
            document.querySelector(".ship-div p.invalid-msg").innerText = "";
        });
    });
}

// listener remove invalid class for promo
const promoInput = document.getElementById("promo");
promoInput.addEventListener('blur', () => {
    promoInput.classList.remove("invalid-input");
    document.querySelector(".promo-div p.invalid-msg").innerText = "";
});

const paymentInputs = document.querySelectorAll(".payment-div input[type='text'], .payment-div select");
paymentInputs.forEach(input => {
    input.addEventListener('blur', () => {
        input.classList.remove("invalid-input");
        document.querySelector(".payment-div p.invalid-msg").innerText = "";
    });
});