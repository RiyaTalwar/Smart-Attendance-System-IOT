#include <Keypad.h>
#include <Adafruit_GFX.h>
#include <Wire.h>
#include <Adafruit_SSD1306.h>
#include <Adafruit_Fingerprint.h>
#include <SoftwareSerial.h>
SoftwareSerial mySerial(12, 13);
SoftwareSerial sw(2,3);
Adafruit_Fingerprint finger = Adafruit_Fingerprint(&mySerial);

#define OLED_RESET 4
Adafruit_SSD1306 display(OLED_RESET);

int c=0,c2=0;

const byte numRows= 4; //number of rows on the keypad
const byte numCols= 4; //number of columns on the keypad

/*keymap defines the key pressed according to the row and columns just as appears on the keypad*/
char keymap[numRows][numCols]={{'1','2','3','A'},{'4','5','6','B'},{'7','8','9','C'},{'*','0','#','D'}};

//Code that shows the the keypad connections to the arduino terminals
byte rowPins[numRows] = {9,8,7,6}; //Rows 0 to 3
byte colPins[numCols]= {5,4,10,11}; //Columns 0 to 3 3 changed 10, 2 to 11

String courseId="";
char keypressed;

//initializes an instance of the Keypad class
Keypad myKeypad= Keypad(makeKeymap(keymap), rowPins, colPins, numRows, numCols);

void setup()
{
  Serial.begin(115200);
  while (!Serial);  // For Yun/Leo/Micro/Zero/...
  delay(100);
  Serial.println("\n\nAdafruit finger detect test");

  // set the data rate for the sensor serial port
  finger.begin(57600);
  
  if (finger.verifyPassword()) {
    Serial.println("Found fingerprint sensor!");
  } else {
    Serial.println("Did not find fingerprint sensor :(");
    while (1) { delay(1); }
  }

  finger.getTemplateCount();
  Serial.print("Sensor contains "); Serial.print(finger.templateCount); Serial.println(" templates");
  Serial.println("Waiting for valid finger...");
  
   display.begin(SSD1306_SWITCHCAPVCC, 0x3C);// initialize with the I2C addr 0x3C (for the 128x32)(initializing the display)
  display.clearDisplay();
  display.setTextColor(WHITE);
  display.setTextSize(1);
  display.setCursor(0,0);
  
}

//If key is pressed, this key is stored in keypressed variable
//If key is not equal to NO_KEY, then this key is printed out
//if count=17, then count is reset back to 0 (this means no key is pressed during the whole keypad scan process
void loop()
{
// display.print("Course ID:");
 //display.setCursor(0,10);
 
  keypressed=myKeypad.getKey();
  if(keypressed=='A')
  {
    display.clearDisplay();
    display.setCursor(0,0);
    display.print("Course ID:");
    display.setCursor(0,10);
    c2=1;
  }
   else if(keypressed=='#'&&c==0&&c2==1)
   {
    display.clearDisplay();
    display.setCursor(0,0);
    display.print("Start attendance");
    c=1;
   // while(1)
    //{
   // getFingerprintIDez();
   // }
   // delay(50);
    
   }
   else if(keypressed=='D')
   {
    display.clearDisplay();
    display.setCursor(0,0);
    display.print("Attendance over");
    c=0;
    mySerial.end();
    sw.begin(115200);
    String postdata="Dcourse_id=" + courseId ;
    Serial.println(postdata);
    sw.print(postdata);
    sw.println(); 
    sw.end();
    finger.begin(57600);
    courseId="";
   }
   else if(c==0&&c2==1)
   {
       keypadData(keypressed);
   }
  if(c==1)
  {
   // Serial.println(courseId);
   // Serial.println("startttttt");
    //display.clearDisplay();
    display.setCursor(0,10);
     int id=getFingerprintIDez();
     if(id!=-1)
     {
      mySerial.end();
       sw.begin(115200);
      String postdata="finger_id=" + (String)id + "&course_id=" + courseId +"&attendance="+ '1';
      Serial.println(postdata);
       sw.print(postdata);
      sw.println(); 
      sw.end();
      finger.begin(57600);  
     }
     //delay(50);
  }
  display.display();
}

void keypadData(char keypressed)
{
  if(keypressed)
    display.print(keypressed);
   if(keypressed!=NO_KEY)
   {
    if(keypressed=='0'||keypressed=='1'||keypressed=='2'||keypressed=='3'||keypressed=='4'||keypressed=='5'||keypressed=='6'||keypressed=='7'||keypressed=='8'||keypressed=='9')
      courseId+=keypressed;
   }

}


uint8_t getFingerprintID() {
  Serial.println("hi");
  uint8_t p = finger.getImage();
  switch (p) {
    case FINGERPRINT_OK:
      Serial.println("Image taken");
      break;
    case FINGERPRINT_NOFINGER:
      Serial.println("No finger detected");
      return p;
    case FINGERPRINT_PACKETRECIEVEERR:
      Serial.println("Communication error");
      return p;
    case FINGERPRINT_IMAGEFAIL:
      Serial.println("Imaging error");
      return p;
    default:
      Serial.println("Unknown error");
      return p;
  }

  // OK success!

  p = finger.image2Tz();
  switch (p) {
    case FINGERPRINT_OK:
      Serial.println("Image converted");
      break;
    case FINGERPRINT_IMAGEMESS:
      Serial.println("Image too messy");
      return p;
    case FINGERPRINT_PACKETRECIEVEERR:
      Serial.println("Communication error");
      return p;
    case FINGERPRINT_FEATUREFAIL:
      Serial.println("Could not find fingerprint features");
      return p;
    case FINGERPRINT_INVALIDIMAGE:
      Serial.println("Could not find fingerprint features");
      return p;
    default:
      Serial.println("Unknown error");
      return p;
  }
  
  // OK converted!
  p = finger.fingerFastSearch();
  if (p == FINGERPRINT_OK) {
    Serial.println("Found a print match!");
  } else if (p == FINGERPRINT_PACKETRECIEVEERR) {
    Serial.println("Communication error");
    return p;
  } else if (p == FINGERPRINT_NOTFOUND) {
    Serial.println("Did not find a match");
    return p;
  } else {
    Serial.println("Unknown error");
    return p;
  }   
  
  // found a match!
  Serial.print("Found ID #"); Serial.print(finger.fingerID); 
  Serial.print(" with confidence of "); Serial.println(finger.confidence); 
  //delay(2000);
  return finger.fingerID;
}

// returns -1 if failed, otherwise returns ID #
int getFingerprintIDez() {
  //Serial.println("ok");
  uint8_t p = finger.getImage();
 
  if (p != FINGERPRINT_OK)  return -1;

  p = finger.image2Tz();
  if (p != FINGERPRINT_OK)  return -1;

  p = finger.fingerFastSearch();
  if (p != FINGERPRINT_OK)  return -1;
  
  // found a match!
    display.clearDisplay();
  display.setCursor(0,0);
  Serial.print("Found ID #"); Serial.print(finger.fingerID); 
  Serial.print(" with confidence of "); Serial.println(finger.confidence);
  display.setCursor(0,10);
  display.print(finger.fingerID); 
  display.print(" marked Present"); 
   //delay(5000);
  return finger.fingerID; 
}

