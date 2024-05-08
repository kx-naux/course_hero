INSERT INTO Billing_Address (ADDRESS_ID, LINE_1, LINE_2, CITY, STATE_RESIDES, POSTALCODE, COUNTRY)
VALUES ('BA0000001', '123 Main St', 'Apt 101', 'Springfield', 'IL', '12345', 'USA'),
       ('BA0000002', '10 Downing Street', NULL, 'London', 'England', 'SW1A 2AA', 'UK'),
       ('BA0000003', '9 Bukti Aman', NULL, 'Bayan Lepas', 'Penang', '11600', 'Malaysia'),
       ('BA0000004', '101 Lorong Pine', 'Peace Residence', 'Selangor', 'Kuala Lumpur', '50450', 'Malaysia'),
       ('BA0000005', 'Gran Vía, 1', NULL, 'Madrid', 'Barcelona', '28013', 'Spain');

INSERT INTO PAYMENT_METHOD (PAYMENT_METHOD_ID, PAYMENT_NAME, ACCEPTED_CURRENCIES)
VALUES 
    ('PM00001', 'Credit Card', 'USD, EUR, MYR'),
    ('PM00002', 'PayPal', 'USD, GBP, MYR'),
    ('PM00003', 'Bank Transfer', 'USD, EUR, GBP, MYR'),
    ('PM00004', 'Cryptocurrency', 'BTC, ETH, XRP'),
    ('PM00005', 'Cash', 'MYR'),
    ('PM00006', 'TNG', 'MYR');

INSERT INTO PROMOTIONS (PROMOTION_ID,PROMO_DESCRIPTION,PROMOTION_NAME,PROMO_CODE, START_TIME, END_TIME, PROMO_TYPE, AMOUNT, MIN_REQ, LEGAL_TNC, STATUS, GEOGRAPHIC_RESTIRICTION)
VALUES 
    ('PO0000001','Discount 2020' ,'Happy Discoutn Hour from now until november','B102', '2024-04-15 00:00:00', '2024-04-30 23:59:59', 'Discount', 20.0, 100.0, 'Terms and conditions apply', 'Active', 'USA, Canada'),
    ('PO0000002','Buy 1 free 1 mayday', 'Happy buy one free one Hour from now until november','CA', '2024-05-01 00:00:00', '2024-05-15 23:59:59', 'Buy One Get One', 0.0, 50.0, 'Terms and conditions apply', 'Inactive', 'UK, France'),
    ('PO0000003','extra free shipping', 'Happy free shipping day from now until november','P9012', '2024-06-01 00:00:00', '2024-06-30 23:59:59', 'Free Shipping', 0.0, 75.0, 'Terms and conditions apply', 'Active', NULL),
    ('PO0000004','discount happy hour', 'Happy percentage discount from now until november','P2741','2024-07-01 00:00:00', '2024-07-31 23:59:59', 'Percentage Discount', 15.0, 80.0, 'Terms and conditions apply', 'Active', NULL),
    ('PO0000005','limited cashback now', 'Happy cashbck period from now until november','P231' ,'2024-08-01 00:00:00', '2024-08-15 23:59:59', 'Cashback', 10.0, 120.0, 'Terms and conditions apply', 'Inactive', 'Germany, Italy');

INSERT INTO CHATROOMS (CHATROOM_ID, CHATROOM_TYPE, STATUS, REQ_TITLE, REQ_DESCRIPTION)
VALUES 
    ('CH0000001', 'Public', 'Active', 'General Discussion', 'A place for general conversations and discussions.'),
    ('CH0000002', 'Private', 'Inactive', 'Project Planning', 'Discussion and planning for upcoming projects.'),
    ('CH0000003', 'Public', 'Active', 'Technical Support', 'Get help and support for technical issues.'),
    ('CH0000004', 'Private', 'Active', 'Team Collaboration', 'Collaboration space for team members.'),
    ('CH0000005', 'Public', 'Inactive', 'Event Coordination', 'Planning and coordination of upcoming events.');

