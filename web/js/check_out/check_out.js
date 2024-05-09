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
    if (document.querySelector(".payment-method-div input#cardMethod:checked") !== null) {
        
    }

    const payBankDiv = document.querySelector(".payment-div .payment-bank");
    if (document.querySelector(".payment-method-div input#bankMethod:checked") !== null) {
        
    }

    const payTngDiv = document.querySelector(".payment-div .payment-tng");
    if (document.querySelector(".payment-method-div input#tngMethod:checked") !== null) {
        
    }

    // submit form
    const form = document.getElementById("checkOutForm");
    form.submit();
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