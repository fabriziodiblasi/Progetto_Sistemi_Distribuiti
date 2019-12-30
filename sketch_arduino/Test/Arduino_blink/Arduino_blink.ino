// www.ProgettiArduino.com
// Arduino blink facciamo lampeggiare un led
#include <Servo.h>

#define LED_ROSSO_SX 1              
#define LED_VERDE_SX 2

#define LED_ROSSO_DX 4            
#define LED_VERDE_DX 5

#define PIN_SERVO 9
#define PIN_SWITCH_SERVO 12


Servo myservo;
int pos, flag_servo, switch_servo;


void apri_valvola(){

 if(flag_servo == 1 ){
  flag_servo = 0;
  for(pos=pos; pos>=0; pos--){
    myservo.write(pos);
    delay(15);
  }
 }
  
 
}

void chiudi_valvola(){
  
 if(flag_servo == 0 ){
  flag_servo = 1;
  for(pos=pos; pos < 90; pos++){
    myservo.write(pos);
    delay(15);
  }
 }

}


void setup() {
  pinMode(LED_ROSSO_SX, OUTPUT);
  pinMode(LED_ROSSO_DX, OUTPUT);
  pinMode(LED_VERDE_SX, OUTPUT);
  pinMode(LED_VERDE_DX, OUTPUT);

  myservo.attach(PIN_SERVO);
  myservo.write(0);
  pos = 0;
  flag_servo = 0;
}

void loop() {

  digitalWrite(LED_VERDE_SX, HIGH);   // accende il LED VERDE A SX
  delay(1000);                        // aspetta un secondo
  digitalWrite(LED_VERDE_SX, LOW);    // spegne il LED VERDE A SX
  delay(1000);                        // aspetta un secondo
  digitalWrite(LED_ROSSO_SX, HIGH);   // accende il LED ROSSO A SX
  delay(1000);                        // aspetta un secondo
  digitalWrite(LED_ROSSO_SX, LOW);    // spegne il LED ROSSO A SX
  delay(1000);                        // aspetta un secondo



  
  digitalWrite(LED_VERDE_DX, HIGH);   // accende il LED VERDE A DX
  delay(1000);                        // aspetta un secondo
  digitalWrite(LED_VERDE_DX, LOW);    // spegne il LED VERDE A DX
  delay(1000);                        // aspetta un secondo
  digitalWrite(LED_ROSSO_DX, HIGH);   // accende il LED ROSSO A DX
  delay(1000);                        // aspetta un secondo
  digitalWrite(LED_ROSSO_DX, LOW);    // spegne il LED ROSSO A DX
  delay(1000);                        // aspetta un secondo


  chiudi_valvola();
  if(digitalRead(PIN_SWITCH_SERVO) == HIGH){
    Serial.println("VALVOLA CHIUSA");
  }
  delay(1000);                        // aspetta un secondo

  apri_valvola();
  if(digitalRead(PIN_SWITCH_SERVO) == LOW){
    Serial.println("VALVOLA APERTA");
  }
  delay(1000);                        // aspetta un secondo


  
}