INSERT INTO PRODUCT_CATEGORY (PRODCAT_ID, CATEGORY_NAME, DESCRIPTION,IMG_URL)
VALUES 
    ('PC0000001', 'Courses', 'Category for books.',null),
    ('PC0000002', 'Merchandise', 'Category for merchandise.',null);

INSERT INTO COURSE_CATEGORY (COURSECAT_ID, CATEGORY_NAME, DESCRIPTION,IMG_URL)
VALUES 
    ('CC0000001', 'Programming', 'Category for programming courses.',null),
    ('CC0000002', 'Business', 'Category for business-related courses.',''),
    ('CC0000003', 'Design', 'Category for design courses.',''),
    ('CC0000004', 'Language', 'Category for language learning courses.',''),
    ('CC0000005', 'Science', 'Category for science and technology courses.','');

INSERT INTO MERCH_CATEGORY (MERCHCAT_ID, CATEGORY_NAME, DESCRIPTION,IMG_URL)
VALUES 
    ('MC0000001', 'T-Shirts', 'Category for t-shirts.',''),
    ('MC0000002', 'Mugs', 'Category for mugs and cups.',''),
    ('MC0000003', 'Keychain', 'Category for keychains and souvenirs.',null),
    ('MC0000004', 'Accesories', 'Category for everyday accesories.',''),
    ('MC0000005', 'Stationaries', 'Category for stationaries.',null);

INSERT INTO AUTHORS (AUTHOR_ID,IMG_URL, AUTHOR_NAME, NATIONALITY, INSTITUTION, AWARDS_HONORS, BIOGRAPHY, DATE_JOINED,AUTHOR_POSITION, WEBSITE)
VALUES 
    ('AU0000001','', 'Lena Watson', 'American', 'Stanford University', 'Nobel Prize in Computer Science', 'Dr. Lena Watson is a renowned computer scientist at     Stanford University, known for her groundbreaking work in artificial intelligence and machine learning.', '2021-08-20', 'Manager At Mooiko',  'https://www.example.com/lenawatson'),
    ('AU0000002','', 'Max Chen', 'Chinese', 'Massachusetts Institute of Technology', 'Fields Medal in Mathematics', 'Dr. Max Chen is a brilliant mathematician at MIT, recognized for his contributions to number theory and algebraic geometry.', '2022-04-15', 'Manager At SiP', 'https://www.example.com/maxchen'),
    ('AU0000003','', 'Emily Park', 'British', 'University College London', 'Turing Award in Computer Science', 'Dr. Emily Park is a cybersecurity expert at UCL, renowned for her innovative algorithms and protocols that enhance online security.', '2023-11-28', 'Manager At MySoftware', 'https://www.example.com/emilypark'),
    ('AU0000004',null, 'Alejandro Lopez', 'Mexican', 'California Institute of Technology', 'Breakthrough Prize in Physics', 'Dr. Alejandro Lopez is a theoretical physicist at Caltech, known for his groundbreaking research in quantum mechanics and string theory.', '2024-03-10', 'Manager At Pentamaster', 'https://www.example.com/alejandrolopez'),
    ('AU0000005', '','Sara Khan', 'Indian', 'Harvard University', 'National Medal of Science', 'Dr. Sara Khan is a leading bioinformatician at Harvard, specializing in computational biology and genomics.', '1985-06-25', 'Manager At QuantumMetal', 'https://www.example.com/sarakhan');

