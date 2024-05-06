<style>
.theme-switch {
  position: relative;
  display: flex;
  justify-content: center;
  direction: rtl;
}

#theme-checkbox {
  display: none;
}

#theme-checkbox + label {
  /* change the font-size below to change the size of the switch button*/
  font-size: 2rem;
  height: 1em;
  width: 2.5em;
  border-radius: 0.25em;
  cursor: pointer;
  display: flex;
  justify-content: space-between;
  background-color: #e2e3f0;
  position: relative;
}

#theme-checkbox:checked + label {
  background-color: #3a3a3a;
}

#theme-checkbox + label div {
  width: 0.8em;
  height: 0.8em;
  border-radius: inherit;
  position: absolute;
  top: 0.1em;
  left: 0.1em;
  z-index: 10;
  transition: 0.5s cubic-bezier(1, 0.33, 0.11, 1.34);
  background-color: #f6f6f9;
}

#theme-checkbox:checked + label div {
  /* left: calc(2.5em - .8em - .1em); */
  left: 1.6em;
  background-color: #181a1e;
}

#theme-checkbox + label span {
  display: flex;
}

#theme-checkbox + label svg {
  display: inline-block;
  height: 1em;
  width: 1em;
  padding: 0.15em;
  box-sizing: border-box;
}

#theme-checkbox + label span:first-of-type {
  color: #363949;
}

#theme-checkbox + label span:last-of-type {
  color: #cecece;
}

</style>
<div class="theme-switch">
  <input type="checkbox" id="theme-checkbox" />
  <label for="theme-checkbox">
    <div></div>
    <span>
      <svg
        xmlns="http://www.w3.org/2000/svg"
        viewBox="0 0 24 24"
        fill="currentColor"
        class="w-6 h-6"
      >
        <path
          fill-rule="evenodd"
          d="M9.528 1.718a.75.75 0 01.162.819A8.97 8.97 0 009 6a9 9 0 009 9 8.97 8.97 0 003.463-.69.75.75 0 01.981.98 10.503 10.503 0 01-9.694 6.46c-5.799 0-10.5-4.701-10.5-10.5 0-4.368 2.667-8.112 6.46-9.694a.75.75 0 01.818.162z"
          clip-rule="evenodd"
        ></path>
      </svg>
    </span>
    <span>
      <svg
        xmlns="http://www.w3.org/2000/svg"
        viewBox="0 0 24 24"
        fill="currentColor"
        class="w-6 h-6"
      >
        <path
          d="M12 2.25a.75.75 0 01.75.75v2.25a.75.75 0 01-1.5 0V3a.75.75 0 01.75-.75zM7.5 12a4.5 4.5 0 119 0 4.5 4.5 0 01-9 0zM18.894 6.166a.75.75 0 00-1.06-1.06l-1.591 1.59a.75.75 0 101.06 1.061l1.591-1.59zM21.75 12a.75.75 0 01-.75.75h-2.25a.75.75 0 010-1.5H21a.75.75 0 01.75.75zM17.834 18.894a.75.75 0 001.06-1.06l-1.59-1.591a.75.75 0 10-1.061 1.06l1.59 1.591zM12 18a.75.75 0 01.75.75V21a.75.75 0 01-1.5 0v-2.25A.75.75 0 0112 18zM7.758 17.303a.75.75 0 00-1.061-1.06l-1.591 1.59a.75.75 0 001.06 1.061l1.591-1.59zM6 12a.75.75 0 01-.75.75H3a.75.75 0 010-1.5h2.25A.75.75 0 016 12zM6.697 7.757a.75.75 0 001.06-1.06l-1.59-1.591a.75.75 0 00-1.061 1.06l1.59 1.591z"
        ></path>
      </svg>
    </span>
  </label>
</div>
<!-- Right after the browser renders the sidebar -->
<script type="text/javascript">
    // If localStorage is supported by the browser
    if (typeof(Storage) !== "undefined") {
        // If we need to open the bar
        if(localStorage.getItem("theme") === "dark"){
        // change background
        document.documentElement.style.setProperty('--color-background', '#181a1e');
        document.documentElement.style.setProperty('--color-white', '#363949');
        document.documentElement.style.setProperty('--color-dark', '#edeffd');
        document.documentElement.style.setProperty('--color-dark-variant', '#a3bdcc');
        document.documentElement.style.setProperty('--color-light', 'rgba(0,0,0,0.4)'); 
        document.documentElement.style.setProperty('--filter', 'brightness(1.1)'); 
        
        // check the dark theme button
        document.getElementById("theme-checkbox").checked = true;
        
        }
    }
</script>
<script src="../admin_js/adminThemeToggler.js"></script>
