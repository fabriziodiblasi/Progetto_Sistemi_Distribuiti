// www.ProgettiArduino.com
// Arduino blink facciamo lampeggiare un led
#include <Servo.h>

#define LED_ROSSO_SX 1              
#define LED_VERDE_SX 2

#define LED_ROSSO_DX 4            
#define LED_VERDE_DX 5

#define PIN_SERVO 9

Servo myservo;
int pos = 0;



void apri_valvola(){
  
  myservo.write(0);
 
}

void chiudi_valvola(){
  
  myservo.write(90);

}


void setup() {
  pinMode(LED_ROSSO_SX, OUTPUT);
  pinMode(LED_ROSSO_DX, OUTPUT);
  pinMode(LED_VERDE_SX, OUTPUT);
  pinMode(LED_VERDE_DX, OUTPUT);

  myservo.attach(PIN_SERVO);
  myservo.write(0);

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
  delay(1000);                        // aspetta un secondo

  apri_valvola();
  delay(1000);                        // aspetta un secondo


  
}