INSERT INTO ACCOUNTS (account_id, username, email, saltedpassword, salt)
VALUES 
    ('AC0000001', 'Woeiyi','tanw-pm22@student.tarc.edu.my', 'rsMy/Kq0BnTwMSCf27ARkb2Rg/OaTmbBBIMZ2DuOyio=', 'salt1'),
    ('AC0000002', 'KahXuan','lowkx-pm22@student.tarc.edu.my', '8dGxzvvhQiV0Bm8JU4VLIgNHydvcADLdxUEXUbLOOEg=', 'salt2'),
    ('AC0000003', 'YuBeng','wooyb-pm22@student.tarc.edu.my', 'cAjNy6eveLrPrRTVQ9jJEt6qOUQ33+D+sLI2Ci3gblg=', 'salt3'),
    ('AC0000004', 'Snijders','wangccs-pm22@student.tarc.edu.my', 'otvdT0YCdjwhOHXMs4f3iWa/vQj9CgeU7pT0bx4ismw=', 'salt4'),
    ('AC0000005', 'ZhanLiang','lauzl-pm22@student.tarc.edu.my', 'orl7K6ppIZsbzVrnUcFPPRmgwiq9NPX/F3pKyOiuFI4=', 'salt5');

INSERT INTO SHIPPING_METHOD (SHIPPING_METHOD_ID, SHIPPING_TYPE, DELIVERY_SPEED, DESCRIPTION, COVERAGE_AREA, SHIPPING_RATES, RETURN_POLICY)
VALUES 
    ('SM0000001', 'Standard Shipping', '5-7 Business Days', 'Standard shipping method for domestic delivery', 'Malaysia', 10.00, 'Returns accepted within 30 days, customer pays return shipping.'),
    ('SM0000002', 'Express Shipping', '2-3 Business Days', 'Express shipping method for domestic delivery', 'Malaysia, Indonesia, India', 20.00, 'Returns accepted within 15 days, free return shipping.'),
    ('SM0000003', 'Intl.Shipping', '7-14 Business Days', 'Shipping method for international delivery', 'USA, UK, JPN', 30.00, 'Returns accepted within 60 days, customer pays return shipping.'),
    ('SM0000004', 'Priority Shipping', '1-2 Business Days', 'Priority shipping method for domestic delivery', 'USA, China', 25.00, 'Returns accepted within 30 days, free return shipping.'),
    ('SM0000005', 'Standard Shipping', '5-7 Business Days', 'Standard shipping method for domestic delivery', 'USA, SG', 15.00, 'Returns accepted within 45 days, customer pays return shipping.');

INSERT INTO USERS (USER_ID, ACCOUNT_ID, DISPLAY_NAME, DOB, ADDRESS_ID, USERTYPE, VALIDITY, DATE_JOINED, GENDER, IMG_DATA)
VALUES 
    ('U00000001', 'AC0000001', 'Jackson Wang', '1990-05-15', 'BA0000001', 'Customer', '2024-12-31', '2024-04-15 08:30:00', 'Male',null),
    ('U00000002', 'AC0000002', 'Pentamaster Manager', '1985-09-20', 'BA0000002', 'Admin', '2025-12-31', '2024-04-15 09:15:00', 'Female',null),
    ('U00000003', 'AC0000003', 'Mooiko Starbucks', '1998-03-10', 'BA0000003', 'Customer', '2023-12-31', '2024-04-15 10:00:00', 'Male',NULL),
    ('U00000004', 'AC0000004', 'MySoft bae', '1976-11-28', 'BA0000004', 'Customer', '2024-12-31', '2024-04-15 10:45:00', 'Female',null),
    ('U00000005', 'AC0000005', 'SEN Tenz', '1995-07-02', 'BA0000005', 'Admin', '2025-12-31', '2024-04-15 11:30:00', 'Male',null);

INSERT INTO BANKCARDINFO (CARDINFOID, USER_ID, CARDTYPE, CARD_HOLDER_NAME, CARD_NO, EXPIRY_DATE, CVV)
VALUES 
    ('BC0000001', 'U00000001', 'Master', 'Woeiyi', '1234567890123456', '12/25', '123'),
    ('BC0000002', 'U00000002', 'Visa', 'Kah Xuan', '1234067890103050', '12/20', '124'),    
    ('BC0000003', 'U00000003', 'American', 'Yu Beng', '4567890123456789', '09/23', '789'),
    ('BC0000004', 'U00000004', 'Discover', 'Snijders', '7901234567890129', '03/22', '321'),
    ('BC0000005', 'U00000005', 'Master', 'Zhan Liang', '5678901234567890', '11/21', '654');

