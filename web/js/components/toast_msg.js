let timeoutId; // Declare a variable to hold the timeout ID globally
const toast = document.querySelector(".toast");
const toast_progess = toast.querySelector(".toast-progress");
const toast_close = toast.querySelector(".toast-close");

const TOAST_ERROR = 1;
const TOAST_SUCCESS = 2;
const TOAST_WARNING = 3;
const TOAST_DEFAULT = 4;

/**
 * Displays a toast message with the specified type, title, and message.
 * 
 * @param {number} type - The type of toast message. Use one of the predefined constants: TOAST_ERROR, TOAST_SUCCESS, TOAST_WARNING, or TOAST_DEFAULT.
 * @param {string} title - The title of the toast message.
 * @param {string} message - The content of the toast message.
 */
function toast_msg(type = TOAST_DEFAULT, title, message) {
    clearTimeout(timeoutId); // Clear previous timeout
    toast_progess.classList.remove('active');
    toast_progess.classList.add("reset-animation");

    toast.className = 'toast';
    toast.classList.add("active");

    if (type === TOAST_ERROR) {
        toast.classList.add("toast-error");
    } else if (type === TOAST_SUCCESS) {
        toast.classList.add("toast-success");
    } else if (type === TOAST_WARNING) {
        toast.classList.add("toast-warning");
    }

    void toast_progess.offsetWidth; // Trigger reflow to apply CSS changes immediately
    toast_progess.classList.remove("reset-animation");
    toast_progess.classList.add("active");

    timeoutId = setTimeout(() => {
        toast.className = 'toast';
    }, 5000);
}

toast_close.addEventListener("click", () => {
    toast.className = 'toast';
});