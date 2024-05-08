import smtplib
import datetime
import sys
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

class OTP:
    EMAIL = "lowkx-pm22@student.tarc.edu.my"  
    PASSWORD = "rqbtfnqhzyitjamx"       

    def __init__(self, otp: str = None, time: str = None) -> None:
        self.__otp = otp
        self.__time_sent = datetime.datetime.strptime(time, '%Y-%m-%d %H:%M:%S') if time else ""

    def get_time_sent(self) -> str:
        return self.__time_sent.strftime('%Y-%m-%d %H:%M:%S')

    def send_otp_email(self, recipient_email: str) -> None:
        msg = MIMEMultipart()
        msg['From'] = self.EMAIL
        msg['To'] = recipient_email
        msg['Subject'] = '(No Reply) Course Hero - OTP Verification'
        body = f"""<div style="font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2">
            <div style="margin:50px auto;width:70%;padding:20px 0">
            <div style="border-bottom:1px solid #eee">
            <a href="" style="font-size:1.4em;color: #273b91;text-decoration:none;font-weight:600">Course Hero</a>
        </div>
        <p style="font-size:1.1em">Hi,</p>
        <p>Use the following OTP to complete your related Procedures. OTP is valid
            for 5 minutes</p>
        <h2
            style="background: #273b91;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;">
            {self.__otp}</h2>
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
            print("OTP email sent successfully.")
        except Exception as e:
            print("Failed to send OTP email:", e)

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python script.py <otp_code> <receiver_email>")
        sys.exit(1)

    otp_code = sys.argv[1]
    receiver_email = sys.argv[2]

    otp = OTP(otp_code)
    otp.send_otp_email(receiver_email)