INSERT INTO TNG_ACCOUNTS (TNG_ID, USER_ID, PHONENO)
VALUES 
    ('TG000001', 'U00000001', '0123456789'),
    ('TG000002', 'U00000002', '0115678892'),
    ('TG000003', 'U00000003', '0133001902'),
    ('TG000004', 'U00000004', '0170921034'),
    ('TG000005', 'U00000005', '0134920384');

INSERT INTO SOCIAL_MEDIA_LINKS (SOCIALMEDIALINK_ID, AUTHOR_ID, SOCIALMEDIA_NAME, DEST_LINK)
VALUES 
    ('SL0000001', 'AU0000001', 'Twitter', 'https://twitter.com/lenaWatson'),
    ('SL0000002', 'AU0000001', 'Facebook', 'https://www.facebook.com/lenaWatson2'),
    ('SL0000003', 'AU0000002', 'Instagram', 'https://www.instagram.com/ChenMax'),
    ('SL0000004', 'AU0000002', 'LinkedIn', 'https://www.linkedin.com/in/MaxChen'),  
    ('SL0000005', 'AU0000003', 'YouTube', 'https://www.youtube.com/em.Park'),
    ('SL0000006', 'AU0000005', 'YouTube', 'https://www.youtube.com/SaraKhan');

INSERT INTO PRODUCT (PRODUCT_ID, PRODCAT_ID, DESCRIPTION, PROD_NAME, PRICE, RATE_WEIGHTAGE, AVG_RATING, DISCOUNT, IMAGE_PATH, STATUS) 
VALUES 
    ('PR0000001', 'PC0000001', 'Learn Algebra Now !!! fillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfill', 'Algebra Part 1', 1500.0, 5, 4.5, 0.1, 'images/algebraP1.jpg','Active'),
    ('PR0000002', 'PC0000001', 'Algebra Rocks Hoodie !!! fillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfill', 'Algebra Rocks Hoodie', 800.0, 4, 4.2, 0.05, 'images/algebraHoodie.jpg','Active'),
    ('PR0000003', 'PC0000001', 'JavaScript!!! fillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfill', 'JavaScript', 100.0, 3, 4.0, 0.0, 'images/Javascript.jpg','Active'),
    ('PR0000004', 'PC0000001', 'Learn C+ Now !!! fillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfill', 'C++', 50.0, 2, 3.8, 0.0, 'images/c++.jpg','Active'),
    ('PR0000005', 'PC0000001', 'I Love Bio Tshirt !!! fillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfill', 'I Love Bio Tshirt', 30.0, 1, 4.1, 0.0, 'images/bioShirt.jpg','Active'),
    ('PR0000006', 'PC0000002', 'I Love Bio Cup !!! fillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfill', 'I Love Bio Cup', 25.0, 1, 2.0, 2, 'image/bioCup.jpg','Active'),
    ('PR0000007', 'PC0000002', 'Java Hoodie !!! fillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfill', 'Java Hoodie', 99.0, 2, 4.0, 1.5, 'image/javaHoodie.jpg','Active'),
    ('PR0000008', 'PC0000002', 'Buy Physics Cap Now !!! fillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfill', 'Physics Cap', 60.0, 3, 3.0, 0.0, 'image/physicsCap.jpg','Active'),
    ('PR0000009', 'PC0000002', 'Buy Physics Shirts Now fillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfill', 'Physics Shirt', 60.0, 3, 3.0, 0.0, 'image/physicsCap.jpg','Active'),
    ('PR0000010', 'PC0000002', 'Buy Physics laptop cover Now !!! fillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfillfill', 'Physics Laptop', 60.0, 3, 3.0, 0.0, 'image/physicsCap.jpg','Active');

