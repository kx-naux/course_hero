<!-- Footer section -->
<section class="section footer-section">
    <div class="footer-div flex-col">
        <div class="footer-top flex-row">
            <div class="flex-col">
                <p class="footer-list-title">Courses</p>
                <ul class="footer-list">
                    <li><a href="<%= webpath.getPageUrl("products") %>">Catalog</a></li>
                    <li><a href="<%= webpath.getPageUrl("promotion")%>">Promotion</a></li>
                </ul>
            </div>
            <div class="flex-col">
                <p class="footer-list-title">Service</p>
                <ul class="footer-list">
                    <li><a href="#">Live Support</a></li>
                    <li><a href="#">Help center</a></li>
                    <li><a href="#">How to buy</a></li>
                    <li><a href="#">How to sell</a></li>
                </ul>
            </div>
            <div class="flex-col">
                <p class="footer-list-title">Company</p>
                <ul class="footer-list">
                    <li><a href="<%= webpath.getPageUrl("about us") %>">About</a></li>
                    <li><a href="#">News</a></li>
                    <li><a href="#">Investor</a></li>
                    <li><a href="#">Careers</a></li>
                    <li><a href="<%= webpath.getPageUrl("home") %>#faq">FAQs</a></li>
                    <li><a href="#">Term</a></li>
                </ul>
            </div>
            <!--contact div-->
            <div class="flex-col">
                <p class="footer-list-title">Contact</p>
                <p class="footer-contact"><a href="https://maps.app.goo.gl/HvkXdPKRUdNbj6Lh8" target="_blank">${contactLocation}</a></p>  
                <p class="footer-contact"><a href="tel:+601117608595" target="_blank"><i class="ri-phone-line"></i>${contactNo}</a></p>
                <p class="footer-contact"><a href="mailto:sales@coursehero.com" target="_blank"><i class="ri-mail-line"></i>${contactEmail}</a></p>
            </div>
        </div>
        <hr class="footer-divider" />
        <div class="footer-bot flex-row">
            <!--logo and copyright div-->
            <div class="footer-bot-left flex-row">
                <a href="<%= webpath.getPageUrl("home") %>"><img class="footer-logo" src=${companyLogo} alt=${companyName} /></a>
                <p>${copyright}</p>
            </div>
            <!--social media div-->
            <div class="footer-bot-right flex-row">
                <a><i class="ri-facebook-fill"></i></a>
                <a><i class="ri-linkedin-fill"></i></a>
                <a><i class="ri-youtube-fill"></i></a>
                <a><i class="ri-instagram-fill"></i></a>
                <a><i class="ri-tiktok-fill"></i></a>
            </div>
        </div>
    </div>
</section>