
#include <ESP8266WiFi.h>
#include <WiFiClient.h> 
#include<string.h>
#include <ESP8266HTTPClient.h>

// Update these with values suitable for your network.
const char* ssid = "Riya";
const char* password = "shreyariya";

int c=0;

WiFiClient client;   
const char *host = "https://riyatalwar1697.000webhostapp.com";   // website or IP address of server

//PubSubClient client(wifiClient);

void setup_wifi() {
    delay(10);
    // We start by connecting to a WiFi network
    Serial.println();
    Serial.print("Connecting to ");
    Serial.println(ssid);
    WiFi.begin(ssid, password);
    while (WiFi.status() != WL_CONNECTED) {
      delay(500);
      Serial.print(".");
    }
    randomSeed(micros());
    Serial.println("");
    Serial.println("WiFi connected");
    Serial.println("IP address: ");
    Serial.println(WiFi.localIP());
}


void setup() {
  Serial.begin(115200);
  Serial.setTimeout(500);// Set time out for 
  setup_wifi();
  //client.setServer(mqtt_server, mqtt_port);
  //reconnect();
}

void loop() {
   //client.loop();
   HTTPClient http;
   char bfr[101];
   memset(bfr,0, 101);
   Serial.readBytesUntil( '\n',bfr,100);
   
    if(bfr[0]=='D')
     {
         http.begin("http://riyatalwar1697.000webhostapp.com/testmail.php");              //Specify request destination
         http.addHeader("Content-Type", "application/x-www-form-urlencoded");
         String s="";
         for(int i=1;i<sizeof(bfr);i++)
         {
            s+=bfr[i];
         }
         Serial.println(s);
         int httpCode = http.POST(s);   //Send the request
         String payload = http.getString();
         Serial.println(httpCode);   //Print HTTP return code
         Serial.println(payload); 
         http.end();  //Close connection
     }
     else
     {
        http.begin("http://riyatalwar1697.000webhostapp.com/postdemo.php");              //Specify request destination
        http.addHeader("Content-Type", "application/x-www-form-urlencoded");
        int httpCode = http.POST(bfr);   //Send the request
        String payload = http.getString();
        Serial.println(httpCode);   //Print HTTP return code
        Serial.println(payload); 
        http.end();  //Close 
        Serial.println(bfr);
     }
 }