INSERT INTO MERCHANDISE (MERCH_ID, PRODUCT_ID, MERCHCAT_ID,DIMENSION_H_CM, DIMENSION_W_CM, DIMENSION_L_CM, WEIGHT_KG, STOCK_BALANCE)
VALUES 
    ('M00000001', 'PR0000006','MC0000001', 10.5, 5.2, 15.3, 0.75, 100),
    ('M00000002', 'PR0000007','MC0000001', 20.0, 12.5, 30.0, 1.25, 150),
    ('M00000003', 'PR0000008','MC0000002', 15.0, 8.0, 25.0, 1.0, 120),
    ('M00000004', 'PR0000009','MC0000003', 12.0, 6.5, 20.0, 0.9, 80),
    ('M00000005', 'PR0000010','MC0000001', 18.0, 10.0, 28.0, 1.1, 200);

INSERT INTO COURSES (COURSE_ID, PRODUCT_ID, SYLLABUS, COURSECAT_ID, LEARNING_OBJ,LENGTH_HOUR,COURSE_LEVEL,REQUIREMENTS,DETAILED_DESC,DATE_ADDED,VIDEO_DATA)
VALUES 
    ('CR0000001', 'PR0000001', 'IDE, Terminal', 'CC0000001', 'Learn programming fundamentals',9,'Beginner','Diploma in CS\nDiploma in Accounting','Detail Description of IDE,Termianl Learning','2024-04-15 10:30:00',null),
    ('CR0000002', 'PR0000002', 'Text Editor, Web Browser', 'CC0000001', 'Introduction to HTML and CSS',9.5,'Intermediate','Diploma in CS\nDiploma in Accounting','Detail Description of IDE,Termianl Learning','2024-04-15 10:30:00',null),
    ('CR0000003', 'PR0000003', 'Programming IDE, Database Management System', 'CC0000001', 'Database design and SQL queries',10,'Advance','Diploma in CS\nDiploma in Accounting','Detail Description of IDE,Termianl Learning','2024-04-15 10:30:00',null),
    ('CR0000004', 'PR0000004', 'Word Processor, Spreadsheet', 'CC0000004', 'Introduction to Microsoft Office applications',3,'Diploma Holder','Diploma in CS\nDiploma in Accounting','Detail Description of IDE,Termianl Learning','2024-04-15 10:30:00',null),
    ('CR0000005', 'PR0000005', 'Code Editor, Compiler', 'CC0000003', 'Advanced programming concepts',38,'Beginner','Diploma in CS\nDiploma in Accounting','Detail Description of IDE,Termianl Learning','2024-04-15 10:30:00',null);

INSERT INTO COURSE_SUBSCRIPTIONS (SUBSCRIPTIONS_ID, COURSE_ID, USER_ID, SUBSCRIBE_TIME, START_TIME, PROGRESS, FINISH_TIME)
VALUES 
    ('SU0000001', 'CR0000001', 'U00000001', '2024-04-15 10:00:00', '2024-04-15 10:00:00', 'Ongoing', '2024-07-30 10:00:00'),
    ('SU0000002', 'CR0000002', 'U00000002', '2024-04-16 09:30:00', '2024-04-16 09:30:00', 'Completed', '2024-04-20 09:30:00'),
    ('SU0000003', 'CR0000003', 'U00000003', '2024-04-17 14:20:00', '2024-04-17 14:20:00', 'Ongoing', '2024-04-20 14:20:00'),
    ('SU0000004', 'CR0000004', 'U00000004', '2024-04-18 11:45:00', '2024-04-18 11:45:00', 'Not Start', '2024-07-20 11:45:00'),
    ('SU0000005', 'CR0000005', 'U00000005', '2024-04-19 08:00:00', '2024-04-19 08:00:00', 'Ongoing', '2024-08-20 08:00:00');

