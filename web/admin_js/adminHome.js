let themeToggler = document.querySelector("#theme-checkbox");
//theme changer
themeToggler.addEventListener('click', () => {
    let currentValue = document.documentElement.style.getPropertyValue('--color-background');
    if(currentValue === '#181a1e'){
        document.documentElement.style.setProperty('--color-background', '#f6f6f9');
        document.documentElement.style.setProperty('--color-white', '#fff');
        document.documentElement.style.setProperty('--color-dark', '#363949');
        document.documentElement.style.setProperty('--color-dark-variant', '#677483');
        document.documentElement.style.setProperty('--color-light', 'rgba(132, 139, 200, 0.18)'); 
        // Save the state of the theme as "dark"
        localStorage.setItem("theme", "white");
    }
    else{
        document.documentElement.style.setProperty('--color-background', '#181a1e');
        document.documentElement.style.setProperty('--color-white', '#363949');
        document.documentElement.style.setProperty('--color-dark', '#edeffd');
        document.documentElement.style.setProperty('--color-dark-variant', '#a3bdcc');
        document.documentElement.style.setProperty('--color-light', 'rgba(0,0,0,0.4)'); 
        // Save the state of the theme as "dark"
        localStorage.setItem("theme", "dark");
    }
    themeToggler.querySelector('i:nth-child(1)').classList.toggle('active');
    themeToggler.querySelector('i:nth-child(2)').classList.toggle('active');
}); 