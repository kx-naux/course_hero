import smtplib
import datetime
import sys
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

class AccountInfoChange:
    EMAIL = "lowkx-pm22@student.tarc.edu.my"  
    PASSWORD = "rqbtfnqhzyitjamx"       

    def __init__(self, type: str = None, time: str = None) -> None:
        self.__type = type
        self.__time_sent = datetime.datetime.strptime(time, '%Y-%m-%d %H:%M:%S') if time else ""

    def get_time_sent(self) -> str:
        return self.__time_sent.strftime('%Y-%m-%d %H:%M:%S')

    def send_email(self, recipient_email: str) -> None:
        msg = MIMEMultipart()
        msg['From'] = self.EMAIL
        msg['To'] = recipient_email
        msg['Subject'] = '(No Reply) Course Hero - Your Account Information Has Been Changed'
        body = f"""<div style="font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2">
    <div style="margin:50px auto;width:70%;padding:20px 0">
    <div style="border-bottom:1px solid #eee">
    <a href="" style="font-size:1.4em;color: #273b91;text-decoration:none;font-weight:600">Course Hero</a>
</div>
<p style="font-size:1.1em">Hi,</p>
<p>This is to inform you that changes have been made to your account information on our platform.</p>

<strong>Your {self.__type.lower()} is changed.</strong>
<br>

<p>If you did not initiate this change or if you believe your account security may have been compromised, please contact our support team immediately.</p>

<p>For your security, we recommend taking the following actions:</p>
<ul>   
<li>Reset your password immediately if you suspect unauthorized access to your account.</li>
<li>Review your account activity for any unusual or unauthorized transactions.</li>
</ul>
<p>Thank you for your attention to this matter. We are committed to ensuring the security and privacy of your account.</p>

<p style="font-size:0.9em;">Regards,<br />Course Hero</p>
<hr style="border:none;border-top:1px solid #eee" />
<div style="float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300">
    <p>{str(datetime.datetime.now().date())}</p>
    <p>{str(datetime.datetime.now().time())[:8]}</p>
</div>
    </div>
</div>"""
        msg.attach(MIMEText(body, 'html'))

        try:
            server = smtplib.SMTP('smtp.gmail.com', 587)
            server.starttls()
            server.login(self.EMAIL, self.PASSWORD)
            server.sendmail(self.EMAIL, recipient_email, msg.as_string())
            server.quit()
            self.__time_sent = datetime.datetime.now()
            print("Email sent successfully.")
        except Exception as e:
            print("Failed to send email:", e)

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python script.py <type> <receiver_email>")
        sys.exit(1)

    type = sys.argv[1]
    receiver_email = sys.argv[2]

    acc = AccountInfoChange(type)
    acc.send_email(receiver_email)