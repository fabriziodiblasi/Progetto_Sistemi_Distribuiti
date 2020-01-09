// www.ProgettiArduino.com
// Arduino blink facciamo lampeggiare un led
#include <Servo.h>

#define PIN_SWITCH_SERVO 12

Servo myservo;

void setup() {
  Serial.begin(9600);
  myservo.attach(9);
  myservo.write(0);

}

void loop() {

  if(digitalRead(PIN_SWITCH_SERVO) == HIGH){
    Serial.println("VALVOLA CHIUSA");
  }
  delay(1000);

  if(digitalRead(PIN_SWITCH_SERVO) == LOW){
    Serial.println("VALVOLA APERTA");
  }
  delay(1000);
  
}