INSERT INTO COURSE_CERT_TYPE (CERTTYPE_ID, COURSE_ID, DESCRIPTION, ISSUING_AUTHORITY, CERT_LEVEL)
VALUES 
    ('CT0000001', 'CR0000001', 'Completion certificate for Introduction to Java Programming', 'Java Institute', 'Basic'),
    ('CT0000002', 'CR0000002', 'Advanced certificate for Data Structures and Algorithms', 'Tech Academy', 'Advanced'),
    ('CT0000003', 'CR0000003', 'Certification in Web Development', 'WebTech Inc.', 'Intermediate'),
    ('CT0000004', 'CR0000004', 'Cybersecurity Fundamentals Certificate', 'CyberSec Solutions', 'Basic'),
    ('CT0000005', 'CR0000004', 'Cybersecurity Fundamentals Certificate', 'CyberSec Solutions', 'Expert');

INSERT INTO COURSE_CERTIFICATES (CERTIFICATE_ID, CERTTYPE_ID, USER_ID, COURSE_ID, TIME_ISSUED, CERTIFICATE_NUMBER)
VALUES 
    ('C00000001', 'CT0000001', 'U00000001', 'CR0000001', '2024-04-15 10:30:00', 'C123456'),
    ('C00000002', 'CT0000001', 'U00000002', 'CR0000002', '2024-04-16 11:45:00', 'C789012'),
    ('C00000003', 'CT0000003', 'U00000001', 'CR0000003', '2024-04-17 09:15:00', 'C345678'),
    ('C00000004', 'CT0000004', 'U00000004', 'CR0000004', '2024-04-18 14:20:00', 'C901234'),
    ('C00000005', 'CT0000002', 'U00000005', 'CR0000005', '2024-04-19 13:00:00', 'C567890');

INSERT INTO CART_ITEMS (CARTITEM_ID, PRODUCT_ID, USER_ID, QUANTITY, TIME_ADDED)
VALUES 
    ('CI0000001', 'PR0000001', 'U00000001', 2, '2024-04-15 10:30:00'),
    ('CI0000002', 'PR0000002', 'U00000001', 1, '2024-04-16 11:45:00'),
    ('CI0000003', 'PR0000003', 'U00000001', 3, '2024-04-17 09:15:00'),
    ('CI0000004', 'PR0000002', 'U00000002', 1, '2024-04-18 14:20:00'),
    ('CI0000005', 'PR0000005', 'U00000003', 2, '2024-04-19 13:00:00');

INSERT INTO RATINGS (RATING_ID, PRODUCT_ID, USER_ID, SCORE, COMMENT, TIME_RATED, TOTAL_VOTE)
VALUES 
    ('R00000001', 'PR0000001', 'U00000001', 4, 'Great product!', '2024-04-15 10:30:00', 1),
    ('R00000002', 'PR0000001', 'U00000002', 5, 'Excellent service!', '2024-04-16 11:45:00', 4),
    ('R00000003', 'PR0000003', 'U00000001', 3, 'Average quality.', '2024-04-17 09:15:00', 3),
    ('R00000004', 'PR0000004', 'U00000003', 2, 'Poor packaging.', '2024-04-18 14:20:00', 1),
    ('R00000005', 'PR0000005', 'U00000005', 5, 'Outstanding experience!', '2024-04-19 13:00:00', 11);

INSERT INTO SHIPPING (SHIPPING_ID, ADDRESS_ID, SHIPPING_DATE, EXPECTED_DELIVERY_DATE, SHIPPING_COST, TTL_WEIGHT_KG, DIMENSTION_CM_HxWxL, SHIPPING_NOTES)
VALUES 
    ('SH0000001', 'BA0000001', '2024-04-14', '2024-04-21', 25.50, 10.2, '30x20x15', 'Handle with care'),
    ('SH0000002', 'BA0000001', '2024-04-15', '2024-04-22', 30.75, 12.8, '40x25x20', 'Fragile items inside'),
    ('SH0000003', 'BA0000003', '2024-04-16', '2024-04-23', 20.00, 8.5, '25x15x10', 'Standard shipping'),
    ('SH0000004', 'BA0000004', '2024-04-17', '2024-04-24', 35.25, 14.3, '45x30x25', 'Express delivery'),
    ('SH0000005', 'BA0000005', '2024-04-18', '2024-04-25', 18.50, 7.6, '20x15x10', 'Handle with care');

