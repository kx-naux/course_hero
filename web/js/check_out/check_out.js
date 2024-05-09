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
    const addressRadios = document.querySelectorAll('div.address-div');

    addressRadios.forEach(addressDiv => {
        addressDiv.addEventListener('click', function () {
            // Find the radio button inside the clicked shipping method div
            const radioButton = this.querySelector('input[type="radio"]');
            radioButton.checked = true;

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
    }

    document.getElementById("storingPayment").checked = false;
}

// credit card listener
const logo = {
    'visa': "./img/check_out/visa.png",
    'master': "./img/check_out/master.png",
    'american': "./img/check_out/american.png"
};

document.addEventListener("DOMContentLoaded", () => {
    const first4No = document.getElementById("storedCardNo").getAttribute("first");
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
});

document.getElementById("cardHolder").addEventListener("input", function(event) {
    const inputText = event.target.value;
    const titleCaseText = toTitleCase(inputText);    
    const validatedText = titleCaseText.replace(/[^a-zA-Z\s]/g, ''); // Remove any characters that are not letters or whitespace
    event.target.value = validatedText;
});

function toTitleCase(str) {
    return str.replace(/\b\w+/g, function(txt) {
        return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
    });
}

document.getElementById("cardNo").addEventListener("input", function(event) {
    const inputText = event.target.value;
    const digitsOnlyText = inputText.replace(/\D/g, ''); // Replace any non-digit characters with an empty string
    event.target.value = digitsOnlyText;
});

document.getElementById("expDate").addEventListener("input", function(event) {
 const inputText = event.target.value;
    
    // Remove any non-digit characters
    const cleanedInput = inputText.replace(/\D/g, '');
    
    // Format the input as MM/YY
    const formattedInput = cleanedInput.replace(/^(\d{2})(\d{0,2})$/, '$1/$2');
    
    // Update the input field with the formatted value
    event.target.value = formattedInput;
});

document.getElementById("ccv").addEventListener("input", function(event) {
    const inputText = event.target.value;
    const digitsOnlyText = inputText.replace(/\D/g, ''); // Replace any non-digit characters with an empty string
    event.target.value = digitsOnlyText;
});

// bank listener

// tng e-wallet listener

// form submission
document.getElementById("placeOrderBtn").addEventListener("click", () => {
    submitCheckoutForm();
});

function submitCheckoutForm() {

    const form = document.getElementById("checkOutForm");
    form.submit();
}