INSERT INTO TRANSACTIONS (TRANSACTION_ID, USER_ID, SUBTOTAL, PROMOTION_ID, PROMO_AMOUNT, TAX, TRANSACTION_TYPE, TRANSACTION_FEE, TOTAL, STATUS, SHIPPING_ID, TIME_ADDED)
VALUES 
    ('TR0000001', 'U00000001', 150.00, 'PO0000001', 10.00, 5.25, 'Online Banking', 2.50, 147.75, 'COMPLETED', 'SH0000001', '2024-04-14 09:30:00'),
    ('TR0000002', 'U00000001', 200.00, NULL, 0.00, 7.00, 'Cash', 3.00, 204.00, 'SHIPPING', 'SH0000001', '2024-04-14 09:30:00'),
    ('TR0000003', 'U00000003', 120.00, 'PO0000002', 15.00, 4.50, 'Credit/Debit', 2.00, 121.50, 'COMPLETED', 'SH0000003', '2024-04-16 11:20:00'),
    ('TR0000004', 'U00000002', 180.00, NULL, 0.00, 6.75, 'Bank Transfer', 2.75, 183.75, 'SHIPPING', 'SH0000004', '2024-04-17 12:00:00'),
    ('TR0000005', 'U00000005', 100.00, 'PO0000003', 20.00, 3.00, 'Credit/Debit', 2.25, 99.75, 'COMPLETED', 'SH0000005', '2024-04-18 13:15:00');

INSERT INTO ORDERS (TRANSACTION_ID, PRODUCT_ID, QUANTITY)
VALUES 
    ('TR0000001', 'PR0000001', 2),
    ('TR0000001', 'PR0000002', 1),
    ('TR0000001', 'PR0000003', 1),
    ('TR0000001', 'PR0000004', 1),
    ('TR0000001', 'PR0000005', 3);

INSERT INTO REFUNDS (REFUND_ID, TRANSACTION_ID, REFUND_TYPE, REASON)
VALUES 
    ('RF0000001', 'TR0000001', 'Full Refund', 'Product damaged during shipping'),
    ('RF0000002', 'TR0000002', 'Partial Refund', 'Customer changed mind'),
    ('RF0000003', 'TR0000003', 'Full Refund', 'Product not as described'),
    ('RF0000004', 'TR0000004', 'Partial Refund', 'Late delivery'),
    ('RF0000005', 'TR0000005', 'Full Refund', 'Product out of stock');

INSERT INTO AUTHOR_CONTRIBUTION (CONTRIBUTION_ID, AUTHOR_ID, COURSE_ID, CONTRIBUTION, DESCRIPTION)
VALUES 
    ('ACN000001', 'AU0000001', 'CR0000001', 0.25, 'Authored several chapters'),
    ('ACN000002', 'AU0000002', 'CR0000001', 0.75, 'Lead instructor for the course'),
    ('ACN000003', 'AU0000003', 'CR0000003', 0.1, 'Provided technical expertise'),
    ('ACN000004', 'AU0000004', 'CR0000004', 0.4, 'Contributed to course design and curriculum'),
    ('ACN000005', 'AU0000005', 'CR0000005', 0.3, 'Developed supplemental materials'),
    ('ACN000006', 'AU0000001', 'CR0000002', 0.12, 'Half Chapter');

INSERT INTO MESSAGES (MESSAGE_ID, USER_ID, CHATROOM_ID, MESSAGE_TYPE, CONTENT, DESTINATION, TIME_CREATED)
VALUES 
    ('MS0000001', 'U00000001', 'CH0000001', 'Text', 'Hello, how are you?', 'All', '2024-04-13 10:30:00'),
    ('MS0000002', 'U00000001', 'CH0000001', 'Image', 'image.jpg', 'All', '2024-04-13 11:45:00'),
    ('MS0000003', 'U00000003', 'CH0000002', 'Text', 'Good morning!', 'All', '2024-04-14 08:00:00'),
    ('MS0000004', 'U00000004', 'CH0000002', 'Text', 'Hi there!', 'All', '2024-04-14 09:15:00'),
    ('MS0000005', 'U00000005', 'CH0000003', 'Text', 'Have a nice day!', 'All', '2024-04-15 12:00:00');

INSERT INTO PAYMENTS (PAYMENT_ID, PAYMENT_METHOD_ID, TRANSACTION_ID, ACCOUNT_NUMBER, PAYMENT_GATEWAY, AMOUNT, STATUS, DATE_ISSUED)
VALUES 
    ('PY0000001', 'PM00002', 'TR0000001', '123456789012345', 'PayPal', 100.0, 'Completed', '2024-04-13 10:30:00'),
    ('PY0000002', 'PM00003', 'TR0000002', '987654321098765', 'Public Bank', 75.0, 'Pending', '2024-04-13 11:45:00'),
    ('PY0000003', 'PM00003', 'TR0000003', '456789012345678', 'Square', 50.0, 'Completed', '2024-04-13 09:15:00'),
    ('PY0000004', 'PM00002', 'TR0000004', '567890123456789', 'PayPal', 120.0, 'Failed', '2024-04-13 12:00:00'),
    ('PY0000005', 'PM00001', 'TR0000005', '345678901234567', 'Stripe', 90.0, 'Completed', '2024-04-13 08:00:00');

INSERT INTO PREFERRED_COURSE (USER_ID, COURSECAT_ID,TIME_ADDED)
VALUES 
    ('U00000001', 'CC0000003',NULL),
    ('U00000002', 'CC0000004',NULL),
    ('U00000003', 'CC0000003',NULL),
    ('U00000004', 'CC0000002',NULL),
    ('U00000005', 'CC0000001',NULL);

INSERT INTO TABLES_RECORD_COUNTER (TB_NBAME,COUNTER)
VALUES
    ('ACCOUNTS',5),
    ('AUTHORS',5),
    ('AUTHOR_CONTRIBUTION',6),
    ('BANKCARDINFO',5),
    ('BILLING_ADDRESS',5),
    ('CART_ITEMS',5),
    ('CHATROOMS',5),
    ('COURSES',5),
    ('COURSE_CATEGORY',5),
    ('COURSE_CERTIFICATES',5),
    ('COURSE_CERT_TYPE',5),
    ('COURSE_SUBSCRIPTIONS',5),
    ('MERCHANDISE',5),
    ('MERCH_CATEGORY',5),
    ('MESSAGES',5),
    ('ORDERS',5),
    ('PAYMENTS',5),
    ('PAYMENT_METHOD',6),
    ('PRODUCT',8),
    ('PRODUCT_CATEGORY',5),
    ('PROMOTIONS',5),
    ('RATINGS',5),
    ('REFUNDS',5),
    ('SHIPPING',5),
    ('SHIPPING_METHOD',5),
    ('SOCIAL_MEDIA_LINKS',6),
    ('TNG_ACCOUNTS',5),
    ('TRANSACTIONS',5),
    ('USERS',5),
    ('USER_CHATROOMS',0),
    ('WISHLIST',5);

INSERT INTO WISHLIST (WISHLIST_ID,USER_ID,PRODUCT_ID,DATE_ADDED)
VALUES
    ('WL0000001','U00000001','PR0000001','2024-04-13 10:30:00'),
    ('WL0000002','U00000001','PR0000002','2024-04-13 10:31:00'),
    ('WL0000003','U00000001','PR0000003','2024-04-13 10:32:00'),
    ('WL0000004','U00000002','PR0000004','2024-04-13 10:33:00'),
    ('WL0000005','U00000002','PR0000005','2024-04-13 10:34:00');
    













